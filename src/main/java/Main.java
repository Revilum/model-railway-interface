import kieler_gen.Interface;

import java.util.Scanner; 

public class Main {
    static void main() {
        Interface chartInterface = new Interface(false);

        Scanner scanner = new Scanner(System.in); 
        do {
            System.out.println("Enter command:");
            String input = scanner.nextLine();
            String[] inputParts = input.split(" ");

            if (inputParts.length == 0) {
                continue;
            }

            String command = inputParts[0];
            switch (command) {
                case "setPoint":
                    
                    break;
                case "setLight":
                    
                    break;
                case "setGate":
                    
                    break;
                case "setBell":
                    
                    break;
                case "setTrack":
                    
                    break;
                case "send":
                
                    break;
                case "clear":
                
                    break;
                case "disconnect":
                    //TODO: disconnect from railway
                    break;
            
                default:
                    System.out.println("WARNING! Command not found!");
                    break;
            }

            System.out.println("Command: " + input); 
        } while (true);

        // scanner.close(); //just do this at the very end of the program
    }
}
