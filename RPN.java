import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class RPN 
{
	static char operators[] = {'+', '-', '*', '/', '(', ')' };

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
		for( char operator : operators )
		{
			if( operator == character )
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
}
