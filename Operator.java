abstract public class Operator
{
	private boolean can_operate;
	private boolean left_assoc;
	private char operator;
	private int precedence;

	abstract public double operate( double A, double B );

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

