package view;
import model.GameModel;
import java.util.Arrays;
import java.util.Scanner;
import extras.Messages;
import extras.Triple;
import extras.CommandOptions;

public class CLIView {

    private GameModel model;
    private boolean init;

    public CLIView(GameModel model) {

        this.model = model;
        init = true; // set init flag to true

        while (true){
        try {runView(new Scanner(System.in));} // try to run view 
        catch(Exception e) {System.out.println("\n" + Messages.HORIZONTAL_BAR + e.getMessage() + Messages.HORIZONTAL_BAR + "\n"); renderBoard();}} // notify user about errors 
    }
    
    /**
     * RunView method is being caled in the main loop that allows for user menu interaction
     */
    public void runView(Scanner scanner) throws Exception {
        
        CommandOptions option;
        if (this.init) {renderBoard();} // for init render the board; it needs model data and it can be acess via controller (MVC)
        String commandPattern = "([A-J])(10|[1-9])"; // regex for this game
        String command = scanner.nextLine().toUpperCase();
        
        try {option = CommandOptions.valueOf(command);} // check with enum
        catch(Exception e) {throw new Exception(Messages.WRONG_COMMAND);} // check if command is valid
        switch (option){
            case ATTACK -> {
                System.out.println(Messages.ENTER_TARGET); 
                command = scanner.nextLine(); // ask for coordX
                java.util.regex.Pattern regex = java.util.regex.Pattern.compile(commandPattern); // compile regex
                java.util.regex.Matcher matcher = regex.matcher(command); // see if request valid
                if (matcher.matches()) {
                    String col = matcher.group(1);
                    String row = matcher.group(2);
                    int coordY = col.charAt(0) - 'A' + 1; // tranlsate to digits
                    int coordX = Integer.parseInt(row);

                    if (!this.model.isValid(coordX, coordY)) {throw new Exception(Messages.NUMBER_OUT_OF_BOUND_EXCEPTION);} // make sure with the model that coordinates within range
                    

                    // check if the field has not been hit before 
                    if (this.model.hasBeenHitBefore(coordX, coordY)) {throw new Exception(Messages.POSITION_CHECKED_EXCEPTION);} 
                    System.out.println(Messages.SHOOTING + col + Messages.VERTICAL_BAR + row);
                    Triple results = this.model.change(coordX, coordY);
                    String result = results.getSecondValue();
                    System.out.println("\n" + Messages.HORIZONTAL_BAR + result + Messages.HORIZONTAL_BAR + "\n");

                    if (model.checkIfGameOver()) { // if all ships are down notify user and terminate
                        renderBoard();
                        System.out.println(Messages.HORIZONTAL_BAR + Messages.ALL_SHIPS_DOWN + Messages.HORIZONTAL_BAR);
                        System.out.println(Messages.HORIZONTAL_BAR + Messages.TRIES_SUM + model.getCountVal() + Messages.HORIZONTAL_BAR);
                        System.exit(0);
                    }

                    System.out.println(Messages.CURRENT_TRIES + model.getCountVal()); // dispaly total tries 
                    renderBoard(); // invoke rendering 
  
                    break; // break action loop
                    
                } else { throw new Exception(Messages.NUMBER_OUT_OF_BOUND_EXCEPTION);}
                  

            }
                    
            case EXIT -> {System.out.println(Messages.BYE);System.exit(0);break;}
                
            default -> {System.out.println(Messages.WRONG_COMMAND);break;} // user could try other commands legal in other menu parts
        }
    }
    /**
     * Render the board
     */
    public void renderBoard() {
        if (this.init) { // on init notify its new board 
            System.out.println(Messages.HORIZONTAL_BAR);
            System.out.println(Messages.BOARD_RENDERED);
            this.init = false;
        }
        System.out.println("\n" + Messages.USER_CLI_ATTACK_CONSOLE); // main console
        // print map. replace "o" with "-" for better visual aspect
        for (String[] till : this.model.getBoardLayout()) {System.out.println(Arrays.toString(till).replace('o', '-'));}
    }

    
}
