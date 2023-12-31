import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EnemyManager extends JFrame {
    // Define interface components / 定义界面组件
    // Define text fields for input / 定义文本字段用于输入
    private JTextField nameField; // Field for inputting the name / 用于输入名称的字段
    private JComboBox<String> imageSelector; // Image selector component / 图片选择器组件
    private JTextField speedField; // Field for inputting the speed / 用于输入速度的字段
    private JButton startGameButton; // Declaration of the start game button / 开始游戏按钮的声明


    // Define buttons for various actions / 定义按钮进行各种操作
    private JButton addButton; // Button to add a new entry / 用于添加新条目的按钮
    private JButton editButton; // Button to edit an existing entry / 用于编辑现有条目的按钮
    private JButton deleteButton; // Button to delete an entry / 用于删除条目的按钮
    private JButton saveButton; // Button to save changes / 用于保存更改的按钮

    // Define a list to display enemy types / 定义列表显示敌人类型
    private JList<EnemyType> enemyList; // List for displaying types of enemies / 用于展示敌人类型的列表
    private DefaultListModel<EnemyType> enemyListModel; //new

    public List<EnemyType> getEnemyTypes() {
        // Get a list of all enemy types / 获取所有敌人类型的列表
        List<EnemyType> enemyTypes = new ArrayList<>();
        for (int i = 0; i < enemyListModel.getSize(); i++) {
            enemyTypes.add(enemyListModel.getElementAt(i));
        }
        return enemyTypes;
    }

    public EnemyManager() {
        setTitle("Enemy Type Manager"); // Set window title / 设置窗口标题
        setSize(400, 647); // Set window size / 设置窗口大小
        setLayout(new BorderLayout()); // Set layout manager / 设置布局管理器
        setLocationRelativeTo(null); // Set the window to be displayed in the center / 设置窗口居中显示
        enemyListModel = new DefaultListModel<>();//new
        // Create and add form / 创建并添加表单
        createForm();

        // Create and add buttons / 创建并添加按钮
        createButtons();

        // Create and add enemy type list / 创建并添加敌人种类列表
        createEnemyList();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Set default close operation / 设置默认关闭操作
        setVisible(true); // Set window visible / 设置窗口可见

        loadEnemyTypesFromCSV();


        // Additional action listeners for buttons / 为按钮添加额外的动作监听器
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEnemyType(); // Call method to add a new enemy type / 调用方法以添加新的敌人类型
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveEnemyTypesToCSV(); // Call method to save enemy types to CSV / 调用方法将敌人类型保存到CSV文件
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            // Get the index of the selected item in the list / 获取列表中选中项的索引
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIdx = enemyList.getSelectedIndex();
                if (selectedIdx != -1) { // Check if an item is selected / 检查是否有选中的项
                    enemyListModel.remove(selectedIdx); // Remove the selected item from the model / 从模型中移除选中的项
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            // Get the index of the selected item in the list / 获取列表中选中项的索引
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIdx = enemyList.getSelectedIndex();
                if (selectedIdx != -1) { // Check if an item is selected / 检查是否有选中的项
                    EnemyType selectedEnemy = enemyListModel.get(selectedIdx); // Get the selected enemy type / 获取选中的敌人类型
                    // Set fields to the values of the selected enemy / 将字段设置为选中敌人的值
                    nameField.setText(selectedEnemy.getName());
                    imageSelector.setSelectedItem(selectedEnemy.getImage());
                    speedField.setText(String.valueOf(selectedEnemy.getSpeed()));
                    enemyListModel.remove(selectedIdx); // Temporarily remove the enemy for updating / 暂时移除敌人以便更新
                }
            }
        });


    }

    private void createForm() {
        JPanel formPanel = new JPanel(new GridLayout(3, 2)); // Use grid layout / 使用网格布局
        nameField = new JTextField(); // Name field / 名称字段
        speedField = new JTextField(); // Speed field / 速度字段

        // Image selector / 图片选择器
        List<String> images = new ArrayList<>(); // Create a list to store image file names / 创建存储图片文件名的列表
        images.add("fireball.png"); // Replace with actual image file name / 替换为实际的图片文件名
        images.add("waterball.png"); //No "resources/"!!!!!  just xxx.png
        images.add("magicball.png");
        images.add("iceball.png");
        images.add("snowball.png"); // More more more !!!
        //Add more images... / 添加更多图片……

        // Initialize image selector and populate data / 初始化图片选择器并填充数据
        imageSelector = new JComboBox<>(images.toArray(new String[0]));

        formPanel.add(new JLabel("Name:")); // Add label / 添加标签
        formPanel.add(nameField); // Add name input field / 添加名称输入框
        formPanel.add(new JLabel("Image:")); // Add image label to form / 添加图片标签到表单
        formPanel.add(imageSelector); // Add image selector to form / 将图片选择器添加到表单
        formPanel.add(new JLabel("Speed:")); // Add label / 添加标签
        formPanel.add(speedField); // Add speed input field / 添加速度输入框

        add(formPanel, BorderLayout.NORTH); // Add form panel to window / 将表单面板添加到窗口
    }

    private void createButtons() {
        JPanel buttonPanel = new JPanel(); // Create button panel / 创建按钮面板
        addButton = new JButton("Add"); // Add button / 添加按钮
        editButton = new JButton("Edit"); // Edit button / 编辑按钮
        deleteButton = new JButton("Delete"); // Delete button / 删除按钮
        saveButton = new JButton("Save"); // Save button / 保存按钮

        buttonPanel.add(addButton); // Add "Add" button to panel / 将添加按钮添加到面板
        buttonPanel.add(editButton); // Add "Edit" button to panel / 将编辑按钮添加到面板
        buttonPanel.add(deleteButton); // Add "Delete" button to panel / 将删除按钮添加到面板
        buttonPanel.add(saveButton); // Add "Save" button to panel / 将保存按钮添加到面板

        add(buttonPanel, BorderLayout.SOUTH); // Add button panel to window / 将按钮面板添加到窗口

        startGameButton = new JButton("Start Game"); // Initialize the start game button / 初始化开始游戏按钮
        buttonPanel.add(startGameButton); // Add the button to the panel / 将按钮添加到面板

        // Add action listener to the button / 添加按钮的动作监听器
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(); // Call the start game method / 调用开始游戏方法
            }
        });

        add(buttonPanel, BorderLayout.SOUTH); // Add the button panel to the window / 将按钮面板添加到窗口
    }

    private void startGame() {
        System.out.println("Starting game..."); // Output starting game message / 输出开始游戏消息
        List<EnemyType> enemyTypes = getEnemyTypes();
        // Launch the game window / 启动游戏窗口
        new GameWindow(enemyTypes); // Assume GameWindow is your game window class / 假设 GameWindow 是您的游戏窗口类
        this.dispose();// Close the enemy manager window / 关闭敌人管理窗口
    }

    private void createEnemyList() {
        enemyList = new JList<>(enemyListModel); // Create enemy type list / 创建敌人种类列表
        add(new JScrollPane(enemyList), BorderLayout.CENTER); // Add scrollable list to window / 添加带滚动条的列表到窗口
    }


    private void addEnemyType() {
        String name = nameField.getText();
        String image = (String) imageSelector.getSelectedItem();
        int speed;
        try {
            speed = Integer.parseInt(speedField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Speed must be an integer", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        EnemyType newEnemy = new EnemyType(name, image, speed);
        enemyListModel.addElement(newEnemy); // Add new enemy to the list model / 将新敌人添加到列表模型中
    }

    private void loadEnemyTypesFromCSV() {
        String csvFile = "data/enemies.csv"; // Path to the CSV file / CSV文件路径
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                // Update the split regex to handle complex CSV structures / 更新分割字符串的正则表达式以处理复杂的CSV结构
                String[] enemyData = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (enemyData.length >= 3) {
                    // Handle fields enclosed in quotes / 处理引号封装的字段
                    String name = unformatCsvField(enemyData[0]); // Extract name / 提取名称
                    String image = unformatCsvField(enemyData[1]); // Extract image / 提取图片
                    int speed = Integer.parseInt(unformatCsvField(enemyData[2])); // Extract and parse speed / 提取并解析速度

                    EnemyType enemy = new EnemyType(name, image, speed); // Create new EnemyType / 创建新的EnemyType
                    enemyListModel.addElement(enemy); // Add enemy to the list model / 将敌人添加到列表模型中
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String formatCsvField(String data) {
        // Format the field for CSV output, handling quotes / 格式化CSV输出的字段，处理双引号
        return "\"" + data.replace("\"", "\"\"") + "\"";
    }

    private String unformatCsvField(String data) {
        // Unformat the field from CSV input, handling quotes / 从CSV输入中反格式化字段，处理双引号
        if (data.startsWith("\"") && data.endsWith("\"")) {
            data = data.substring(1, data.length() - 1); // Remove the leading and trailing quotes / 移除首尾双引号
        }
        return data.replace("\"\"", "\""); // Convert double quotes to single quotes / 将两个双引号转换为一个双引号
    }

    private void saveEnemyTypesToCSV() {
        // Method to save enemy types to a CSV file / 将敌人类型保存到CSV文件的方法
        String csvFile = "data/enemies.csv"; // CSV file path / CSV文件路径

        try (FileWriter writer = new FileWriter(csvFile)) {
            for (int i = 0; i < enemyListModel.getSize(); i++) {
                EnemyType enemy = enemyListModel.getElementAt(i); // Get each enemy type / 获取每种敌人类型
                // Format each field and concatenate for CSV / 格式化每个字段并连接成CSV
                String line = formatCsvField(enemy.getName()) + ","
                        + formatCsvField(enemy.getImage()) + ","
                        + enemy.getSpeed();
                writer.write(line + "\n"); // Write the formatted line to the file / 将格式化的行写入文件
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new EnemyManager(); // Create and display enemy type manager interface / 创建并显示敌人种类管理界面
    }
}
