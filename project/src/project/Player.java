package project;
//turn into abstract class, not player, but all game objects
public abstract class Player extends GameObject{
	
	Player(int x, int y, int xInc, int yInc, int iW, int iH, GameObjectEnum GobjEnum) {
		super(x, y, xInc, yInc, iW, iH, GobjEnum);
	}
	
}