import javax.swing.SwingUtilities;

public class Main {
    /*public static void main(String[] args) {
        System.out.println("Hello world!");
    }*/
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EnemyManager(); // Create and display the EnemyManager interface on the EDT / 在 EDT 上创建并显示 EnemyManager 界面
            }
        });
    }
}


