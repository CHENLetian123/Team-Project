import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 创建并显示游戏窗口
            GameWindow window = new GameWindow();
            window.setVisible(true);
        });
    }
}
