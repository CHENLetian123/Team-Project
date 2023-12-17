import javax.swing.*;
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
