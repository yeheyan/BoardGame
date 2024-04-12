/**
 * Yehe Yan
 * 2024.4.10
 * EvoAI - A Game of Evolution
 */

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {  //invokeLater(Runnable doRun) Causes doRun.run() to be executed asynchronously on the AWT event dispatching thread.
            GameFrame view = new GameFrame();
            Controller controller = new Controller(view);
            controller.enterGame(); // Start game
        });
    }
}