/**
 * Connects to a OneLinerSocketServer to retrieve cheesy jokes.
 *
 *
 * @author stevelyall, with portions adapted from:
 *
 * http://docs.oracle.com/javase/tutorial/displayCode.html?code=http://docs.oracle.com/javase/tutorial/networking/sockets/examples/EchoClient.java
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;



public class OneLinerSocketClient {
    static String hostname;
    static int port;
    static OneLinerClientProtocol cp = new OneLinerClientProtocol();

    /**
     * Sets up the socket, connects to the server, and gets input from the user
     *
     * Precondition: A server application is running and is accessible
     * Postcondition: The client application has stopped.
     * Complexity: Undefined
     */
    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println(
                    "Usage: java OneLinerSocketClient <host name> <port number>");
            System.exit(1);
        }

        hostname = args[0];

        try {
            port = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException e) {
            System.out.println(args[1] + " is not a valid port number.");
            return;
        }

        System.out.println("Connecting to " + hostname + " on port " + port + "...");
        try (
                Socket socket = new Socket(hostname, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        ) {

            String userInput, serverResponse;
            System.out.print(cp.getPrompt());

            while ((userInput = stdIn.readLine()) != null) {

                if (!cp.isValidInput(userInput)) {
                    System.out.println("That is not a valid command.\n" + cp.getPrompt());
                    continue;
                }

                if (cp.isDisconnectCommand(userInput)) {
                    System.out.println("Disconnecting...");
                    return;
                }

                out.println(userInput); // write to socket

                serverResponse = in.readLine();
                System.out.println("\nServer: " + serverResponse + "\n");
                System.out.println(cp.processResponse(serverResponse));

                System.out.println(cp.getPrompt());
            }

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostname);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostname);
            System.exit(1);
        }
    }

}
