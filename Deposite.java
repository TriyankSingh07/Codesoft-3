package demo3;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class Deposite{
	public static JFrame f;
	JLabel l1,l2,l3;
	
	static JTextField t1,t2;
	public static JRadioButton b1,b2;
	static JButton jb1,jb2;
	ButtonGroup bg;
	
	Deposite(){
		f=new JFrame("Deposite");
		f.setSize(500,300);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		
		l1=new JLabel("Enter Amount");
		l1.setBounds(20,30,190,20);
		f.add(l1);
		
		t1=new JTextField();
		t1.setBounds(180,30,150,20);
		f.add(t1);
			
		l2=new JLabel("Select Account Type");
		l2.setBounds(20,100,190,20);
		f.add(l2);
		
		b1=new JRadioButton("Savings");
		b1.setBounds(30,140,100,20);
		f.add(b1);
		b1.setSelected(true); 
		
		b2=new JRadioButton("Current");
		b2.setBounds(150,140,100,20);
		f.add(b2);
		
		bg=new ButtonGroup();
		bg.add(b1);
		bg.add(b2);
		
		jb1=new JButton("Submit");
		jb1.setBounds(100,200,100,30);
		f.add(jb1);
		
		jb2=new JButton("Back");
		jb2.setBounds(280,200,100,30);
		f.add(jb2);
		
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				   f.dispose();
					new ATM();					
				}
			});
		
		f.setVisible(true);
		
		 jb1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {		
						 try {
							 f.dispose();
							m1();
						} catch (Exception e1) {
							
							e1.printStackTrace();
						}
				}
				});
	}

	public static void main(String[] args) {
		new Deposite();		 
	}
	
	public static void m1(){
	    try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	        String url = "jdbc:oracle:thin:@localhost:1521:xe";
	        String usr = "mca62";
	        String password = "javapass";
	        Connection connection = DriverManager.getConnection(url, usr, password);
	        
	        String enteramount=t1.getText();
            if(enteramount.isEmpty()) {
           	 JOptionPane.showMessageDialog(null, "Enter amount");
            return;
            }
            
	        int amount = Integer.parseInt(t1.getText());
	        String q2 = "Select type from account WHERE pinno = ?";
	        PreparedStatement pst1 = connection.prepareStatement(q2);

	        pst1.setInt(1, First.pinno);
	        ResultSet rs= pst1.executeQuery();
	        if(rs.next()) {
            	
	        String select=rs.getString("type");
                String selectedAccountType = "";
                if (b1.isSelected()) {
                    selectedAccountType = "savings";
                } else if (b2.isSelected()) {
                    selectedAccountType = "current";
                } 
				if(selectedAccountType.equals(select)) {
	        
	       if(amount>=100 && amount%100==0 && amount<=50000){ 
	        String q = "UPDATE account SET balance = balance + ? WHERE pinno = ?";
	        PreparedStatement pst = connection.prepareStatement(q);

	        pst.setInt(1, amount);
	        pst.setInt(2, First.pinno);
	        
	        int rowsUpdated = pst.executeUpdate();
            
	        if (rowsUpdated > 0) {
	            JOptionPane.showMessageDialog(null, "Balance updated successfully.");
	        } else {
	            JOptionPane.showMessageDialog(null, "Wrong PIN or failed to update balance.");
	        }
	       }else {
	    	   JOptionPane.showMessageDialog(null, "Amount must be less than 50000 or Greater than 100");
	       }
	       
	        
	    } else {
	    	JOptionPane.showMessageDialog(null, "Account Type Mismtach");
	    }
	        }
	        connection.close();
	    }
	    catch (ClassNotFoundException | SQLException ex) {
			        ex.printStackTrace();
			    }
	    	
	    }
	
	    
	}

