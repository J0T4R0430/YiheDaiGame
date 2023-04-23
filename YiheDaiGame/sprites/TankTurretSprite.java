import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TankTurretSprite implements DisplayableSprite {

	// a sprite that can be displayed and moves based on its own polling of the
	// keyboard object

	private Image[] rotatedImages = new Image[360];
	private Image rotatedImage;
	private double offsetAngle = 180;
	private double ROTATION_SPEED = 120; // degrees per second
	private double currentAngle = 0;
	private int currentImageAngle = 0;
	private Image image;

	private double width = 50;
	private double height = 50;
	private boolean dispose = false;
	private double centerX = 0;
	private double centerY = 0;

	private final double FORWARD = 2;
	private double velocity = 0;
	private double velocityX = 0;
	private double velocityY = 0;
	private TankSprite tank = null;
	private int right = 0;
	private int left = 0;

	public TankTurretSprite(TankSprite body, int player) {
		this.tank = body;

		if (player == 1) {
			this.centerX = -100;
			try {
				image = ImageIO.read(new File("res/Tank1Turret.png"));
			} catch (IOException e) {
				System.out.println(e.toString());
			}
			right = 74;
			left = 76;
		} else if (player == 2) {
			this.centerX = +100;
			try {
				image = ImageIO.read(new File("res/Tank2Turret.png"));
			} catch (IOException e) {
				System.out.println(e.toString());
			}
			right = 100;
			left = 102;
		}
		for (int i = 0; i < 360; i++) {
			rotatedImages[i] = ImageRotator.rotate(image, i);
		}
	}

	public Image getImage() {
		currentImageAngle = (int) ((currentAngle + offsetAngle) % 360);

		return rotatedImages[currentImageAngle];

	}

	// DISPLAYABLE

	public boolean getVisible() {
		return true;
	}

	public double getMinX() {
		return this.tank.getMinX();
	}

	public double getMaxX() {
		return this.tank.getMaxX();
	}

	public double getMinY() {
		return this.tank.getMinY();
	}

	public double getMaxY() {
		return this.tank.getMaxY();
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public double getCenterX() {
		return this.tank.getCenterX();
	};

	public double getCenterY() {
		return this.tank.getCenterY();
	};

	public boolean getDispose() {
		return dispose;
	}

	public void setDispose(boolean dispose) {
		this.dispose = dispose;
	}

	public double getCurrentAngle() {
		return this.currentImageAngle;
	}

	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {

		this.currentAngle = this.tank.getCurrentAngle();
		double angleInRadians = Math.toRadians(this.currentAngle);

		if (keyboard.keyDown(right)) {
			this.offsetAngle -= (ROTATION_SPEED * (actual_delta_time * 0.001));
		}

		else if (keyboard.keyDown(left)) {
			this.offsetAngle += (ROTATION_SPEED * (actual_delta_time * 0.001));
		}

		if (currentAngle >= 360) {
			currentAngle -= 360;
		}
		if (currentAngle < 0) {
			currentAngle += 360;
		}
		if (offsetAngle >= 360) {
			offsetAngle -= 360;
		}
		if (offsetAngle < 0) {
			offsetAngle += 360;
		}

		currentAngle %= 360;
		offsetAngle %= 360;
//        System.out.println("centerX:" + this.tank.getCenterX() + " centerY:" + this.tank.getCenterY());

	}

	public void setCenterX(double centerX) {
		// TODO Auto-generated method stub
		this.centerX = centerX;

	}

	public void setCenterY(double centerY) {
		// TODO Auto-generated method stub
		this.centerY = centerY;
	}

	public void setVelocityX(double pixelsPerSecond) {
		// TODO Auto-generated method stub
		this.velocityX = pixelsPerSecond;
	}

	public void setVelocityY(double pixelsPerSecond) {
		// TODO Auto-generated method stub
		this.velocityY = pixelsPerSecond;
	}

}
