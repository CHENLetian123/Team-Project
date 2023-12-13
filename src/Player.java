import java.awt.Graphics;

public class Player {
    private int x, y;
    private int speed = 5; // 控制角色移动的速度
    private final int initialX; // 初始 X 坐标
    private final int initialY; // 初始 Y 坐标

    public Player(int x, int y) {
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
    public void draw(Graphics g) {
        g.fillRect(x, y, 20, 20); // 画一个小方块作为角色
    }

    // 新增重置位置的方法
    public void resetPosition() {
        x = initialX; // 重置到初始 X 坐标
        y = initialY; // 重置到初始 Y 坐标
    }
}
