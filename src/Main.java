
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import controller.GameController;
import model.GameModel;
import view.CLIView;
import view.JView;
import extras.GameReader;
import extras.Messages;
import extras.CommandOptions;

// @borowiak-filip
public class Main {
public static void main(String[] args) {run();}

/**
 * main run method ---> root loop
 */
public static void run() {

    boolean running = true; //init running flag

    System.out.println(Messages.WELCOME);
    Scanner scanner = new Scanner(System.in); // init scanner
    GameReader gr = new GameReader(); // init GameReader Object
    GameModel model = new GameModel(); // init GameModel
    GameController controller = new GameController(model); // init Controller

    while(running) { // if running flag is still on

        CommandOptions option; // init enum
        System.out.println(Messages.FIRST_MENU);
        String command = scanner.nextLine().toUpperCase(); // get command from the user

        try {option = CommandOptions.valueOf(command); // see if command matches legal commands

            switch(option) {
                case LOAD -> {running = loadGame(scanner, gr, controller, running);break;}
                
                case START -> {running = startGame(scanner, controller, running);break;}

                case EXIT -> {System.out.println(Messages.BYE); running = false; break;}

                default -> {System.out.println(Messages.WRONG_COMMAND);} // user can still try to override with legal command for example attack which is valid
            }

        }catch (Exception CommandException) {System.out.println(Messages.WRONG_COMMAND);} // any other command print dont throw exception to stay within the menu

    }

}

/**
 * This method allows to load a file and then launch next phase of the menu. If file is incorrect it will stay here
 */
private static boolean loadGame(Scanner scanner, GameReader gr, GameController controller, boolean running) throws Exception {
    System.out.println(Messages.NEW_GAME);
    System.out.println(Messages.LOADING_FILE);
    boolean fileCorrect = false;  // if file read correctly this flag will turn to true      
    try {
        var shipCoords = gr.readFile(Messages.READ_FILE_PATH);
        fileCorrect = true; controller.initialize(shipCoords);

    } // read file, set to flag to true and init moodel via controller
    catch(Exception e) {System.out.println(e.getMessage());return running;} // if file is corrupted throw a message and allow to run normal game or correct the file
    System.out.println(Messages.FILE_LOADED);
    

    if (fileCorrect) {running = interfaceMenu(scanner, controller, running);} // pass to GUI/CLI menu

return running;
}

/**
 * This method launches the game with hardcoded setup
 */
private static boolean startGame(Scanner scanner, GameController controller, boolean running) throws Exception {
    System.out.println(Messages.NEW_GAME);
        // Hard corded Ships objects
        List<int[][]> shipCoords = new ArrayList<>();
        int [][] shipPartsCoordinates_2_1 = {{2, 2}, {3, 2}}; // each pair indicates x,y coordinates
        int [][] shipPartsCoordinates_2_2 = {{2, 3}, {3, 3}};
        int [][] shipPartsCoordinates_3 = {{1, 5}, {2, 5}, {3, 5}};
        int [][] shipPartsCoordinates_4 = {{9, 2}, {9, 3}, {9, 4}, {9, 5}};
        int [][] shipPartsCoordinates_5 = {{1, 10}, {2, 10}, {3, 10}, {4, 10}, {5, 10}};
        
        // for presentation. Normally uncomment
        shipCoords.add(shipPartsCoordinates_2_1); // add shipParts to the list
        shipCoords.add(shipPartsCoordinates_2_2);
        shipCoords.add(shipPartsCoordinates_3);
        shipCoords.add(shipPartsCoordinates_4);
        shipCoords.add(shipPartsCoordinates_5);
        controller.initialize(shipCoords); // init moodel via controller

        running = interfaceMenu(scanner, controller,  running); // pass to GUI/CLI menu

return running;
}

/**
 * This method allows to initialise GUI JSwing interface
 */
public static void initGUI(GameController controller) {new JView(controller);}

/**
 * This method allows to initialise CLI interface
 */
public static void initCLI(GameController controller) {

     GameModel model = controller.getGameModel(); // it is needed since controller changes its model, which is GUI model 
    new CLIView(model);
}

/**
 * This method allows to choose between CLI and GUI
 */
public static boolean interfaceMenu(Scanner scanner, GameController controller, boolean running) throws Exception {

        while(running) { // keep in loop to allow for correct commands
            CommandOptions option; // init enum
            System.out.println(Messages.SECOND_MENU);
            String command = scanner.nextLine().toUpperCase(); // get command from a user

            try {option = CommandOptions.valueOf(command); // see if command matches legal commands
                switch(option) {
                    case GUI -> {
                    javax.swing.SwingUtilities.invokeLater(
                        new Runnable() {
                            public void run () {try {initGUI(controller);
                            } catch (Exception e) {System.out.println(Messages.GUI_EXCEPTION);}} //if GUI crashes it will be handled by this clause
                        }
                    );
                    return false;} // need to return false to disable the menu, otherwise it will ask to choose second terminal and crash
                    
                    case CLI -> {initCLI(controller);break;} // init CLI Interface
    
                    case EXIT -> {System.out.println(Messages.BYE); running = false; break;} // user can exit the game --> terminate
    
                    default -> {System.out.println(Messages.WRONG_COMMAND);} // user can still try to override with legal command for example attack which is valid
                }
    
            } catch (Exception e) {System.out.println(Messages.WRONG_COMMAND);} // any other command print dont throw exception to stay within the menu
        }
        return running;

    }

}
    

