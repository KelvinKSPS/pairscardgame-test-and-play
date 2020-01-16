package pairscardgame.test.and.play.launcher;

import java.io.IOException;

public class GameHandler {
	private static Process pid;
	private static String hardcodedFileName = "pairscardgame-master-0.0.1-SNAPSHOT-jar-with-dependencies";
	public static void startGame() {
		try {
			pid = Runtime.getRuntime().exec("java -jar ../pairscardgame/target/" + hardcodedFileName + ".jar");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void finishGame() {
		pid.destroy();
	}
}
