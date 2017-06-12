import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;

public class EmployeeInfo extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeInfo frame = new EmployeeInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null;
	private JLabel lblId;
	private JLabel lblUsername;
	private JLabel lblName;
	private JLabel lblAge;
	private JTextField txtId;
	private JTextField txtUsername;
	private JTextField txtName;
	private JTextField txtAge;
	private JButton btnSave;
	private JButton btnUpdate;
	private JButton btnDelete;

	public void refreshTable() {
		try {
			String query = "select id,name,username,age from employeeInfo";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			pst.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public EmployeeInfo() {
		connection = sqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 588, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnLoadTable = new JButton("Load Data");
		btnLoadTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "select id,name,username,age from employeeInfo";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					rs.close();
					pst.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnLoadTable.setBounds(12, 8, 111, 21);
		contentPane.add(btnLoadTable);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(185, 10, 375, 314);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		lblId = new JLabel("ID");
		lblId.setBounds(12, 51, 50, 13);
		contentPane.add(lblId);

		lblUsername = new JLabel("Username");
		lblUsername.setBounds(12, 74, 62, 13);
		contentPane.add(lblUsername);

		lblName = new JLabel("Name");
		lblName.setBounds(12, 97, 50, 13);
		contentPane.add(lblName);

		lblAge = new JLabel("Age");
		lblAge.setBounds(12, 120, 50, 13);
		contentPane.add(lblAge);

		txtId = new JTextField();
		txtId.setBounds(74, 48, 96, 19);
		contentPane.add(txtId);
		txtId.setColumns(10);

		txtUsername = new JTextField();
		txtUsername.setBounds(74, 71, 96, 19);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);

		txtName = new JTextField();
		txtName.setBounds(74, 94, 96, 19);
		contentPane.add(txtName);
		txtName.setColumns(10);

		txtAge = new JTextField();
		txtAge.setBounds(74, 117, 96, 19);
		contentPane.add(txtAge);
		txtAge.setColumns(10);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "insert into employeeInfo (id,username,name,age) values (?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, txtId.getText());
					pst.setString(2, txtUsername.getText());
					pst.setString(3, txtName.getText());
					pst.setString(4, txtAge.getText());
					pst.execute();

					JOptionPane.showConfirmDialog(null, "Data Saved.");
					refreshTable();
					pst.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnSave.setBounds(74, 146, 91, 21);
		contentPane.add(btnSave);

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "update employeeInfo set id = '" + txtId.getText() + "', username='"
							+ txtUsername.getText() + "', name='" + txtName.getText() + "', age='" + txtAge.getText()
							+ "' where id = '" + txtId.getText() + "'";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.execute();
					JOptionPane.showConfirmDialog(null, "Data Saved.");
					pst.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				refreshTable();
			}
		});
		btnUpdate.setBounds(74, 177, 91, 21);
		contentPane.add(btnUpdate);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "delete from employeeInfo where id='" + txtId.getText() + "'";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.execute();
					JOptionPane.showConfirmDialog(null, "Data Saved.");
					
					pst.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				refreshTable();
			}
		});
		btnDelete.setBounds(74, 208, 91, 21);
		contentPane.add(btnDelete);
		
		refreshTable();
	}
}
