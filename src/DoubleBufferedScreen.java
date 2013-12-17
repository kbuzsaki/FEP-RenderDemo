
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The top level "screen" for FEP.
 * Would have a list of Screen Layers that would each write to the internal
 * buffers in order of layering.
 * Each layer would figure out how to write itself to the screen.
 * 
 * Currently, just has a list of animations that it renders at the origin on
 * itself.
 * @author Kyle
 */
public class DoubleBufferedScreen extends Component {
    
    // width of screen
    private final int width;
    // height of screen
    private final int height;
    
    // scale to zoom at
    private int scale;
    
    // the currently visible image
    private BufferedImage screen;
    // the double buffer
    private BufferedImage buffer;
    
    // list of all of the animations
    private final List<Animation> animations;
    
    public DoubleBufferedScreen(int width, int height) {
        this.width = width;
        this.height = height;
        this.scale = 1;
        
        screen = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        animations = new ArrayList<>();
    }
    
    // sets zoom scale
    public void setScale(int scale) {
        this.scale = scale;
    }
    
    // re-renders with the given tick
    public void render(int tick) {
        Graphics2D bufferGraphics = buffer.createGraphics();
        bufferGraphics.clearRect(0, 0, width, height);
        
        for(Animation a : animations) {
            bufferGraphics.drawRenderedImage(a.getFrame(tick), null);
        }
        
        bufferGraphics.dispose();
        
        // swap out the double buffer
        BufferedImage temp = screen;
        screen = buffer;
        buffer = temp;
        
        repaint();
    }

    // the preferred size of the screen is the dimensions of its buffers
    // (used for frame.pack())
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width * scale, height * scale);
    }
    
    // paints the current screen at the desired scale
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(scale, scale);
        g2d.drawImage(screen, 0, 0, null);
    }
    
    // hook into the animations so that Main.java can add and remove animations
    // as desired
    public List<Animation> getAnimList() {
        return animations;
    }
    
}
