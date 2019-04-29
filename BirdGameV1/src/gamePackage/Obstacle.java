package gamePackage;
import java.awt.Toolkit;
import java.awt.geom.Point2D;

public class Obstacle extends Point2D {
	private final int OBSTACLE_SPEED = 15;
	protected HitBox obstacleBox;
	private double xPosition;
	private double yPosition;
	final int imgWidth = 150;
	
	public Obstacle(double startingX){
		xPosition = startingX;
		this.obstacleBox = new HitBox((int)this.getX(), (int)this.getY(), (int)this.xPosition, imgWidth);
		
		int maxWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()- this.obstacleBox.width;
		int minWidth = 0;
		
		int randY = (int)(Math.random() * (maxWidth - minWidth + 1) + minWidth);
		
		this.yPosition = randY;
		
		// REPLACE THE MAGIC NUMBERS HERE - WE SHOULD CONSIDER ENUMERATED TYPES FOR THE IMG WIDTH / LENGTH
		this.obstacleBox = new HitBox((int)this.getX(), (int)this.getY(), (int)this.xPosition, imgWidth);
	}
	

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return this.xPosition;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return this.yPosition;
	}
	
	public void scroll() {
		this.setLocation(this.getX() - OBSTACLE_SPEED, this.getY());
	}
	
	@Override
	public void setLocation(double x, double y) {
		this.xPosition = x;
		this.yPosition = y;
	}
	
	public int getObstacleSpeed() {
		return this.OBSTACLE_SPEED;
	}
}
