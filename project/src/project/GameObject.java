package project;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class GameObject implements java.io.Serializable {
	/**
	 Auto Generated serialUID
	 */
	private static final long serialVersionUID = 7670614514181502342L;
	int xloc,yloc,xIncr,yIncr;
	int imageWidth, imageHeight;
	BufferedImage[][] imageArray;
	Image img;
	Rectangle bounds;
	GameObjectEnum GobjEnum;
	
	GameObject(){
		this.xloc = 0;
		this.yloc = 0;
		this.xIncr = 0;
		this.yIncr = 0;
		this.imageWidth = 0;
		this.imageHeight = 0;
		this.img = null;
		this.GobjEnum = null;
		bounds = null;
	}
	
	GameObject(int x, int y, int xInc, int yInc, int iW, int iH, GameObjectEnum GobjEnum){
		this.xloc = x;
		this.yloc = y;
		this.xIncr = xInc;
		this.yIncr = yInc;
		this.imageWidth = iW;
		this.imageHeight = iH;
		this.GobjEnum = GobjEnum;
		this.img = this.loadImage();
		this.bounds = this.loadBounds();
	}
	
	public Rectangle loadBounds() {
		return (new Rectangle(xloc,yloc,imageWidth,imageHeight));
	}
	
	public void updateBounds() {
		this.bounds = (new Rectangle(xloc,yloc,imageWidth,imageHeight));
	}
	public Image loadImage() {
		try {
			return ImageIO.read(new File (this.GobjEnum.getFullImagePath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public abstract void move();
	
	public int getXloc() {
		return xloc;
	}
	public void setXloc(int xloc) {
		this.xloc = xloc;
	}
	public int getYloc() {
		return yloc;
	}
	public void setYloc(int yloc) {
		this.yloc = yloc;
	}
	public int getxIncr() {
		return xIncr;
	}
	public void setxIncr(int xIncr) {
		this.xIncr = xIncr;
	}
	public int getyIncr() {
		return yIncr;
	}
	public void setyIncr(int yIncr) {
		this.yIncr = yIncr;
	}
	public int getImageWidth() {
		return imageWidth;
	}
	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}
	public int getImageHeight() {
		return imageHeight;
	}
	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}
	public BufferedImage[][] getImageArray() {
		return imageArray;
	}
	public void setImageArray(BufferedImage[][] imageArray) {
		this.imageArray = imageArray;
	}
	public Image getImg() {
		return img;
	}
	public void setImg(Image img) {
		this.img = img;
	}
	public Rectangle getBounds() {
		return this.bounds;
	}
}