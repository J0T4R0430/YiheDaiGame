import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Crosshair implements MovableSprite, DisplayableSprite{
	
	private static Image image;
	private double currentAngle = 0;
	private double width = 20;
	private double height = 20;
	private boolean dispose = false;
	private double centerX = 0;
	private double centerY = 0;
	private String filename = "res/crosshair.png";
	private final double VELOCITY = 2;
	private final int MAXRANGE = 200;
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
		return this.tank.getMinX() - horizontalDisplacement + 15;
	}

	public double getMaxX() {
		return this.tank.getMaxX() - horizontalDisplacement + 15;
	}

	public double getMinY() {
		return this.tank.getMinY() - verticalDisplacement + 15;
	}

	public double getMaxY() {
		return this.tank.getMaxY() - verticalDisplacement + 15;
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public double getCenterX() {
		return this.getMinX() + (this.getMaxX() - this.getMinX())/2;
	}

	public double getCenterY() {
		return this.getMinY() + (this.getMaxY() - this.getMinY())/2;
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
		if(keyboard.keyDown(38) && position < MAXRANGE) {
			this.position += VELOCITY;
		}
		if (keyboard.keyDown(40) && position > MINRANGE) {
			this.position -= VELOCITY;
		}
		if (keyboard.keyDown(32) && shooting <=0) {
		    shooting = 500;
		    shoot(universe, keyboard);
		}
		
//		System.out.println("maxX:" + this.getMaxX() + " minY:" + this.getMaxY());
//		System.out.println("minX:" + this.getMinX() + " minY:" + this.getMinY());
		
		
		this.verticalDisplacement = Math.sin(angleInRadians) * this.position;
		this.horizontalDisplacement = Math.cos(angleInRadians) * this.position;
		shooting -= actual_delta_time;
		
		
		
//		this.centerY += position * actual_delta_time * 0.001 * Math.cos(angleInRadians);
//		this.centerY += position * actual_delta_time * 0.001 * Math.sin(angleInRadians);

	}
	
	public void shoot(Universe universe, KeyboardInput keyboard) {
		double shootingAngle = this.currentAngle;
		if(keyboard.keyDown(37)) {
			shootingAngle -= 25 * this.position / 200;
		}else if (keyboard.keyDown(39)) {
			shootingAngle += 25 * this.position / 200;
		}
		if(keyboard.keyDown(65)) {
			shootingAngle -=10;
		}else if(keyboard.keyDown(68)) {
			shootingAngle +=10;
		}	
		
		if (shootingAngle >= 360) {
			shootingAngle -= 360;
        }
        if (shootingAngle < 0) {
        	shootingAngle += 360;
        }  
	    BulletSprite bullet = new BulletSprite(this.tank.getCenterX(), this.tank.getCenterY(), this.position + 24, shootingAngle);
	    universe.getSprites().add(bullet);
	}

}
