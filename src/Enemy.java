import java.awt.Graphics;
//import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Enemy {
    //private Image fireballImage;
    private Image enemyImage;
    private int x, y, speed;
    private int size = 50; // 敌人的大小
    private Random random = new Random();

    // 修改后的构造函数
    public Enemy(String imagePath, int speed) {
        // Constructor: Initialize the enemy with an image and speed / 构造函数：用图像和速度初始化敌人

        // 根据传入的 imagePath 加载相应的图像
        ImageIcon ii = new ImageIcon(getClass().getResource(imagePath));
        enemyImage = ii.getImage();
        this.speed = speed; // set speed / 设置速度
        resetPosition(); // Reset the enemy's position / 重置敌人的位置
    }

        /*URL imageUrl = getClass().getResource(imagePath);
        if (imageUrl == null) {
            throw new RuntimeException("资源文件未找到: " + imagePath);
        }
        ImageIcon ii = new ImageIcon(imageUrl);
        enemyImage = ii.getImage();
        this.speed = speed;
        resetPosition();
    }*/
    //Used to test what is the ture path of the images


    public void move() {    // Move the enemy based on its speed / 根据速度移动敌人
        y += speed;
    }

    public void resetPosition() {    // Reset the enemy's position off the top of the screen / 重置敌人的位置到屏幕顶部之外
        y = -size; // 从屏幕顶部外开始
        x = random.nextInt(1024 - size); // random position / 随机位置
        //speed = 2 + random.nextInt(3); // random speed / 随机速度
    }

    public void increaseSpeed() {    // Increase the enemy's speed / 增加敌人的速度
        speed += 1;
    }

    /*public void draw(Graphics g) {
        g.drawImage(fireballImage, x, y, size, size, null);
    }*/

    public void draw(Graphics g) {    // Draw the enemy on the screen / 在屏幕上绘制敌
        g.drawImage(enemyImage, x, y, size, size, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
