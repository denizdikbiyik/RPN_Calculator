package Calculator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

/**
*
* @author DenizDikbiyik
*/

public class RPN_GUI extends JFrame implements ActionListener {
		
	public String textFieldGetter = "";

	private JPanel contentPane;
	private JPanel panel;
	private final JPanel panel_1 = new JPanel();
	private JTextField textField;
	
	/**
	 * Change the design of texts.
	 */
	private Font myFont = new Font("Times New Roman",Font.ROMAN_BASELINE,30);
	private Font myFont2 = new Font("Times New Roman",Font.ROMAN_BASELINE,25);
	private JButton[] numberButtons = new JButton[10];
	private JButton[] functionButtons = new JButton[9];
	private JButton addButton,subButton,mulButton,divButton;
	private JButton decButton, equButton, delButton, clrButton, negButton;
		
	/**
	* This is a program for RPN calculator in java.
	    * @param args
	    * Launch the application.
	*/

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RPN_GUI frame = new RPN_GUI();
					frame.setVisible(true);										
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RPN_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("RPN Calculator");
		setBounds(100, 100, 415, 535);
		setSize(335, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		setContentPane(contentPane);
		
		/**
		 * Create the panel.
		 * setBounds to have a square.
		 * setLayout, so GridLayout to have rows and columns in number and operation buttons area.
		 * Background color is changed to have nice design.
		 * Then, fonts are changed.
		 */
		panel = new JPanel();
		panel.setBounds(10, 50, 300, 300);
		panel.setLayout(new GridLayout(4, 4, 4, 4));
		panel.setBackground(Color.BLACK);		
		
		textField = new JTextField();
		textField.setBounds(50, 25, 300, 50);
		textField.setFont(myFont);
		Border border = BorderFactory.createLineBorder(Color.GRAY, 3);
		textField.setBorder(border);
		textField.setEditable(false);
		contentPane.add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		
		/**
		 * Buttons are added to the area.
		 */
		addButton = new JButton("+");
		subButton = new JButton("-");
		mulButton = new JButton("*");
		divButton = new JButton("/");
		decButton = new JButton(".");
		equButton = new JButton("^");
		delButton = new JButton("Delete");
		clrButton = new JButton("Clear");
		negButton = new JButton("+/-");
		
		/**
		 * Number buttons are added with their numbers.
		 */
		functionButtons[0] = addButton;
		functionButtons[1] = subButton;
		functionButtons[2] = mulButton;
		functionButtons[3] = divButton;
		functionButtons[4] = decButton;
		functionButtons[5] = equButton;
		functionButtons[6] = delButton;
		functionButtons[7] = clrButton;
		functionButtons[8] = negButton;
		
		/**
		 * For loop is added for JButtons. Action listener is added to get the interaction.
		 */
		for(int i = 0; i < 9; i++) {
			functionButtons[i].addActionListener(this);
			functionButtons[i].setFont(myFont);
			functionButtons[i].setFocusable(false);
		}
				
		for(int i = 0; i < 10; i++) {
			numberButtons[i] = new JButton(String.valueOf(i));
			numberButtons[i].addActionListener(this);
			numberButtons[i].setFont(myFont);
			numberButtons[i].setFocusable(false);
		}
		
		/**
		 * Buttons are added to the panel.
		 */
		delButton.setFont(myFont2);
		clrButton.setFont(myFont2);
		
		panel.add(numberButtons[7]);
		panel.add(numberButtons[8]);
		panel.add(numberButtons[9]);
		panel.add(addButton);
		
		panel.add(numberButtons[4]);
		panel.add(numberButtons[5]);
		panel.add(numberButtons[6]);
		panel.add(subButton);
		
		panel.add(numberButtons[1]);
		panel.add(numberButtons[2]);
		panel.add(numberButtons[3]);
		panel.add(mulButton);
		
		panel.add(numberButtons[0]);		
		panel.add(decButton);
		panel.add(equButton);
		panel.add(divButton);
		
		contentPane.add(panel);
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		panel_1.add(negButton);
		panel_1.add(delButton);
		panel_1.add(clrButton);

	}

	/**
	 * Actions are got via buttons and sent to engine class.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
				
		/**
		 * Number buttons' actions.
		 */
		for(int i = 0; i < 10; i++) {
			if(e.getSource() == numberButtons[i]){					
				textFieldGetter = textField.getText();				
				RPN_Engine.numberButtons (i, textFieldGetter);								
				textField.setText(RPN_Engine.textFieldSetter);
			}
		}
			
		/**
		 * Decimal button to add decimal points.
		 */
		if(e.getSource() == decButton) {			
			textFieldGetter = textField.getText();						
			RPN_Engine.decimalButton (textFieldGetter);			
			textField.setText(RPN_Engine.textFieldSetter);																	
		}
			
		/**
		 * Addition button.
		 */
		if(e.getSource() == addButton) {			
			textFieldGetter = textField.getText();						
			RPN_Engine.additionButton (textFieldGetter);			
			textField.setText(RPN_Engine.textFieldSetter);			
		}
			
		/**
		 * Subtraction button.
		 */
		if(e.getSource() == subButton) {
			textFieldGetter = textField.getText();						
			RPN_Engine.subtractionButton (textFieldGetter);			
			textField.setText(RPN_Engine.textFieldSetter);			
		}
				
		/**
		 * Multiplication button.
		 */
		if(e.getSource() == mulButton) {
			textFieldGetter = textField.getText();						
			RPN_Engine.multiplicationButton (textFieldGetter);
			textField.setText(RPN_Engine.textFieldSetter);		
		}		
		
		/**
		 * Division button.
		 */
		if(e.getSource() == divButton) {
			textFieldGetter = textField.getText();						
			RPN_Engine.divisionButton (textFieldGetter);			
			textField.setText(RPN_Engine.textFieldSetter);				
		}
			
		/**
		 * Enter button to add numbers from text field to stack.
		 */
		if(e.getSource() == equButton) {							
			textFieldGetter = textField.getText();			
			RPN_Engine.headButton (textFieldGetter);			
			textField.setText(RPN_Engine.textFieldSetter);			
		}
			
		/**
		 * Clear button to clear all the text from text field.
		 */
		if(e.getSource() == clrButton) {			
			RPN_Engine.clearButton ();			
			textField.setText(RPN_Engine.textFieldSetter);	
		}
			
		/**
		 * Delete button to delete all last character from text field.
		 */
		if(e.getSource() == delButton) {			
			textFieldGetter = textField.getText();			
			RPN_Engine.deleteButton (textFieldGetter);
			textField.setText(RPN_Engine.textFieldSetter);															
		}
		
		/**
		 * Change sign button to change numbers' signs.
		 */
		if(e.getSource() == negButton) {			
			textFieldGetter = textField.getText();			
			RPN_Engine.changeSignButton (textFieldGetter);			
			textField.setText(RPN_Engine.textFieldSetter);												
		}			
									
	}
}