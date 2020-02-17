import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class DisplayDB extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayDB frame = new DisplayDB();
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
	
	public DisplayDB() {
		setTitle("Filter Passangers");
		setBounds(100, 100, 515, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 55, 479, 195);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnFilter = new JButton("Filter");
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection conn = null;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sultana","root","2797");
					String query = "SELECT * FROM PASSANGERS WHERE LOCATION_Where_To = ?";
					String input = txtInput.getText();
					PreparedStatement pstmt = conn.prepareStatement(query);
					pstmt.setString(1, input);
					ResultSet rs = pstmt.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnFilter.setBounds(370, 11, 119, 33);
		contentPane.add(btnFilter);
		
		txtInput = new JTextField();
		txtInput.setBounds(158, 11, 185, 33);
		contentPane.add(txtInput);
		txtInput.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Destination's Name");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 11, 126, 33);
		contentPane.add(lblNewLabel);
	}
}
