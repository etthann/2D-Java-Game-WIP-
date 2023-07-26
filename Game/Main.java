import java.io.IOException;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame("Terraria Knockoff");
        GamePanel gamePanel = new GamePanel(window);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.requestFocusInWindow();
        gamePanel.startGameThread();
    }
}
    