import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class TitleScreen {
    GamePanel gp;

    public TitleScreen(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) throws IOException {
        BufferedImage titleImage = ImageIO.read(getClass().getResourceAsStream("Photos//Title//TitleBackground.jpg"));
        BufferedImage startGame = ImageIO
                .read(getClass().getResourceAsStream("Photos//Title//startGame-removebg-preview.png"));
        BufferedImage startButton = ImageIO
                .read(getClass().getResourceAsStream("Photos//Title//StartButton-removebg-preview.png"));

        JButton button = new JButton("Start");
        button.setBounds((int) gp.screenSize.getWidth() / 2 - (int) gp.screenSize.getWidth() / 9
                        + (int) gp.screenSize.getWidth() / 95,
                (int) gp.screenSize.getHeight() / 2 - (int) gp.screenSize.getHeight() / 20 + (int) gp.screenSize.getHeight()/80,
                ((int) gp.screenSize.getWidth() / 5), (int) gp.screenSize.getHeight() / 5);
        g2.drawImage(titleImage, 0, 0, (int) gp.screenSize.getWidth(), (int) gp.screenSize.getHeight(), null);
        g2.drawImage(startGame,
                (int) gp.screenSize.getWidth() / 3 - (int) gp.screenSize.getWidth() / 5
                        + (int) gp.screenSize.getWidth() / 95,
                (int) gp.screenSize.getHeight() / 3 - (int) gp.screenSize.getHeight() / 3,
                ((int) gp.screenSize.getWidth() / 2) + (int) gp.screenSize.getWidth() / 5,
                (int) gp.screenSize.getHeight() / 2 + (int) gp.screenSize.getHeight() / 5, null);
        g2.drawImage(startButton,
                (int) gp.screenSize.getWidth() / 2 - (int) gp.screenSize.getWidth() / 9
                        + (int) gp.screenSize.getWidth() / 95,
                (int) gp.screenSize.getHeight() / 2 - (int) gp.screenSize.getHeight() / 20 + (int) gp.screenSize.getHeight()/80,
                ((int) gp.screenSize.getWidth() / 5), (int) gp.screenSize.getHeight() / 5, null);
        gp.add(button);
    }
}
