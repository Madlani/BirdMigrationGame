package gamePackage;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class HitBox extends Rectangle {

	HitBox(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

//	HitBox(Model m, int width, int height) {
//		super((int) m.getX(), (int) m.getY(), width, height);
//	}

	/**
	 * changeWidth()
	 * Changes the width of the HitBox.
	 * @param newWidth
	 */
	public void changeWidth(int newWidth) {
		this.width = newWidth;
	}

	/**
	 * changeHeight()
	 * Changes the height of the HitBox.
	 * @param newHeight
	 */
	public void changeHeight(int newHeight) {
		this.height = newHeight;
	}

	/**
	 * setSize()
	 * Sets the size of the HitBox to the specified dimensions
	 * @param newWidth, the new width for the HitBox
	 * @param newHeight, the new height for the HitBox
	 */
	@Override
	public void setSize(int newWidth, int newHeight) {
		this.width = newWidth;
		this.height = newHeight;
	}
}
