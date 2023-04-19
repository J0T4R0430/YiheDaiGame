import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BulletSprite implements DisplayableSprite{
    private static Image image; 
    private double centerX = 0;
    private double centerY = 0;
    private double width = 40;
    private double height = 40;
    private boolean dispose = false;    
    private double velocityX = 0;
    private double velocityY = 0;
    private int SPEED = 24;
    private double angle = 0;
    private static Image[] rotatedImages = new Image[360];
    private Image rotatedImage;
    private String filename = "res/bullet.png";
    private double startpos = 0;
    private double endpos = 0;
    private double currentpos = 0;
    private int timer = 100;
    
    public BulletSprite(double centerX, double centerY, double position, double angle) {
    	super();
        this.angle = angle;
        this.startpos = 28;
        this.currentpos = this.startpos;
        this.endpos = position;
        this.centerX = centerX;
        this.centerY = centerY;
        
        
        
        
        
        try {
            image = ImageIO.read(new File("res/bullet.png"));
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }       
        
        if (image != null) {
            for (int i = 0; i < 360; i++) {
                rotatedImages[i] = ImageRotator.rotate(image, i);           
            }
        }
    }

     
    public Image getImage() {
    	if(timer < 100) {
    		try {
                image = ImageIO.read(new File(filename));
            }
            catch (IOException e) {
                System.out.println(e.toString());
            } 
    		return image;
    	}else {
        return rotatedImages[(int) angle];
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
        if(currentpos == endpos) {
        	this.filename = "res/explosion.png";
        	if(timer == 100) {
        		this.centerX -= SPEED * Math.cos(angleInRadians);
                this.centerY -= SPEED * Math.sin(angleInRadians);
        	}
            explode();
        }else {
        currentpos += Math.min(SPEED, endpos - currentpos);
        System.out.println(currentpos + "    " + endpos);
        this.centerX -= Math.min(SPEED, endpos - currentpos) * Math.cos(angleInRadians);
        this.centerY -= Math.min(SPEED, endpos - currentpos) * Math.sin(angleInRadians);
        }
    }
    
    public void explode() {
    	timer -= 1;
    	if (timer < 0) {
    		this.dispose = true;
    	}
    }
}
