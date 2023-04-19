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
    private double startpos = 0;
    private double endpos = 0;
    private double currentpos = 0;
    private double timer = 500;
    
    public BulletSprite(double centerX, double centerY, double position, double angle) {
    	super();
        this.angle = angle;
        this.startpos = 28;
        this.currentpos = this.startpos;
        this.endpos = position;
        this.centerX = centerX;
        this.centerY = centerY;
                        
        
        try {
            this.image = ImageRotator.rotate(ImageIO.read(new File("res/bullet.png")),(int) angle);
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }                
    }

     
    public Image getImage() {
        return this.image;

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
        if(currentpos > endpos) {
            if(timer == 0) {
                this.dispose = true;
            }
        }else {
        currentpos += Math.min(SPEED, endpos - currentpos);
        this.centerX -= Math.min(SPEED, endpos - currentpos) * Math.cos(angleInRadians);
        this.centerY -= Math.min(SPEED, endpos - currentpos) * Math.sin(angleInRadians);
        }
    }

}
