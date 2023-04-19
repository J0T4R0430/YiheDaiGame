import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Crosshair implements MovableSprite, DisplayableSprite{
	
	private static Image image;
	private double currentAngle = 0;
	private double width = 30;
	private double height = 30;
	private boolean dispose = false;
	private double centerX = 0;
	private double centerY = 0;
	private String filename = "res/crosshair.png";
	private final double VELOCITY = 2;
	private final int MAXRANGE = 320;
	private final int MINRANGE = 36;
//	this minimum range is actually 28, so the bullet starts at 28 and go to position.
	private double position = MINRANGE + 50;
	private double velocityX = 0;
	private double velocityY = 0;
	private TankTurretSprite tank = null;
	private double horizontalDisplacement = 0;
	private double verticalDisplacement = 0;
	private double shooting = 500;
	
	
	
	
	public Crosshair(TankTurretSprite turret) {
		this.tank = turret;
		
		if (image == null) {
            try {
                image = ImageIO.read(new File(filename));
            }
            catch (IOException e) {
                System.out.println(e.toString());
                }
		}
	}
	
	
	
	

	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(double centerY) {
        this.centerY = centerY;	
	}

	public void setVelocityX(double pixelsPerSecond) {
        this.velocityX = pixelsPerSecond;	
	}

	public void setVelocityY(double pixelsPerSecond) {
        this.velocityY = pixelsPerSecond;	
	}

	public Image getImage() {
		return image;
	}

	public boolean getVisible() {

		return true;
	}

	public double getMinX() {
//		return this.tank.getMinX() - horizontalDisplacement + 10;
		return this.centerX - this.width / 2;
	}

	public double getMaxX() {
//		return this.tank.getMaxX() - horizontalDisplacement + 10;
	    return this.centerX + this.width / 2;
	}

	public double getMinY() {
//		return this.tank.getMinY() - verticalDisplacement + 10;
		return this.centerY - this.height / 2;
	}

	public double getMaxY() {
//		return this.tank.getMaxY() - verticalDisplacement + 10;
	    return this.centerY + this.height / 2;

	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public double getCenterX() {
		return this.centerX;
	}

	public double getCenterY() {
		return this.centerY;
	}

	public boolean getDispose() {
		return dispose;
	}

	public void setDispose(boolean dispose) {
		this.dispose = dispose;		
	}

	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {
		this.currentAngle = ((TankTurretSprite) this.tank).getCurrentAngle();
		double angleInRadians = Math.toRadians(this.currentAngle);
		double deltapos = 0;
		if(keyboard.keyDown(38) && position < MAXRANGE) {
			deltapos = VELOCITY;
		}
		else if (keyboard.keyDown(40) && position > MINRANGE) {
			deltapos = -VELOCITY;
		}
		if (keyboard.keyDown(32) && shooting <=0) {
		    shooting = 1500;
		    shoot(universe, keyboard);
		}
		this.position += deltapos;
		
//		System.out.println("maxX:" + this.getMaxX() + " minY:" + this.getMaxY());
//		System.out.println("minX:" + this.getMinX() + " minY:" + this.getMinY());
		
		
		this.verticalDisplacement = Math.sin(angleInRadians) * this.position;
		this.horizontalDisplacement = Math.cos(angleInRadians) * this.position;
		this.centerX = this.tank.getCenterX() - this.horizontalDisplacement;
		this.centerY = this.tank.getCenterY() - this.verticalDisplacement;
		shooting -= actual_delta_time;
		
		
		
//		this.centerY += position * actual_delta_time * 0.001 * Math.cos(angleInRadians);
//		this.centerY += position * actual_delta_time * 0.001 * Math.sin(angleInRadians);

	}
	
	public void shoot(Universe universe, KeyboardInput keyboard) {
		double shootingAngle = this.currentAngle;

	    BulletSprite bullet = new BulletSprite(this.tank.getCenterX(),
	            this.tank.getCenterY(), this.position + 52, shootingAngle);
	    universe.getSprites().add(bullet);
	}

}
