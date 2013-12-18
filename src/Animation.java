
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Simple encapsulation over a list of frames in an animation.
 * @author Kyle
 */
public class Animation {
    
    private ImmutableImage[] frames;
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
            
            frames = new ImmutableImage[length];
            for(int i = 0; i < length; i++) {
                BufferedImage frame = source.getSubimage(i * side, 0, side, side);
                frames[i] = ImmutableImage.copyOf(frame);
            }
        } catch(IOException ex) {}
    }
    
    // safe now!
    // frames are stored as ImmutableImages
    public ImmutableImage getFrame(int tick) {
        index = tick % length;
        return frames[index];
    }
    
}
