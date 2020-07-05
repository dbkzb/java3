package mosaic;

public class Launcher {
    
    public static void main(String[] args) {

        Model m = new Model();
        MainFrame f = new MainFrame();
        Controller c = new Controller();

        f.model = m;
        f.controller = c;

        c.model = m;
        c.view = f;

        m.initialise();
        f.initialise();
    }
}
