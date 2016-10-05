package brainfuck;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class ImageWriter {
	private int width;
	private int height;

	public ImageWriter(int[] colors) {
		this(colors, 9, 9);
	}

	public ImageWriter(int[] colors, int width, int height) {
		this.width = width;
		this.height = height;
		BufferedImage image  = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		draw(image, colors);
		writeBmp(image, "./test.bmp");
	}

	public void draw(BufferedImage image, int[] colors) {
		for (int y = 0; y < width; y+=3) {
			for (int i = 0; i < 3; i++) {
				drawLine(image, colors, y+i, i);
			}
		}
	}

	public void drawLine(BufferedImage image, int[] colors, int line, int j) {
		for (int x = 0; x < width; x+=3) {
			for (int i = 0; i < 3; i++) {
				if (colors[x/3+(line-j)] == null) {
					image.setRGB(x+i, line, 0x000000);
				}
				image.setRGB(x+i, line, colors[x/3+(line-j)]);
			}
		}
	}

	public void writeBmp(BufferedImage image, String path) {
		try {
			RenderedImage rendImage = image;
			ImageIO.write(rendImage, "bmp", new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
