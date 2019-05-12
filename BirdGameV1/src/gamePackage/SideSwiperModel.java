package gamePackage;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.Timer;

public class SideSwiperModel extends Model {
	
	private int scaledImageWidth = Model.scaledImageWidth;
	private int scaledImageHeight = Model.scaledImageHeight;
	private int offset = 600;
	
	public SideSwiperModel() {
		super();
	}
	
	/**
	 * updateGameObjectLocationAndDirection()
	 * This method overrides the Model's updateGameObjectLocationAndDirection(). This will position the GameObject
	 * on the right side of the screen once it moves too far left, otherwise continue to move the GameObject by the 
	 * speed that was pre-determined.
	 */
	@Override
	public void updateGameObjectLocationAndDirection(GameObject o) {
		if(o.getX() <= -o.GameObjectBox.width) {
			resetGameObjectLocation(o);
		}
		else {
			o.setLocation(o.getX() - o.getGameObjectSpeed(), o.getY());
		}
	}
	
	/**
	 * resetGameObjectLocation()
	 * Reset's the GameObject at a random y-height. This method is called from 
	 * updateGameObjectLocationAndDirection() in this class.
	 */
	@Override
		public void resetGameObjectLocation(GameObject o) {

			int maxHeight = scaledImageHeight - o.GameObjectBox.height;
			int minHeight = 0;
			
			int width = scaledImageWidth;
			int rand = (int)(Math.random()*(maxHeight - minHeight + 1) + minHeight);
			
			o.setLocation(width + offset, rand);
	}
}



