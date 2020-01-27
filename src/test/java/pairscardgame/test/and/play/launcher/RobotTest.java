package pairscardgame.test.and.play.launcher;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Match;
import org.sikuli.script.Region;

import com.google.common.collect.Iterators;

import static pairscardgame.test.and.play.framework.Files.*;

import java.util.Iterator;

import pairscardgame.test.and.play.framework.Wrapper;


public class RobotTest {
	public static Wrapper screen = new Wrapper();

	@Before
	public void beforeAll() throws Exception {

		
	}
	
	//@Test
	public void testEasy() throws Exception {
		GamePlayer gamePlayer = new GamePlayer();
		gamePlayer.startGameAndRobot("Easy");
		assertTrue(screen.exists(levelScreen("Winner")));
	}

	//@Test
	public void testMedium() throws Exception {
		GamePlayer gamePlayer = new GamePlayer();
		gamePlayer.startGameAndRobot("Medium");
		assertTrue(screen.exists(levelScreen("Winner")));
	}
	
	@Test
	public void testHard() throws Exception {
		GamePlayer gamePlayer = new GamePlayer();
		gamePlayer.startGameAndRobot("Hard");
		assertTrue(screen.exists(levelScreen("Winner")));
	}
	
	//@Test
	public void deckOnline() throws Exception {
		GamePlayer gamePlayer = new GamePlayer();
		gamePlayer.startGameAndRobot(null, "DeckOnline");
		assertTrue(screen.exists(levelScreen("Winner")));
	}

	
	
	@After
	public void finisher() {
		screen.delay(5);
		GameHandler.finishGame();
	}
	
	

}
