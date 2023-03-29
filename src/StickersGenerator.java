import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickersGenerator {

  public void create(InputStream stream, String archiveName) throws Exception {
    // read image
    BufferedImage original = ImageIO.read(stream);
    // create in-memory image
    int width = original.getWidth();
    int height = original.getHeight();
    int newHeight = height + 400;

    BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

    // copy image with new size and edit
    Graphics2D graphics = (Graphics2D) newImage.getGraphics();
    graphics.drawImage(original, 0, 0, null);

    // configure typeface
    Font font = new Font(Font.SANS_SERIF, Font.BOLD, 200);
    graphics.setColor(Color.YELLOW);
    graphics.setFont(font);

    // write a frase in the bottom of image
    graphics.drawString("TOPZERA", 300, newHeight - 100);

    // write image on disc
    ImageIO.write(newImage, "png", new File("saida/" + archiveName));

  }
}
