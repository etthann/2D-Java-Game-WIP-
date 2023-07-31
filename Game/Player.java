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
    public int gravity;
    public boolean collisionLeft;
    public boolean collisionRight;
    private boolean isJumping = false;
    public int dx = 0;

    public Player(GamePanel gp, KeyHandler keyH, MouseHandler mouseH, World world) {
        this.gp = gp;
        this.keyH = keyH;
        this.direction = "";
        this.mouseH = mouseH;
        this.world = world;
        gravity = world.scale * 2;
        setDefaultValues();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 7;
        currentFrame = 0;
    }

    public void ground() {
        if (world.getTileBeneath(x, y, 1) != 2) {
            y += world.scale;
        } else {
            gravity = world.scale * 2;
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
        if (world.getTilePerson(x, y, 1) != 3 && world.getTilePerson(x, y, 2) != 3) {
            collisionLeft = true;
            collisionRight = true;
        }
    }

    public void update() {
        ground();

        boolean canJump = !isJumping && world.getTileBeneath(x, y, 1) != 3;

        if (canJump && keyH.jumpPressed && world.getTilePerson(x, y, 2) == 3 && world.getTileBeneath(x, y, 1) != 3
                && world.getTileBeneath(x, y, 2) != 3 && world.getTilePerson(x, y, 1) == 3) {
            isJumping = true;
            y -= world.scale * 2;
        }

        if (isJumping) {
            y -= gravity;
    
            if (world.getTileBeneath(x, y, 1) == 3) {
                y += world.scale;
                isJumping = false;
            }
            // Adjust the collision for jumping to the right
            if (keyH.jumpPressed && keyH.rightPressed && world.getTileRight(x, y - gravity, 1) == 3) {
                isJumping = false;
            }
        }

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

        if (keyH.jumpPressed) {
            direction = "jump";
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
                } else {
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

            int offsetY = world.scale - world.scale / 2 - world.scale / 4;
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
