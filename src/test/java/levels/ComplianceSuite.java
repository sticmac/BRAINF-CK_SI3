package test.levels;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import java.util.List;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.rules.ExternalResource;
import org.junit.rules.TemporaryFolder;

import test.levels.one.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ZeroTest.class,
	OneTest.class,
	TwoTest.class,
	ThreeTest.class,
	FourTest.class,
	FiveTest.class,
	SixTest.class,
	SevenTest.class,
	EightTest.class,
	TenTest.class,
	ElevenTest.class,
})

public class ComplianceSuite {
	static SecurityManager securityManager;
	@ClassRule
	public static TemporaryFolder testFolder = new TemporaryFolder();;
	public static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	public static ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	public static int exitCode = 0;

	@BeforeClass
	public static void setUp() {
		securityManager = System.getSecurityManager();
		System.setSecurityManager(new SecurityManager() {
			@Override
			public void checkExit(int status) {
				super.checkExit(status); // This is IMPORTANT!
				exitCode = status;
				throw new SecurityException();
			}
		});
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@AfterClass
	public static void tearDown() {
		System.setSecurityManager(securityManager);

		System.setOut(null);
		System.setErr(null);
	}

	public static void reset() {
		outContent.reset();
		errContent.reset();
		exitCode = 0;
	}

	public static String createTmpFile(String name) throws IOException {
		return testFolder.newFile(name).getPath();
	}

	public static void writeToFile(String filename, String content) throws IOException, FileNotFoundException {
		Path filepath = Paths.get(filename);
		try (BufferedWriter writer = Files.newBufferedWriter(filepath)) {
			writer.write(content);
		}
	}

	public static void writeImage(String filename, int colors[], int nbCol) throws IOException {
		final int SIZE_SQUARE = 3;
		int nbLine = (int) Math.ceil((float)colors.length / nbCol);

		BufferedImage image = new BufferedImage(SIZE_SQUARE*nbCol, SIZE_SQUARE*nbLine, BufferedImage. TYPE_INT_RGB);
		Graphics graph = image.createGraphics();

		for (int i = 0; i < colors.length; i++) {
			graph.setColor(new Color(colors[i]));
			//Add a rectangle to the graphic. The first two arguments are the coordinates.
			//The others are the size of the rectangle.
			graph.fillRect(SIZE_SQUARE*(i%nbCol), SIZE_SQUARE*(i/nbCol), SIZE_SQUARE, SIZE_SQUARE);
		}

		ImageIO.write(image, "bmp", new File(filename));
	}

	public static int[] readStdOutImage() throws IOException {
		byte[] out = outContent.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(out);

		BufferedImage img = ImageIO.read(bais);

		int height = img.getHeight();
		int width = img.getWidth();

		int nbLine = (int)Math.ceil((float)height/3);
		int nbCol = (int)Math.ceil((float)width/3);

		int[] colors = new int[nbLine*nbCol];

		int i = 0;
		for (int y = 0 ; y < height ; y+=3) {
			for (int x = 0 ; x < width ; x+=3) {
				colors[i] = img.getRGB(x, y);
				i++;
			}
		}

		return colors;
	}

}
