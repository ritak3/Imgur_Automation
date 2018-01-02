
package SOURCE_CODE.SFDC;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import USER_SPACE.TestPrerequisite.DataSetup;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
 
public class GUIFORFRAMEWORK extends JFrame implements ActionListener 
  { 
	
	SFDCAutomationFW sfdc;
    JLabel l1, l2, l3, l4, l5, l6, l7, l8;
    JTextField tf1, tf2, tf3, tf4;
    JButton btn1, btn2,btn3,btn4,btn5,btn6;
    
    
    GUIFORFRAMEWORK() throws Exception
    {	
    	
    	UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
    	setSize(700, 600);
    	
    	setLayout(null);
    	
    	
         
        setResizable(true);       
            
        setLocationRelativeTo(null);
        
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	setTitle("SALESFORCE SELENIUM AUTOMATION TESTING FRAMEWORK");
 
        l1 = new JLabel("PLEASE SELECT YOUR OPTION:");
        l1.setForeground(Color.blue);
        //l1.setAlignmentX(TOP_ALIGNMENT);
        //l1.setAlignmentY(LEFT_ALIGNMENT);
        
        
        l1.setFont(new Font("Serif", Font.BOLD, 16));
 
        
        
        //l2 = new JLabel("SFDC LOGIN URL:");
        //l3 = new JLabel("USER NAME (SYSTEM ADMIN):");
        //l4 = new JLabel("NAME OF SFDC OBJECT:");
        //l5 = new JLabel("SFDC URL OF THE RECORD:");
        
       
        //tf1 = new JTextField();
        //tf2 = new JTextField();
        //tf3 = new JTextField();
        //tf4 = new JTextField();
 
        //p1 = new JPasswordField();
        //p2 = new JPasswordField();
        //tf5 = new JTextField();
        
        btn1 = new JButton("1. Setup Automation Pre-Requisite");
        btn2 = new JButton("3. Create New Test Script");
        btn3 = new JButton("2. Create Object Repository");
        btn4 = new JButton("4. Run As Batch");
        btn5 = new JButton("5. Generate HTML Summary Report");
        btn6 = new JButton("6. Run Individually");
      
        
        btn1.addActionListener(this);
      
        btn2.addActionListener(this);
       
        btn3.addActionListener(this);
       
        btn4.addActionListener(this);
       
        btn5.addActionListener(this);
        
        btn6.addActionListener(this);
        
        
        setIconImage(Toolkit.getDefaultToolkit().getImage("HomeScreen.png"));
        JLabel background=new JLabel(new ImageIcon("HomeScreen.png"));
        background.setBounds(40, 50, 600, 200);
        background.setLayout(new FlowLayout());
        
        //background.applyComponentOrientation(getComponentOrientation());
        
        add(background);
    	
        
        l1.setBounds(100, 30, 400, 30); //Header label
        
        //l2.setBounds(60, 110, 200, 30); //First Line Label
        //l3.setBounds(60, 150, 200, 30);
        //l4.setBounds(60, 190, 200, 30);
        //l5.setBounds(60, 230, 200, 30);
        
       
        //tf1.setBounds(230, 110, 300, 30); //First Line Text Box
        //tf2.setBounds(230, 150, 300, 30);
        //tf3.setBounds(230, 190, 300, 30);
        //tf4.setBounds(230, 230, 300, 30);
        
        //tf1.setText(DataSetup.SFDC_LOGIN_URL);
        //tf2.setText(DataSetup.SYSTEM_ADMIN_USERNAME);
        //tf3.setText(DataSetup.NAME_OF_SFDC_OBJECT);
        //tf4.setText(DataSetup.SFDC_RECORD_URL);
       
        btn1.setBounds(50, 350, 270, 30);
        btn1.setBackground(new Color(64,64,64));
        btn1.setForeground(new Color(255,255,255));
        btn1.setFont(new Font("Serif", Font.BOLD, 15));
                     
        btn2.setBounds(50, 410, 270, 30);
        btn2.setBackground(new Color(64,64,64));
        btn2.setForeground(new Color(255,255,255));
        btn2.setFont(new Font("Serif", Font.BOLD, 15));
        
        btn3.setBounds(380, 350, 230, 30);
        btn3.setBackground(new Color(64,64,64));
        btn3.setForeground(new Color(255,255,255));
        btn3.setFont(new Font("Serif", Font.BOLD, 15));
        
        btn4.setBounds(380, 410,230, 30);
        btn4.setBackground(new Color(64,64,64));
        btn4.setForeground(new Color(255,255,255));
        btn4.setFont(new Font("Serif", Font.BOLD, 15));
        
        btn5.setBounds(200, 470, 270, 30);
        btn5.setBackground(new Color(64,64,64));
        btn5.setForeground(new Color(255,255,255));
        btn5.setFont(new Font("Serif", Font.BOLD, 15));
        
        
        btn6.setBounds(360, 480, 230, 30);
        btn6.setBackground(new Color(64,64,64));
        btn6.setForeground(new Color(255,255,255));
        btn6.setFont(new Font("Serif", Font.BOLD, 15));
        
       // add(l1);
                
        
        add(btn1);
        add(btn2);
        add(btn3);
        add(btn4);
        add(btn5);
        //add(btn6);
        
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
        	new GUI_FOR_PREREQUISITE();
        }
        else if(e.getSource() == btn2)
        {
        	this.setVisible(false);
        	new GUI_FOR_TESTSCRIPT_CREATION();
        }
        else if(e.getSource() == btn3)
        {
        	this.setVisible(false);
        	new GUI_For_ObjectRepository();
        	
        }
        else if(e.getSource() == btn4)
        {
        	this.setVisible(false);
        	new GUI_FOR_BATCHRUN();
        }
        else if(e.getSource() == btn5)
        {
        	//this.setVisible(false);
        	new GenerateHTMLSummaryReport();
        }
        else if(e.getSource() == btn6)
        {
        	this.setVisible(false);
        	new GUI_FOR_INDIVIDUAL_RUN();
        }
    	}
    	catch(Exception e1)
    	{
    		e1.printStackTrace();
    		
    	}
    	
    } 
    
   
    public static void main(String[] args) throws Exception{
        new GUIFORFRAMEWORK();      
      }
    
}