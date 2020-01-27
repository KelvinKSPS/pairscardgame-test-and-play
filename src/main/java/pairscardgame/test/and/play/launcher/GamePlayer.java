package pairscardgame.test.and.play.launcher;

import static org.junit.Assert.assertTrue;
import static pairscardgame.test.and.play.framework.Files.initialScreen;
import static pairscardgame.test.and.play.framework.Files.levelScreen;
import static pairscardgame.test.and.play.framework.Files.temp;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Image;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.sikuli.script.ScreenImage;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

import pairscardgame.test.and.play.framework.Wrapper;

public class GamePlayer {
	private Wrapper screen = new Wrapper();
	private double timeout = 0.4;
	private double flipTimeout = timeout * 2;

	public GamePlayer() {
		super();
	}

	public void startGameAndRobot(String level) throws Exception {
		this.start();
		this.selectLevel(level);
		this.startRobot();
	}
	
	public void startGameAndRobot(String level, String deckName) throws Exception {
		this.startRobot(deckName);
	}
	
	private void startRobot() throws FindFailed {
		startRobot("SidiDeckComplete");
	}

	private void startRobot(String deckName) throws FindFailed {

		Iterator<Match> regions = screen.findAll(levelScreen(deckName));
		List<Card> allCards = new ArrayList<>();
		
		
		while (regions.hasNext()) {
			Match match = regions.next();
			Card card = new Card(match);
			allCards.add(card);
		}
		Iterator<Card> allCardsToIterator = new ArrayList<Card>(allCards).iterator();
		
		
		double similarity = 2;
		
		while(allCardsToIterator.hasNext()) {

			Card firstCard = allCardsToIterator.next();
			
			firstCard.getMatch().click();
			screen.delay(flipTimeout);
			
			Region currentRegion = firstCard.getMatch();
			setupRegionToMemory(firstCard, currentRegion);
		
			if(compareRegionToMemory(allCards, firstCard, similarity)) {
				continue;
			}
						
			Card secondCard = allCardsToIterator.next();
			secondCard.getMatch().click();
			screen.delay(flipTimeout);
			
			currentRegion = secondCard.getMatch();
			setupRegionToMemory(secondCard, currentRegion);
			
			if(screen.getDifferencePercent(secondCard.getImage(), firstCard.getImage()) < similarity) {
				allCards.remove(secondCard);
				allCards.remove(firstCard);
				continue;
			};
			
			if(compareRegionToMemory(allCards, secondCard, similarity)) {
				secondCard.getMatch().click();
				screen.delay(timeout);
				continue;
			}

		}
		
			
	}

	private void setupRegionToMemory(Card currentCard, Region currentRegion) {
		currentCard.setRegion(currentRegion);
		ScreenImage image = screen.capture(currentRegion);
		currentCard.setImage(image.getImage());
		currentCard.setFound();
	}

	private boolean compareRegionToMemory(List<Card> allCards, Card currentCard, double similarity) {
		boolean found = false;
		for(Card card: allCards) {
			if(card.isFound() && card.getIntId()!=currentCard.getIntId()) {
				if(screen.getDifferencePercent(currentCard.getImage(),card.getImage()) < similarity) {
					card.getMatch().click();
					screen.delay(timeout);
					allCards.remove(currentCard);
					allCards.remove(card);
					found = true;
					break;
				}
			}
		}
		return found;
	}
		

	

	private void selectLevel(String level) throws Exception {
		screen.click(initialScreen(level));
		screen.waitAndClick(levelScreen("SidiDeck"), 2);
		screen.delay(timeout);
	}

	private void start() throws Exception {
		GameHandler.startGame();
		screen.waitForImage(initialScreen("PairsCardGame"), 3);
	}
}
