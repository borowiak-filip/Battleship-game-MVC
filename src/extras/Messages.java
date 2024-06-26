package extras;

//@borowiak-filip
/**
 * Messages object containing all game messages and exceptions  
 */
public class Messages {

    public static final String READ_FILE_PATH = "layouts/map.txt";

    public static final String WELCOME = "\n --- Welcome to the Battle Ship Game --- \n";
    public static final String BYE = "See You Later!";
    public static final String FIRST_MENU = "Type... \n 'START' - to init environment \n 'LOAD'  - to load an external map layout \n 'EXIT' - to exit";
    public static final String SECOND_MENU = "Type... \n 'GUI' - for Graphical Interface \n 'CLI' - for Terminal Interface \n 'EXIT' - to exit";
    public static final String NEW_GAME = "Starting new game...";
    public static final String GOOD_SHOT = "Good shot!";
    public static final String SUNK_SHIP = "Good shot! You have sunk the ship!";
    public static final String MISSED_SHOT = "You missed";
    public static final String GAME_OVER = "Game Over - Well Done";
    public static final String WRONG_COMMAND = "Wrong Command!";
    public static final String FIELD_CHECKED = "This field has been already checked!";
    public static final String USER_CLI_ATTACK_CONSOLE = "Console: press: \n - 'ATTACK' to attack \n - 'EXIT' to exit \n";
    public static final String ENTER_TARGET = "Enter the coordinates in a format {LetterDigit}, eg., A6 ";
    public static final String HORIZONTAL_BAR = "------------";
    public static final String BOARD_RENDERED = "New Board Rendered";
    public static final String ALL_SHIPS_DOWN = "All ships are down You won!";
    public static final String CURRENT_TRIES = "\nCurrent Tries count: ";
    public static final String SHOOTING = "Shooting at: ";
    public static final String VERTICAL_BAR = "|";
    public static final String NEW_SHIP = "Creating SHIP No: ";
    public static final String TRIES_SUM = "Tries required to win the game: --> ";
    public static final String TITLE = "BattleShip Game!";
    public static final String LOADING_FILE = "Loading an external file.. from src/layouts";
    public static final String FILE_LOADED = "File loaded";
    public static final String ATTACK_SHIP_GUI = "Attack Ship!";
    public static final String GUI_WELCOME = " -- Welcome to the Battleship Game! There are 5 different ships. Your job is to sunk them all, Good Luck! --";
    public static final String GUI_EXCEPTION = "Error has occured with GUI Module";
    public static final String FILE_EXCEPTION = "Could not read from the file!";
    public static final String SHIP_COUNT_EXCEPTION = "Game requires 5 ships";
    public static final String SHIP_LENGTH_EXCEPTION = "Wrong ships lengths. Game requires 2 ships of length 2, and 1 ship of length 3, 4, and 5!";
    public static final String SHIP_ADJACENT_EXCEPTION = "Ships need to be either vertically or horizontally adjacent";
    public static final String NUMBER_OUT_OF_BOUND_EXCEPTION = "Input Coordinates are out of the bounds or command is in wrong format!";
    public static final String POSITION_CHECKED_EXCEPTION = "Those coordinates have already been checked! Do not waste your ammunition..";
    
    
    
        
}

