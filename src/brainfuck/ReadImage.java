package brainfuck;

import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;

import brainfuck.virtualmachine.Machine;

public class ReadImage {
	private BufferedImage img;
	private List<Integer> instructions;

	public ReadImage(String url) throws IOException {
		img = ImageIO.read(new File(url));
		instructions = new ArrayList<>();

		int height = img.getHeight();
		int width = img.getWidth();

		for (int y = 0 ; y < height ; y+=3) {
			for (int x = 0 ; x < width ; x+=3) {
				instructions.add(img.getRGB(x, y));
			}
		}
	}

	public IntStream getInstructions() {
		return instructions.stream().mapToInt(Integer::intValue);
	}
}
