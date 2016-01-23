public class ParanRight extends Operator
{
	private char operator = ')';
	private boolean left_assoc = true;
	private boolean can_operate = false;
	private int precedence = 10;

	public double operate( double A, double B )
	{
		return 0.0;
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
