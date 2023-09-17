package demo3;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class Statement extends JFrame {
	JLabel l1;
	JComboBox jc;
	JButton b1,b2,b3,b4;
	Statement(){
		super("Statement");
		setSize(500,300);
		setLocationRelativeTo(null);
		setLayout(null);
		
		l1=new JLabel("Select the Account");
		l1.setBounds(30,20,130,20);
		add(l1);
		
		String values[]= {"Savings","Current","FD","RD"};
	    jc=new JComboBox(values);
	    jc.setBounds(250,20,100,20);
	    add(jc);
	    
	    b1=new JButton("Check Balance");
	    b1.setBounds(30,150,150,30);
	    add(b1);
	    
	    b2=new JButton("Last Transaction");
	    b2.setBounds(300,150,150,30);
	    add(b2);
	    
	    b3=new JButton("Last 5 Transaction");
	    b3.setBounds(30,200,150,30);
	    add(b3);
	    
	    b4=new JButton("Mini Statement");
	    b4.setBounds(300,200,150,30);
	    add(b4);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Statement();
	}

}
