import controller.Controller2D;
import view.Window;

public class Main { //spustí aplikaci, vytvoří instanci *Window* a *Controller2D*
    public static void main(String[] args) {
        Window window = new Window(800, 600);
        new Controller2D(window.getPanel());
    }
}