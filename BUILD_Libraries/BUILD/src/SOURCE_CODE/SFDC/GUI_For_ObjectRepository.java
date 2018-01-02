package SOURCE_CODE.SFDC;

import javax.swing.*;

import USER_SPACE.TestPrerequisite.DataSetup;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
 
public class GUI_For_ObjectRepository extends JFrame implements ActionListener 
  { 
    JLabel l1, l2, l3, l4, l5, l6, l7, l8;
    JTextField tf1, tf2, tf3, tf4;
    JButton btn1, btn2,btn3,btn4;
    ArrayList al_sfdcloginurl,al_sysadmin,al_sfdcobject,al_sfdcrecordurl;
    
 
    GUI_For_ObjectRepository()
    {	
    	
    	setSize(600, 500);
    	setLayout(null);
    	    	        
        setResizable(true);       
            
        setLocationRelativeTo(null);
        
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setTitle("Creating Object Repository");
 
        l1 = new JLabel("Please provide below information to create Object Repository:");
        l1.setForeground(Color.blue);
        l1.setFont(new Font("TimesRoman", Font.ITALIC, 15));
        //l1.setAlignmentX(LEFT_ALIGNMENT);
        
        //l1.setFont(new Font("Serif", Font.BOLD, 14));
 
        l2 = new JLabel("SFDC LOGIN URL:");
        l2.setFont(new Font("TimesRoman",Font.BOLD,11));
        l3 = new JLabel("USER NAME (SYSTEM ADMIN):");
        l3.setFont(new Font("TimesRoman",Font.BOLD,11));
        l4 = new JLabel("NAME OF SFDC OBJECT:");
        l4.setFont(new Font("TimesRoman",Font.BOLD,11));
        l5 = new JLabel("SFDC URL OF THE RECORD:");
        l5.setFont(new Font("TimesRoman",Font.BOLD,11));
        
       
        tf1 = new JTextField();
        tf1.setBackground(new Color(224,224,224));
        tf2 = new JTextField();
        tf2.setBackground(new Color(224,224,224));
        tf3 = new JTextField();
        tf3.setBackground(new Color(224,224,224));
        tf4 = new JTextField();
        tf4.setBackground(new Color(224,224,224));
 
        //p1 = new JPasswordField();
        //p2 = new JPasswordField();
        //tf5 = new JTextField();
        
        btn1 = new JButton("Submit");
        btn2 = new JButton("Clear");
        btn3 = new JButton("<< Back");
        btn4 = new JButton("Save & Next>>");
        
        al_sfdcloginurl = new ArrayList();
        al_sysadmin = new ArrayList();
        al_sfdcobject = new ArrayList();
        al_sfdcrecordurl = new ArrayList(); 
        
        
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        
        
        l1.setBounds(100, 30, 400, 30); //Header label
        
        l2.setBounds(60, 110, 200, 30); //First Line Label
        l3.setBounds(60, 150, 200, 30);
        l4.setBounds(60, 190, 200, 30);
        l5.setBounds(60, 230, 200, 30);
        
       
        tf1.setBounds(230, 110, 300, 30); //First Line Text Box
        tf2.setBounds(230, 150, 300, 30);
        tf3.setBounds(230, 190, 300, 30);
        tf4.setBounds(230, 230, 300, 30);
        
        tf1.setText(DataSetup.SFDC_LOGIN_URL);
        tf2.setText(DataSetup.SYSTEM_ADMIN_USERNAME);
        tf3.setText(DataSetup.NAME_OF_SFDC_OBJECT);
        tf4.setText(DataSetup.SFDC_RECORD_URL);
       
        
        btn3.setBounds(30, 350, 100, 30);
        btn3.setBackground(new Color(64,64,64));
        btn3.setForeground(new Color(255,255,255));
        btn3.setFont(new Font("Serif", Font.BOLD, 15));
        
        btn1.setBounds(160, 350, 100, 30);
        btn1.setBackground(new Color(64,64,64));
        btn1.setForeground(new Color(255,255,255));
        btn1.setFont(new Font("Serif", Font.BOLD, 15));
        
        btn2.setBounds(300, 350, 100, 30);
        btn2.setBackground(new Color(64,64,64));
        btn2.setForeground(new Color(255,255,255));
        btn2.setFont(new Font("Serif", Font.BOLD, 15));
       
        btn4.setBounds(420, 350, 140, 30);
        btn4.setBackground(new Color(64,64,64));
        btn4.setForeground(new Color(255,255,255));
        btn4.setFont(new Font("Serif", Font.BOLD, 15));
        
        
        add(l1);
        
        add(l2);
        add(tf1);
        
        add(l3);
        add(tf2);
        
        add(l4);
        add(tf3);
        
        add(l5);
        add(tf4);
        
        add(btn1);
        add(btn2);
        add(btn3);
        add(btn4);
        
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
        	
        	al_sfdcloginurl.add(tf1.getText());
        	al_sysadmin.add(tf2.getText());
        	al_sfdcobject.add(tf3.getText());
        	al_sfdcrecordurl.add(tf4.getText());
        	
        	this.setVisible(false);
        	SFDCAutomationFW sfdc = new SFDCAutomationFW("CreatingObjectRepository");
        	for(int i=0;i<al_sfdcobject.size();i++)
        	{
        		               
                System.out.println("----------- Begin of Object Repository Generation for an Object -------------");
                DB.Connect(DataSetup.Logininfo);
                sfdc.OpenURL(null, al_sfdcloginurl.get(i).toString(), DB.ReadXLData("Setup", "Browser", "SL_NO", "Setup1"));
                sfdc.LoginToSFDC(al_sysadmin.get(i).toString());
                Thread.sleep(5000L);
    			sfdc.OpenSFDCRecordAsperURL(al_sfdcrecordurl.get(i).toString());
    			Thread.sleep(15000L);
    			sfdc._CreateAUTRepository(al_sfdcobject.get(i).toString());
    			sfdc.LogOff();
    			sfdc.Quit();
    			sfdc.AddToXLLogs("Object Repository Created Successfully for the Object ("+al_sfdcobject.get(i).toString()+")", "Pass");
    			System.out.println("----------- End of Object Repository Generation for an Object -------------");
        	}
        	sfdc.GenerateXLLog();
        	JOptionPane.showMessageDialog(null, "ObjectRepository is Created Successfully. Please Refresh the project and verify inside USER_SPACE.ObjectReposotory");
			System.exit(0);
        	/*
        	String url = tf1.getText();
        	String username = tf2.getText();
        	String sfdcobject = tf3.getText();
        	String sfdcrecord_url = tf4.getText();
        	
            System.out.println(url);
            System.out.println(username);
            System.out.println(sfdcobject);
            System.out.println(sfdcrecord_url);
            
            this.setVisible(false);
            
            SFDCAutomationFW sfdc = new SFDCAutomationFW("CreatingObjectRepository");
            System.out.println("----------- Begin of Object Repository Generation -------------");
            sfdc.OpenURL(null, url, DataSetup.Browser);
            sfdc.LoginToSFDC(username);
            Thread.sleep(5000L);
			sfdc.OpenSFDCRecordAsperURL(sfdcrecord_url);
			Thread.sleep(15000L);
			sfdc.CreateAUTRepository(sfdcobject);
			sfdc.GenerateXLLog();
			
			System.out.println("----------- End of Object Repository Generation -------------");
			
			JOptionPane.showMessageDialog(null, "ObjectRepository is Created Successfully. Please Refresh the project and look for ("+sfdcobject+"Screen.java) inside USER_SPACE.ObjectReposotory");
			System.exit(0);
			
			*/
        }
        else if(e.getSource() == btn2)
        {
        	tf1.setText("");
        	tf2.setText("");
        	tf3.setText("");
        	tf4.setText("");
        	
        }
        else if(e.getSource() == btn3)
        {
        	this.setVisible(false);
        	new GUIFORFRAMEWORK();
        	//GUI_For_Framework  swingControlDemo = new GUI_For_Framework();      
            //swingControlDemo.showInitialScreen();
        }
        else if(e.getSource() == btn4)
        {
        	
        	al_sfdcloginurl.add(tf1.getText());
        	al_sysadmin.add(tf2.getText());
        	al_sfdcobject.add(tf3.getText());
        	al_sfdcrecordurl.add(tf4.getText());
        	
        	tf1.setText(DataSetup.SFDC_LOGIN_URL);
            tf2.setText(DataSetup.SYSTEM_ADMIN_USERNAME);
            tf3.setText(DataSetup.NAME_OF_SFDC_OBJECT);
            tf4.setText(DataSetup.SFDC_RECORD_URL);
        	       	
        }
    	}
    	catch(Exception e1)
    	{
    		e1.printStackTrace();
    		
    	}
    	
    } 
    
}