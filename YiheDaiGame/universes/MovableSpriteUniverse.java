import java.util.ArrayList;

public class MovableSpriteUniverse implements Universe {

	private boolean complete = false;	
	private Background background = null;	
	private DisplayableSprite player1 = null;
	private DisplayableSprite player2 = null;
	private ArrayList<DisplayableSprite> sprites = new ArrayList<DisplayableSprite>();
	private long elapsedTime = 0;
	private String status = "";
	private double velocityX = 0;
    private double velocityY = 0;
    ArrayList<DisplayableSprite> disposedSprites = new ArrayList<DisplayableSprite>();

	private final double VELOCITY = 2;	
	
//	//require a separate list for sprites to be removed to avoid a concurence exception
	private ArrayList<DisplayableSprite> disposalList = new ArrayList<DisplayableSprite>();

	
	public MovableSpriteUniverse () {
	
		this.setXCenter(0);
		this.setYCenter(0);
		
		ImageSprite background = new ImageSprite(
		        -AnimationFrame.SCREEN_WIDTH/2, -AnimationFrame.SCREEN_HEIGHT/2,
		        AnimationFrame.SCREEN_WIDTH/2, AnimationFrame.SCREEN_HEIGHT/2, "res/DesertMap.png");
		sprites.add(background);
	
		TankSprite tank1 =new TankSprite(1);
		TankSprite tank2 = new TankSprite(2);
		player1 = tank1;
		player2 = tank2;
		
		sprites.add(tank1);
		sprites.add(tank1.getTurret());
		
		sprites.add(tank2);
		sprites.add(tank2.getTurret());
		sprites.add(tank1.getCrosshair());
		sprites.add(tank2.getCrosshair());

	
}
	
	public double getScale() {
		return 1;
	}

	public double getXCenter() {
		return 0;
	}

	public double getYCenter() {
		return 0;
	}
	
	public void setXCenter(double xCenter) {
	}

	public void setYCenter(double yCenter) {
	}
	
	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
	}

	public ArrayList<Background> getBackgrounds() {
		return null;
	}

	public DisplayableSprite getPlayer1() {
		return player1;
	}

	public ArrayList<DisplayableSprite> getSprites() {
		return sprites;
	}
		
	public boolean centerOnPlayer() {
		return false;
	}		
	
	public void update(KeyboardInput keyboard, long actual_delta_time) {
	
		
		for (int i = 0; i < sprites.size(); i++) {
			DisplayableSprite sprite = sprites.get(i);
			
			sprite.update(this, keyboard, actual_delta_time);
    	}    	
		disposeSprites();
	}
	
	protected void disposeSprites() {
        
    	
		for (int i = 0; i < sprites.size(); i++) {
			DisplayableSprite sprite = sprites.get(i);
    		if (sprite.getDispose() == true) {
    			disposedSprites.add(sprite);
    		}
    	}
		for (int i = 0; i < disposedSprites.size(); i++) {
			DisplayableSprite sprite = disposedSprites.get(i);
			sprites.remove(sprite);
    	}
    	if (disposedSprites.size() > 0) {
    		disposedSprites.clear();
    	}
    }	
	public String toString() {
		return this.status;
	}	

}
