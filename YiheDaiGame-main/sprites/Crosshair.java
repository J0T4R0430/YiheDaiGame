import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Crosshair implements MovableSprite, DisplayableSprite{
	
	private static Image image;
	private double currentAngle = 0;
	private double distance = 100;
	private double width = 20;
	private double height = 20;
	private boolean dispose = false;
	private double centerX = 0;
	private double centerY = 0;
	private String filename = "res/crosshair.png";
	private final double VELOCITY = 2;
	private final int MAXRANGE = 200;
	private final int MINRANGE = 36;
	private double position = MINRANGE + 30;
	private double velocityX = 0;
	private double velocityY = 0;
	private TankTurretSprite tank = null;
	private double horizontalDisplacement = 0;
	private double verticalDisplacement = 0;
	
	
	
	
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
		System.out.println(currentAngle);
		if(keyboard.keyDown(38) && position < MAXRANGE) {
			this.position += VELOCITY;
		}
		if (keyboard.keyDown(40) && position > MINRANGE) {
			this.position -= VELOCITY;
		}
		
		this.verticalDisplacement = Math.sin(angleInRadians) * this.position;
		this.horizontalDisplacement = Math.cos(angleInRadians) * this.position;
		
		
		
//		this.centerY += position * actual_delta_time * 0.001 * Math.cos(angleInRadians);
//		this.centerY += position * actual_delta_time * 0.001 * Math.sin(angleInRadians);

	}

}
