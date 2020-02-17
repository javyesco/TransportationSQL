import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;

public class AddPassanger extends JFrame {

	private JPanel contentPane;
	private JTextField txtpassName;
	private JTextField txtPickup;
	private JDateChooser dateChooser;
	private JLabel lblNewLabel_3;
	private JTextField txtWhereTo;
	private JLabel lblNewLabel_4;
	private JTextField txtCellPhone;
	private JLabel lblNewLabel_5;
	private JTextField txtNumberPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPassanger frame = new AddPassanger();
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
	public AddPassanger() {
		setTitle("Add Passanger");
		setBounds(100, 100, 450, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Passanger's Name");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 11, 147, 37);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Pickup Location");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 59, 147, 37);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Date_of_travel");
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_2.setBounds(10, 155, 147, 37);
		contentPane.add(lblNewLabel_2);
		
		txtpassName = new JTextField();
		txtpassName.setBounds(167, 11, 147, 37);
		contentPane.add(txtpassName);
		txtpassName.setColumns(10);
		
		txtPickup = new JTextField();
		txtPickup.setBounds(167, 59, 147, 37);
		contentPane.add(txtPickup);
		txtPickup.setColumns(10);
		
		JButton btnAddPass = new JButton("Add");
		btnAddPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = txtpassName.getText();
				String pickupLoc = txtPickup.getText();
				String whereToLoc = txtWhereTo.getText();
				String cellphone = txtCellPhone.getText();
				String numOfPass = txtNumberPass.getText();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String date = df.format(dateChooser.getDate());
				
				Connection conn = null;
				PreparedStatement pstmt = null;
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sultana","root","2797");
					pstmt = conn.prepareStatement("INSERT INTO PASSANGERS VALUES(0,?,?,?,?,?,?)");
					pstmt.setString(1, name);
					pstmt.setString(2, pickupLoc);
					pstmt.setString(3, whereToLoc);
					pstmt.setString(4, cellphone);
					pstmt.setString(5, date);
					pstmt.setString(6, numOfPass);
					int i = pstmt.executeUpdate();
					
					if(i>0) {
						JOptionPane.showMessageDialog(null, "Data was Added");
					}else {
						JOptionPane.showMessageDialog(null, "Data was not Added");
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnAddPass.setBounds(167, 299, 95, 37);
		contentPane.add(btnAddPass);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(167, 155, 147, 37);
		contentPane.add(dateChooser);
		
		lblNewLabel_3 = new JLabel("Where-To Location");
		lblNewLabel_3.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_3.setBounds(10, 107, 147, 37);
		contentPane.add(lblNewLabel_3);
		
		txtWhereTo = new JTextField();
		txtWhereTo.setBounds(167, 107, 147, 37);
		contentPane.add(txtWhereTo);
		txtWhereTo.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Cellphone Number");
		lblNewLabel_4.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_4.setBounds(10, 203, 147, 37);
		contentPane.add(lblNewLabel_4);
		
		txtCellPhone = new JTextField();
		txtCellPhone.setBounds(167, 203, 147, 37);
		contentPane.add(txtCellPhone);
		txtCellPhone.setColumns(10);
		
		lblNewLabel_5 = new JLabel("Number of Passangers");
		lblNewLabel_5.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_5.setBounds(10, 251, 147, 37);
		contentPane.add(lblNewLabel_5);
		
		txtNumberPass = new JTextField();
		txtNumberPass.setBounds(167, 251, 147, 37);
		contentPane.add(txtNumberPass);
		txtNumberPass.setColumns(10);
	}
}
