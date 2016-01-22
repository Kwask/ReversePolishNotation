public class Operator
{
	public boolean left_assoc;
	public char operator;
	public int precedence;

	Operator( boolean assoc, char op, int prec )
	{
		left_assoc = assoc;
		operator = op;
		precedence = prec;
	}
}

