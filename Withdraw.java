package demo3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class Withdraw {
    public static JFrame f;
    JLabel l1, l2, l3;
    static JTextField t1, t2;
    private static JRadioButton b1, b2;
   public static  JButton jb1,jb2;

    Withdraw() {
        f = new JFrame("Withdraw");
        f.setSize(500, 300);
        f.setLocationRelativeTo(null);
        f.setLayout(null);

        l1 = new JLabel("Enter Amount");
        l1.setBounds(30, 40, 200, 20);
        f.add(l1);

        t1 = new JTextField();
        t1.setBounds(270, 40, 120, 20);
        f.add(t1);

        l2 = new JLabel("Enter ATM PIN");
        l2.setBounds(30, 90, 200, 20);
        f.add(l2);

        t2 = new JTextField();
        t2.setBounds(270, 90, 120, 20);
        f.add(t2);

        l3 = new JLabel("Select Account Type:");
        l3.setBounds(30, 140, 200, 20);
        f.add(l3);

        b1 = new JRadioButton("Savings");
        b1.setBounds(100, 175, 100, 20);
        b1.setSelected(true);
        f.add(b1);

        b2 = new JRadioButton("Current");
        b2.setBounds(250, 175, 100, 20);
        f.add(b2);

        ButtonGroup bg = new ButtonGroup();
        bg.add(b1);
        bg.add(b2);

        jb1 = new JButton("Withdraw");
        jb1.setBounds(100,220,100,30);
        f.add(jb1);
        
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

        
        jb2=new JButton("Back");
		jb2.setBounds(280,220,100,30);
		f.add(jb2);
		
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				    f.dispose();
					new ATM();					
				}
			});

        f.setVisible(true);
    }

    public static void main(String[] args) {
        new Withdraw();
    }

    public static void m1() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String usr = "mca62";
            String password = "javapass";
            Connection connection = DriverManager.getConnection(url, usr, password);
            
            String enteramount=t1.getText();
            if(enteramount.isEmpty()) {
           	 JOptionPane.showMessageDialog(null, "Enter Amount");
            return;
            }
            
            String enterpin=t2.getText();
            if(enterpin.isEmpty()) {
           	 JOptionPane.showMessageDialog(null, "Enter pin");
            return;
            }
            
            String pin = t2.getText();
            int withdrawAmount = Integer.parseInt(t1.getText());
            int verify = Integer.parseInt(t2.getText());

            String pinQuery = "SELECT pinno, balance,type FROM account WHERE pinno = ?";
            PreparedStatement pinStmt = connection.prepareStatement(pinQuery);
            pinStmt.setInt(1, First.pinno);
            ResultSet pinResult = pinStmt.executeQuery();

            if (pinResult.next()) {
                int balance = pinResult.getInt("balance");
                 String select=pinResult.getString("type");
                if (verify == First.pinno) {
                    if (withdrawAmount < balance) {
                        String selectedAccountType = "";
                        
                        if (b1.isSelected()) {
                            selectedAccountType = "savings";
                        } else if (b2.isSelected()) {
                            selectedAccountType = "Current";
                        } else {
                            JOptionPane.showMessageDialog(null, "Please select an account type.");
                            return;
                        }
                        if(selectedAccountType.equals(select)) {
                        if (withdrawAmount >= 500 && withdrawAmount % 100 == 0 && withdrawAmount <= 50000) {
                            String updateQuery = "UPDATE account SET balance = balance - ? WHERE pinno = ?";
                            PreparedStatement pst2 = connection.prepareStatement(updateQuery);

                            pst2.setInt(1, withdrawAmount);
                            pst2.setInt(2, First.pinno);

                            int rowsUpdated = pst2.executeUpdate();

                            if (rowsUpdated > 0) {
                                JOptionPane.showMessageDialog(null, "Withdrawal successful. Please take your cash.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Failed to withdraw.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Amount must be greater or equal to 500 and less than 50000");
                        }
                    }
                      else {
                    	  JOptionPane.showMessageDialog(null, "Account type mismatch");
                    }
                    }  else {
                        JOptionPane.showMessageDialog(null, "Insufficient funds");
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid PIN!");
                }
            }

            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
}
