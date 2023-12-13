import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
public class GameWindow extends JFrame {
    private GamePanel gamePanel; // 游戏面板
    private boolean isGameStarted = false; // 游戏是否已开始的标志

    public GameWindow() {
        // 设置窗口标题
        setTitle("Sorcerers Siege");

        // 设置窗口关闭操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置窗口的大小
        setSize(800, 600);

        // 初始化游戏面板并添加到窗口中
        gamePanel = new GamePanel();
        add(gamePanel);

        // 显示开始菜单
        showStartMenu();

        // 设置窗口居中显示
        setLocationRelativeTo(null);
    }

    private void showStartMenu() {
        // 创建开始按钮
        JButton startButton = new JButton("开始游戏");
        startButton.addActionListener(e -> {
            isGameStarted = true;
            startButton.setVisible(false); // 隐藏按钮
            gamePanel.startGame(); // 调用 GamePanel 中的方法开始游戏
        });

        // 创建暂停和重启按钮
        JButton pauseButton = new JButton("暂停");
        pauseButton.addActionListener(e -> gamePanel.pauseGame());

        JButton resumeButton = new JButton("继续");
        resumeButton.addActionListener(e -> gamePanel.resumeGame());

        JButton restartButton = new JButton("重新开始");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.restartGame(); // 调用 GamePanel 中的重启游戏方法
            }
        });


        // 将按钮添加到窗口
        JPanel buttonPanel = new JPanel(); // 创建一个面板来放置按钮
        buttonPanel.add(startButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(resumeButton);
        buttonPanel.add(restartButton);
        this.add(buttonPanel, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameWindow window = new GameWindow();
            window.setVisible(true);
        });
    }
}
