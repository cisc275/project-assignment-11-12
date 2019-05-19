package project;

import java.awt.BorderLayout;
import java.awt.Dialog;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuPopUp extends JDialog{

	private JPanel popmenupanel;

	public MenuPopUp() {
		
		this.setModalityType(Dialog.DEFAULT_MODALITY_TYPE.APPLICATION_MODAL);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setTitle("Exit to Main Menu");
		this.setFocusable(true);
		
		popmenupanel = new JPanel();
		this.getContentPane().add(popmenupanel,BorderLayout.CENTER);
		
		JLabel question = new JLabel("Are you sure you want to exit to Main Menu?");
		JLabel yes = new JLabel("YES : Press 1");
		JLabel no = new JLabel("CANCEL : Press 2");
		
		popmenupanel.add(question); popmenupanel.add(yes); popmenupanel.add(no);
		//this.setVisible(true);
	}
}
