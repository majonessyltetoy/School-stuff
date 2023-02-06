import java.util.List;

public interface Robol {
	void interpret();
}

class Program implements Robol {
	Grid grid;
	Robot robot;
	public Program(Grid grid, Robot robot) {
		this.grid = grid;
		this.robot = robot;
		robot.setGrid(grid);
	}
	public void interpret() {
		robot.interpret();
	}
}

class Grid {
	// grid size
	Number xSize;
	Number ySize;
	public Grid(Number left, Number right) {
		xSize = left;
		ySize = right;
	}
	public void interpret() {
	}
	public boolean OnBoard(Number x, Number y) {
		return ((xSize.toInt() >= x.toInt() && ySize.toInt() >= y.toInt()) &&
		(x.toInt() >= 0 && y.toInt() >= 0));
	}
}

class Robot implements Robol {
	Grid g;
	// position on the grid
	Number x;
	Number y;
	
	List<VarDecl> vd;
	Start s;
	List<Statement> st;
	public Robot(List<VarDecl> vd, Start s, List<Statement> st) {
		this.vd = vd;
		this.s = s;
		this.st = st;
	}
	public void setGrid(Grid g) {
		this.g = g;
	}
	public void interpret() {
		s.interpret(this);
		for (Statement stmt : st) {
			stmt.interpret(this);
		}
	}

	public List<VarDecl> getVar() {
		return vd;
	}
	
	public void setVarDecl(Identifier id, Expression exp) {
		int found = -1;
		for (int i=0; i<vd.size(); i++) {
			if (id.equals(vd.get(i).getId())) {
				found = i;
			}
		}
		if (found == -1) {
			vd.add(new VarDecl(id, exp));
		}
		else {
			vd.get(found).setVar(exp);
		}
	}
	
	public void moveTo(Number x, Number y) {
		if (g.OnBoard(x, y)) {
			this.x = x;
			this.y = y;
		}
		else {
			System.out.println("Error! Bounds of grid has been overstepped.");
			System.exit(1);
		}
	}
	public Number getX() {
		return x;
	}
	public Number getY() {
		return y;
	}
	public void printPos() {
		System.out.println("(" + x.toInt() + ", " + y.toInt() + ")");
	}
}

class VarDecl {
	Identifier id;
	Expression var;
	public VarDecl(Identifier i, Expression x) {
		id = i;
		var = x;
	}
	public void setVar(Expression var) {
		this.var = var;
	}
	public Identifier getId() {
		return id;
	}
	public Expression interpret() {
		return var;
	}
}

interface Statement {
	public abstract void interpret(Robot r);
}

class Start implements Statement {
	Expression left;
	Expression right;
	public Start(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	public void interpret(Robot r) {
		Expression tmpLeft = left.interpret(r);
		Expression tmpRight = right.interpret(r);
		while (!(tmpLeft instanceof Number) || !(tmpRight instanceof Number)) {
			if (!(tmpLeft instanceof Number)) {
				tmpLeft = tmpLeft.interpret(r);
			}
			if (!(tmpRight instanceof Number)) {
				tmpRight = tmpRight.interpret(r);
			}
		}
		r.moveTo((Number)tmpLeft, (Number)tmpRight);
	}
}

class Stop implements Statement {
	public void interpret(Robot r) {
		r.printPos();
		return;
	}
}

class Move implements Statement {
	Direction dir;
	Expression exp;
	public Move(Direction dir, Expression exp) {
		this.dir = dir;
		this.exp = exp;
	}
	public void interpret(Robot r) {
		if (dir.interpret().equals("north")) {
			r.moveTo(r.getX(), (Number)new PlussExp(r.getY(), exp).interpret(r));
		}
		if (dir.interpret().equals("east")) {
			r.moveTo((Number)new PlussExp(r.getX(), exp).interpret(r), r.getY());
		}
		if (dir.interpret().equals("south")) {
			r.moveTo(r.getX(), (Number)new MinExp(r.getY(), exp).interpret(r));
		}
		if (dir.interpret().equals("west")) {
			r.moveTo((Number) new MinExp(r.getX(), exp).interpret(r), r.getY());
		}
	}
}

class Direction {
	String dir;
	public Direction(String dir) {
		this.dir = dir;
	}
	public String interpret() {
		return dir;
	}
}

class Assignment implements Statement {
	Identifier id;
	Expression exp;
	public Assignment(Identifier i, Expression x) {
		id = i;
		exp = x;
	}
	public void interpret(Robot r) {
		r.setVarDecl(id, exp.interpret(r));
	}
}
class While implements Statement {
	BoolExp condition;
	List<Statement> statements;
	public While(BoolExp bool, List<Statement> st) {
		condition = bool;
		statements = st;
	}
	public void interpret(Robot r) {
		while ((((Number)(condition.interpret(r))).toInt() == 1)) {
			for (Statement stmt : statements) {
				stmt.interpret(r);
			}
		}
	}
}
interface Expression {
	public abstract Expression interpret(Robot r);
}

class Identifier implements Expression {
	String stringId;
	public Identifier(String stringId) {
		this.stringId = stringId;
	}
	public String getString() {
		return stringId;
	}
	public boolean equals(Identifier other) {
		if (stringId.equals(other.getString())) {
			return true;
		}
		else {
			return false;
		}
	}
	public Expression interpret(Robot r) {
		List<VarDecl> vd = r.getVar();
		for (VarDecl v : vd) {
			if (stringId.equals(v.getId().getString())) {
				return v.interpret();
			}
		}
		System.out.println("Error! Variable is undefined.");
		System.exit(1);
		return new Number(0);
	}
}

class Number implements Expression {
	int x;
	public Number(int x) {
		this.x = x;
	}
	public Expression interpret(Robot r) {
		return new Number(x);
	}
	public int toInt() {
		return x;
	}
}

abstract class BoolExp implements Expression {
	protected Expression left;
	protected Expression right;
}

class GreatExp extends BoolExp {
	public GreatExp(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	public Expression interpret(Robot r) {
		if (!(left instanceof Number)) {
			return new GreatExp(left.interpret(r), right).interpret(r);
		}
		if (!(right instanceof Number)) {
			return new GreatExp(left, right.interpret(r)).interpret(r);
		}
		if (((Number)left).toInt() > ((Number)right).toInt()) {
			return new Number(1);
		}
		else {
			return new Number(0);
		}
	}
}
class LessExp extends BoolExp {
	public LessExp(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	public Expression interpret(Robot r) {
		if (((Number)(new GreatExp(left, right).interpret(r))).toInt() == 1) {
			return new Number(0);
		}
		else {
			return new Number(1);
		}
	}
}
class EqExp extends BoolExp {
	public EqExp(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	public Expression interpret(Robot r) {
		if (!(left instanceof Number)) {
			return new EqExp(left.interpret(r), right).interpret(r);
		}
		if (!(right instanceof Number)) {
			return new EqExp(left, right.interpret(r)).interpret(r);
		}
		if (((Number)left).toInt() == ((Number)right).toInt()) {
			return new Number(1);
		}
		else {
			return new Number(0);
		}
	}
}

abstract class ArithExp implements Expression {
	protected Expression left;
	protected Expression right;
}

class PlussExp extends ArithExp {
	public PlussExp(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	public Expression interpret(Robot r) {
		if (!(left instanceof Number)) {
			return new PlussExp(left.interpret(r), right).interpret(r);
		}
		if (!(right instanceof Number)) {
			return new PlussExp(left, right.interpret(r)).interpret(r);
		}
		return new Number(((Number)left).toInt() + ((Number)right).toInt());
	}
}
class MinExp extends ArithExp {
	public MinExp(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	public Expression interpret(Robot r) {
		if (!(left instanceof Number)) {
			return new MinExp(left.interpret(r), right).interpret(r);
		}
		if (!(right instanceof Number)) {
			return new MinExp(left, right.interpret(r)).interpret(r);
		}
		return new Number(((Number)left).toInt() - ((Number)right).toInt());
	}
}
class MultExp extends ArithExp {
	public MultExp(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	public Expression interpret(Robot r) {
		if (!(left instanceof Number)) {
			return new MultExp(left.interpret(r), right).interpret(r);
		}
		if (!(right instanceof Number)) {
			return new MultExp(left, right.interpret(r)).interpret(r);
		}
		return new Number(((Number)left).toInt() * ((Number)right).toInt());
	}
}



