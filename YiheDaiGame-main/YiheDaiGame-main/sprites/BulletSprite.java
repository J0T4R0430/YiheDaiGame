import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BulletSprite implements DisplayableSprite{
    private static Image image; 
    private double centerX = 0;
    private double centerY = 0;
    private double width = 50;
    private double height = 50;
    private boolean dispose = false;    
    private double velocityX = 0;
    private double velocityY = 0;
    private int velocity = 20;
    private double currentAngle = 0;
    private static Image[] rotatedImages = new Image[360];
    private Image rotatedImage;
    private String filename = "res/error.png";
    private int startpos = 0;
    private int endpos = 0;
    private int currentpos = 0;
    
    public BulletSprite(double[] trajectory) {
        this.currentAngle = trajectory[2];
        this.startpos = (int) trajectory[0];
        this.currentpos = this.startpos;
        this.endpos = (int) trajectory[1];
        this.centerX = trajectory[3];
        this.centerY = trajectory[4];
        
        
        
        
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

    @Override
    public Image getImage() {
        // TODO Auto-generated method stub
        return rotatedImages[(int) currentAngle];
    }

    @Override
    public boolean getVisible() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public double getMinX() {
        // TODO Auto-generated method stub
        return centerX - (width / 2);
    }

    @Override
    public double getMaxX() {
        // TODO Auto-generated method stub
        return centerX - (width / 2);
    }

    @Override
    public double getMinY() {
        // TODO Auto-generated method stub
        return centerY - (height / 2);
    }

    @Override
    public double getMaxY() {
        // TODO Auto-generated method stub
        return centerY + (height / 2);
    }

    @Override
    public double getHeight() {
        // TODO Auto-generated method stub
        return height;
    }

    @Override
    public double getWidth() {
        // TODO Auto-generated method stub
        return width;
    }

    @Override
    public double getCenterX() {
        // TODO Auto-generated method stub
        return centerX;
    }

    @Override
    public double getCenterY() {
        // TODO Auto-generated method stub
        return centerY;
    }

    @Override
    public boolean getDispose() {
        // TODO Auto-generated method stub
        return dispose;
    }

    @Override
    public void setDispose(boolean dispose) {
        // TODO Auto-generated method stub
        this.dispose = dispose;
    }

    @Override
    public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {
        // TODO Auto-generated method stub
        double angleInRadians = Math.toRadians(this.currentAngle);
        if(currentpos >= endpos) {
            this.setDispose(true);
        }
        currentpos += velocity;
        this.centerX += this.velocity * Math.cos(angleInRadians)* 0.001 * this.velocity;
        this.centerY += this.velocity * Math.sin(angleInRadians)* 0.001 * this.velocity;
    }
    
}
