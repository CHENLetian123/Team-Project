public class EnemyType {
    private String name;
    private String image;
    private int speed;

    // Constructor for EnemyType class / EnemyType 类的构造函数
    public EnemyType(String name, String image, int speed) {
        this.name = name; // Assign the name parameter to the name field / 将名称参数赋值给name字段
        this.image = image; // Assign the image parameter to the image field / 将图片参数赋值给image字段
        this.speed = speed; // Assign the speed parameter to the speed field / 将速度参数赋值给speed字段
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return name + " (" + image + ", " + speed + ")";
    }
}
