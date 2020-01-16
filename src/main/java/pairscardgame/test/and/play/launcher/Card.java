package pairscardgame.test.and.play.launcher;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import org.sikuli.script.Image;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.ScreenImage;

public class Card {
	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}
	static private int idCount = 0;
	private int id = 0;
	private boolean matched = false;
	private boolean found;
	private Match match;
	private BufferedImage image;
	private Region region;
	private Rectangle rect;

	public Card (Match match) {
		this.rect = match.getRect();
		this.match = match;
		this.setFound(false);
		this.setImage(null);
		this.id = idCount;
		idCount++;
	}
	
	public boolean isMatched() {
		return matched;
	}

	public void setMatched() {
		this.matched = true;
	}

	
	public int getIntId() {
		return id;
	}
	
	public String getId() {
		return ""+id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public boolean isFound() {
		return found;
	}

	public void setFound(boolean found) {
		this.found = found;
	}
	
	public void setFound() {
		this.found = true;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public void setImage(BufferedImage bufferedImage) {
		this.image = bufferedImage;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void setRegion(Region region) {
		this.region = region;
	}
	
	public Region getRegion() {
		return region;
	}
}
