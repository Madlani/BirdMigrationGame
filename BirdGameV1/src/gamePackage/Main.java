package gamePackage;

public class Main {
	
	public static void main(String[] args) {
		System.setProperty("sun.java2d.opengl", "True");
		
		Controller c = new Controller();
		c.start();
	}	
}
