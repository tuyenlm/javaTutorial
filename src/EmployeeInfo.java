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
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EmployeeInfo extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox<String> comboBox;

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
	private JList listName;
	private JTextField textSearch;
	private JComboBox comboBoxSelect;
	private JMenu mnEdit;
	private JMenu mnSource;

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

	public void fillComboBox() {
		try {
			String query = "select * from employeeInfo";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				comboBox.addItem(rs.getString("name"));

			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	public void loadList() {
		try {
			String query = "select * from employeeInfo";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			DefaultListModel DLM = new DefaultListModel();

			while (rs.next()) {
				DLM.addElement(rs.getString("name"));
			}
			listName.setModel(DLM);
			pst.close();
			rs.close();
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
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnNew = new JMenu("New");
		mnNew.setIcon(new ImageIcon("C:\\Users\\tuyen\\workspace\\JFaceTutorial\\src\\image\\check.png"));
		mnFile.add(mnNew);
		
		JMenuItem mntmJavaProject = new JMenuItem("Java Project");
		mnNew.add(mntmJavaProject);
		
		JMenuItem mntmProject = new JMenuItem("Project");
		mnNew.add(mntmProject);
		
		JMenuItem mntmOpen = new JMenuItem("Open File");
		mnFile.add(mntmOpen);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmClose = new JMenuItem("Close");
		mnFile.add(mntmClose);
		
		JMenuItem mntmCloseAll = new JMenuItem("Close All");
		mnFile.add(mntmCloseAll);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		mnFile.add(mntmExit);
		
		mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JRadioButtonMenuItem rdbtnmntmRadioButton = new JRadioButtonMenuItem("Radio Button");
		mnEdit.add(rdbtnmntmRadioButton);
		
		JCheckBoxMenuItem chckbxmntmCheckBox = new JCheckBoxMenuItem("Check box");
		mnEdit.add(chckbxmntmCheckBox);
		
		mnSource = new JMenu("Source");
		menuBar.add(mnSource);
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
		btnLoadTable.setBounds(12, 72, 111, 21);
		contentPane.add(btnLoadTable);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(222, 141, 338, 162);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row = table.getSelectedRow();
					String id = (table.getModel().getValueAt(row, 0)).toString();
					String query = "select * from employeeInfo where id='" + id + "'";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					while (rs.next()) {
						txtId.setText(rs.getString("id"));
						txtName.setText(rs.getString("name"));
						txtUsername.setText(rs.getString("username"));
						txtAge.setText(rs.getString("age"));
					}
					pst.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(table);

		lblId = new JLabel("ID");
		lblId.setBounds(12, 115, 32, 13);
		contentPane.add(lblId);

		lblUsername = new JLabel("Username");
		lblUsername.setBounds(107, 115, 62, 13);
		contentPane.add(lblUsername);

		lblName = new JLabel("Name");
		lblName.setBounds(273, 115, 50, 13);
		contentPane.add(lblName);

		lblAge = new JLabel("Age");
		lblAge.setBounds(418, 115, 50, 13);
		contentPane.add(lblAge);

		txtId = new JTextField();
		txtId.setBounds(35, 112, 50, 19);
		contentPane.add(txtId);
		txtId.setColumns(10);

		txtUsername = new JTextField();
		txtUsername.setBounds(165, 112, 96, 19);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);

		txtName = new JTextField();
		txtName.setBounds(310, 112, 96, 19);
		contentPane.add(txtName);
		txtName.setColumns(10);

		txtAge = new JTextField();
		txtAge.setBounds(448, 112, 96, 19);
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
		btnSave.setBounds(263, 72, 91, 21);
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
		btnUpdate.setBounds(366, 72, 91, 21);
		contentPane.add(btnUpdate);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(null, "Do you really want to delete?", "Delete",
						JOptionPane.YES_NO_OPTION);
				if (action == 0) {
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
			}
		});
		btnDelete.setBounds(469, 72, 91, 21);
		contentPane.add(btnDelete);

		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "select * from employeeInfo where name='" + (String) comboBox.getSelectedItem()
							+ "'";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					while (rs.next()) {
						txtId.setText(rs.getString("id"));
						txtName.setText(rs.getString("name"));
						txtUsername.setText(rs.getString("username"));
						txtAge.setText(rs.getString("age"));
					}
					pst.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				refreshTable();
			}
		});
		comboBox.setBounds(150, 73, 101, 19);
		contentPane.add(comboBox);

		listName = new JList();
		listName.setBounds(127, 141, 83, 162);
		contentPane.add(listName);

		textSearch = new JTextField();
		textSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String selection = (String)comboBoxSelect.getSelectedItem();
					String query = "select id,name,username,age from employeeInfo where "+selection+"='" + textSearch.getText()
							+ "'";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		textSearch.setBounds(12, 174, 96, 19);
		contentPane.add(textSearch);
		textSearch.setColumns(10);
		
		comboBoxSelect = new JComboBox();
		comboBoxSelect.setModel(new DefaultComboBoxModel(new String[] {"id", "name", "username", "age"}));
		comboBoxSelect.setBounds(12, 141, 96, 23);
		contentPane.add(comboBoxSelect);

//		refreshTable();
		fillComboBox();
		loadList();
	}
}
