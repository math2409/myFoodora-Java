/**
 * The runnable class that allows a user to launch the CLUI
 * and to interact with the application through commands. You can exit
 * the CLUI by typing exit, and have a list of commands by typing help.
 * @author mathias Thirion
 * @author maxime Leboeuf
 */

package fr.cs.group15.myFoodora.clui;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainCLUI {
	public static void main(String[] args) {
		CommandHandler commandHandler = new CommandHandler();
		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		
		System.out.println("MyFoodora is now working. Type your command, or type 'help' for a list of commands.");
		
		while (running) {
			System.out.println(">>> ");
			String input = scanner.nextLine().trim();
			String[] parts = splitInput(input);
			String commandName = parts[0];
			String[] commandArgs = new String[parts.length - 1];
			System.arraycopy(parts, 1, commandArgs, 0, parts.length - 1);
			
			if (commandName.equalsIgnoreCase("exit")) {
				running = false;
				System.out.println("Exiting MyFoodora");
			} else {
				commandHandler.handleCommand(commandName, commandArgs);
			}
		}
		
		
		scanner.close();
		System.out.println("MyFoodora exited.");
	}
	
	/**
	 * Splits the input, and takes into account that "" allow for spaces inside arguments
	 * @param input
	 * @return the list of arguments
	 */
	public static String[] splitInput(String input) {
        ArrayList<String> matches = new ArrayList<>();
        Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(input);
        while (m.find()) {
            matches.add(m.group(1).replace("\"", ""));
        }
        return matches.toArray(new String[matches.size()]);
    }
}
