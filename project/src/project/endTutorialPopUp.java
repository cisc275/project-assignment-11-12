package project;

import java.awt.BorderLayout;
import java.awt.Dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class endTutorialPopUp extends JDialog{

		private JPanel tutorialpanel;

		public endTutorialPopUp() {
			
			this.setModalityType(Dialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setSize(300, 300);
			this.setLocationRelativeTo(null);
			this.setTitle("Finish Tutorial");
			this.setFocusable(true);
			
			tutorialpanel = new JPanel();
			this.getContentPane().add(tutorialpanel,BorderLayout.CENTER);
			
			JLabel done = new JLabel("You've finished the tutorial!");
			JLabel play = new JLabel("Press P to continue to game.");
			
			tutorialpanel.add(done); tutorialpanel.add(play); 
		}
}
