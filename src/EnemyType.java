import java.awt.*;

public class EnemyType {
    private Image image;
    private int speed;
    private int health;

    public EnemyType(Image image, int speed, int health) {
        this.image = image;
        this.speed = speed;
        this.health = health;
    }

    // Getter 和 Setter 方法
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
