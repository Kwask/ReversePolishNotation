public class Power extends Operator
{
	private char operator = '^';
	private boolean left_assoc = true;
	private int precedence = 4;

	public double operate( double A, double B )
	{
		return Math.pow( A, B );
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
