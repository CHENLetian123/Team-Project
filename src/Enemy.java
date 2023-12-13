import java.awt.Graphics;
import java.util.Random;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Enemy {
    private Image fireballImage;
    private int x, y, speed;
    private int size = 20; // 敌人的大小
    private Random random = new Random();

    public Enemy() {
        // 加载火球图像
        ImageIcon ii = new ImageIcon("resources/fireball.png");
        fireballImage = ii.getImage();
        resetPosition();
    }

    public void move() {
        y += speed;
    }

    public void resetPosition() {
        y = -size; // 从屏幕顶部外开始
        x = random.nextInt(800 - size); // 随机位置
        speed = 2 + random.nextInt(3); // 随机速度
    }

    // 新增的增加速度的方法
    public void increaseSpeed() {
        speed += 1; // 增加敌人的速度
    }

    public void draw(Graphics g) {
        g.drawImage(fireballImage, x, y, size, size, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
