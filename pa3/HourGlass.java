//Draw an hourglass figure

public class HourGlass
{
    private static int size = 0;
    private static char HOURGLASS_CHAR = '@';
    private static char FILLER_CHAR = '\'';

    private static final int MIN_ROWS = 3;
    private static final int MAX_ROWS = 79;

    private static final String USAGE_MSG =
        "java HourGlass size [hourglass_char] [filler_char]\n" +
        "               size must be an odd integer >= " + MIN_ROWS + 
        " and <= " + MAX_ROWS + "\n";

    private static final String MIN_MAX_ERR_MSG =
        "Hourglass size must be >= " + MIN_ROWS +
        " and <= " + MAX_ROWS + "\n";

    private static final String EVEN_ERR_MSG = 
        "Hourglass size must be an odd integer\n";
    
    private static final String EXCEPTION_ERR_MSG =
        + size + " is not a valid integer. \n";
    
    private static final String HOURGLASS_CHAR_ERR_MSG =
        "Hourglass char argument must be a single character\n";
    
    private static final String FILLER_CHAR_ERR_MSG =
        "Filler char argument must be a single character\n";
    
    public static void main(String[] args) 
    {
        //size must be odd integer between (inclusive) 3 and 79
        try 
        {
            size = Integer.parseInt(args[0]);
            
            if((size <= MIN_ROWS) || (size >= MAX_ROWS))
            {
                System.out.print(MIN_MAX_ERR_MSG);
                System.exit(1);
            }

            if((size % 2) == 0)
            {
                System.out.print(EVEN_ERR_MSG);
                System.exit(1);
            }
        }

        catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) 
        {
            System.out.print(USAGE_MSG);
            System.exit(1);
        }

        catch(NumberFormatException nfe) 
        {
            System.out.print(EXCEPTION_ERR_MSG);
            System.exit(1);
        }

        //there can only be one hourglass character specified; if not specified, is default
        try
        {
            HOURGLASS_CHAR = args[1].charAt(0);
            
            if (args[1].length() > 1)
            {
                System.out.print(HOURGLASS_CHAR_ERR_MSG);
            }
        }

        catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException)
        {
            HOURGLASS_CHAR = '@';
        }

        //there can only be one filler character specified; if not specified, is defualt
        try
        {
            FILLER_CHAR = args[2].charAt(0);
            
            if (args[2].length() > 1)
            {
                System.out.print(FILLER_CHAR_ERR_MSG);
            }
        }

        catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException)
        {
            FILLER_CHAR = '\'';
        }

        //"draw" top of hourglass - using for loops
        for(int a = 0; a < (size + 1) / 2; a++)
        {
            for(int b = a; b > 0; b--)
            {
                System.out.print(FILLER_CHAR);
            }

            for(int c = (size - a - a); c > 0; c--)
            {
                System.out.print(HOURGLASS_CHAR);
            }

            for(int d = a; d > 0; d--)
            {
                System.out.print(FILLER_CHAR);
            }

            System.out.print("\n");
        }

        //"draw" bottom of hourglass - reverse previous method
        for(int i = (size - 3) / 2; i >= 0; i--)
        {
            for(int j = i; j > 0; j--)
            {
                System.out.print(FILLER_CHAR);
            }

            for(int k = (size - i - i); k > 0; k--)
            {
                System.out.print(HOURGLASS_CHAR);
            }

            for(int l = i; l > 0; l--)
            {
                System.out.print(FILLER_CHAR);
            }

            System.out.print("\n");
        }
    }
}