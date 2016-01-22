public class Operator
{
	public boolean left_associativity;
	public char operator;

	Operator( boolean assoc, char op )
	{
		left_associativity = assoc;
		operator = op;
	}
}

