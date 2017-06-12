import java.awt.EventQueue;
import java.awt.Image;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null;
	private JTextField txtUsername;
	private JPasswordField pwdPassrord;
	private JLabel lbImage;

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		connection = sqliteConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(48, 36, 59, 13);
		frame.getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(48, 70, 59, 13);
		frame.getContentPane().add(lblPassword);

		txtUsername = new JTextField();
		txtUsername.setText("");
		txtUsername.setBounds(125, 33, 96, 19);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

		JButton btnLogin = new JButton("Login");
		Image img2 = new ImageIcon(this.getClass().getResource("/Ok-icon.png")).getImage();
		btnLogin.setIcon(new ImageIcon(img2));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "select * from employeeInfo where username =? and password=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, txtUsername.getText());
					pst.setString(2, pwdPassrord.getText());
					ResultSet rs = pst.executeQuery();
					int count = 0;
					while (rs.next()) {
						count += 1;
					}
					if (count == 1) {
						JOptionPane.showConfirmDialog(null, "Username and Password is correct");
						frame.dispose();
						EmployeeInfo emplInfo = new EmployeeInfo();
						emplInfo.setVisible(true);
						
					} else if (count > 1) {
						JOptionPane.showConfirmDialog(null, "Duplicate Username and Password");
					} else
						JOptionPane.showConfirmDialog(null, "Username and Password is not correct. Try again");
					rs.close();
					pst.close();
				} catch (Exception e2) {
					JOptionPane.showConfirmDialog(null, e2);
				}
			}
		});
		btnLogin.setBounds(83, 109, 91, 21);
		frame.getContentPane().add(btnLogin);

		pwdPassrord = new JPasswordField();
		pwdPassrord.setBounds(125, 67, 96, 19);
		frame.getContentPane().add(pwdPassrord);

		lbImage = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/login-icon.png")).getImage();
		lbImage.setIcon(new ImageIcon(img));
		lbImage.setBounds(257, 10, 129, 161);
		frame.getContentPane().add(lbImage);
	}
}
