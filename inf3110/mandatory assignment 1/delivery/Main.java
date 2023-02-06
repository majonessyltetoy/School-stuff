import java.util.ArrayList;

class Main {
	public static void main(String[] args) {
		Program p;
		Grid g;
		Robot r;
		ArrayList<VarDecl> vars;
		ArrayList<Statement> stmt;
		ArrayList<Statement> whileStmt;



		System.out.println("\nTesting Code 1: Simple Example");
		vars = new ArrayList<VarDecl>();
		
		stmt = new ArrayList<Statement>();
		stmt.add(new Move(new Direction("west"), new Number(15)));
		stmt.add(new Move(new Direction("south"), new Number(15)));
		stmt.add(new Move(new Direction("east"), new PlussExp(new Number(2), new Number(4))));
		stmt.add(new Move(new Direction("north"), new PlussExp(new Number(10), new Number(27))));
		stmt.add(new Stop());
		
		g = new Grid(new Number(64), new Number(64));
		r = new Robot(vars, new Start(new Number(23), new Number(30)), stmt);
		p = new Program(g, r);
		p.interpret();
		
		
		
		System.out.println("\nTesting Code 2: Example with variables");
		// Testing Code 2
		vars = new ArrayList<VarDecl>();
		vars.add(new VarDecl(new Identifier("i"), new Number(5)));
		vars.add(new VarDecl(new Identifier("j"), new Number(3)));
		
		stmt = new ArrayList<Statement>();
		stmt.add(new Move(new Direction("north"), new MultExp(new Number(3), new Identifier("i"))));
		stmt.add(new Move(new Direction("east"), new Number(15)));
		stmt.add(new Move(new Direction("south"), new Number(4)));
		stmt.add(new Move(new Direction("west"), new PlussExp(new PlussExp(new MultExp(new Number(2), new Identifier("i")),
															   			   new MultExp(new Number(3), new Identifier("j"))),
															 (new Number(5)))));
		stmt.add(new Stop());
		
		g = new Grid(new Number(64), new Number(64));
		r = new Robot(vars, new Start(new Number(23), new Number(6)), stmt);
		p = new Program(g, r);
		p.interpret();
		
		
		
		System.out.println("\nTesting Code 3: Example with while loop and assignment");
		// Testing Code 3
		vars = new ArrayList<VarDecl>();
		vars.add(new VarDecl(new Identifier("i"), new Number(5)));
		vars.add(new VarDecl(new Identifier("j"), new Number(3)));
		
		stmt = new ArrayList<Statement>();
		stmt.add(new Move(new Direction("north"), new MultExp(new Number(3), new Identifier("i"))));
		stmt.add(new Move(new Direction("west"), new Number(15)));
		stmt.add(new Move(new Direction("east"), new Number(4)));
		whileStmt = new ArrayList<Statement>();
		whileStmt.add(new Move(new Direction("south"), new Identifier("j")));
		whileStmt.add(new Assignment(new Identifier("j"), new MinExp(new Identifier("j"), new Number(1))));
		stmt.add(new While(new GreatExp(new Identifier("j"), new Number(0)), whileStmt));
		stmt.add(new Stop());
		
		g = new Grid(new Number(64), new Number(64));
		r = new Robot(vars, new Start(new Number(24), new Number(6)), stmt);
		p = new Program(g, r);
		p.interpret();
		
		
		
		System.out.println("\nTesting Code 4: Example with movement over the edge");
		vars = new ArrayList<VarDecl>();
		vars.add(new VarDecl(new Identifier("j"), new Number(3)));
		
		stmt = new ArrayList<Statement>();
		whileStmt = new ArrayList<Statement>();
		whileStmt.add(new Move(new Direction("north"), new Identifier("j")));
		stmt.add(new While(new GreatExp(new Identifier("j"), new Number(0)), whileStmt));
		stmt.add(new Stop());
		
		g = new Grid(new Number(64), new Number(64));
		r = new Robot(vars, new Start(new Number(1), new Number(1)), stmt);
		p = new Program(g, r);
		p.interpret();
	}
}



