package SOURCE_CODE.SFDC;


import javax.swing.*;

import USER_SPACE.TestPrerequisite.DataSetup;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
 
public class GUI_FOR_TESTSCRIPT_CREATION extends JFrame implements ActionListener 
  { 
    JLabel l,l1, l2;
    JTextField tf1, tf2;
    JButton btn1, btn2;
    SFDCAutomationFW sfdc;
 
    GUI_FOR_TESTSCRIPT_CREATION()
    {	
    	
    	setSize(600, 500);
    	setLayout(null);
    	    	        
        setResizable(true);       
            
        setLocationRelativeTo(null);
        
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setTitle("Creating Test Script");
    	
    	l = new JLabel("Please provide below information to create Object Repository:");
        l.setForeground(Color.blue);
        l.setFont(new Font("TimesRoman", Font.ITALIC, 15));
     
        l2 = new JLabel("NAME OF NEW TEST SCRIPT:");
        l1 = new JLabel("MODULE NAME / FUNCTIONAL AREA:");
        
       
        tf1 = new JTextField();
        tf2 = new JTextField();
        
        btn1 = new JButton("<< Cancel / Back");
        btn2 = new JButton("Create New TestScript");
        
        btn1.addActionListener(this);
        btn2.addActionListener(this);
 
        
        l.setBounds(40, 20, 400, 30); //Top text
        
        l1.setBounds(40, 100, 300, 30); //First Line Label
        l1.setFont(new Font("TimesRoman",Font.BOLD,11));
        
        l2.setBounds(40, 150, 300, 30);
        l2.setFont(new Font("TimesRoman",Font.BOLD,11));
        
               
        tf1.setBounds(260, 100, 200, 30); //First Line Text Box
        tf1.setBackground(new Color(224,224,224));
        tf2.setBounds(260, 150, 200, 30);
        tf2.setBackground(new Color(224,224,224));
        
               
        btn1.setBounds(80, 300, 150, 30);
        btn1.setBackground(new Color(64,64,64));
        btn1.setForeground(new Color(255,255,255));
        btn1.setFont(new Font("Serif", Font.BOLD, 15));
        
        btn2.setBounds(340, 300, 200, 30);
        btn2.setBackground(new Color(64,64,64));
        btn2.setForeground(new Color(255,255,255));
        btn2.setFont(new Font("Serif", Font.BOLD, 15));
        
        add(l);
        add(l1);
        add(tf1);
        add(l2);
        add(tf2);
        add(btn1);
        add(btn2);
        
        getContentPane().setBackground(new Color(102,178,255));
        setBackground(new Color(102,178,255));
        setVisible(true);
        
    }
 
    public void actionPerformed(ActionEvent e) 
     {
    	
    	try
    	{
    	
        if (e.getSource() == btn1)
        {
        	this.setVisible(false);
        	new GUIFORFRAMEWORK();
        	
        }
        else if(e.getSource() == btn2)
        {
        	String ModuleName = tf1.getText();
        	String TestScriptName = tf2.getText();
        	
        	sfdc = new SFDCAutomationFW(TestScriptName);
        	sfdc._CreateTestScriptFile(ModuleName, TestScriptName);
        	JOptionPane.showMessageDialog(null, TestScriptName+ ".java file has been created as per template. Please Refresh the project.");
        	this.setVisible(false);
        	new GUIFORFRAMEWORK();
        }
        
    	}
    	catch(Exception e1)
    	{
    		e1.printStackTrace();
    		
    	}
    	
    } 
    
}