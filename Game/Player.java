import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entitiy {
    GamePanel gp;
    KeyHandler keyH;
    String direction;
    MouseHandler mouseH;
    World world;
    public boolean jump = true;
    public boolean gravity = true;
    public int grassTile = 0;
    public int[][] nearTiles = new int[3][3];
    public boolean collisionLeft;
    public boolean collisionRight;

    public Player(GamePanel gp, KeyHandler keyH, MouseHandler mouseH, World world) {
        this.gp = gp;
        this.keyH = keyH;
        this.direction = "";
        this.mouseH = mouseH;
        this.world = world;
        setDefaultValues();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 7;
        currentFrame = 0;
    }

    public void ground() {
        if (world.getTileBeneath(x, y) != 2) {
            y += world.scale;
        } else {
            y += 0;
        }

        if (world.getTileLeft(x, y, 1) != 3 || world.getTileLeft(x, y, 2) != 3) {
            collisionLeft = true;
        } else {
            collisionLeft = false;
        }

        if (world.getTileRight(x, y, 1) != 3 || world.getTileRight(x, y, 2) != 3) {
            collisionRight = true;
        } else {
            collisionRight = false;
        }
        if (world.getTileUp(x, y) != 3) {
            jump = false;
        } else {
            jump = true;
        }
        if (world.getTilePerson(x, y, 1) != 3 && world.getTilePerson(x, y, 2) != 3) {
            collisionLeft = true;
            collisionRight = true;
        }
    }

    public void update() {
        ground();

        if (keyH.leftPressed && !collisionLeft) {
            direction = "left";
            x -= speed;
        } else {
            direction = "left";
            x -= 0;
        }
        if (keyH.rightPressed && !collisionRight) {
            direction = "right";
            x += speed;
        } else {
            direction = "right";
            x += 0;
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

            // Adjust y-coordinate to appear on top of the grass
            int offsetY = world.scale - world.scale / 2 - world.scale / 4; // Set the desired offset (height of the
                                                                           // grass tiles)
            if (keyH.leftPressed) {
                g2.drawImage(currentImage, x + world.scale * 2 + world.scale / 2, y - offsetY,
                        world.scale * -3,
                        world.scale * 3, null);
            } else {
                g2.drawImage(currentImage, x - world.scale / 4, y - offsetY, world.scale * 3,
                        world.scale * 3, null);
            }
        }
    }
}
