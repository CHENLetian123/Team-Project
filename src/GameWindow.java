import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.util.List;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameWindow extends JFrame {

    private List<EnemyType> enemyTypes;
    private GamePanel gamePanel; // 游戏面板
    private boolean isGameStarted = false; // 游戏是否已开始的标志

    public GameWindow(List<EnemyType> enemyTypes) {
        // Constructor: Initialize the game window with enemy types / 构造函数：用敌人类型初始化游戏窗口
        this.enemyTypes = enemyTypes;

        System.out.println("GameWindow is being initialized..."); // Output initialization message / 输出初始化消息
        // 设置窗口标题
        setTitle("Sorcerers Siege");

        // 设置窗口关闭操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置窗口的大小
        setSize(1024, 1024);

        // 初始化游戏面板并添加到窗口中
        gamePanel = new GamePanel(enemyTypes);
        add(gamePanel);

        // 显示开始菜单
        showStartMenu();

        // 设置窗口居中显示
        setLocationRelativeTo(null);

        initializeGame(); // Initialize the game / 初始化游戏
    }

    private void initializeGame() {
        // Initialize the game panel with enemy types / 使用敌人类型初始化游戏面板

        /*System.out.println("GameWindow is being initialized...");

        setTitle("Sorcerers Siege");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);*/

        gamePanel = new GamePanel(enemyTypes); // 将敌人种类数据传递给游戏面板
        add(gamePanel);

        /*showStartMenu();
        setLocationRelativeTo(null);*/
    }


    private void showStartMenu() {
        // Create start, pause, resume, and restart buttons / 创建开始、暂停、继续和重启按钮
        JButton startButton = new JButton("Start Game 开始游戏");
        startButton.addActionListener(e -> {
            isGameStarted = true;
            startButton.setVisible(false); // 隐藏按钮
            gamePanel.startGame(); // 调用 GamePanel 中的方法开始游戏
        });

        // 创建暂停和重启按钮
        JButton pauseButton = new JButton("Pause Game 暂停");
        pauseButton.addActionListener(e -> gamePanel.pauseGame());

        JButton resumeButton = new JButton("Resume Game 继续");
        resumeButton.addActionListener(e -> gamePanel.resumeGame());

        JButton restartButton = new JButton("Restart Game 重新开始");
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

        setVisible(true); // very very very important stuff!!!
        // Make the window visible / 使窗口可见
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                List<EnemyType> enemies = readEnemiesFromFile("data/enemies.csv");
                GameWindow window = new GameWindow(enemies);
                window.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private static List<EnemyType> readEnemiesFromFile(String filePath) throws IOException {
        // Read enemy types from a file / 从文件中读取敌人类型
        List<EnemyType> enemies = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // 跳过标题行
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String name = values[0];
                String image = values[1];
                int speed = Integer.parseInt(values[2]);
                enemies.add(new EnemyType(name, image, speed));
            }
        }
        return enemies;
    }
}