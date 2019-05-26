package gamePackage;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Deserialize {
	public static Model pullGame() throws Exception {
		FileInputStream fis = new FileInputStream("BirdGameV1/test.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Model m = (Model) ois.readObject();
		ois.close();
		return m;
		
	}
}
