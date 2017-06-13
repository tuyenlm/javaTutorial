import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class empl {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					empl window = new empl();
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
	public empl() {
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
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(12, 27, 410, 224);
		frame.getContentPane().add(tabbedPane_1);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.addTab("New tab", null, tabbedPane, null);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(12, 10, 91, 21);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 20, 175, 152);
		panel_1.add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		tabbedPane_1.addTab("New tab", null, panel_2, null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		table.setCellSelectionEnabled(false);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField())
		{
		  public boolean isCellEditable(EventObject e)
		  {
		    if (e instanceof MouseEvent)
		    {
		      int count = ((MouseEvent) e).getClickCount();
		      Point p = ((MouseEvent) e).getPoint();
		 
		      int previousRow = t.getSelectedRow();
		      int previousColumn = t.getSelectedColumn();
		      int selectedRow = t.rowAtPoint(p);
		      int selectedColumn = t.columnAtPoint(p);
		 
		      System.out.println("Count = " + count);
		      System.out.println("Previous; Row = " + previousRow + " , Column = " + previousColumn);
		      System.out.println("Selected; Row = " + selectedRow + " , Column = " + selectedColumn);
		 
		      return (selectedRow == previousRow || count == 2);
		    }
		 
		    // Check keyboard input event?
		 
		    return false;
		  }
		});
		panel_2.add(table);
	}
}
