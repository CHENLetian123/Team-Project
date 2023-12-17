import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Main class of the application
public class Main {
    // A static list to store different types of enemies
    private static List<EnemyType> enemyTypes = new ArrayList<>();

    // Main method - entry point of the application
    public static void main(String[] args) {
        // Ensures that the GUI creation is done in the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            // Create and display the game window
            GameWindow window = new GameWindow();
            window.setVisible(true);
        });
    }

    private static void createAndShowConfigUI() {
        // 创建配置界面的窗口 / Create the configuration interface window
        JFrame configFrame = new JFrame("Game Configuration");
        configFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗口关闭时的操作 / Set the operation when the window is closed
        configFrame.setLayout(new FlowLayout()); // 设置布局管理器为流式布局 / Set the layout manager to FlowLayout

        // 创建开始游戏的按钮 / Create the Start Game button
        JButton startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 关闭配置界面 / Close the configuration interface
                configFrame.dispose();

                // 创建并显示游戏窗口，传递敌人类型列表 / Create and display the game window, passing the list of enemy types
                GameWindow window = new GameWindow(enemyTypes);
                window.setVisible(true);
            }
        });

        // 将组件添加到配置界面 / Add components to the configuration interface
        configFrame.add(startGameButton);

        // 设置配置界面的大小并显示 / Set the size of the configuration interface and display it
        configFrame.pack(); // 调整窗口大小以适应组件 / Adjust window size to fit components
        configFrame.setLocationRelativeTo(null); // 设置窗口在屏幕中居中 / Set the window to be centered on the screen
        configFrame.setVisible(true); // 使窗口可见 / Make the window visible
    }


    // Method to create and add a new enemy type to the list
    public static void createEnemyType(EnemyType enemyType) {
        enemyTypes.add(enemyType);
    }

    // Method to read an enemy type from the list based on its index
    public static EnemyType readEnemyType(int index) {
        return enemyTypes.get(index);
    }

    // Method to update an existing enemy type in the list at a specific index
    public static void updateEnemyType(int index, EnemyType enemyType) {
        enemyTypes.set(index, enemyType);
    }

    // Method to delete an enemy type from the list based on its index
    public static void deleteEnemyType(int index) {
        enemyTypes.remove(index);
    }
}
