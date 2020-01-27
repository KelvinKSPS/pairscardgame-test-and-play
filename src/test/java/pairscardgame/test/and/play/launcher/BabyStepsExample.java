package pairscardgame.test.and.play.launcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static pairscardgame.test.and.play.framework.Files.initialScreen;
import static pairscardgame.test.and.play.framework.Files.levelScreen;

import java.io.IOException;
import java.util.Iterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Location;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class BabyStepsExample {

	static Screen screen;
	static String path = System.getProperty("user.dir");
	static Process p;

	@BeforeClass
	public static void beforeAll() throws FindFailed, IOException {
		
		screen = new Screen();
		Pattern title = new Pattern(path + "/resources/baby-steps/BasicModeTitle.png");
		String cmd = "gnome-calculator";
		p = Runtime.getRuntime().exec(cmd);
		screen.wait(title, 3);

		Location center = screen.getCenter();
		screen.dragDrop(title, center);

		screen.type(Key.TAB);
	}

	@After
	public void afterEach() {
		screen.type(Key.ESC);
	}

	@Test
	public void sumUsingPKeys() throws FindFailed {
		Pattern one = new Pattern(path + "/resources/baby-steps/1.png");
		screen.wait(one, 3);
		screen.type("10");
		screen.type("+");
		screen.type("4");
		screen.type("=");
		screen.type(Key.ENTER);
		String result = catchResult();
		assertEquals(result, "14");
	}

	@Test
	public void sumUsingPict() throws FindFailed {
		
		Pattern one = new Pattern(path + "/resources/baby-steps/1.png");
		Pattern zero = new Pattern(path + "/resources/baby-steps/0.png");
		Pattern plus = new Pattern(path + "/resources/baby-steps/plus.png");
		Pattern four = new Pattern(path + "/resources/baby-steps/4.png");
		Pattern equals = new Pattern(path + "/resources/baby-steps/equals.png");

		screen.click(one);
		screen.click(zero);
		screen.click(plus);
		screen.click(four);
		screen.click(equals);

		String result = catchResult();
		assertEquals(result, "14");
		
	}

	@AfterClass
	public static void afterClass() throws FindFailed {
		p.destroy();
	}

	private String catchResult() throws FindFailed {
		screen.type("a", KeyModifier.CTRL);
		screen.type("c", KeyModifier.CTRL);
		String result = App.getClipboard();
		return result;
	}

}
