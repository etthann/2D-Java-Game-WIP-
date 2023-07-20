import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable  {
    private Thread gameThread;
    private KeyHandler keyH = new KeyHandler();
    public Dimension screenSize;
    private int FPS = 20;
    Player player = new Player(this, keyH);
    TitleScreen ts = new TitleScreen(this);
    int screen = 0;


    public GamePanel(JFrame window) {
        GraphicsDevice device = window.getGraphicsConfiguration().getDevice();
        if (device.isFullScreenSupported()) {
            device.setFullScreenWindow(window);
            if (device.getFullScreenWindow() != null) {
                screenSize = device.getFullScreenWindow().getSize();
                this.setPreferredSize(screenSize);
            }
        } else {
            screenSize = new Dimension(1200, 1000);
            this.setPreferredSize(screenSize);
        }

        this.setDoubleBuffered(true);
        this.setBackground(Color.black);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }


    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double interval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/interval;
            lastTime = currentTime;
            if (delta >= 1 ) {
                player.update();   
                repaint();
                delta--;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //Add Graphics Below
        try {
            if (screen == 0) {
                ts.draw(g2);
            }
            if (screen == 1) {
                player.draw(g2);
            }
            
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        g2.dispose();
    }
}
