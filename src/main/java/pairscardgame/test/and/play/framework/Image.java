package pairscardgame.test.and.play.framework;

import org.sikuli.script.Pattern;

public class Image {

	private Pattern pattern;
	final static private String FOLDER = System.getProperty("user.dir") + "/resources/";

	public Image(String name, String path) {
		this.setPattern(name, path);
	}

	public Image(String name, String path, double similarity) {
		this.pattern = new Pattern(FOLDER + name + "/" + path);
	}

	public Pattern getPattern() {
		return pattern;
	}

	private void setPattern(String name, String path) {
		this.pattern = new Pattern(FOLDER + name + "/" + path);
	}
}
