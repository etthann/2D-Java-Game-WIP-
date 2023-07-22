import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entitiy {
    GamePanel gp;
    KeyHandler keyH;
    String direction;
    MouseHandler mouseH;

    public Player(GamePanel gp, KeyHandler keyH, MouseHandler mouseH) {
        this.gp = gp;
        this.keyH = keyH;
        this.direction = "";
        this.mouseH = mouseH;
        setDefaultValues();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 7;
        currentFrame = 0;
    }

    public void update() {
        if (keyH.leftPressed) {
            direction = "left";
            x -= speed;
        }
        if (keyH.rightPressed) {
            direction = "right";
            x += speed;
        }
        if (keyH.jumpPressed) {
            direction = "jump";
            jump();
        }
        if (!keyH.upPressed && !keyH.downPressed && !keyH.leftPressed && !keyH.rightPressed && !keyH.jumpPressed) {
            direction = "idle";
        }  
    }

    public void draw(Graphics2D g2) throws IOException, InterruptedException {
        BufferedImage[] images = null;
        boolean noKeysPressed = !keyH.upPressed && !keyH.downPressed && !keyH.leftPressed && !keyH.rightPressed;

        switch (direction) {
            case "left":
                images = new BufferedImage[7];
                for (int i = 0; i < 7; i++) {
                    images[i] = ImageIO.read(getClass().getResourceAsStream("Photos/Player/Move/0" + i + "_Walk.png"));
                }
                break;
            case "right":
                images = new BufferedImage[7];
                for (int i = 0; i < 7; i++) {
                    images[i] = ImageIO.read(getClass().getResourceAsStream("Photos/Player/Move/0" + i + "_Walk.png"));
                }
                break;
            case "jump":
                if (keyH.jumpPressed && keyH.rightPressed) {
                    images = new BufferedImage[7];
                    for (int i = 0; i < 7; i++) {
                        images[i] = ImageIO
                                .read(getClass().getResourceAsStream("Photos/Player/Jump/0" + i + "_Jump.png"));
                    }
                } else if (keyH.jumpPressed && keyH.leftPressed) {
                    images = new BufferedImage[7];
                    for (int i = 0; i < 7; i++) {
                        images[i] = ImageIO
                                .read(getClass().getResourceAsStream("Photos/Player/Jump/0" + i + "_Jump.png"));
                    }
                }
                break;
            case "idle":
                if (noKeysPressed) {
                    images = new BufferedImage[5];
                    for (int i = 0; i < 5; i++) {
                        images[i] = ImageIO
                                .read(getClass().getResourceAsStream("Photos/Player/Idle/0" + i + "_Idle.png"));
                    }
                }
                break;
        }

        if (noKeysPressed && images == null) {
            images = new BufferedImage[5];
            for (int i = 0; i < 5; i++) {
                images[i] = ImageIO.read(getClass().getResourceAsStream("Photos/Player/Idle/0" + i + "_Idle.png"));
            }
        }

        if (images != null && images.length > 0) {
            currentFrame++;
            if (currentFrame >= images.length) {
                currentFrame = 0;
            }

            BufferedImage currentImage = images[currentFrame];
            if (keyH.leftPressed) {
                g2.drawImage(currentImage, x + ((int) ((gp.screenSize.getWidth()) / 9)), y,
                        (int) ((gp.screenSize.getWidth() * -1) / 9),
                        (int) (gp.screenSize.getHeight() / 7), null);
            } else {
                g2.drawImage(currentImage, x, y, (int) (gp.screenSize.getWidth() / 9),
                        (int) (gp.screenSize.getHeight() / 7), null);
            }
        }
    }

    public void jump() {

    }

}
