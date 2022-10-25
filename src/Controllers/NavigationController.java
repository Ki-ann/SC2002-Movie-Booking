package Controllers;

import java.util.Stack;

public class NavigationController {

	protected static NavigationController instance = null;

	public static NavigationController getInstance() {
		if (instance == null)
			instance = new NavigationController();
		return instance;
	}

	private Stack<INavigation> stack;

	/**
	 * 
	 * @param navigation
	 */
	public void Load(INavigation navigation) {
		// TODO - implement Controllers.NavigationController.Load
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param level
	 */
	public void goBack(int... level) {
		// TODO - implement Controllers.NavigationController.goBack
		throw new UnsupportedOperationException();
	}

}