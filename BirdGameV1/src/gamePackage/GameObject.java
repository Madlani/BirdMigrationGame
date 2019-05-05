package gamePackage;
import java.awt.Toolkit;
import java.awt.geom.Point2D;

public class GameObject extends Point2D {
	private final int SPEED = 15;
	protected HitBox GameObjectBox;
	private double xPosition;
	private double yPosition;
	final int imgWidth = 100;
	
	private ObjectType type;
	
	public GameObject(double startingX, ObjectType t, int imgWidth, int imgHeight){
		xPosition = startingX;
		this.type = t;
		
		this.GameObjectBox = new HitBox((int)this.getX(), (int)this.getY(), imgWidth, imgHeight);
		
		int maxWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()- this.GameObjectBox.width;
		int minWidth = 0;
		
		int randY = (int)(Math.random() * (maxWidth - minWidth + 1) + minWidth);
		
		this.yPosition = randY;
		
		
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
		this.setLocation(this.getX() - SPEED, this.getY());
	}
	
	@Override
	public void setLocation(double x, double y) {
		this.xPosition = x;
		this.yPosition = y;
	}
	
	public int getGameObjectSpeed() {
		return this.SPEED;
	}


	public ObjectType getType() {
		return type;
	}
}
