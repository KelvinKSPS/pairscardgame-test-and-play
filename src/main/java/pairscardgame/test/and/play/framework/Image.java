package pairscardgame.test.and.play.framework;

import org.sikuli.script.Pattern;

public class Image {

	private Pattern pattern;
	public double similarity = 0.6;
	private String folder = "/resources/";

	public Image(String name, String path) {
		this.setPattern(name, path);
	}

	public Image(String name, String path, double similarity) {
		this.pattern = new Pattern(System.getProperty("user.dir") + folder + name + "/" + path);
		this.similarity = similarity;
	}

	public Pattern getPattern() {
		return pattern;
	}

	private void setPattern(String name, String path) {
		this.pattern = new Pattern(System.getProperty("user.dir") + folder + name + "/" + path);
	}
}
