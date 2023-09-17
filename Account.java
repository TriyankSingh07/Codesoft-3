package demo3;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;

public class Account {
    public static JFrame f;
    public static JLabel l1, l2, l3, l4, l5, l6;
    public static JButton b1;
    JTextField t1;

       Account() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String usr = "mca62";
        String password = "javapass";
        Connection con = DriverManager.getConnection(url, usr, password);
        int temp=First.pinno;

        String q = "select * from account where pinno =?";
        PreparedStatement pst = con.prepareStatement(q);
        pst.setInt(1,temp);
        ResultSet rs = pst.executeQuery();

        f = new JFrame("Account info");
        f.setSize(500, 300);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        l1 = new JLabel("Your Account Info");
        l1.setFont(new Font("", Font.BOLD, 14));
        l1.setBounds(180, 10, 250, 20);
        f.add(l1);

        while (rs.next()) {
            String acc = rs.getString("ACCNO");
            String name = rs.getString("NAME");
            String bank = rs.getString("BANKNAME");
            String ifsc = rs.getString("IFSC");
            String address = rs.getString("ADDRESS");

            l2 = new JLabel("Account no : " + acc);
            l2.setBounds(30, 40, 250, 20);
            f.add(l2);

            l3 = new JLabel("Name : " + name);
            l3.setBounds(30, 80, 250, 20);
            f.add(l3);

            l4 = new JLabel("Bank Name : " + bank);
            l4.setBounds(30, 120, 250, 20);
            f.add(l4);

            l5 = new JLabel("IFSC Code : " + ifsc);
            l5.setBounds(30, 160, 250, 20);
            f.add(l5);

            l6 = new JLabel("Address : " + address);
            l6.setBounds(30, 200, 250, 20);
            f.add(l6);
            
            b1=new JButton("Back");
   		    b1.setBounds(250,200,100,25);		
   		    f.add(b1);
   		    
   		   b1.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				f.dispose();
 					new ATM();
 				}
 			});
        }

        f.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        new Account();
    }
}
