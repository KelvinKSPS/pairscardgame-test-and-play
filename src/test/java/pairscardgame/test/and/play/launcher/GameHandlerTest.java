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


public class GameHandlerTest {
	public static Wrapper screen = new Wrapper();

	@Before
	public void beforeAll() throws Exception {

		GameHandler.startGame();
		screen.waitForImage(initialScreen("PairsCardGame"), 3);

	}

	@Test
	public void testLevelButtonsAndSidiLogo() {
		boolean exists = screen.exists(initialScreen("Easy")) 
				&& screen.exists(initialScreen("Medium"))
				&& screen.exists(initialScreen("Hard"))
				&& screen.exists(initialScreen("SidiLogo"));
		
		assertTrue(exists);
	}

	@Test
	public void testEasyLevel() throws Exception {
		screen.click(initialScreen("Easy"));
		screen.waitAndClick(levelScreen("SidiDeck"), 2);
		screen.delay();
	
		Iterator<Match> regions = screen.findAll(levelScreen("SidiDeck"));
		int cardsQuantity = Iterators.size(regions);
		
		assertEquals(cardsQuantity, 8);
	}
	
	@Test
	public void testMediumLevel() throws Exception {
		screen.click(initialScreen("Medium"));
		screen.waitAndClick(levelScreen("SidiDeck"), 2);
		screen.delay();
		
		Iterator<Match> regions = screen.findAll(levelScreen("SidiDeck"));
		int cardsQuantity = Iterators.size(regions);
		assertEquals(cardsQuantity, 12);
	}
	
	@Test
	public void testHardLevel() throws Exception {
		screen.click(initialScreen("Hard"));
		screen.waitAndClick(levelScreen("SidiDeck"), 2);
		screen.delay();
		
		Iterator<Match> regions = screen.findAll(levelScreen("SidiDeck"));
		int cardsQuantity = Iterators.size(regions);
		
		assertEquals(cardsQuantity, 16);
	}
	
	@After
	public void finisher() {
		GameHandler.finishGame();
	}
	
	

}
