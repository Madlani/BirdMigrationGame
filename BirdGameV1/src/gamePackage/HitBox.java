package gamePackage;
import java.awt.Rectangle;

public class HitBox extends Rectangle {

	HitBox(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	HitBox(Model m, int width, int height) {
		super((int) m.getX(), (int) m.getY(), width, height);
	}

	public void changeWidth(int new_width) {
		this.width = new_width;
	}

	public void changeHeight(int new_height) {
		this.height = new_height;
	}

	public void setSize(int new_width, int new_height) {
		this.width = new_width;
		this.height = new_height;
	}
}
