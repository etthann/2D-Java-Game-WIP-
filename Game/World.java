import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import PerlinNoise.FastNoiseLite;
import PerlinNoise.FastNoiseLite.FractalType;

public class World {
    GamePanel gp;
    KeyHandler keyH;
    MouseHandler mouseH;
    Player player;
    Tile tile[] = new Tile[10];
    public int scale = 30;
    public int[][] worldMap;
    public float noiseValue;

    public World(GamePanel gp, KeyHandler keyH, MouseHandler mouseH, Player player) throws IOException {
        this.gp = gp;
        this.keyH = keyH;
        this.mouseH = mouseH;
        this.player = player;
        worldMap = new int[(int) gp.screenSize.getWidth() / scale][(int) gp.screenSize.getHeight() / scale];
        getTile();
        generateWorld();
    }

    public void getTile() throws IOException {
        for (int i = 0; i < tile.length; i++) {
            tile[i] = new Tile();
        }
        tile[0].image = ImageIO.read(getClass().getResourceAsStream("Photos\\World\\Floor\\Stone.PNG"));
        tile[1].image = ImageIO.read(getClass().getResourceAsStream("Photos\\World\\Floor\\Dirt.PNG"));
        tile[2].image = ImageIO.read(getClass().getResourceAsStream("Photos\\World\\Floor\\Grass.PNG"));
        tile[3].image = ImageIO.read(getClass().getResourceAsStream("Photos\\World\\Sky\\sky.jpg"));
        tile[4].image = ImageIO.read(getClass().getResourceAsStream("Photos\\World\\Floor\\download.png"));
    }

    public void generateWorld() {
        FastNoiseLite pNoise = new FastNoiseLite(generateSeed());
        pNoise.SetNoiseType(FastNoiseLite.NoiseType.Perlin);
        pNoise.SetFrequency(0.05f);
        pNoise.SetFractalType(FractalType.FBm);
        pNoise.SetFractalOctaves(5);

        for (int r = 0; r < worldMap.length; r++) {
            float noiseValue = (pNoise.GetNoise(r, 0) + 1) * 0.5f;
            int terrainHeight = (int) (noiseValue * worldMap[r].length + 1);

            for (int c = 0; c < worldMap[r].length; c++) {
                if (c == terrainHeight - 1) {
                    worldMap[r][c] = 2;
                } else if (c >= terrainHeight && c < terrainHeight + 5) {
                    worldMap[r][c] = 1;
                } else if (c >= terrainHeight + 5) {
                    worldMap[r][c] = 0;
                } else {
                    worldMap[r][c] = 3;
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
        for (int r = 0; r < worldMap.length; r++) {
            for (int c = 0; c < worldMap[r].length; c++) {
                g2.drawImage(tile[worldMap[r][c]].image, r * scale, c * scale, scale, scale, null);
            }
        }
    }

    public int generateSeed() {
        Random random = new Random();
        int seed = random.nextInt(1001);
        return seed;
    }

    public int getTileBeneath(int x, int y) {
        int tileX = (x + scale) / scale;
        int tileY = (y +scale*3) / scale;
    
        if (tileX >= 0 && tileY >= 0 && tileX < worldMap.length && tileY < worldMap[0].length) {
            return worldMap[tileX][tileY];
        }
        return 0;
    }

    public int getTileLeft(int x, int y, int num) {
        int tileX = (x +scale*2/3) / scale;
        int tileY = (y +scale) / scale;
        int tileY2 = (y +scale*2) / scale;
        if (tileX >= 0 && tileY >= 0 && tileX < worldMap.length && tileY < worldMap[0].length) {
            if (num == 1) 
                return worldMap[tileX][tileY];
            if (num == 2)
                return worldMap[tileX][tileY2];
        }
        return 0;
    }

    public int getTileRight(int x, int y, int num) {
        int tileX = (x + scale*2 - scale/4) / scale;
        int tileY = (y +scale) / scale;
        int tileY2 = (y +scale*2) / scale;
        if (tileX >= 0 && tileY >= 0 && tileX < worldMap.length && tileY < worldMap[0].length) {
            if (num == 1) 
                return worldMap[tileX][tileY];
            if (num == 2)
                return worldMap[tileX][tileY2];
        }
        return 0;
    }

    public int getTileUp(int x, int y) {
        int tileX = (x + scale) / scale;
        int tileY = (y +scale) / scale;
        if (tileX >= 0 && tileY >= 0 && tileX < worldMap.length && tileY < worldMap[0].length) {
            return worldMap[tileX][tileY];
        }
        return 0;
    }

    public int getTilePerson(int x,int y,int num) {
        int tileX = (x + scale) / scale;
        int tileY = (y + scale) / scale;
        int tileY2 = (y + scale*2) / scale;
        if (tileX >= 0 && tileY >= 0 && tileX < worldMap.length && tileY < worldMap[0].length) {
            if (num == 1) 
                return worldMap[tileX][tileY];
            else
                return worldMap[tileX][tileY2];
        }
        return 0;
    }

}