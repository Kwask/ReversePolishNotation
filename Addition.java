public class Addition extends Operator
{
	private char operator = '+';
	private boolean left_assoc = true;
	private boolean can_operate = true;
	private int precedence = 2;

	public double operate( double A, double B )
	{
		return A+B;
	}

	public boolean canOperate()
	{
		return can_operate;
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

