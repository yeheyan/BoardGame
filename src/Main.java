import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameFrame view = new GameFrame();
            Controller controller = new Controller(view);
            controller.enterGame(); // Start or enter game logic
        });
    }
}