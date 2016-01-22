import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class ReversePolishNotation
{
	private static Operator operators[] = { new Operator( false, '^' ),
											new Operator( true, '*' ),
											new Operator( true, '/' ),
											new Operator( true, '+' ),
											new Operator( true, '-' ) };

	public static void main( String[] args ) 
	{
		Scanner scanner = new Scanner( System.in );
		System.out.print( "Enter an equation: " );
		String input = scanner.next();
		
		List<String> tokens = tokenize( input );

		for( String token : tokens )
		{
			System.out.print( token + "\n" );
		}
	}

	private static List<String> shuntingYard( List<String> tokens )
	{
		List<String> output = new ArrayList<String>();
		Stack<String> operators = new Stack<String>();

		for( String token : tokens )
		{
			if( isNumber( token ))
			{
				output.add( token );
				continue;
			}

			if( isOperator( token ))
			{
				return output;
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

			// Add it to our return list
			if( isOperator( character ) || i == input.length()-1 )
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
		for( Operator operator : operators )
		{
			if( operator.operator == character )
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

}
