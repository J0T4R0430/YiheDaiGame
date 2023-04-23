import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BulletSprite implements DisplayableSprite {
	private static Image image1 = null;
	private static Image image2 = null;
	private double centerX = 0;
	private double centerY = 0;
	private double width = 40;
	private double height = 40;
	private boolean dispose = false;
	private double velocityX = 0;
	private double velocityY = 0;
	private int SPEED = 24;
	private double angle = 0;
	private double startpos = 0;
	private double endpos = 0;
	private double currentpos = 0;
	private double timer = 500;
	private boolean exploded = false;

	public BulletSprite(double centerX, double centerY, double position, double angle) {
		super();
		this.angle = angle;
		this.startpos = 28;
		this.currentpos = this.startpos;
		this.endpos = position;
		this.centerX = centerX;
		this.centerY = centerY;

		try {
			if (image1 == null) {
				image1 = ImageRotator.rotate(ImageIO.read(new File("res/bullet.png")), (int) angle);
			}
		} catch (IOException e) {
			System.out.println(e.toString());
		}

		try {
			if (image2 == null) {
				image2 = ImageRotator.rotate(ImageIO.read(new File("res/explosion.png")), (int) angle);
			}
		} catch (IOException e) {
			System.out.println(e.toString());
		}

	}

	public Image getImage() {
		if (exploded) {
			return BulletSprite.image2;
		} else {
			return BulletSprite.image1;

		}

	}

	public boolean getVisible() {

		return true;
	}

	public double getMinX() {

		return centerX - (width / 2);
	}

	public double getMaxX() {

		return centerX - (width / 2);
	}

	public double getMinY() {

		return centerY - (height / 2);
	}

	public double getMaxY() {

		return centerY + (height / 2);
	}

	public double getHeight() {

		return height;
	}

	public double getWidth() {

		return width;
	}

	public double getCenterX() {

		return centerX;
	}

	public double getCenterY() {

		return centerY;
	}

	public boolean getDispose() {

		return dispose;
	}

	public void setDispose(boolean dispose) {

		this.dispose = dispose;
	}

	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {

		double angleInRadians = Math.toRadians(this.angle);
		if (!exploded) {
			if (currentpos == endpos) {
				exploded = true;

			} else {
				currentpos += Math.min(SPEED, endpos - currentpos);
				this.centerX -= Math.min(SPEED, endpos - currentpos) * Math.cos(angleInRadians);
				this.centerY -= Math.min(SPEED, endpos - currentpos) * Math.sin(angleInRadians);
			}
		} else {
			if (timer == 0) {
				this.dispose = true;
			}
			timer -= 1;
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

}
