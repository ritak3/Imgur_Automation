package SOURCE_CODE.SFDC;

import USER_SPACE.TestPrerequisite.DataSetup;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
 
public class GUI_FOR_INDIVIDUAL_RUN extends JFrame implements ActionListener  
{ 
    JLabel l1,l2,l3;
    JButton btn1, btn2, btn3;
    JComboBox cb1,cb2; 
   
    String Sel_Module;
    String Sel_TC;
    JTextArea console;
    JScrollPane sp;
    PrintStream printStream;
 
    GUI_FOR_INDIVIDUAL_RUN() throws Exception
    {	
    	
    	Sel_Module = "Accounts";
    	setSize(800, 720);
    	
    	setLayout(null);
    	    	        
        setResizable(true);       
            
        setLocationRelativeTo(null);
        
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setTitle("Run Test Scripts as Batch / Individual");
 
        l1 = new JLabel("Select a Module / Functional Area:");
        l1.setForeground(Color.blue);
        l1.setFont(new Font("Serif", Font.BOLD, 12));
        
        l2 = new JLabel("Select a Test Script:");
        l2.setForeground(Color.blue);
        l2.setFont(new Font("Serif", Font.BOLD, 12));
      
        //
        console = new JTextArea("");
        
        JScrollPane sp = new JScrollPane(console);   
        
        //Reading All the Module Names from Modules Sheet
        DB.Connect(DataSetup.Logininfo);
        ResultSet rs = DB.GetAllExcelValues("MODULES");
        int Columns = rs.getMetaData().getColumnCount();
        int row = 0;     
        ArrayList al_Mod = new ArrayList();
        while (rs.next()) 
        {
        	//System.out.println("Module Name is: "+rs.getString(2).toString());
        	al_Mod.add(rs.getString(2).toString());       	
        }	
        String[] MOD = new String[al_Mod.size()];
        for(int i=0;i<al_Mod.size();i++)
        {
        	//System.out.println(al_Mod.get(i));
        	MOD[i] = al_Mod.get(i).toString();
        }
        
        
             
        cb1  = new JComboBox(MOD);
        cb1.addItem("--None--");
        cb1.setSelectedItem("--None--");
        
        cb2  = new JComboBox();
        //cb2.setSelectedItem("Accounts");
        
        
        btn1 = new JButton("<< Back");
        btn2 = new JButton("Run Test Script");
        btn3 = new JButton("Update Build");
 
        
        cb1.addActionListener(this);
        cb2.addActionListener(this);
        
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
     
        l1.setBounds(50, 30, 200, 30); 
        cb1.setBounds(280, 30, 300, 30);
        
        l2.setBounds(50, 80, 200, 30); 
        cb2.setBounds(280, 80, 300, 30);
        
        sp.setBounds(50, 150, 700, 400);
        console.setBackground(Color.DARK_GRAY);
        sp.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 5));
        
        //s.setAutoscrolls(true);
        console.setForeground(Color.WHITE);
        
       
        l2.setVisible(false);
        cb2.setVisible(false);
        
        
        btn1.setBounds(50, 600, 100, 30);
        btn1.setBackground(Color.PINK);
        
        btn2.setBounds(600, 30, 150, 30);
        btn2.setBackground(Color.PINK);
        
        btn3.setBounds(600, 600, 150, 30);
        btn3.setBackground(Color.PINK);
        
        
        add(l1);
        add(l2);
        //add(l3);
        add(cb1);
        add(cb2);
        
        add(btn1);
        add(btn2);
        add(btn3);
        
        add(sp);
        
        getContentPane().setBackground(Color.CYAN);
        setBackground(Color.CYAN);
        setVisible(true);
        
    }
   
    public void actionPerformed(ActionEvent e) 
     {
    	
    	try
    	{
    	
    	if(e.getSource() == cb1)
        {
    		cb2.removeAllItems();
    		if(!cb1.getSelectedItem().toString().equalsIgnoreCase("--None--"))
    		{
    			Sel_Module = cb1.getSelectedItem().toString();
        		System.out.println("Module Selected is:"+Sel_Module);
        	    
        		cb2.removeAll();
        		//Reading All the TestCases from EXE_[ModuleName] Sheet
                DB.Connect(DataSetup.Logininfo);
                ResultSet rs1 = DB.GetAllExcelValues("EXE_"+Sel_Module);
                int Columns = rs1.getMetaData().getColumnCount();
                int row = 0;     
                ArrayList al_tc = new ArrayList();
                while (rs1.next()) 
                {
                	//System.out.println("Module Name is: "+rs.getString(2).toString());
                	al_tc.add(rs1.getString(2).toString());       	
                }	
                
                for(int i=0;i<al_tc.size();i++)
                {
                	
                	cb2.addItem(al_tc.get(i).toString());
                	
                }
        	    
                            
                l2.setVisible(true);
                cb2.setVisible(true);
    		}
    		            
        }
    	
    	else if (e.getSource() == btn1)
        {
    		
    	 	try
        	{
    	 	
        		
        	}catch (Exception e1) {
       		 e1.printStackTrace();
    		}	
        }
        else if(e.getSource() == btn2)
        {
           	try
        	{
        	
           	 int pos = console.getCaretPosition(); //get the cursor position
             
           	// console.insert(cb2.getSelectedItem().toString(), pos); //insert your text
                
           	 printStream = new PrintStream(new CustomOutputStream(console));
           	 // keeps reference of standard output stream
             //standardOut = System.out;
              
             // re-assigns standard output stream and error output stream
             
        	 //System.out.println("Trying to enter the text in textarea");
             Runtime rt = Runtime.getRuntime();
 	 		 rt.exec("cmd /c start SINGLE_RUN.bat");
 	 		 System.out.println("testing");
 	 		
            
        	 //System.exit(0);
        	}catch (Exception e1) {
       		 e1.printStackTrace();
    		}	
 
        }
        else if(e.getSource() == btn3)
        {     	
        	
        	try
        	{
        	
        	
        		
        	}catch (Exception e1) {
       		 e1.printStackTrace();
    		}	
        	
        }
            
        }
    	catch(Exception e3)
    	{
    		e3.printStackTrace();
    		
    	}
    	
    } 

    
}
