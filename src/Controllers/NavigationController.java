package Controllers;

import Views.ConsoleIOManager;
import java.util.Stack;

/**
 * Base navigation controller class using a Facade and Singleton Pattern that keeps track
 * of the order in which navigation controller is called and instantiates each controller accordingly.
 *
 * @author Phee Kian Ann
 * @version 1.0
 * @since 2022-10-25
 */
public class NavigationController {

    /**
     * NavigationController singleton pattern instance.
     */
    protected static NavigationController instance = null;

    /**
     * Creates a new instance of NavigationController if there isn't one,
     * else returns the old created instance.
     * @return Instance of NavigationController singleton.
     */
    public static NavigationController getInstance() {
        if (instance == null)
            instance = new NavigationController();
        return instance;
    }

    /**
     * Stack keeps track controller creation order using a FILO order.
     */
    private final Stack<INavigation> stack = new Stack<>();

    /**
     * Loads a new INavigation object, pushes it into the stack and runs the Start() method.
     *
     * <br>Example:
     * <pre>
     * {@code
     *      NavigationController.getInstance().Load(new AdminController());
     * }
     * </pre>
     * @param navigation Takes in an object that implements INavigation interface
     * @see INavigation
     */
    public void load(INavigation navigation) {
        instance.stack.push(navigation);
        navigation.start();
    }

    /**
     * Pop the top level in the stack by given amount and Load the next controller.
     * @param level (Optional) Given an int, decides the amount of controllers to pop from the stack.
     */
    public void goBack(int... level) {
        // Default Pop 1 level
        int popLevels = 1;
        // Override default if there are args passed
        if (level.length > 0) {
            popLevels = level[0];
        }

        INavigation lastController = stack.pop();

        if (popLevels <= stack.size() + 1) {
            if (stack.size() == 0) {
                ConsoleIOManager.printLine("Already on the first page!");
            }

            // Pop until it has reached required
            while (popLevels > 0 && stack.size() > 0) {
                lastController = stack.pop();
                --popLevels;
            }
        } else {
            ConsoleIOManager.printLine("Too large of a value is given! Unable to go back!");
            return;
        }

        // Reload the last popped navigation
        load(lastController);
    }

}