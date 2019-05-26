package gamePackage;

import java.io.Serializable;

public class Main implements Serializable {
	
	public static void main(String[] args) {
		//.System.setProperty("sun.java2d.opengl", "True");
		Controller c = new Controller();
		c.start();
	}	
}
