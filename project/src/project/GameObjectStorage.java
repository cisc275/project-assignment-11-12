package project;

import java.util.*;

public class GameObjectStorage {
	Player p;
	Fox f;
	SunTimer sun;
	MoonTimer moon;
	ArrayList<ScoringObject> scoringObjects; 
	Scoring score;
	
	GameObjectStorage(){
		this.scoringObjects = new ArrayList<ScoringObject>();
	}
	
	public Fox getFox() {
		return this.f;
	}
	public Player getPlayer() {
		return this.p;
	}
	public SunTimer getSunTimer() {
		return this.sun;
	}
	public MoonTimer getMoonTimer() {
		return this.moon;
	}
	public ArrayList<ScoringObject> getScoringObjects(){
		return this.scoringObjects;
	}
	public void setPlayer(Player p) {
		this.p = p;
	}
	public void setFox(Fox f) {
		this.f = f;
	}
	public void setSunTimer(SunTimer s) {
		this.sun = s;
	}
	public void setMoonTimer(MoonTimer m) {
		this.moon = m;
	}
	public void setScoringObjects(ArrayList<ScoringObject> scoringObjects) {
		this.scoringObjects = scoringObjects;
	}
	public void setScore(Scoring s) {
		this.score = s;
	}
}