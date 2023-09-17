package demo3;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class ChangePIN {
	
	public static JFrame f;
	public static JLabel l1,l2,l3;
	public static JTextField t1,t2,t3;
	public static JButton b1,b2;
	
	ChangePIN(){
		f=new JFrame("Change PIN");
		f.setSize(500,300);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		
		l1=new JLabel("Enter Old PIN");
		l1.setBounds(30,40,100,20);
		f.add(l1);
		
		t1=new JTextField();
		t1.setBounds(240,40,150,20);
		f.add(t1);
		
		l2=new JLabel("Enter New PIN");
		l2.setBounds(30,80,100,20);
		f.add(l2);
		
		t2=new JTextField();
		t2.setBounds(240,80,150,20);
		f.add(t2);
		
		l3=new JLabel("Confirm New PIN");
		l3.setBounds(30,120,100,20);
		f.add(l3);
		
		t3=new JTextField();
		t3.setBounds(240,120,150,20);
		f.add(t3);
		
		b1=new JButton("Change");
		b1.setBounds(100,190,100,30);
		f.add(b1);
		
		b2=new JButton("Back");
		b2.setBounds(280,190,100,30);
		f.add(b2);
		
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				    f.dispose();
					new ATM();					
				}
			});

		
		f.setVisible(true);
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ChangePIN();
				
			}
			
		} );
	}
		
	
	
	
public static void main(String[] args) {
	new ChangePIN();
}

public static void ChangePIN() {
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String usr = "mca62";
        String password = "javapass";
        Connection connection = DriverManager.getConnection(url, usr, password);
        
        String o=t1.getText();
        if(o.isEmpty()) {
       	 JOptionPane.showMessageDialog(null, "Enter pin");
        return;
        }
        
        String n1=t2.getText();
        if(n1.isEmpty()) {
       	 JOptionPane.showMessageDialog(null, "Enter new PIN");
        return;
        }
        
        String n2=t3.getText();
        if(n2.isEmpty()) {
       	 JOptionPane.showMessageDialog(null, "Enter confirm new PIN");
        return;
        }
        int old = Integer.parseInt(t1.getText());
        int new1 = Integer.parseInt(t2.getText());

        String q1 = "UPDATE account SET pinno=? WHERE pinno=?";
        PreparedStatement pst1 = connection.prepareStatement(q1);
        pst1.setInt(1, new1);  
        pst1.setInt(2, old);   
        int rowsUpdated = pst1.executeUpdate();

        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(null, "Pinno updated successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update pinno");
        }

        connection.close();
    } catch (ClassNotFoundException | SQLException ex) {
        ex.printStackTrace();
    }
}


}
