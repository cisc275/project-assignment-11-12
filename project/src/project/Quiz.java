package project;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Quiz extends JDialog{

	private JPanel quizpanel;
	JLabel ans1, ans2;
	ArrayList<Question> questions;
	int correctAns;
	
	public Quiz() {
		loadquestions();
		
		this.setModalityType(Dialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(400, 200);
		this.setTitle("Fox Quiz");
		this.setLocationRelativeTo(null);
		this.setFocusable(true);
		
		quizpanel = new JPanel();
		this.getContentPane().add(quizpanel,BorderLayout.CENTER);
		
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		int r = rand.nextInt(questions.size());
		JLabel intro = new JLabel ("Uh Oh! A fox is watching you! \n Answer his questions correctly!");
		JLabel question = new JLabel(questions.get(r).question);
		ans1 = new JLabel("1: " + questions.get(r).ans1);
		ans2 = new JLabel("2: " + questions.get(r).ans2);
		correctAns = questions.get(r).correctAns;
		
		quizpanel.add(intro); quizpanel.add(question); quizpanel.add(ans1); quizpanel.add(ans2);
	}
	
	/**
	 * Creates questions for in-game quiz about clapper rail.
	 * @param none
	 * @return none
	 * @author Anna Bortle
	 */
	public void loadquestions() {
		questions = new ArrayList<>();
		
		questions.add(new Question("Clapper Rails are...", "migratory", "non-migratory", 2));
		questions.add(new Question("A body of water where a river meets the sea is called an...", "estuary", "actuary", 1));
		questions.add(new Question("Clapper Rails make their nests up high in trees.", "True", "False", 2));
		questions.add(new Question("Which of these would a Clapper Rail most likely eat?", "Fish", "Mouse", 1));
		questions.add(new Question("Clapper Rails spend a lot of their time flying", "True", "False", 2));
		questions.add(new Question("The Red Fox is a natural predator of the Clapper Rail", "True", "False", 1));
		questions.add(new Question("Clapper Rails lay...", "one large egg", "many small eggs", 2));
		questions.add(new Question("Clapper Rails have beaks that are...", "short and fat", "long and slender", 2));
		questions.add(new Question("The size of a Clapper Rail is similar to the size of a...", "chicken", "cardinal", 1));
		questions.add(new Question("Clapper Rails live in...", "marshes", "forests", 1));
	}
	
	/**
	 * Allows for outside classes to add an ActionListener to the quiz buttons.
	 * @param Controller c (ActionListener)
	 * @return none
	 * @author Anna Bortle
	 */
	public void addListenertoQuiz(Controller c) {
		this.addKeyListener(c);
	}
}
