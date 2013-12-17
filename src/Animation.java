
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Simple encapsulation over a list of frames in an animation.
 * @author Kyle
 */
public class Animation implements Paintable {
    
    private BufferedImage[] frames;
    private int side;    // the side length of the square animation
    private int length;  // number of frames in animation
    private int index;   // current frame index
    
    public Animation(File animFile) {
        // loads the sprite sheet and chops it up into frames
        try {
            BufferedImage source = ImageIO.read(animFile);
            side = source.getHeight();
            length = source.getWidth() / side;
            index = 0;
            
            frames = new BufferedImage[length];
            for(int i = 0; i < length; i++) {
                frames[i] = source.getSubimage(i * side, 0, side, side);
            }
        } catch(IOException ex) {}
    }
    
    // unsafe, RenderedImage can't be written to,
    // but return value can be cast back to a BufferedImage and written to
    public RenderedImage getFrame(int tick) {
        index = tick % length;
        return frames[index];
    }
    
    // preferred maybe? not sure how to communicate the position 
    // or the best way to store the index
    @Override
    public void paint(Graphics2D g2d) {
        g2d.drawImage(frames[index], 0, 0, null);
    }
}
