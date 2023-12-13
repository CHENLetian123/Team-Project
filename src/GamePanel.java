import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;


public class GamePanel extends JPanel {
    private Player player;
    private List<Enemy> enemies;
    private Timer timer; // 用于定时生成敌人
    private Timer gameUpdateTimer; // 用于定时更新游戏状态
    private int score = 0; // 记录玩家得分
    private boolean gameOver = false; // 游戏结束标志
    /*private int difficultyTimer = 0; // 游戏难度计时器*/
    private int nextDifficultyThreshold = 10; // 下一次难度提升的分数阈值

    public GamePanel() {
        // 初始化玩家和敌人列表
        player = new Player(100, 100);
        enemies = new ArrayList<>();

        // 定时生成敌人
        timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enemies.add(new Enemy());
            }
        });
        timer.start();

        // 设置面板可以获得焦点以响应键盘事件
        setFocusable(true);

        // 添加键盘监听器以控制玩家
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (gameOver) {
                    return;
                }

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        player.moveUp();
                        break;
                    case KeyEvent.VK_DOWN:
                        player.moveDown();
                        break;
                    case KeyEvent.VK_LEFT:
                        player.moveLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        player.moveRight();
                        break;
                }
                repaint();
            }
        });

        // 初始化游戏更新定时器
        gameUpdateTimer = new Timer(50, e -> updateGame());
    }

    public void startGame() {
        player = new Player(100, 100); // 重置玩家位置
        enemies.clear(); // 清除所有敌人
        score = 0; // 重置分数
        gameOver = false; // 重置游戏结束标志
        //difficultyTimer = 0;
        nextDifficultyThreshold = 10; // 重置难度提升的分数阈值

        // 启动游戏更新定时器
        gameUpdateTimer.start();
        requestFocusInWindow(); // 确保 GamePanel 获得焦点
    }

    public void pauseGame() {
        gameUpdateTimer.stop(); // 暂停游戏更新定时器
    }

    public void resumeGame() {
        if (!gameOver) {
            gameUpdateTimer.start(); // 重新启动游戏更新定时器
        }
    }

    public void restartGame() {
        // 重置游戏状态
        player = new Player(100, 100); // 重置玩家位置
        enemies.clear(); // 清除所有敌人
        score = 0; // 重置分数
        gameOver = false; // 重置游戏结束标志
        //difficultyTimer = 0; // 重置难度计时器
        nextDifficultyThreshold = 10; // 重置难度提升的分数阈值

        // 重新启动游戏更新定时器
        gameUpdateTimer.start();
        // 重新启动用于生成敌人的定时器
        timer.start();
        // 确保 GamePanel 获得焦点
        requestFocusInWindow();
    }

    private void updateGame() {
        // 移动所有敌人
        for (Enemy enemy : enemies) {
            enemy.move();
        }
        // 检查是否有碰撞发生
        checkCollisions();
        // 更新分数
        updateScore();
        // 重绘界面
        repaint();

        /*// 每过一段时间增加游戏难度
        difficultyTimer++;
        if (difficultyTimer % 600 == 0) {
            for (Enemy enemy : enemies) {
                enemy.increaseSpeed();
            }
        }*/
    }

    private void checkCollisions() {
        Rectangle playerBounds = new Rectangle(player.getX(), player.getY(), 20, 20);
        for (Enemy enemy : enemies) {
            Rectangle enemyBounds = new Rectangle(enemy.getX(), enemy.getY(), 20, 20);
            if (playerBounds.intersects(enemyBounds)) {
                gameOver(); // 处理游戏结束
            }
        }
    }

    private void gameOver() {
        gameUpdateTimer.stop(); // 停止游戏更新
        timer.stop(); // 停止生成新的敌人
        gameOver = true; // 设置游戏结束标志
    }

    // 更新分数的方法
    private void updateScore() {
        for (Enemy enemy : enemies) {
            if (enemy.getY() > 600) { // 当敌人离开屏幕底部
                score++; // 增加分数
                enemy.resetPosition(); // 重置敌人位置
                if (score >= nextDifficultyThreshold) {
                    increaseDifficulty(); // 增加难度
                    nextDifficultyThreshold += 10; // 更新下一次难度提升的分数阈值
                }
            }
        }
    }

    private void increaseDifficulty() {
        for (Enemy enemy : enemies) {
            enemy.increaseSpeed(); // 增加每个敌人的速度
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gameOver) {
            g.drawString("Game Over! 你的分数： " + score, 350, 300); // 游戏结束时显示得分
            return;
        }
        // 显示难度等级
        int difficultyLevel = (nextDifficultyThreshold - 10) / 10; // 计算当前难度等级
        g.drawString("难度等级： " + difficultyLevel, 10, 35); // 显示难度等级
/*
        // 显示难度等级
        int difficultyLevel = difficultyTimer / 600; // 假设每600个时间单位增加1级难度
        g.drawString("Difficulty: " + difficultyLevel, 10, 35); // 显示难度等级
*/
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1); // 绘制边界框

        // 绘制玩家和敌人
        player.draw(g);
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }

        // 显示分数
        g.drawString("分数：" + score, 10, 20);
    }
}
