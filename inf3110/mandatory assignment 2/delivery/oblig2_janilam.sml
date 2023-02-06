exception OutOfBounds;

datatype exp =
	Identifier of string
|	Number of int
|	Exp of exp
|	ArithExp of arithexp
|	BoolExp of boolexp
and arithexp =
	Plus of exp * exp
|	Minus of exp * exp
|	Multi of exp * exp
and boolexp =
	BiggerThan of exp * exp
|	LessThan of exp * exp
|	EqualTo of exp * exp;

datatype direction = Direction of string;
datatype grid = Size of int * int;
datatype vardecl = VarDecl of string * exp;
datatype start = Start of exp * exp;

datatype stmt =
	Stop
|	Move of direction * exp
|	Assignment of string * exp
|	While of boolexp * stmt list;

datatype robot = Robot of vardecl list * start * stmt list;
datatype program = Program of grid * robot;

fun evalExp(Number(x), decls) = x
|	evalExp(Exp x, decls) = evalExp(x, decls)
|	evalExp(Identifier(i), decls) =
	let fun match(i1, VarDecl(i2, e)::decl) =
		if i1 = i2 then evalExp(e, decls) else match(i1,decl)
	in match(i, decls) end
|	evalExp(ArithExp(Plus(e1, e2)), decls) =
	evalExp(e1, decls) + evalExp(e2, decls)
|	evalExp(ArithExp(Minus(e1, e2)), decls) =
	evalExp(e1, decls) - evalExp(e2, decls)
|	evalExp(ArithExp(Multi(e1, e2)), decls) =
	evalExp(e1, decls) * evalExp(e2, decls)
|	evalExp(BoolExp(BiggerThan(e1, e2)), decls) =
	if evalExp(e1, decls) > evalExp(e2, decls) then 1 else 0
|	evalExp(BoolExp(LessThan(e1, e2)), decls) =
	if evalExp(e1, decls) < evalExp(e2, decls) then 1 else 0
|	evalExp(BoolExp(EqualTo(e1, e2)), decls) =
	if evalExp(e1, decls) = evalExp(e2, decls) then 1 else 0

fun isOutSide(x, y, xpos, ypos, decls) =
	if evalExp(BoolExp(BiggerThan(xpos, Number(x))), decls) = 1 then true
	else if evalExp(BoolExp(BiggerThan(ypos, Number(y))), decls) = 1 then true
	else if evalExp(BoolExp(LessThan(xpos, Number(0))), decls) = 1 then true
	else if evalExp(BoolExp(LessThan(ypos, Number(0))), decls) = 1 then true
	else false;

fun interpret(Program(Size(x,y),Robot(decls,Start(xpos,ypos), Stop::stmtlst))) =
	if isOutSide(x,y,xpos,ypos,decls)
	then raise OutOfBounds
	else print("("^Int.toString(evalExp(xpos, decls))^","^Int.toString(evalExp(ypos, decls))^")\n")
|	interpret(Program(Size(x, y),
	Robot(decls, Start(xpos, ypos),
	Assignment(s, exp)::stmtlst))) : unit = if isOutSide(x,y,xpos,ypos,decls)
	then raise OutOfBounds
	else let
		fun replace(s,exp,nil) = nil
		|	replace(s,exp,VarDecl(i,e)::lst) =
			if s = i
		    then VarDecl(i,Number(evalExp(exp, decls)))::replace(s,exp,lst)
		    else VarDecl(i,e)::replace(s,exp,lst)
		in
			interpret(Program(Size(x,y),Robot(replace(s,exp,decls),Start(xpos,ypos),stmtlst)))
		end
|	interpret(Program(Size(x,y),Robot(decls,Start(xpos,ypos),While(b,stmts)::stmtlst))) =
	if isOutSide(x,y,xpos,ypos,decls)
	then raise OutOfBounds
	else if evalExp(BoolExp(b), decls) = 1
		then interpret(Program(Size(x,y),Robot(decls,Start(xpos,ypos),stmts@While(b,stmts)::stmtlst)))
		else interpret(Program(Size(x,y),Robot(decls,Start(xpos,ypos),stmtlst)))
|	interpret(Program(Size(x,y),Robot(decls,Start(xpos,ypos),Move(Direction(d),exp)::stmtlst))) =
	if isOutSide(x,y,xpos,ypos,decls)
	then raise OutOfBounds
	else case d of
			"north" => interpret(Program(Size(x,y),Robot(decls,Start(xpos,Number(evalExp(ArithExp(Plus(ypos,exp)),decls))),stmtlst)))
		|	"east"  => interpret(Program(Size(x,y),Robot(decls,Start(Number(evalExp(ArithExp(Plus(xpos,exp)),decls)),ypos),stmtlst)))
		|	"south" => interpret(Program(Size(x,y),Robot(decls,Start(xpos,Number(evalExp(ArithExp(Minus(ypos,exp)),decls))),stmtlst)))
		|	"west"  => interpret(Program(Size(x,y),Robot(decls,Start(Number(evalExp(ArithExp(Minus(xpos,exp)),decls)),ypos),stmtlst)));
	

(* TEST CODE 1 *)
let
	val stmtlst = [Move(Direction("west"), Number(15)),
	               Move(Direction("south"), Number(15)),
	               Move(Direction("east"), ArithExp(Plus(Number(2), Number(4)))), 
	               Move(Direction("north"), ArithExp(Plus(Number(10), Number(27)))),
	               Stop]
	val vardecl = []
in
	(print("\nTesting Code 1:\n");
	interpret(Program(Size(64,64), Robot(vardecl, Start(Number(23), Number(30)), stmtlst)))
	handle
	OutOfBounds => print("Robot fell of the grid!\n"))
end;

(* TEST CODE 2 *)
let
	val stmtlst = [Move(Direction("north"), ArithExp(Multi(Number(3), Identifier("i")))),
	               Move(Direction("east"), Number(15)),
	               Move(Direction("south"), Number(4)), 
	               Move(Direction("west"), ArithExp(Plus(ArithExp(Plus(ArithExp(Multi(Number(2), Identifier("i"))),
	                                                     ArithExp(Multi(Number(3), Identifier("j"))))),
	                                                Number(5)))),
	               Stop]
	val vardecl = [VarDecl("i", Number(5)),
	               VarDecl("j", Number(3))]
in
	(print("\nTesting Code 2:\n");
	interpret(Program(Size(64,64), Robot(vardecl, Start(Number(23), Number(6)), stmtlst)))
	handle
	OutOfBounds => print("Robot fell of the grid!\n"))
end;

(* TEST CODE 3 *)
let
	val whilestmt1 = [Move(Direction("south"), Identifier("j")),
	                  Assignment("j", ArithExp(Minus(Identifier("j"), Number(1))))]
	val stmtlst = [Move(Direction("north"), ArithExp(Multi(Number(3), Identifier("i")))),
	               Move(Direction("west"), Number(15)),
	               Move(Direction("east"), Number(4)),
	               While(BiggerThan(Identifier("j"), Number(0)), whilestmt1),
	               Stop]
	val vardecl = [VarDecl("i", Number(5)),
	               VarDecl("j", Number(3))]
in
	(print("\nTesting Code 3:\n");
	interpret(Program(Size(64,64), Robot(vardecl, Start(Number(24), Number(6)), stmtlst)))
	handle
	OutOfBounds => print("Robot fell of the grid!\n"))
end;

(* TEST CODE 4 *)
let
	val whilestmt1 = [Move(Direction("north"), Identifier("j"))]
	val stmtlst = [While(BiggerThan(Identifier("j"), Number(0)), whilestmt1),
	               Stop]
	val vardecl = [VarDecl("j", Number(3))]
in
	(print("\nTesting Code 4:\n");
	interpret(Program(Size(64,64), Robot(vardecl, Start(Number(1), Number(1)), stmtlst)))
	handle
	OutOfBounds => print("Robot fell of the grid!\n"))
end;


