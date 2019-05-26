package gamePackage;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Serialize {

	public static void dumpGame(Model m) throws Exception {
		FileOutputStream fos = new FileOutputStream("BirdGameV1/test.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(m);
		oos.close();
	}
}
