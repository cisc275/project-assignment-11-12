package project;

import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//import project.View.DrawPanel;

@SuppressWarnings("serial")
public class View extends JFrame{
	GameObjectStorage GobjS;
	DrawPanel panelContainer, menupanel, game1panel, game2panel, end1panel, end2panel;
	static String currentpanel;
	
	int frameCount;
	int picNum;
	final static int frameWidth = 800;
	final static int frameHeight = 600;
	int imageHeight;
	int imageWidth;
	BufferedImage[][] imageArray;
	Button exit, game1, game2, ans1, ans2, menu1, menu2, menu, cancel, replay;
	
	Image g2_backimage;
	Image g1_backimage;
	Image osprey_image;
	Image clapperrail_image;
	Image trout_image;
	Image seaweed_image;
	Image strippedbass_image;
	Image background;
	Image egg_image;
	Image energy_image;
	Image fox_image;
	
	int rand;
	static boolean randflag = true;
	
	CardLayout cl = new CardLayout();
	
	public View() {
		this.setPreferredSize(new Dimension(frameWidth, frameHeight));
		//load images
		try {
			g1_backimage = ImageIO.read(new File("images/g1_background.png"));
			g2_backimage = ImageIO.read(new File("images/g2_background.png"));
			osprey_image = ImageIO.read(new File("images/o_temp.png"));
			clapperrail_image = ImageIO.read(new File("images/cr_temp.png"));
			egg_image = ImageIO.read(new File("images/egg.png"));
			energy_image = ImageIO.read(new File("images/energy.png"));
			fox_image = ImageIO.read(new File("images/fox.png"));
			
			trout_image = ImageIO.read(new File("images/trout_temp.png"));
			seaweed_image = ImageIO.read(new File("images/seaweed.png"));
			strippedbass_image = ImageIO.read(new File("images/striped_bass.png"));
			
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		//set up card layout & panels
		panelContainer = new DrawPanel();
		panelContainer.setLayout(cl);
		
		createlayouts();
		
		panelContainer.add(menupanel, "0");
		panelContainer.add(game1panel, "1");
		panelContainer.add(game2panel, "2");
		panelContainer.add(end1panel, "3");
		panelContainer.add(end2panel, "4");
		
		cl.show(panelContainer, "0");
		
		this.add(panelContainer);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	public void createlayouts() {
		menupanel = new DrawPanel();
		menupanel.setLayout(null);
		menupanel.setBackground(Color.darkGray);
		
		game1 = new Button("Game 1: Osprey");
		game1.setBounds(200,50,400,100);
		game2 = new Button("Game 2: Clapper Rail");
		game2.setBounds(200,200,400,100);
		menupanel.add(game1);
		menupanel.add(game2);
		
		game1panel = new DrawPanel();
		game1panel.setLayout(null);
		menu1 = new Button("main menu");
		menu1.setBounds(frameWidth-150,10, 100, 30);
		game1panel.add(menu1);
		
		game2panel = new DrawPanel();
		game2panel.setLayout(null);
		menu2 = new Button("main menu");
		menu2.setBounds(frameWidth-150,10, 100, 30);
		game2panel.add(menu2);
		
		end1panel = new DrawPanel();
		end1panel.setLayout(null);
		end1panel.setBackground(Color.gray);
		
		end2panel = new DrawPanel();
		end2panel.setLayout(null);
		end2panel.setBackground(Color.gray);
	}
	
	
	
	public void addGameObjectStorageToView(GameObjectStorage GobjS) {
		this.GobjS = GobjS;
	}
	
	public BufferedImage createBufferedImage() {
		return new BufferedImage(1,1,1);
	}
	
	public static String getContent() {
		return currentpanel;
	}
	
	public void initializeBackground() {
		if(currentpanel == "g1") {
			this.background = g1_backimage;
		}
		else if(currentpanel == "g2") {
			this.background = g2_backimage;
		}
		else {
			this.background = g1_backimage;
		}
	}
	
	public void initializeGameImages() {
		if(currentpanel == "g1") {
			GobjS.getPlayer().setImg(osprey_image);
			this.background = g1_backimage;
		}
		else if(currentpanel == "g2") {
			GobjS.getPlayer().setImg(clapperrail_image);
			this.background = g2_backimage;
		}
		else {
			GobjS.getPlayer().setImg(osprey_image);
			this.background = g1_backimage;
		}
	}
	
	private class DrawPanel extends JPanel{
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (currentpanel == "g1") {
				g.drawImage(background, 0, 0, Color.gray, this);
				this.paintPlayer(g);
				this.paintScoringObjects(g);
				this.paintEnergy(g);
				//System.out.println(GobjS.getPlayer().getyIncr());
			}
			if (currentpanel == "g2") {
				g.drawImage(background, 0, 0, Color.gray, this);
				this.paintPlayer(g);
				this.paintScoringObjects(g);
				this.paintEggs(g);
				this.paintFox(g);
			}
			if (currentpanel == "e1") {
				//just draw something temp on panel for now
				g.drawImage(osprey_image, GobjS.getPlayer().getXloc(), GobjS.getPlayer().getYloc(), GobjS.getPlayer().getImageWidth(), GobjS.getPlayer().getImageHeight(), this);
				g.drawString(GobjS.score.toString(), 400, 200);
			}
			if (currentpanel == "e2") {
				//just draw something temp on panel for now
				g.drawImage(clapperrail_image, GobjS.getPlayer().getXloc(), GobjS.getPlayer().getYloc(), GobjS.getPlayer().getImageWidth(), GobjS.getPlayer().getImageHeight(), this);
				g.drawString(GobjS.score.toString(), 400, 200);
			}
		}
		
		
		public void paintPlayer(Graphics g) {
			g.drawImage(GobjS.getPlayer().getImg(), GobjS.getPlayer().getXloc(), GobjS.getPlayer().getYloc(), GobjS.getPlayer().getImageWidth(), GobjS.getPlayer().getImageHeight(), this);
		}
		
		public void paintScoringObjects(Graphics g) {
			for(ScoringObject so : GobjS.getScoringObjects()) {
				g.drawImage(so.getImg(), so.getXloc(), so.getYloc(), so.getImageWidth(), so.getImageHeight(), this);
			}
		}
		public void paintEggs(Graphics g) {
			Random r = new Random();
			for (int i=0; i < GobjS.score.totalScore;i++) {
				g.drawImage(egg_image, 30+20*i,10, 40, 50, this);
			}
		}
		public void paintFox(Graphics g) {
			g.drawImage(GobjS.getFox().getImg(), GobjS.getFox().getXloc(), GobjS.getFox().getYloc(), GobjS.getFox().getImageWidth(), GobjS.getFox().getImageHeight(), this);
		}
		
		public void paintEnergy(Graphics g) {
			for (int i=0; i < GobjS.score.totalScore;i++) {
				g.drawImage(energy_image, 5+20*i,10, 30, 40, this);
			}
		}
		
		
	}
	

	/**
	 * adds the Controller class as the listener to buttons in View
	 * @param the controller instance 
	 * @return none
	 * @author Brendan Azueta
	 */
	
	public void addControllertoButton(Controller c) {
		game1.addActionListener(c);
		game2.addActionListener(c);
		//ans1.addActionListener(c);
		//ans2.addActionListener(c);
		menu1.addActionListener(c);
		menu2.addActionListener(c);
		//replay.addActionListener(c);
	}
	
	
	
}
