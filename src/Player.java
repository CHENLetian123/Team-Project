import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Player {
    private int x, y;
    private int speed = 10; // 控制角色移动的速度
    private final int initialX; // 初始 X 坐标
    private final int initialY; // 初始 Y 坐标
    private Image playerImage;
    private int imageWidth = 100; // 新图像的宽度
    private int imageHeight = 100; // 新图像的高度

    //public Player(int x, int y) {
    public Player(int x, int y) {
        // 加载玩家图像
        ImageIcon ii = new ImageIcon(getClass().getResource("player.png"));
        playerImage = ii.getImage();

        // 调整图像大小
        playerImage = playerImage.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);


        this.x = x;
        this.y = y;
        this.initialX = x; // 记录初始位置
        this.initialY = y; // 记录初始位置
    }

    public void moveUp() {
        y -= speed;
    }

    public void moveDown() {
        y += speed;
    }

    public void moveLeft() {
        x -= speed;
    }

    public void moveRight() {
        x += speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // 用于绘制角色的方法
    /*public void draw(Graphics g) {
        g.fillRect(x, y, 20, 20); // 画一个小方块作为角色
    }*/
    public void draw(Graphics g) {
        g.drawImage(playerImage, x, y, null); // 使用图像绘制玩家
        // 注意：您可能需要调整 x, y 的值和图像的大小
    }
    // 新增重置位置的方法
    public void resetPosition() {
        x = initialX; // 重置到初始 X 坐标
        y = initialY; // 重置到初始 Y 坐标
    }
}
