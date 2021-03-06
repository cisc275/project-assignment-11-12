package project;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class View extends JFrame{
	
	static int frameWidth, frameHeight;
	
	GameObjectStorage GobjS;
	CardLayout cl = new CardLayout();
	DrawPanel panelContainer, menupanel, game1panel, game2panel, end1panel, end2panel, end3panel;
	
	boolean quizflag = false;
	boolean foxDirectionflag = false;
	int G1EnergyCount = 0;
	
	//game 1 tutorial
	int tutorialcount1 = 0;
	boolean tutorialflag1 = true;
	boolean learndiveflag1 = false;
	boolean learnmovementflag1 = false;
	boolean learnscoringflag1 = false;

	// game 2 tutorial
	int tutorialcount = 0;
	boolean tutorialflag = true;
	boolean learnmovementflag = false;
	boolean learnscoringflag = false;
	
	int CRframeCount, frameCount;
	int picNum, picNumMap;
	
	BufferedImage g1_background;
	BufferedImage[] map;
	Image osprey;
	Image fish1,fish2,fish3;
	Image seaweed_image;
	Image energy_image;
	
	BufferedImage g2_background;
	BufferedImage[][] CR_images;
	BufferedImage clapperrail_image;
	Image food,food2,food3,food4;
	Image trash,trash2,trash3,trash4;
	Image egg_image;
	BufferedImage[] fox_images;
	Image sun_image;
	Image moon_image;
	
	BufferedImage background;
	BufferedImage crmenu;
	BufferedImage omenu;
	BufferedImage end1, end2, end3;

	
	Image space;
	Image upArrow;
	Image arrowKeys;
	Image redX, checkmark;
	Image plus, minus;

	public View() {
		CRframeCount = 3;
		frameCount = 23;
		picNum = 0;
		picNumMap=0;
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		Dimension viewdim = this.getSize();
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
		panelContainer.add(end3panel, "5");
				
		cl.show(panelContainer, "0");
		
		//load images
		try {
			g1_background = ImageIO.read(new File("images/g1_background.png"));
			g1_background = getScaledImage(g1_background, frameWidth, frameHeight);
			map = createMapimages();
			osprey = ImageIO.read(new File("images/osprey.png"));
			fish1 = ImageIO.read(new File("images/fish.png"));
			fish2 = ImageIO.read(new File("images/fish2.png"));
			fish3 = ImageIO.read(new File("images/fish3.png"));
			seaweed_image = ImageIO.read(new File("images/seaweed.png"));
			energy_image = ImageIO.read(new File("images/energy.png"));
			
			g2_background = ImageIO.read(new File("images/wetland2.jpg"));
			g2_background = getScaledImage(g2_background, frameWidth, frameHeight);
			CR_images = createCRimages();
			clapperrail_image = CR_images[0][0]; //initialize a image to start with
			food = ImageIO.read(new File("images/food.png"));
			food2 = ImageIO.read(new File("images/food2.png"));
			food3 = ImageIO.read(new File("images/food3.png"));
			food4 = ImageIO.read(new File("images/food4.png"));
			trash = ImageIO.read(new File("images/trash.png"));
			trash2 = ImageIO.read(new File("images/trash2.png"));
			trash3 = ImageIO.read(new File("images/trash3.png"));
			trash4 = ImageIO.read(new File("images/trash4.png"));
			egg_image = ImageIO.read(new File("images/egg.png"));
			fox_images = createFOXimages();
			sun_image = ImageIO.read(new File("images/sun.png"));
			moon_image = ImageIO.read(new File("images/moon.png"));
			
			omenu = ImageIO.read(new File("images/omenu.png"));
			omenu = getScaledImage(omenu, frameWidth/2, frameHeight);
			crmenu = ImageIO.read(new File("images/crmenu.png"));
			crmenu = getScaledImage(crmenu, frameWidth/2, frameHeight);
			end1 = ImageIO.read(new File("images/end1.png"));
			end1 = getScaledImage(end1, frameWidth, frameHeight);
			end2 = ImageIO.read(new File("images/end2.png"));
			end2 = getScaledImage(end2, frameWidth, frameHeight);
			end3 = ImageIO.read(new File("images/end3.png"));
			end3 = getScaledImage(end3, frameWidth, frameHeight);
			
			space = ImageIO.read(new File("images/space.jpeg"));
			upArrow = ImageIO.read(new File("images/upArrow.png"));
			arrowKeys = ImageIO.read(new File("images/arrowkeys.jpg"));
			redX = ImageIO.read(new File("images/RedX.png"));
			checkmark = ImageIO.read(new File("images/Checkmark.png"));
			plus = ImageIO.read(new File("images/plus.png"));
			minus = ImageIO.read(new File("images/minus.png"));
			} catch(IOException e) {
					e.printStackTrace();
			}	
	}
	
	/**
	 * Creates the fox buffered images for game 2
	 * 
	 * @param none
	 * @return BufferedImage array
	 * @author Anna Bortle
	 */
	public BufferedImage[] createFOXimages(){
		fox_images = new BufferedImage[23];
		try {
			for (int i=0; i<23; i++) {
				fox_images[i] = ImageIO.read(new File("images/fox/fox"+ i + ".png"));
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return fox_images;
	}
	
	public BufferedImage[] createMapimages() {
		map = new BufferedImage[23];
		try {
			for (int i = 0; i<23; i++) {
				map[i] = ImageIO.read(new File("images/minimap/mm" + (i+1) + ".png"));
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * Creates the clapper rail buffered images for game 2
	 * 
	 * @param none
	 * @return BufferedImage array
	 * @author Anna Bortle
	 */
	public BufferedImage[][] createCRimages(){
		CR_images = new BufferedImage[4][3];
		try {
			for (int i=0; i<3; i++) {
				CR_images[0][i] = ImageIO.read(new File("images/claprail_right_"+(i+1)+".png"));
				CR_images[1][i] = ImageIO.read(new File("images/claprail_right_water_"+(i+1)+".png"));
				CR_images[2][i] = ImageIO.read(new File("images/claprail_left_"+(i+1)+".png"));
				CR_images[3][i] = ImageIO.read(new File("images/claprail_left_water_"+(i+1)+".png"));
			}    
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return CR_images;
	}
	
	/**
	 * Updates the BufferedImage for the CR and Fox
	 * 
	 * @param loc (location & heading of CR, i.e. in water/on land, facing right/left)
	 * @return none
	 * @author Anna Bortle
	 */
	public void updateG2images(int loc) {
		GobjS.getPlayer().setImg((CR_images[loc][picNum%CRframeCount]) );
		GobjS.getFox().setImg(fox_images[picNum%frameCount]);
		}
	
	/**
	 * Allows Controller to update View on which way fox should be facing.
	 * 
	 * @param Mflag: Model's flag to designate fox direction
	 * @return none
	 * @author Anna Bortle
	 */
	public void updateFoxDirection(boolean Mflag) {
		foxDirectionflag = Mflag;
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

		
		game1panel = new DrawPanel();
		game1panel.setLayout(null);
		JLabel menu1 = new JLabel("[M] Menu");
		menu1.setBounds(frameWidth-Constants.MENU_SUBX,Constants.MENU_Y, Constants.MENU_W, Constants.MENU_H);
		game1panel.add(menu1);
		
		game2panel = new DrawPanel();
		game2panel.setLayout(null);
		JLabel menu2 = new JLabel("[M] Menu");
		menu2.setBounds(frameWidth-Constants.MENU_SUBX,Constants.MENU_Y, Constants.MENU_W, Constants.MENU_H);
		game2panel.add(menu2);
		
		end1panel = new DrawPanel();
		end1panel.setLayout(null);
		end1panel.setBackground(Color.gray);
		
		end2panel = new DrawPanel();
		end2panel.setLayout(null);
		end2panel.setBackground(Color.gray);
		
		end3panel = new DrawPanel();
		end3panel.setLayout(null);
		end3panel.setBackground(Color.gray);
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
			this.background = g1_background;
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
			picNum++;
			if (this.equals(menupanel)) {
				g.drawImage(omenu, Constants.INITIAL_POS, Constants.INITIAL_POS, this);
				g.drawImage(crmenu, frameWidth/Constants.D2, Constants.INITIAL_POS, this);
				g.fillRect(frameWidth/Constants.D2 - Constants.TEN, Constants.INITIAL_POS, Constants.FONT_SIZE_ARIAL, frameHeight);
				g.drawString("[Esc] Exit Game", Constants.TEN, Constants.FONT_SIZE_ARIAL);
			}
			if (this.equals(game1panel)) {
				g.drawImage(g1_background, Constants.INITIAL_POS, Constants.INITIAL_POS, Color.gray, this);
				if(tutorialflag1) {
					if(!learndiveflag1) {
						g.setFont(new Font("Arial", Font.PLAIN, Constants.FONT_SIZE_ARIAL));
						g.drawImage(space, frameWidth/Constants.D7-Constants.SPACE_SUBX, Constants.SPACE_Y, Constants.SPACE_W, Constants.SPACE_H, this);
						g.drawImage(fish1, frameWidth-(frameWidth/Constants.D4), (frameWidth+Constants.ADD_TO_Y1)-frameWidth, Constants.FISH_IMAGE_W, Constants.FISH_IMAGE_H, this);
						g.drawImage(fish2, frameWidth-(frameWidth/Constants.D4), (frameWidth+Constants.ADD_TO_Y2)-frameWidth, Constants.FISH_IMAGE_W, Constants.FISH_IMAGE_H, this);
						g.drawImage(fish3, frameWidth-(frameWidth/Constants.D4), (frameWidth+Constants.ADD_TO_Y3)-frameWidth, Constants.FISH_IMAGE_W, Constants.FISH_IMAGE_H, this);
						g.drawImage(seaweed_image, frameWidth-(frameWidth/Constants.D4), (frameWidth+Constants.SEAWEED_ADD_TO_Y)-frameWidth, Constants.FISH_IMAGE_W, Constants.SEAWEED_IMAGE_H, this);
						g.drawImage(checkmark, frameWidth-(frameWidth/Constants.D6), (frameWidth+Constants.ADD_TO_Y1)-frameWidth, Constants.FISH_IMAGE_W, Constants.FISH_IMAGE_H, this);
						g.drawImage(checkmark, frameWidth-(frameWidth/Constants.D6), (frameWidth+Constants.ADD_TO_Y2)-frameWidth, Constants.FISH_IMAGE_W, Constants.FISH_IMAGE_H, this);
						g.drawImage(checkmark, frameWidth-(frameWidth/Constants.D6), (frameWidth+Constants.ADD_TO_Y3)-frameWidth, Constants.FISH_IMAGE_W, Constants.FISH_IMAGE_H, this);
						g.drawImage(redX, frameWidth-(frameWidth/Constants.D6), (frameWidth+Constants.X_ADD_TO_Y)-frameWidth, Constants.FISH_IMAGE_W, Constants.FISH_IMAGE_H, this);
						g.drawString("Use space bar to dive.", frameWidth/Constants.D7, frameHeight/Constants.D3);						
						if(GobjS.score.totalScore >= 4) {
							learndiveflag1 = true;
						}
					}
					//learn to move left and right in GAME 1
					if(learndiveflag1) {
						if(!learnmovementflag1) {
							g.setFont(new Font("Arial", Font.PLAIN, Constants.FONT_SIZE_ARIAL));
							g.setColor(Color.YELLOW);
							g.fillRect(frameWidth/Constants.D3+Constants.RECT1_OFFSET, (frameHeight+Constants.RECT_ADD_Y)-frameHeight, Constants.RECT_HW, Constants.RECT_HW);
							g.fillRect(frameWidth/Constants.D3+Constants.RECT2_OFFSET, (frameHeight+Constants.RECT_ADD_Y)-frameHeight, Constants.RECT_HW, Constants.RECT_HW);
							g.drawImage(arrowKeys, frameWidth/Constants.D3+Constants.ARROW_OFFSET, (frameHeight+Constants.KEYS_ADD_Y)-frameHeight, Constants.ARROWKEYS_HW, Constants.ARROWKEYS_HW, this);
							g.setColor(Color.BLACK);
							g.drawString("Use the Arrow Keys to move left and right!", frameWidth/Constants.D3+Constants.STRING_ADD_X, frameHeight/Constants.D3);
							if(GobjS.score.totalScore >= 10) {
								learnmovementflag1 = true;
							}
						}
					}
					//FINISH TUTORIAL FOR GAME 1
					this.paintPlayer(g);
					if(learnmovementflag1) {
						this.paintScoringObjects(g);
						if(!learnscoringflag1) {
							g.setFont(new Font("Arial", Font.PLAIN, Constants.FONT_SIZE_ARIAL));
							g.drawString("Collect fish while avoiding seaweed to build up energy!", frameWidth/Constants.D3 + Constants.STRING_ADD_X2, frameHeight/Constants.D4);
							g.drawImage(upArrow, frameWidth/Constants.D12, frameHeight/Constants.D12, Constants.UP_ARROW_W, Constants.UP_ARROW_H, this);
						}
						this.paintScoringObjects(g);
						this.paintEnergy(g);
						if(GobjS.score.totalScore > 13) {
							learnscoringflag1 = true;
							System.out.println("Scoring learned");
							tutorialflag1 = false;
							tutorialcount1++;
						}
					}
				}
				this.paintPlayer(g);
				this.paintScoringObjects(g);
				this.paintEnergy(g);
				if (!tutorialflag1) {
					this.paintMap(g);
				}
			}
			if (this.equals(game2panel)) {
				g.drawImage(g2_background, Constants.INITIAL_POS, Constants.INITIAL_POS, Color.white, this);
				
				if (tutorialflag) {
					if (!learnmovementflag) {
						g.setFont(new Font("Arial", Font.PLAIN, Constants.FONT_SIZE_ARIAL));
						g.drawImage(arrowKeys, frameWidth/Constants.D3, frameHeight/Constants.ADD_TO_Y1, Constants.AKEYS_IMW, Constants.AKEYS_IMH, this);
					}
					this.paintPlayer(g);
					
					if (learnmovementflag) {
						if (!learnscoringflag) {
							g.setFont(new Font("Arial", Font.PLAIN, Constants.FONT_SIZE_ARIAL));
							g.drawImage(space, frameWidth/Constants.D4, frameHeight/Constants.ADD_TO_Y1, Constants.SPACE_IMW, Constants.SPACE_IMH, this);

							g.drawImage(food, (frameWidth-(frameWidth/Constants.D2))+Constants.FOOD_ADD_TO_X, (frameWidth+Constants.ADD_TO_Y1)-frameWidth, Constants.FOOD_TRASH_IMW, Constants.FOOD_TRASH_IMH, this);
							g.drawImage(food2, (frameWidth-(frameWidth/Constants.D2))+Constants.FOOD_ADD_TO_X, (frameWidth+Constants.ADD_TO_Y2)-frameWidth, Constants.FOOD_TRASH_IMW, Constants.FOOD_TRASH_IMH, this);
							g.drawImage(food3, (frameWidth-(frameWidth/Constants.D2))+Constants.FOOD_ADD_TO_X, (frameWidth+Constants.ADD_TO_Y3)-frameWidth, Constants.FOOD_TRASH_IMW, Constants.FOOD_TRASH_IMH, this);
							g.drawImage(food4, (frameWidth-(frameWidth/Constants.D2))+Constants.FOOD_ADD_TO_X, (frameWidth+Constants.FT_ADD_TO_Y4)-frameWidth, Constants.FOOD_TRASH_IMW, Constants.FOOD_TRASH_IMH, this);
							g.drawImage(checkmark, frameWidth-(frameWidth/Constants.D3), (frameWidth+Constants.ADD_TO_Y1)-frameWidth, Constants.FOOD_TRASH_IMW, Constants.FOOD_TRASH_IMH, this);
							g.drawImage(checkmark, frameWidth-(frameWidth/Constants.D3), (frameWidth+Constants.ADD_TO_Y2)-frameWidth, Constants.FOOD_TRASH_IMW, Constants.FOOD_TRASH_IMH, this);
							g.drawImage(checkmark, frameWidth-(frameWidth/Constants.D3), (frameWidth+Constants.ADD_TO_Y3)-frameWidth, Constants.FOOD_TRASH_IMW, Constants.FOOD_TRASH_IMH, this);
							g.drawImage(checkmark, frameWidth-(frameWidth/Constants.D3), (frameWidth+Constants.CX_ADD_TO_Y4)-frameWidth, Constants.FOOD_TRASH_IMW, Constants.FOOD_TRASH_IMH, this);

							g.drawImage(plus, (frameWidth-(frameWidth/Constants.D2))+Constants.PLUS_ADDX, (frameWidth+Constants.PM_ADD_Y)-frameWidth, Constants.PLUS, Constants.PLUS, this);
							g.drawImage(egg_image, (frameWidth-(frameWidth/Constants.D2))+Constants.EGG_ADDX, (frameWidth+Constants.PM_ADD_Y)-frameWidth, Constants.EGG_IMW, Constants.EGG_IMH, this);

							g.drawImage(trash, frameWidth-(frameWidth/Constants.D4), (frameWidth+Constants.ADD_TO_Y1)-frameWidth, Constants.FOOD_TRASH_IMW, Constants.FOOD_TRASH_IMH, this);
							g.drawImage(trash2, frameWidth-(frameWidth/Constants.D4), (frameWidth+Constants.ADD_TO_Y2)-frameWidth, Constants.FOOD_TRASH_IMW, Constants.FOOD_TRASH_IMH, this);
							g.drawImage(trash3, frameWidth-(frameWidth/Constants.D4), (frameWidth+Constants.ADD_TO_Y3)-frameWidth, Constants.FOOD_TRASH_IMW, Constants.FOOD_TRASH_IMH, this);
							g.drawImage(trash4, frameWidth-(frameWidth/Constants.D4), (frameWidth+Constants.FT_ADD_TO_Y4)-frameWidth, Constants.FOOD_TRASH_IMW, Constants.FOOD_TRASH_IMH, this);
							g.drawImage(redX, frameWidth-(frameWidth/Constants.D6), (frameWidth+Constants.ADD_TO_Y1)-frameWidth, Constants.FOOD_TRASH_IMW, Constants.FOOD_TRASH_IMH, this);
							g.drawImage(redX, frameWidth-(frameWidth/Constants.D6), (frameWidth+Constants.ADD_TO_Y2)-frameWidth, Constants.FOOD_TRASH_IMW, Constants.FOOD_TRASH_IMH, this);
							g.drawImage(redX, frameWidth-(frameWidth/Constants.D6), (frameWidth+Constants.ADD_TO_Y3)-frameWidth, Constants.FOOD_TRASH_IMW, Constants.FOOD_TRASH_IMH, this);
							g.drawImage(redX, frameWidth-(frameWidth/Constants.D6), (frameWidth+Constants.CX_ADD_TO_Y4)-frameWidth, Constants.FOOD_TRASH_IMW, Constants.FOOD_TRASH_IMH, this);

							g.drawImage(minus, (frameWidth-(frameWidth/Constants.D2))+Constants.MINUS_ADDX, (frameWidth+Constants.PM_ADD_Y)-frameWidth, Constants.MINUS, Constants.MINUS, this);
							g.drawImage(egg_image, (frameWidth-(frameWidth/Constants.D2))+Constants.EGG_ADDX2, (frameWidth+Constants.PM_ADD_Y)-frameWidth, Constants.EGG_IMW, Constants.EGG_IMH, this);

							g.drawString("Press spacebar to eat, but avoid the trash!", frameWidth/Constants.D4, (frameHeight/Constants.ADD_TO_Y1)+Constants.PLUS_ADDX);
						}
						
						this.paintScoringObjects(g);
						this.paintEggs(g);
						
						if (GobjS.score.totalScore > 5) {
							learnscoringflag = true;
							tutorialflag = false;
							tutorialcount++;
						}
					}
				}
				else {
					this.paintPlayer(g);
					this.paintScoringObjects(g);
					this.paintEggs(g);
					this.paintFox(g);
					this.paintTimer(g);
				}
			}
			if (this.equals(end1panel)) {
				g.setFont(new Font("TimesRoman", Font.PLAIN, Constants.FONT_SIZE_TR)); 
				g.drawImage(end1, Constants.INITIAL_POS,Constants.INITIAL_POS, this);
				g.drawString(GobjS.score.toString(), frameWidth/Constants.D2 - Constants.END_STRING_SUBX, frameHeight/Constants.D3 + Constants.END_STRING_ADDY);
				g.drawString("[M] Main Menu", frameWidth/Constants.D2 - Constants.END_STRING_SUBX, frameHeight/Constants.D2+ Constants.END_STRING_ADDY);
			}
			else if (this.equals(end2panel)) {
				g.setFont(new Font("TimesRoman", Font.PLAIN, Constants.FONT_SIZE_TR)); 
				g.drawImage(end2, Constants.INITIAL_POS,Constants.INITIAL_POS, this);
				g.drawString(GobjS.score.toString(), frameWidth/Constants.D2 - Constants.END_STRING_SUBX, frameHeight/Constants.D3 + Constants.END_STRING_ADDY);
				g.drawString("[M] Main Menu", frameWidth/Constants.D2 - Constants.END_STRING_SUBX, frameHeight/Constants.D2+ Constants.END_STRING_ADDY);
			}
			else if (this.equals(end3panel)) {
				g.setFont(new Font("TimesRoman", Font.PLAIN, Constants.FONT_SIZE_TR)); 
				g.drawImage(end3, Constants.INITIAL_POS,Constants.INITIAL_POS, this);
				g.drawString(GobjS.score.toString(), frameWidth/Constants.D2 - Constants.END_STRING_SUBX, frameHeight/Constants.D3 + Constants.END_STRING_ADDY);
				g.drawString("[M] Main Menu", frameWidth/Constants.D2 - Constants.END_STRING_SUBX, frameHeight/Constants.D2+ Constants.END_STRING_ADDY);
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
		 * Paints the minimap for game 1.
		 * @param g
		 * @return none
		 * @author Anna Bortle
		 */
		public void paintMap(Graphics g) {
			if (picNum%110==0 && !tutorialflag1) {
				picNumMap++;
			}
			g.drawImage(map[picNumMap%frameCount], Constants.INITIAL_POS, frameHeight/Constants.D9, Constants.MINIMAP_XY,Constants.MINIMAP_XY, this);
		}
		/**
		 * Paints eggs for scoring in game 2 (clapper rail).
		 * 
		 * @param g
		 * @return none
		 */
		public void paintEggs(Graphics g) {
			for (int i=0; i < GobjS.score.totalScore;i++) {
				g.drawImage(egg_image, Constants.EGGS_X+Constants.EGGS_ADD_X*i,Constants.EGGS_Y, Constants.EGGS_IMW, Constants.EGGS_IMH, this);
			}
		}
		
		/**
		 * Paints fox for game 2 (clapper rail).
		 * 
		 * @param g
		 * @return none
		 */
		public void paintFox(Graphics g) {
			if (!foxDirectionflag) {
				g.drawImage(GobjS.getFox().getImg(), GobjS.getFox().getXloc(), GobjS.getFox().getYloc(), GobjS.getFox().getImageWidth(), GobjS.getFox().getImageHeight(), this);
			}
			else {
				g.drawImage(GobjS.getFox().getImg(), GobjS.getFox().getXloc() + GobjS.getFox().getImg().getWidth(this)/2, GobjS.getFox().getYloc(), -GobjS.getFox().getImageWidth(), GobjS.getFox().getImageHeight(), this);
			}
		}
		
		/**
		 * Paints sun and moon timers for game 2 (clapper rail).
		 * 
		 * @param g
		 * @return none
		 */
		public void paintTimer(Graphics g) {
			g.drawImage(sun_image, GobjS.getSunTimer().getXLoc(),GobjS.getSunTimer().getYLoc(), Constants.SUN_MOON_IMAGE_W_H, Constants.SUN_MOON_IMAGE_W_H, this);
			g.drawImage(moon_image, GobjS.getMoonTimer().getXLoc(),GobjS.getMoonTimer().getYLoc(), Constants.SUN_MOON_IMAGE_W_H, Constants.SUN_MOON_IMAGE_W_H, this);
		}
		
		/**
		 * Paints energy for scoring in game 1 (osprey).
		 * 
		 * @param g
		 * @return none
		 */
		public void paintEnergy(Graphics g) {
			for (int i=0; i < GobjS.score.totalScore;i++) {
				g.drawImage(energy_image, Constants.ENERGY_X+Constants.ENERGY_ADD_X*i,Constants.ENERGY_Y, Constants.ENERGY_IMW, Constants.ENERGY_IMH, this);
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
}