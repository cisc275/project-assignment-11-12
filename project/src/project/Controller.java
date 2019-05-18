package project;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;


public class Controller implements ActionListener, KeyListener {
	
	Model model;
	View view;
	Quiz quiz;
	Timer t;
	final int drawDelay = 30; // change this to 25
	Action drawAction;
	private int clockcount = 0;
	int currentpanel;
	
	boolean upflag = false;
	boolean downflag = true;
	
	Controller(){
		this.initializeView();
		this.initializeModel();
		drawAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
					view.repaint();
					view.addGameObjectStorageToView(model.getGobjS());
					model.updateGame(currentpanel);
					checkQuiz();
					clockcount++;
					if (clockcount > 2000) { //2000*drawDelay[30] = 60000 = 1.0min
						endGame();	
					}
			}
		};
		
	}
	
	/**
	 * Checks for the existence of a quiz and adds itself to it as an ActionListener.
	 * 
	 * @param none
	 * @return none
	 * @author Anna Bortle
	 */
	public void checkQuiz() {
		if (model.GobjS.getFox() != null) {
			if (model.GobjS.getFox().flag == false) {
				model.GobjS.getFox().quiz.addListenertoQuiz(this);
				model.GobjS.getFox().quiz.setVisible(true); 
				model.GobjS.getFox().flag = true;
			}
		}
	}
	
	public void initializeView() {
		view = new View();
		view.addControllertoButton(this);
		view.addKeyListener(this);
		view.setFocusable(true);
		view.setFocusTraversalKeysEnabled(false);
	}
	
	/**
	 * Switches to the appropriate panel after the game ends.
	 * 
	 * @param none
	 * @return none
	 * @author Anna Bortle
	 */
	public void endGame() {
		if (currentpanel == 1) {
			view.cl.show(view.panelContainer, "3");
			currentpanel = 3;
		}
		else {
			view.cl.show(view.panelContainer, "4");
			currentpanel = 4;
		}
	}
	
	public void initializeModel() {
		model = new Model();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object button = e.getSource();
		if (e.getSource().equals(view.game1)) {
			System.out.println("game1 button pressed");
			view.cl.show(view.panelContainer, "1");
			currentpanel = 1;
			model.initializeGameOne();
		}
		else if (e.getSource().equals(view.game2)) {
			System.out.println("game2 button pressed");
			view.cl.show(view.panelContainer, "2");
			currentpanel = 2;
			model.initializeGameTwo();
		}
		else if (e.getSource().equals(view.menu2) || e.getSource().equals(view.menu1)) {
			System.out.println("menu button pressed");
			model.getGobjS().getScoringObjects().removeAll(model.getGobjS().getScoringObjects()); //clear scoring objects
			view.cl.show(view.panelContainer, "0");
			clockcount = 0;
			currentpanel = 0;
		}
		else if (e.getSource().equals(model.GobjS.getFox().quiz.ans1) || e.getSource().equals(model.GobjS.getFox().quiz.ans2)) {
			if (e.getSource().equals(model.GobjS.getFox().quiz.ans1)){
				System.out.println("ans1 pressed");
				model.updateQuizScore(1, model.GobjS.getFox().quiz.correctAns);
			}
			else {
				System.out.println("ans2 pressed");
				model.updateQuizScore(2, model.GobjS.getFox().quiz.correctAns);
			}
			model.GobjS.getFox().quiz.dispose();
		}
		view.initializeBackground(currentpanel);
	}

	public void start(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Timer t = new Timer(drawDelay, drawAction); //call drawAction every (drawDelay) msecs
				t.start();
				} 
		});	
		
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(model.getGobjS().getPlayer().imageHeight == Constants.CR_IMH) {
			int k = e.getKeyCode();
			switch( k ) { 
	        	case KeyEvent.VK_UP:
	        		if (upflag) {
	        			model.getGobjS().getPlayer().setyIncr(-Constants.CR_Y);
	        		}
	        		upflag = false;
	        		downflag = true;
	        		System.out.println("up");
	        		break;
	        		
	        	case KeyEvent.VK_DOWN:
	        		if (downflag) {
	        			model.getGobjS().getPlayer().setyIncr(Constants.CR_Y);
	        		}
	        		downflag = false;
	        		upflag = true;
	        		System.out.println("down");
	        		break;
	        		
	        	case KeyEvent.VK_LEFT:
	        		System.out.println("left");
	        		if (!(model.getGobjS().getPlayer().getXloc() - Constants.CR_X < 0)){
	        			model.getGobjS().getPlayer().setxIncr(-Constants.CR_X);
	        		}
	        		break;
	        	case KeyEvent.VK_RIGHT :
	        		System.out.println("right");
	        		if (!(model.getGobjS().getPlayer().getXloc() + model.getGobjS().getPlayer().getImageWidth() 
	        				+ Constants.CR_X > View.frameWidth)) {
	        			model.getGobjS().getPlayer().setxIncr(Constants.CR_X);
	        		}
	        		break;
	        	case KeyEvent.VK_SPACE:
	        		//System.out.println("space");
	        		if (currentpanel == 2) {
	        			model.eatFoodOrTrash();
	        			System.out.println("g2 space pressed");
	        		}
	        		if((model.getGobjS().getPlayer().getYloc() == Constants.CR_BOUND_BOTTOM) || (model.getGobjS().getPlayer().getYloc() == Constants.CR_BOUND_TOP) )
	        		{
	        			model.getGobjS().getPlayer().setyIncr(Constants.CR_Y_SPACE);
	        		}
	        		else {
	        			model.getGobjS().getPlayer().setyIncr(-Constants.O_upwardsYIncr);
	        		}
	        		break;
			}
		}
			if(model.getGobjS().p.imageHeight == Constants.O_IMH) {
				
				int k = e.getKeyCode();
				switch( k ) { 
		      
		        	case KeyEvent.VK_SPACE:
		        		model.getGobjS().getPlayer().setyIncr(-Constants.O_upwardsYIncr);
		        		System.out.println("space");
		        		break;
				}
			}
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		int key = arg0.getKeyCode();
		if(key == KeyEvent.VK_SPACE && currentpanel == 1) {
			//model.getGobjS().getPlayer().setyIncr(-O_Y);
		}
		if(key == KeyEvent.VK_SPACE && currentpanel == 2) {
			model.getGobjS().getPlayer().setyIncr(-Constants.CR_Y_SPACE);
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		int key = arg0.getKeyCode();
		if(key == KeyEvent.VK_SPACE && currentpanel == 1) {
			System.out.println("space typed");
		}
	}
	
	
	public static void main(String[] args) {
		Controller c = new Controller();
		c.start();
		//System.out.println("main called.");
	}
}