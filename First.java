package demo3;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.sql.*;

public class First {
	JLabel l1;
	private static JFrame f;
	static JTextField t1;
	static JButton b1,b2;
	public static int pinno ,balance;
	public static int bal;
	First(){
		
		f=new JFrame("Statement");
		f.setSize(500,300);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		
		l1=new JLabel("Enter ATM PIN");
		l1.setBounds(30,40,250,20);
		f.add(l1);
			
		t1=new JTextField();
		t1.setBounds(180,40,120,20);
		f.add(t1);
		
		
		b1=new JButton("Enter");
		b1.setBounds(100,150,100,30);		
		f.add(b1);
		
		b2=new JButton("Forget PIN");
		b2.setBounds(300,150,100,30);		
		f.add(b2);
		
   		f.setVisible(true);
	
	}
	
	public static void main(String[] args) {
		new First();
	
	
	b1.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
					change();
		}
	});
	
	b2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			    f.dispose();
				new Forget();
			}
		});
	
	}
	
	
	public static void change() {	        
	        try {
	        	
	        	 Class.forName("oracle.jdbc.driver.OracleDriver");
	        	 String url = "jdbc:oracle:thin:@localhost:1521:xe";
	             String usr = "mca62";
	             String password = "javapass";
	             Connection connection = DriverManager.getConnection(url, usr, password);
	             String enteredPin=t1.getText();
	             if(enteredPin.isEmpty()) {
	            	 JOptionPane.showMessageDialog(null, "Enter pin");
	           return;
	            }             
	             int pin1=Integer.parseInt(t1.getText());
	    
	            String q1 = "SELECT pinno,balance FROM account WHERE pinno = ?";
	            PreparedStatement preparedStatement = connection.prepareStatement(q1);
	            preparedStatement.setInt(1, pin1);
	            
	            ResultSet resultSet = preparedStatement.executeQuery();
	           
	           
	            if(resultSet.next()) {
	            	pinno=resultSet.getInt("pinno");
	                balance=resultSet.getInt("balance");
	            }
	            
	            if(pin1==pinno) {
	            	new ATM();
	            	f.dispose();
	            }	     
	            else {
	                JOptionPane.showMessageDialog(null, "Wrong PIN. Access denied.");
	            }	            	            
	            connection.close();
	            } 
	            
	        
	            catch (ClassNotFoundException | SQLException ex) {
	            ex.printStackTrace();
	        }
	}
	}
