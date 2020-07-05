package unitconverter;

public class Launcher {

    public static void main(String[] args) {

	Model m = new Model();
	View v = new View();
	Controller c = new Controller();
	
	m.initialise(0.0, "m");
	v.initialise(m, c);
	c.initialise(m, v);
    }
}
