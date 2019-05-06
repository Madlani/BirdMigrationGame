package gamePackage;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("serial")
public class QuestionView extends View {
	
	HashMap<String, Integer> answers;
	JOptionPane question1;
	JButton aButton;
	JButton bButton;
	String[] questions;
	JButton[] options;
	
	public QuestionView()	{
		super();
		answers = new HashMap<>();
		questions = new String[2];
		aButton = new JButton("A");
		bButton = new JButton("B");
		options = new JButton[] {aButton, bButton};
		
		aButton.setMnemonic(KeyEvent.VK_LEFT);
		bButton.setMnemonic(KeyEvent.VK_RIGHT);
		
		answers.put("question1", KeyEvent.VK_RIGHT);
		answers.put("question2", KeyEvent.VK_LEFT);
		
		questions[0] = "question1";
		questions[1] = "question2";
	}
	//Displays the question to be answered by the user
	public void displayQuestion()	{
		question1 = new JOptionPane();
		int response = question1.showOptionDialog(null, "Text", "Top Window", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
		System.out.println(response);
		
		if(response == JOptionPane.YES_OPTION)
			JOptionPane.showMessageDialog(null, "Congrats...you're right");
		
	}
	
	//Displays the explanation of the question after it has been answered by the user
	public void showExplanation()	{
		
	}
	@Override
	public void update(ArrayList<GameObject> list) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		
		
		QuestionView v = new QuestionView();
		v.displayQuestion() ;
	}
}

//-----------------------------------------------------------------------------------------------------



