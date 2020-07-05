package mosaic;

import java.awt.*;
import java.util.*;

public class Model {

    private Color[] colours = null;
    public Color storedColour = null;

    public void initialise() {

        this.colours = new Color[49];

        mixAllColours();
    }

    public Color[] getColours() {
    
        return this.colours;
    }

    protected Color getRandomColour() {
    
        Random r = new Random();
        Color c = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)); 
        
        return c;
    }

    public void mixOneColour(int i) {
    
        this.colours[i] = getRandomColour();
    }

    public void mixAllColours() {
    
        for (int i = 0; i < 49; i++) {
        
            mixOneColour(i);
        }
    }

    public void resetAllColours() {
    
        for (int i = 0; i < 49; i++) {
        
            this.colours[i] = Color.white;
        }
    }

    public void copyColour(int d, Color c) {

        this.colours[d] = c;
    }
}
