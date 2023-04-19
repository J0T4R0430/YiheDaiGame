import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TankTurretSprite implements MovableSprite, DisplayableSprite {
	
	//a sprite that can be displayed and moves based on its own polling of the keyboard object

    private static Image[] rotatedImages = new Image[360];
    private Image rotatedImage;
    private double offsetAngle = 180;
    private double ROTATION_SPEED = 120;    //degrees per second    
    private double currentAngle = 0;
    private int currentImageAngle = 0;
	private static Image image;	
	
	
	private double width = 50;
	private double height = 50;
	private boolean dispose = false;
	private double centerX = 0;
	private double centerY = 0;

	private final double FORWARD = 2;
    private String filename = "res/tankhead.png";
    private double velocity = 0;
	private double velocityX = 0;
	private double velocityY = 0;
	private TankSprite tank = null;
	

    public TankTurretSprite(TankSprite body) {
        this.tank = body;

        if (image == null) {
            try {
                image = ImageIO.read(new File(filename));
            }
            catch (IOException e) {
                System.out.println(e.toString());
            }       
        }
        if (image != null) {
            for (int i = 0; i < 360; i++) {
                rotatedImages[i] = ImageRotator.rotate(image, i);           
            }
        }
    }

    public Image getImage() {
        currentImageAngle = (int) ((currentAngle + offsetAngle)%360);
        
        return rotatedImages[currentImageAngle];
        
		
	}
	
	//DISPLAYABLE
	
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
	
	public double getCurrentAngle(){
		return this.currentImageAngle;
	}
	

	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {
		
		this.currentAngle = this.tank.getCurrentAngle();
	    double angleInRadians = Math.toRadians(this.currentAngle);
	    
        
        if(keyboard.keyDown(37)) {
        	this.offsetAngle -= (ROTATION_SPEED * (actual_delta_time * 0.001));
        }
        
        else if(keyboard.keyDown(39)) {
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
