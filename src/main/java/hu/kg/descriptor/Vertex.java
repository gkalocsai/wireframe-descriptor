package hu.kg.descriptor;

public class Vertex {

	
    double x;
	double y;
	double z;
	String name;
	public Vertex(String name, double x, double y, double z) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.z = z;
	
	
	}
	public Vertex(String name, double[] coords) {
	    this(name,coords[0], coords[1], coords[2]);
		
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getZ() {
		return z;
	}
	public String getName() {
		return name;
	}
	
	
}
