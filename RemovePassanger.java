import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;

public class RemovePassanger extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemovePassanger frame = new RemovePassanger();
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
	public RemovePassanger() {
		setTitle("Remove");
		setBounds(100, 100, 253, 154);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtId = new JTextField();
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		txtId.setBounds(10, 42, 217, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);

		JLabel lblNewLabel = new JLabel("Passanger's ID");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 217, 20);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Remove");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = txtId.getText();
				java.sql.Connection conn = null;
				java.sql.PreparedStatement pstmt = null;
				try {
						Class.forName("com.mysql.jdbc.Driver");
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sultana","root","2797");
						pstmt = conn.prepareStatement("DELETE FROM PASSANGERS WHERE PASSANGER_ID=?");
						pstmt.setString(1, id);
						int i = pstmt.executeUpdate();
						
						if(i>0) {
							JOptionPane.showMessageDialog(null, "Data was Deleted");
						}else {
							JOptionPane.showMessageDialog(null, "Data was not Deleted");
						}
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}
			});
		btnNewButton.setBounds(80, 73, 89, 23);
		contentPane.add(btnNewButton);
		}

	}
