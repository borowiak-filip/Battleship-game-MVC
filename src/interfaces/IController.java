package interfaces;

import java.util.List;
import java.util.Observer;

import model.GameModel;
import view.JView;

// @borowiak-filip

/**
* Controller Interface for MVC Controller
*/
@SuppressWarnings("deprecation")
public interface IController {

    /**
     * This method sets the view;
     */
    public void setView(JView view) ;

    /**
     * This method allows to pass random ship allocation, based on the layout file.
     */
    public void initialize( List<int[][]> shipCoords);

    /**
     * This method forwards user action request to the model. 
     */
    public void change(int coordX, int coordY);
    

    /**
     * This method allows to add an observer to the model.
     */
    public void addObserver(Observer o);

    /**
     * This method allows to retrieve an instance of the model.
     * It is required to retrieve use model for CLI since it does not uses controller
     * @return GameModel object
     */
    public GameModel getGameModel();


    
    
}
