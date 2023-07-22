import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TitleScreen {
        GamePanel gp;
        MouseHandler mouseH;

        public TitleScreen(GamePanel gp, MouseHandler mouseH) {
                this.gp = gp;
                this.mouseH = mouseH;
        }

        public void draw(Graphics2D g2) throws IOException {
                if (mouseH.mouseClick) {
                        if (mouseH.clickX > (int) gp.screenSize.getWidth() / 2 - (int) gp.screenSize.getWidth() / 9
                                        + (int) gp.screenSize.getWidth() / 95
                                        && mouseH.clickX < (int) gp.screenSize.getWidth() / 2
                                                        - (int) gp.screenSize.getWidth() / 9
                                                        + (int) gp.screenSize.getWidth() / 95
                                                        + ((int) gp.screenSize.getWidth() / 5)
                                        && mouseH.clickY > (int) gp.screenSize.getHeight() / 2
                                                        - (int) gp.screenSize.getHeight() / 20
                                                        + (int) gp.screenSize.getHeight() / 80
                                        && mouseH.clickY < (int) gp.screenSize.getHeight() / 2
                                                        - (int) gp.screenSize.getHeight() / 20
                                                        + (int) gp.screenSize.getHeight() / 80
                                                        + (int) gp.screenSize.getHeight() / 5
                                        && gp.screenGame == 0) {
                                gp.screenGame += 1;
                        }
                        mouseH.mouseClick = false;
                }
                BufferedImage titleImage = ImageIO
                                .read(getClass().getResourceAsStream("Photos//Title//TitleBackground.jpg"));
                BufferedImage startGame = ImageIO
                                .read(getClass().getResourceAsStream("Photos//Title//startGame-removebg-preview.png"));
                BufferedImage startButton = ImageIO
                                .read(getClass().getResourceAsStream(
                                                "Photos//Title//StartButton-removebg-preview.png"));
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
                                (int) gp.screenSize.getHeight() / 2 - (int) gp.screenSize.getHeight() / 20
                                                + (int) gp.screenSize.getHeight() / 80,
                                ((int) gp.screenSize.getWidth() / 5), (int) gp.screenSize.getHeight() / 5, null);
        }
}
