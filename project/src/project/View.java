package project;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



//import project.View.DrawPanel;

@SuppressWarnings("serial")
public class View extends JFrame implements java.io.Serializable{
@SuppressWarnings("serial")
public class View extends JFrame{
	
	GameObjectStorage GobjS;
	DrawPanel panelContainer, menupanel, game1panel, game2panel, end1panel, end2panel;
	
	int frameCount;
	int picNum;
	static int frameWidth;
	static int frameHeight;
	BufferedImage[][] imageArray;
	boolean quizflag = false;
	
	int G1EnergyCount = 0;
	
	//game 1 tutorial
	int tutorialcount1 = 0;
	boolean tutorialflag1 = true;
	boolean learnmovementflag1 = false;
	boolean learnscoringflag1 = false;
	
	// game 2 tutorial
	int tutorialcount = 0;
	boolean tutorialflag = true;
	boolean learnmovementflag = false;
	boolean learnscoringflag = false;
	
	
	BufferedImage g2_background;
	BufferedImage g1_backimage;
	BufferedImage osprey;
	BufferedImage clapperrail_image;
	BufferedImage fish1;
	BufferedImage seaweed_image;
	BufferedImage fish2;
	BufferedImage fish3;
	BufferedImage background;
	BufferedImage egg_image;
	BufferedImage energy_image;
	BufferedImage fox_image;
	BufferedImage sun_image;
	BufferedImage moon_image;
	BufferedImage crmenu;
	BufferedImage omenu;
	BufferedImage arrows;
	BufferedImage space;
	BufferedImage upArrow;
	
	
	CardLayout cl = new CardLayout();
	
	public View() {

		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		Dimension viewdim = this.getSize();
		//frameWidth = 800;
		//frameHeight = 800;
		frameWidth = viewdim.width;
		frameHeight = viewdim.height;
		
		//set up card layout & panels
		panelContainer = new DrawPanel();
		panelContainer.setLayout(cl);
		this.add(panelContainer, BorderLayout.CENTER);
		
		createlayouts();
		
		panelContainer.add(menupanel, "0");
		panelContainer.add(game1panel, "1");
		panelContainer.add(game2panel, "2");
		panelContainer.add(end1panel, "3");
		panelContainer.add(end2panel, "4");
				
		cl.show(panelContainer, "0");
		
		//load images
				try {
					omenu = ImageIO.read(new File("images/omenu.png"));
					crmenu = ImageIO.read(new File("images/crmenu.png"));
					omenu = getScaledImage(omenu, frameWidth/2, frameHeight);
					crmenu = getScaledImage(crmenu, frameWidth/2, frameHeight);
					
					g2_background = ImageIO.read(new File("images/g2_background.png"));
					clapperrail_image = ImageIO.read(new File("images/cr_temp.png"));
					egg_image = ImageIO.read(new File("images/egg.png"));
					sun_image = ImageIO.read(new File("images/sun.png"));
					fox_image = ImageIO.read(new File("images/fox.png"));
					moon_image = ImageIO.read(new File("images/moon.png"));
					arrows = ImageIO.read(new File("images/arrows.png"));
					space = ImageIO.read(new File("images/space.jpeg"));
					upArrow = ImageIO.read(new File("images/upArrow.jpg"));
					
					g1_backimage = ImageIO.read(new File("images/g1_background.png"));
					fish3 = ImageIO.read(new File("images/fish3.png"));
					seaweed_image = ImageIO.read(new File("images/seaweed.png"));
					fish1 = ImageIO.read(new File("images/fish.png"));
					fish2 = ImageIO.read(new File("images/fish2.png"));
					energy_image = ImageIO.read(new File("images/energy.png"));
					osprey = ImageIO.read(new File("images/osprey.png"));
					
				} catch(IOException e) {
					e.printStackTrace();
				}
				
	}
	
	/**
	 * Creates different layouts for the game.
	 * 
	 * @param none
	 * @return none
	 */
	public void createlayouts() {
		menupanel = new DrawPanel();
		menupanel.setLayout(null);
		menupanel.setBackground(Color.white);
		
		/*
		JLabel game1 = new JLabel("Press 1 to play Osprey Game");
		game1.setBounds(200,50,400,100);
		JLabel game2 = new JLabel("Press 2 to play Clapper Rail Game");
		game2.setBounds(200,200,400,100);
		menupanel.add(game1);
		menupanel.add(game2);
		*/
		
		game1panel = new DrawPanel();
		game1panel.setLayout(null);
		JLabel menu1 = new JLabel("[M] Menu");
		menu1.setBounds(frameWidth-150,10, 100, 30);
		game1panel.add(menu1);
		
		game2panel = new DrawPanel();
		game2panel.setLayout(null);
		JLabel menu2 = new JLabel("[M] Menu");
		menu2.setBounds(frameWidth-150,10, 100, 30);
		game2panel.add(menu2);
		
		end1panel = new DrawPanel();
		end1panel.setLayout(null);
		end1panel.setBackground(Color.gray);
		
		end2panel = new DrawPanel();
		end2panel.setLayout(null);
		end2panel.setBackground(Color.gray);
	}
	
	/**
	 * Adds game object storage to view.
	 * 
	 * @param GobjS
	 * @return none
	 */
	public void addGameObjectStorageToView(GameObjectStorage GobjS) {
		this.GobjS = GobjS;
	}
	
	/**
	 * Creates new buffered image.
	 * 
	 * @param none
	 * @return buffered image
	 */
	public BufferedImage createBufferedImage() {
		return new BufferedImage(1,1,1);
	}
	
	/**
	 * Initializes background for each game.
	 * 
	 * @param int representing current panel
	 * @return none
	 */
	public void initializeBackground(int currentpanel) {
		if(currentpanel == 1) {
			this.background = g1_backimage;
		}
		else if(currentpanel == 2) {
			this.background = g2_background;
		}
	}
	
	/**
	 * Initializes player image depending on if player is playing game 1 (osprey) or game 2 (clapper rail).
	 * 
	 * @param int representing current panel
	 * @return none
	 */
	public void initializeGameImages(int currentpanel) {
		if(currentpanel == 1) {
			GobjS.getPlayer().setImg(osprey);
		}
		else if(currentpanel == 2) {
			GobjS.getPlayer().setImg(clapperrail_image);
		}
	}
	public void setG1EnergyCount(int G1Energy) {
		this.G1EnergyCount = G1Energy;
	}
	private class DrawPanel extends JPanel{
		
		/**
		 * Paints the components for each panel depending on which panel player is on. 
		 * 
		 * @param g 
		 * @return none
		 */
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (this.equals(menupanel)) {
				g.drawImage(omenu, 0, 0, this);
				g.drawImage(crmenu, frameWidth/2, 0, this);
				g.fillRect(frameWidth/2 - 10, 0, 20, frameHeight);
			}
			if (this.equals(game1panel)) {
				if(tutorialflag1) {
					if(!learnmovementflag1) {
						g.drawImage(space, frameWidth/4, frameHeight/10, 200, 150, this);
						g.drawString("Use space bar to dive.", frameWidth/3 + 200, frameHeight/4);
						if(GobjS.score.totalScore >= 1) {
							learnmovementflag1 = true;
						}
					}
					this.paintPlayer(g);
					if(learnmovementflag1) {
						this.paintScoringObjects(g);
						if(!learnscoringflag1) {
							g.drawString("Collect fish while avoiding seaweed to build up energy!", frameWidth/3 + 200, frameHeight/4);
							g.drawImage(upArrow, 10, 50, 50, 50, this);
						}
						this.paintScoringObjects(g);
						this.paintEnergy(g);
						if(GobjS.score.totalScore > 5) {
							learnscoringflag1 = true;
							System.out.println("Scoring learned");
							tutorialflag1 = false;
							tutorialcount1++;
						}
					}
				}
				//g.drawImage(background, 0, 0, Color.gray, this);
				this.paintPlayer(g);
				this.paintScoringObjects(g);
				this.paintEnergy(g);
			}
			if (this.equals(game2panel)) {
				
				if (tutorialflag) {
					if (!learnmovementflag) {
						g.drawImage(arrows, frameWidth/3, frameHeight/10, 100, 150, this);
						g.drawString("Use arrow keys to move.", frameWidth/3 + 200, frameHeight/4);
					}
					this.paintPlayer(g);
					
					if (learnmovementflag) {
						if (!learnscoringflag) {
							g.drawImage(space, frameWidth/3, frameHeight/10, 100, 150, this);
							g.drawString("Press space to eat, but avoid the trash!.", frameWidth/3 + 200, frameHeight/4);
							g.drawString("Each piece of food gets you an egg, try to get more than 3!", frameWidth/3 + 200, frameHeight/4 + 20);
						}
						this.paintScoringObjects(g);
						
						this.paintEggs(g);
						
						if (GobjS.score.totalScore > 3) {
							learnscoringflag = true;
							System.out.println("scoring learned");
							tutorialflag = false;
							tutorialcount++;
						}
					}
				}
				
				else {
				//g.drawImage(background, 0, 0, Color.gray, this);
				this.paintPlayer(g);
				this.paintScoringObjects(g);
				this.paintEggs(g);
				this.paintFox(g);
				this.paintTimer(g);
				}
			}
			if (this.equals(end1panel)) {
				//just draw something temp on panel for now
				g.drawImage(osprey, GobjS.getPlayer().getXloc(), GobjS.getPlayer().getYloc(), GobjS.getPlayer().getImageWidth(), GobjS.getPlayer().getImageHeight(), this);
				g.drawString(GobjS.score.toString(), 400, 200);
			}
			if (this.equals(end2panel)) {
				//just draw something temp on panel for now
				g.drawImage(clapperrail_image, GobjS.getPlayer().getXloc(), GobjS.getPlayer().getYloc(), GobjS.getPlayer().getImageWidth(), GobjS.getPlayer().getImageHeight(), this);
				g.drawString(GobjS.score.toString(), 400, 200);
			}
			}
		
		/**
		 * Paints player. Osprey for game 1 player, clapper rail for game 2 player. 
		 * 
		 * @param g
		 * @return none
		 */
		public void paintPlayer(Graphics g) {
			g.drawImage(GobjS.getPlayer().getImg(), GobjS.getPlayer().getXloc(), GobjS.getPlayer().getYloc(), GobjS.getPlayer().getImageWidth(), GobjS.getPlayer().getImageHeight(), this);
		}
		
		/**
		 * Paints scoring objects for each game. Fish and seaweed for game 1, food and trash for game 2. 
		 * 
		 * @param g
		 * @return none
		 */
		public void paintScoringObjects(Graphics g) {
			for(ScoringObject so : GobjS.getScoringObjects()) {
				g.drawImage(so.getImg(), so.getXloc(), so.getYloc(), so.getImageWidth(), so.getImageHeight(), this);
			}
		}
		
		/**
		 * Paints eggs for scoring in game 2 (clapper rail).
		 * 
		 * @param g
		 * @return none
		 */
		public void paintEggs(Graphics g) {
			for (int i=0; i < GobjS.score.totalScore;i++) {
				g.drawImage(egg_image, 30+20*i,10, 40, 50, this);
			}
		}
		
		/**
		 * Paints fox for game 2 (clapper rail).
		 * 
		 * @param g
		 * @return none
		 */
		public void paintFox(Graphics g) {
			g.drawImage(GobjS.getFox().getImg(), GobjS.getFox().getXloc(), GobjS.getFox().getYloc(), GobjS.getFox().getImageWidth(), GobjS.getFox().getImageHeight(), this);
		}
		
		/**
		 * Paints sun and moon timers for game 2 (clapper rail).
		 * 
		 * @param g
		 * @return none
		 */
		public void paintTimer(Graphics g) {
			g.drawImage(sun_image, GobjS.getSunTimer().getXLoc(),GobjS.getSunTimer().getYLoc(), 100, 100, this);
			g.drawImage(moon_image, GobjS.getMoonTimer().getXLoc(),GobjS.getMoonTimer().getYLoc(), 100, 100, this);
		}
		
		/**
		 * Paints energy for scoring in game 1 (osprey).
		 * 
		 * @param g
		 * @return none
		 */
		public void paintEnergy(Graphics g) {
			for (int i=0; i < GobjS.score.totalScore;i++) {
				g.drawImage(energy_image, 5+20*i,10, 30, 40, this);
			}
		}
	}
	
	public static BufferedImage getScaledImage(BufferedImage image, int width, int height) throws IOException {
	    int imageWidth  = image.getWidth();
	    int imageHeight = image.getHeight();

	    double scaleX = (double)width/imageWidth;
	    double scaleY = (double)height/imageHeight;
	    AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX, scaleY);
	    AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);

	    return bilinearScaleOp.filter(
	        image,
	        new BufferedImage(width, height, image.getType()));
	}
	/**
	 * adds the Controller class as the listener to buttons in View
	 * @param the controller instance 
	 * @return none
	 * @author Brendan Azueta
	 */
	/*
	public void addControllertoButton(Controller c) {
		game1.addActionListener(c);
		game2.addActionListener(c);
		
	}
	*/
	
	
}