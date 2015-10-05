/**
 * Connects to a OneLinerSocketServer to retrieve cheesy jokes.
 *
 *
 * @author stevelyall, with portions adapted from:
 *
 *
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OneLinerClientProtocol {

    final String prompt = "Type [Y] for a line from the server, [N] to disconnect: \n";


    /**
     * Returns the message to prompt for user input
     *
     * Precondition: The OneLinerClientProtocol object has been instantiated
     * Postcondition: None
     * Complexity: O(1)
     *
     * @return a string message
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * Checks whether the user has provided the command to exit the application
     *
     * Precondition: The OneLinerClientProtocol object has been instantiated
     * Postcondition: None
     * Complexity: O(1)
     *
     * @return true if the input string is N or n, false otherwise
     */
    public boolean isDisconnectCommand(String input) {

        return input.trim().toUpperCase().equals("N");
    }

    /**
     * Checks whether the user has provided a valid command
     *
     * Precondition: The OneLinerClientProtocol object has been instantiated
     * Postcondition: None
     * Complexity: O(n)
     *
     * @return true if the input string is valid, false otherwise
     */
    public boolean isValidInput(String input) {

        return (input.trim().toUpperCase().equals("Y") || input.trim().toUpperCase().equals("N"));
    }

    /**
     * Counts the number of occurrences of the word "computer" in the server's response and provides a message
     *
     * Precondition: The OneLinerClientProtocol object has been instantiated
     * Postcondition: None
     * Complexity: O(n)
     *
     * @return a message to be printed to the console
     */
    public String processResponse(String serverResponse) {

        int numOccurrences = 0;
        Pattern pattern = Pattern.compile("computer");
        Matcher matcher = pattern.matcher(serverResponse.toLowerCase());

        while (matcher.find()) {
            numOccurrences++;
        }

        return "" + numOccurrences + " occurrences of the word 'computer'.\nOh Server, you're so funny!\n";
    }
}
