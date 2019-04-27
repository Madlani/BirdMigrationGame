package gamePackage;
<<<<<<< HEAD
import java.awt.Toolkit;
=======
>>>>>>> 1974baf4edad70ef94db8ecb63e1560156bbdc23
import java.awt.geom.Point2D;


public class Obstacle extends Point2D {
<<<<<<< HEAD
	private String sprite;
	private final int OBSTACLE_SPEED = 15;
	protected HitBox obstacleBox;
	private double xPosition;
	private double yPosition;
	
	public Obstacle(double startingX){
		xPosition = startingX;
		this.obstacleBox = new HitBox((int)this.getX(), (int)this.getY(), (int)this.xPosition, 150);

		int maxHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()- this.obstacleBox.height;
		int minHeight = 0;
		
		int maxWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()- this.obstacleBox.width;
		int minWidth = 0;
		
	//	int randX = (int)(Math.random() * (maxHeight - minHeight + 1) + minHeight);
		int randY = (int)(Math.random() * (maxWidth - minWidth + 1) + minWidth);
		
		
		
		//this.xPosition = randX;
		this.yPosition = randY;
		
		// REPLACE THE MAGIC NUMBERS HERE - WE SHOULD CONSIDER ENUMERATED TYPES FOR THE IMG WIDTH / LENGTH
		this.obstacleBox = new HitBox((int)this.getX(), (int)this.getY(), (int)this.xPosition, 150);
	}
	

=======
	private double xPosition = 450;
	private double yPosition = 450;
	
	private String sprite;
	private final int OBSTACLE_SPEED = 15;
	protected HitBox obstacleBox;
	
	Obstacle(){
		this.obstacleBox = new HitBox((int)this.xPosition,(int) this.yPosition, 150,150);

	}
>>>>>>> 1974baf4edad70ef94db8ecb63e1560156bbdc23
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
