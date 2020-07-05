package timedisplay;

public class Launcher {

    public static void main(String[] args) {

	View v = new View();
	Controller c = new Controller();

	v.initialise(c);
	c.initialise(v);
    }
}
