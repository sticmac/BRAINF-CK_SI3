package brainfuck;

import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * Reads an image and extract color code for each 3x3 square.
 * The parsed image MUST be in .bmp format.
 *
 * @author Julien Lemaire
 */
public class ReadImage {
	/**
	 * List of colors.
	 */
	private List<Integer> colors;

	/**
	 * Main constructor of the <code>ReadImage</code> class.
	 *
	 * @param filename	the filename of the image to decode.
	 * @throws IOException	if the given filename doesn't exist, or if reading failed.
	 */
	public ReadImage(String filename) throws IOException {
		BufferedImage img = ImageIO.read(new File(filename));
		colors = new ArrayList<>();

		int height = img.getHeight();
		int width = img.getWidth();

		for (int y = 0 ; y < height ; y+=3) {
			for (int x = 0 ; x < width ; x+=3) {
				colors.add(img.getRGB(x, y));
			}
		}
	}

	/**
	 * Returns the colors of the decoded image, as an <code>IntStream</code> containing color codes as ints in the ARGB format.
	 *
	 * @return an <code>IntStream</code> containing the integer value of each color in ARGB.
	 */
	public IntStream getData() {
		return colors.stream().mapToInt(Integer::intValue);
	}
}
