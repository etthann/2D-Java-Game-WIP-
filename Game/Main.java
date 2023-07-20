import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("Zombie Game");
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
