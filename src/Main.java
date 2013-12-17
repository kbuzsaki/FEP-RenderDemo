
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("test");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final DoubleBufferedScreen screen = new DoubleBufferedScreen(200, 200);
        screen.setScale(2);
        // adds the first animation
        screen.getAnimList().add(new Animation(new File("resources/heal.png")));
        
        frame.add(screen);
        frame.pack();
        
        // timer to tick the animations
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int tick = 0;

            @Override
            public void run() {
                screen.render(tick);
                tick++;
            }
            
        }, 0, 100);
        
        // demonstrates that the main thread can be blocked / animations can be updated
        int count = 0;
        while(true) {
            // print to show the main thread loop
            System.out.println("main thread blocked by loop");
            
            // change the animations at some point
            if(count == 100) {
                screen.getAnimList().add(new Animation(new File("resources/arrowUp.png")));
            }
            else if(count == 250) {
                screen.getAnimList().remove(0);
            }
            count++;
            
            // having main thread sleep works
            try {
                Thread.sleep(10);
            } catch(InterruptedException ex) {}
        }
        
    }

}
