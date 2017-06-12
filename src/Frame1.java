import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Frame1 {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame1 window = new Frame1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Frame1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextField textFieldN1 = new JTextField();
		textFieldN1.setBounds(32, 25, 96, 19);
		frame.getContentPane().add(textFieldN1);
		textFieldN1.setColumns(10);
		
		JTextField textFieldN2 = new JTextField();
		textFieldN2.setBounds(160, 25, 96, 19);
		frame.getContentPane().add(textFieldN2);
		textFieldN2.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					textField.setText(Integer.toString(Integer.parseInt(textFieldN1.getText())+Integer.parseInt(textFieldN2.getText())));
				} catch (Exception e2) {
					JOptionPane.showConfirmDialog(null, "Please enter valid number");
				}
			}
		});
		btnAdd.setBounds(32, 54, 91, 21);
		frame.getContentPane().add(btnAdd);
		
		JButton btnMinus = new JButton("Minus");
		btnMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					textField.setText(Integer.toString(Integer.parseInt(textFieldN1.getText())-Integer.parseInt(textFieldN2.getText())));
				} catch (Exception e2) {
					JOptionPane.showConfirmDialog(null, "Please enter valid number");
				}
			}
		});
		btnMinus.setBounds(160, 54, 91, 21);
		frame.getContentPane().add(btnMinus);
		
		JLabel lblTheResultIs = new JLabel("The result is:");
		lblTheResultIs.setBounds(32, 89, 91, 13);
		frame.getContentPane().add(lblTheResultIs);
		
		textField = new JTextField();
		textField.setBounds(112, 86, 96, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
}
