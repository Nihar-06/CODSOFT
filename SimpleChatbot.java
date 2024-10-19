import java.util.Scanner;

public class SimpleChatbot {

    // Method to provide chatbot responses based on user input
    public static String getResponse(String userInput) {
        // Convert input to lowercase for case-insensitive matching
        userInput = userInput.toLowerCase();

        // Predefined rules using if-else statements
        if (userInput.contains("hello") || userInput.contains("hi")) {
            return "Hello! How can I assist you today?";
        } else if (userInput.contains("your name")) {
            return "I'm your friendly chatbot. What would you like to know?";
        } else if (userInput.contains("weather")) {
            return "Sorry, I can't provide weather updates yet, but you can check your local forecast.";
        } else if (userInput.contains("bye") || userInput.contains("goodbye")) {
            return "Goodbye! Have a great day!";
        } else if (userInput.contains("help")) {
            return "Sure! I can assist you with general inquiries. Just ask!";
        } else {
            return "I'm not sure how to respond to that. Could you ask something else?";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the simple chatbot. Type 'bye' to exit.");
        
        // Main loop to keep the chatbot running
        while (true) {
            // Get user input
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            // Exit the chatbot if user types 'bye'
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Chatbot: Goodbye!");
                break;
            }

            // Get and print the chatbot's response
            String response = getResponse(userInput);
            System.out.println("Chatbot: " + response);
        }

        scanner.close();
    }
}
