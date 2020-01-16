package pairscardgame.test.and.play.framework;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.sikuli.script.*;

import javassist.NotFoundException;

public class Wrapper extends Screen {

	Screen screen;

	public void click(Image image) throws FindFailed {
		screen.getScreen();
		this.delay();
		this.click(image.getPattern());
	}

	public void doubleClick(Image image) throws FindFailed {
		this.delay();
		this.doubleClick(image.getPattern());
	}

	public boolean exists(Image image) {
		return this.exists(image.getPattern()) != null ? true : false;
	}
	
	public Iterator<Match> findAll(Image image) throws FindFailed {
		return this.findAll(image.getPattern());
	}

	public Wrapper() {
		super();
		screen = new Screen().getScreen();
	}

	public void waitForImage(Image image, int timeout) throws Exception {

		Match state = null;

		while (true) {
			state = screen.exists(image.getPattern(), timeout);
			if (state == null) {
				if (timeout > 0) {
					Thread.sleep(1000);
					timeout--;
					continue;
				} else {
					throw new NotFoundException("Image" + image.getPattern().toString() + " not found");
				}
			} else {
				break;
			}
		}
	}

	public void waitAndClick(Image image, int timeout) throws Exception {

		Match state = null;

		while (true) {

			state = screen.exists(image.getPattern(), timeout);
			if (state == null) {

				if (timeout > 0) {
					this.delay();
					timeout--;
					continue;
				} else {
					throw new NotFoundException("Image" + image.getPattern().toString() + " not found");
				}
			} else {
				this.click(image.getPattern());
				break;
			}
		}
	}

	public void delay() {
		delay(1);
	}
	
	public void delay(double multi) {
		try {
			Thread.sleep((int) (multi * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void delay(int multi) {
		try {
			Thread.sleep(1000 * multi);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String takeScreenshot(String info) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String path = System.getProperty("user.dir") + "/results/" + timeStamp.substring(0, 10) + "/" + info + "_"
				+ timeStamp + "_.jpg";
		try {
			ImageIO.write(screen.capture().getImage(), "jpg", new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	/**
	 * From https://rosettacode.org/wiki/Percentage_difference_between_images#Java
	 * 
	 */
    public double getDifferencePercent(final BufferedImage img1, final BufferedImage img2) {
        int width = img1.getWidth();
        int height = img1.getHeight();
        int width2 = img2.getWidth();
        int height2 = img2.getHeight();
        if (width != width2 || height != height2) {
            throw new IllegalArgumentException(String.format("Images must have the same dimensions: (%d,%d) vs. (%d,%d)", width, height, width2, height2));
        }
 
        long diff = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                diff += pixelDiff(img1.getRGB(x, y), img2.getRGB(x, y));
            }
        }
        long maxDiff = 3L * 255 * width * height;
 
        return 100.0 * diff / maxDiff;
    }
    
    private static int pixelDiff(int rgb1, int rgb2) {
        int r1 = (rgb1 >> 16) & 0xff;
        int g1 = (rgb1 >>  8) & 0xff;
        int b1 =  rgb1        & 0xff;
        int r2 = (rgb2 >> 16) & 0xff;
        int g2 = (rgb2 >>  8) & 0xff;
        int b2 =  rgb2        & 0xff;
        return Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
    }

	
}
