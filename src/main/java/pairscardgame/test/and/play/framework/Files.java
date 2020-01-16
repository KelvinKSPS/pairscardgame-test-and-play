package pairscardgame.test.and.play.framework;

public class Files {
	/*
	 * starting => GERP opening, environment selection and logging
	 */

	public static Image initialScreen(String file) {
		return new Image("initial-screen", file);
	}
	public static Image levelScreen(String file) {
		return new Image("level-screen", file);
	}
	
	public static Image temp(String file) {
		return new Image("temp", file);
	}
		
}
