package unitconverter;

import java.text.*;

public class Model {

    private double meter;
    private double inch;
    private double foot;
    private double yard;
    
    String unit;
    StringBuilder builder;

    static String format(double d) {

	DecimalFormat f = new DecimalFormat("0.0000");

	return f.format(d);
    }

    public void initialise(double n, String u) {

	setMeter(n);
	this.unit = u;
	this.builder = new StringBuilder();
    }

    public double getMeter() {

	return this.meter;
    }

    public void setMeter(double m) {

	this.meter = m;
	this.inch = 1. / 0.0254 * m;
	this.foot = 1. / 0.3048 * m;
	this.yard = 1. / 0.9144 * m;
    }

    public double getInch() {

	return this.inch;
    }

    public void setInch(double i) {

	this.meter = 0.0254 * i;
	this.inch = i;
	this.foot = 1. / 12. * i;
	this.yard = 1. / 36. * i;
    }

    public double getFoot() {

	return this.foot;
    }

    public void setFoot(double f) {

	this.meter = 0.3048 * f;
	this.inch = 12 * f;
	this.foot = f;
	this.yard = 1. / 3. * f;
    }

    public double getYard() {

	return this.yard;
    }

    public void setYard(double y) {

	this.meter = 0.9144 * y;
	this.inch = 36 * y;
	this.foot = 3 * y;
	this.yard = y;
    }

    public void calculate(double n, String u) {

	if (u.equals("m")) {

	    setMeter(n);

	} else if (u.equals("inch")) {

	    setInch(n);

	} else if (u.equals("foot")) {

	    setFoot(n);

	} else if (u.equals("yard")) {

	    setYard(n);
	}
    }
}
