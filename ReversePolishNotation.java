import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class ReversePolishNotation
{
	private static Operator all_operators[] = { new Power(),
												new Multiply(),
												new Division(),
												new Addition(),
												new Subtract(),
												new ParanLeft(),
												new ParanRight() };

	public static void main( String[] args ) 
	{
		Scanner scanner = new Scanner( System.in );
		System.out.print( "Enter an equation: " );
		String input = scanner.nextLine();
		
		List<String> tokens = tokenize( input );
		System.out.print( "Input: \t" );
		for( String token : tokens )
		{
			System.out.print( token + " " );
		}
		System.out.print( "\n" );

		tokens = shuntingYard( tokens );
		System.out.print( "RPN: \t" );
		for( String token : tokens )
		{
			System.out.print( token + " " );
		}
		System.out.print( "\n" );

		double result = evaluate( tokens );
		System.out.print( "Result: " + Double.toString( result ) + "\n" );
	}

	private static double evaluate( List<String> tokens )
	{
		Stack<Double> stack = new Stack<Double>();

		for( String token : tokens )
		{
			if( isNumber( token ))
			{
				double digit = Double.parseDouble( token );
				stack.push( digit );
				continue;
			}

			if( isOperator( token ))
			{
				try
				{
					Operator cur_operator = getOperator( token );
					double digit_B = stack.pop();
					double digit_A = stack.pop();
					
					stack.push( cur_operator.operate( digit_A, digit_B ));
				}
				catch( NumberFormatException error )
				{
					System.out.print( "Invalid expression" );
				}
			}
		}

		return stack.pop();
	}

	private static List<String> shuntingYard( List<String> tokens )
	{
		List<String> output = new ArrayList<String>();
		Stack<Operator> operators = new Stack<Operator>();

		for( String token : tokens )
		{
			if( isNumber( token ))
			{
				output.add( token );
				continue;
			}

			if( isOperator( token ))
			{
				Operator cur_operator = getOperator( token );

				System.out.print( "PROCESS: " + Character.toString( cur_operator.getOp() ) + "\n" );

				// Righthand paranthesis will push everything on
				// the stack to output until a lefthand paran has been recieved
				if( cur_operator.getOp() == ')' )
				{
					try
					{
						Operator top_operator = operators.pop();
						System.out.print( "STACK: Popped " + Character.toString( top_operator.getOp() ) + "\n" );

						while( top_operator.getOp() != '(' )
						{
							if( top_operator.canOperate() )
							{
								output.add( Character.toString( top_operator.getOp() ));
								System.out.print( "QUEUE: Added " + Character.toString( top_operator.getOp() ) + "\n" );
							}
								
							if( operators.empty() )
							{
								break;
							}
							top_operator = operators.pop();
							System.out.print( "STACK: Popped " + Character.toString( top_operator.getOp() ) + "\n" );
						}
					}
					catch( NumberFormatException error )
					{
						System.out.print( "Could not find lefthand paranthesis" );
						return null;
					}
					continue;
				}

				int operators_size = operators.size();
				for( int i = 0; i < operators_size; i++ )
				{
					if( operators.peek() == null )
						break;

					Operator top_operator = operators.peek();

					// Adding the operator to the output
					if( ( cur_operator.canOperate() && top_operator.canOperate() ) &&
					    (( cur_operator.isLeftAssoc() && cur_operator.getPrec() <= top_operator.getPrec() ) ||
					    ( !cur_operator.isLeftAssoc() && cur_operator.getPrec() < top_operator.getPrec() )))
					{
						top_operator = operators.pop();
						System.out.print( "STACK: Popped " + Character.toString( top_operator.getOp() ) + "\n" );

						if( top_operator.canOperate() )
						{
							output.add( Character.toString( top_operator.getOp() ));
							System.out.print( "QUEUE: Added " + Character.toString( top_operator.getOp() ) + "\n" );
						}
					}
					else
					{
						break;
					}
				}
					
				operators.push( cur_operator );
				System.out.print( "STACK: Added " + Character.toString( cur_operator.getOp() ) + "\n" );
			}
		}

		System.out.print( "PROCESS: Moving stack to queue\n" );
		while( !operators.empty() )
		{
			Operator cur_operator = operators.pop();
			System.out.print( "STACK: Popped " + Character.toString( cur_operator.getOp() ) + "\n" );
			if( cur_operator.canOperate() )
			{
				output.add( Character.toString( cur_operator.getOp() ));
				System.out.print( "QUEUE: Added " + Character.toString( cur_operator.getOp() ) + "\n" );
			}
		}

		return output;
	}

	private static List<String> tokenize( String input )
	{
		// List because it needs to dynamically add tokens
		List<String> tokens = new ArrayList<String>();
		
		String token = "";
		for( int i = 0; i < input.length(); i++ )
		{	
			char character = input.charAt( i );
			
			if( isNumber( character ))
			{
				token += character;
			}
			
			if( isOperator( character ) || ( isNumber( character ) && i == input.length()-1 ))
			{
				if( token.length() > 0 )
				{
					tokens.add( token );
				}
				
				if( isOperator( character ))
				{
					tokens.add( Character.toString( character ));
				}
				
				token = "";
			}
		}

		return tokens;
	}

	private static boolean isOperator( char character )
	{
		for( Operator operator : all_operators )
		{
			if( operator.getOp() == character )
			{
				return true;
			}
		}

		return false;
	}

	private static boolean isNumber( char character )
	{
		if( character < '0' )
		{
			return false;
		}
		else if( character > '9' )
		{
			return false;
		}

		return true;
	}

	private static boolean isOperator( String character )
	{
		try
		{
			return isOperator( character.charAt( 0 ));
		}
		catch( NumberFormatException error )
		{
			return false;
		}
	}

	private static boolean isNumber( String number )
	{
		try
		{
			double digit = Double.parseDouble( number );	
		}
		catch( NumberFormatException error )
		{
			return false;
		}

		return true;
	}

	private static Operator getOperator( char character )
	{
		for( Operator operator : all_operators )
		{
			if( operator.getOp() == character )
			{
				return operator;
			}
		}

		return null;
	}

	private static Operator getOperator( String character )
	{
		return getOperator( character.charAt( 0 ) );
	}
}
