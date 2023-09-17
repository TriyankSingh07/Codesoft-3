package demo3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class Forget {
	
	public static JLabel l1,l2,l3;
	public static  JTextField t1,t2,t3;
	public static JButton b1,b2;
	public static JFrame f;
	
	    Forget(){
		f=new JFrame("Forget PIN");
		f.setSize(500,300);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		l1=new JLabel("Enter Account no");
		l1.setBounds(30,40,200,20);
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
		b1.setBounds(110,190,100,30);
		f.add(b1);
		
	    b1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {				
					try {
						m1();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					}
				});
	    
	    b2=new JButton("Back");
		b2.setBounds(270,190,100,30);
		f.add(b2);
		
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				    f.dispose();
					new First();					
				}
			});

		
		f.setVisible(true);
}

	public static void main(String[] args) {
		new Forget();
	}
	
	public static void m1() throws Exception {      
		 try {   
          	
             String account=t1.getText();
             String newpin=t2.getText();
             String confirm=t3.getText();
             
             if(!newpin.equals(confirm)) {
            	 JOptionPane.showMessageDialog(null, "PINs do not match. Please re-enter.");
                 return;
             }
            
            	 Class.forName("oracle.jdbc.driver.OracleDriver");
            	 String url = "jdbc:oracle:thin:@localhost:1521:xe";
                 String usr = "mca62";
                 String password = "javapass";
                 Connection connection = DriverManager.getConnection(url, usr, password);
                 String q="update account set pinno=? where accno=?";
                 PreparedStatement pst=connection.prepareStatement(q);
                 pst.setString(1, newpin);
                 pst.setString(2, account);
                 int rowsUpdated = pst.executeUpdate();
                 
                 if(rowsUpdated>0) {
                	 JOptionPane.showMessageDialog(null, "PIN changed successfully.");
                    f.dispose();
                 }
                 else {
                	 JOptionPane.showMessageDialog(null, "Failed to change PIN.");
                 }
                 connection.close();
             }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
	}
}
