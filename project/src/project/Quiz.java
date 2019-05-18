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
	Button ans1, ans2;
	ArrayList<Question> questions;
	int correctAns;
	
	public Quiz() {
		loadquestions();
		createQuiz();
	}
	
	public void createQuiz() {
		
		this.setModalityType(Dialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(300, 300);
		this.setTitle("Fox Quiz");
		
		quizpanel = new JPanel();
		this.getContentPane().add(quizpanel,BorderLayout.CENTER);
		
		Random rand = new Random();
		int r = rand.nextInt(questions.size());
		JLabel question = new JLabel(questions.get(r).question);
		Button ans1 = new Button(questions.get(r).ans1);
		Button ans2 = new Button(questions.get(r).ans2);
		correctAns = questions.get(r).correctAns;
		
		quizpanel.add(question); quizpanel.add(ans1); quizpanel.add(ans2);
		this.setVisible(true);
	}
	
	
	public void loadquestions() {
		questions = new ArrayList<>();
		//create questions
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
	
	public void addListenertoQuiz(Controller c) {
		ans1.addActionListener(c);
		ans2.addActionListener(c);
	}
}
