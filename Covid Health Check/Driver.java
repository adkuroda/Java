import java.util.Scanner;

/**
 * General Driver class for the project 3. 
 */
public class Driver
{	
	/**
	 * Main class for the driver. 
	 * @param args command line argument.
	 */
	public static void main(String[] args)
	{
		if(args.length < 1) {
			System.out.println("Please provide the file name");
			System.exit(0);
		}
		CovidHealthBuilder build = new CovidHealthBuilder(args[0]);
		String response;
		do
		{
			build.decide();
			System.out.print("Try again? ");
			response = getUserResponse();
		} while (response.toLowerCase().equals("yes"));
		System.out.println("Have a nice day!");
	}  // end main
	/**
	 * Obtains user input. 
	 * @return user input. 
	 */
	public static String getUserResponse()
	{
		Scanner keyboard = new Scanner(System.in);
		String response = keyboard.nextLine();

		return response;
	} // endgetUserResponse

	/**
	 * Evaluates user input.
	 * @return true or false. 
	 */
	public static boolean isUserResponseYes()
	{
		String answer = getUserResponse();
		if (answer.toLowerCase().equals("yes"))
			return true;
		else
			return false;
	} // end isUserResponseYes
} // end Driver

