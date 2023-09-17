package demo3;
import java.awt.*;
import java.sql.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ATM {
	public static JFrame f;
	public static JButton b1,b2,b3,b4,b5,b6,b7;
	
   ATM(){
		f=new JFrame("ATM");
		f.setSize(500,300);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		
		 b1=new JButton("Deposite");
		 b1.setBounds(20,40,150,30);		 
		 f.add(b1);
		 
		 b2=new JButton("Withdraw");
		 b2.setBounds(20,110,150,30);		 
		 f.add(b2);
		 
		 b3=new JButton("Check Balance");
		 b3.setBounds(20,180,150,30);		
		 f.add(b3);
		 
		 b4=new JButton("Change PIN");
		 b4.setBounds(315,40,150,30);		 
		 f.add(b4);
		 
		 b5=new JButton("Account Info");
		 b5.setBounds(315,110,150,30);
		 f.add(b5);
		 
		 b6=new JButton("Exit");
		 b6.setBounds(315,180,150,30);
		 f.add(b6);	
		 
		 f.setVisible(true);
	
	
     b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					new Deposite();					
				}
			});
			
     b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
					new Withdraw();
				}
			});
     b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				m1();
				
				}
			});
     
     b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
					try {
						f.dispose();
						new Account();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
     
     b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
					new ChangePIN();
				}
			});
     
     b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {							
					f.dispose();
				}
			});
			}
			
    public static void main(String[] args) {
		new ATM();		
	}  	
    
    public static void m1() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String usr = "mca62";
            String password = "javapass";
            Connection connection = DriverManager.getConnection(url, usr, password);
            String q = "SELECT balance FROM account WHERE pinno=?";
            PreparedStatement pst = connection.prepareStatement(q);
            pst.setInt(1, First.pinno);

            ResultSet resultSet = pst.executeQuery();
            
            if (resultSet.next()) {
                int bal1 = resultSet.getInt("balance");
                JOptionPane.showMessageDialog(null, "Your Balance is: " + bal1);
            } else {
                JOptionPane.showMessageDialog(null, "Account not found");
            }

            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}

