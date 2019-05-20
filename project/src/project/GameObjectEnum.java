package project;

public enum GameObjectEnum {
	g1Osprey("osprey"),
	g2ClapperRail("cr_temp"),
	g1Fish1("fish"),
	g1Fish2("fish2"),
	g1Fish3("fish3"),
	g1Seaweed("seaweed"),
	g2Food("food"),
	g2Food2("food2"),
	g2Food3("food3"),
	g2Food4("food4"),
	g2Trash("trash"),
	g2Trash2("trash2"),
	g2Trash3("trash3"),
	g2Trash4("trash4"),
	g2Fox("fox");

	String imgFileName;
	
	GameObjectEnum(String imgname){
		this.imgFileName = imgname;
	}

	public String getImageFileName() {
		return this.imgFileName;
	}

	public String getFullImagePath() {
		return "images/" + this.imgFileName + ".png";
	}

}