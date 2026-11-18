import obj.Config;
import obj.Contact;

import java.util.HashMap;
import java.util.Scanner;


public class Main {
    static void main() throws Exception {
        RailwayManager railwayManager = new RailwayManager(Config.getConfig().addresses.hardware.railway);

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            try {

                System.out.print("> ");
                String input = scanner.nextLine();
                String[] inputParts = input.split(" ");

                if (inputParts.length == 0) {
                    continue;
                }

                String command = inputParts[0].toLowerCase();
                switch (command) {
                    case "setpoint":
                        railwayManager.setPoint(inputParts[1], inputParts[2].toLowerCase().equals("true"));
                        break;
                    case "setlight":
                        railwayManager.setLight(inputParts[1], inputParts[2].toLowerCase().equals("true"));
                        break;
                    case "setgate":
                        railwayManager.setGate(inputParts[1], inputParts[2].toLowerCase().equals("true"));
                        break;
                    case "setbell":
                        railwayManager.setBell(inputParts[1], inputParts[2].toLowerCase().equals("true"));
                        break;
                    case "settrack":
                        railwayManager.setTrack(inputParts[1], Integer.parseInt(inputParts[2]),
                                Integer.parseInt(inputParts[3]));
                        break;
                    case "setsignal":
                        boolean[] color = {inputParts[3].toLowerCase().equals("true"), inputParts[4].toLowerCase().equals("true"), inputParts[5].toLowerCase().equals("true")};
                        railwayManager.setSignal(inputParts[1], Integer.parseInt(inputParts[2]), color);
                        break;
                    case "send":
                        railwayManager.setRailway(true);
                        break;
                    case "getcontacts":
                        HashMap<String, Contact[]> contactsMap = railwayManager.requestContacts();
                        for (Contact[] contacts : contactsMap.values()) {
                            for (Contact contact : contacts) {
                                if (contact != null) {
                                    System.out.println(contact.toString());
                                }
                            }
                        }
                        break;
                    case "reset":
                        railwayManager.resetRailway();
                        railwayManager.setRailway(true);
                        break;
                    case "disconnect":
                        railwayManager.disconnect(true, false);
                        break;
                    case "exit":
                        exit = true;
                        railwayManager.disconnect(true, true);
                        scanner.close();
                        break;
                    case "connect":
                        railwayManager.connect();
                        break;
                    case "shutdown":
                        railwayManager.shutdown(false);
                        break;
                    default:
                        System.out.println("WARNING! Command not found!");
                        break;
                }

            } catch (Exception e) {
                System.err.println(e);
            }

        }

        // scanner.close(); //just do this at the very end of the program
    }
}