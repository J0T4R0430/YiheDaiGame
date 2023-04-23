import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CollisionDetection {
	ArrayList<Class> collisionTargetTypes = null;
	
	public ArrayList<Class> getCollisionTargetTypes() {
		return collisionTargetTypes;
	}

	public void setCollisionTargetTypes(ArrayList<Class> collisionTargetTypes) {
		this.collisionTargetTypes = collisionTargetTypes;
	}
	
	public static boolean overlaps(DisplayableSprite spriteA, DisplayableSprite spriteB) {
		return overlaps(
				spriteA.getMinX(), 
				spriteA.getMinY(), 
				spriteA.getMaxX(), 
				spriteA.getMaxY(), 
				spriteB.getMinX(), 
				spriteB.getMinY(), 
				spriteB.getMaxX(), 
				spriteB.getMaxY());		
	}
	
	public static boolean overlaps(double leftA, double topA, double rightA, double bottomA, double leftB, double topB, double rightB, double bottomB) {
		boolean toLeft = (rightA < leftB); //case 1: right edge of A is to the left of left edge of B, so A is fully to left of A
		boolean toRight = (leftA > rightB); //case 2: left edge of A is to the right of right edge of B, so A is fully to right of B
		boolean overlapX = !(toLeft || toRight);

		boolean above = (bottomA < topB); //case 1: bottom edge of A is above top edge of B, so A is fully above B
		boolean below = (topA > bottomB); //case 2: top edge of A is below bottom edge of B, so A is fully below B
		boolean overlapY = !(above || below);

		return (overlapX && overlapY);
	}
	
	public static boolean inside(DisplayableSprite spriteA, DisplayableSprite spriteB) {
		return inside(
				spriteA.getMinX(), 
				spriteA.getMinY(), 
				spriteA.getMaxX(), 
				spriteA.getMaxY(), 
				spriteB.getMinX(), 
				spriteB.getMinY(), 
				spriteB.getMaxX(), 
				spriteB.getMaxY());		
	}
	
	public static boolean inside(double leftA, double topA, double rightA, double bottomA, double leftB, double topB, double rightB, double bottomB) {
		boolean insideX = ((leftB <= leftA) && (rightA <= rightB));
		boolean insideY = ((topB <= topA) && (bottomA <= bottomB));
		if (insideX && insideY) {
			return true;
		}
		else {
			return false;	    	
		}
	}

}
