public class Subtract extends Operator
{
	private char operator = '-';
	private boolean left_assoc = true;
	private int precedence = 2;

	public double operate( double A, double B )
	{
		return A-B;
	}

	public boolean isLeftAssoc()
	{
		return left_assoc;
	}

	public char getOp()
	{
		return operator;
	}

	public int getPrec()
	{
		return precedence;
	}
}
