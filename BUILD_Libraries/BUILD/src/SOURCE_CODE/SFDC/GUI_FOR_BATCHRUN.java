package SOURCE_CODE.SFDC;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;

import USER_SPACE.TestPrerequisite.DataSetup;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
 
public class GUI_FOR_BATCHRUN extends JFrame implements ActionListener  
{ 
    JLabel l1,l2;
    JButton btn1, btn2, btn3,btn4,btn5;
    JComboBox cb; 
    JTable table;
    ResultSet rs;
    JScrollPane pane;
    String Sel_Module;
    DefaultTableModel model;
    ArrayList<Integer> al_dep;
    JCheckBox chk;
    
    //JPasswordField p1, p2;
 
    GUI_FOR_BATCHRUN() throws Exception
    {	
    	
    	Sel_Module = "Accounts";
    	setSize(1000, 650);
    	
    	setLayout(null);
    	    	        
        setResizable(true);       
            
        setLocationRelativeTo(null);
        
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setTitle("Run Test Scripts as Batch / Individual");
 
        l1 = new JLabel("Choose a Module / Functional Area:");
        l1.setForeground(Color.blue);
      
        
        l1.setFont(new Font("Serif", Font.BOLD, 14));
        
        l2 = new JLabel();
        
        //l2.setVisible(false);
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
        
	            
        cb  = new JComboBox(MOD);
        cb.setSelectedItem("Accounts");
        chk = new JCheckBox("Select All");
        model = new DefaultTableModel(new Object[]{"Sl.No.#", "Test Script Name", "Short Description","Browser","Execute?","Dependency"}, 0);

        DB.Connect(DataSetup.Logininfo);
        rs = DB.GetAllExcelValues("EXE_"+Sel_Module);
 
        int C = rs.getMetaData().getColumnCount();
        int RowCount=0;
        int row_counter_jtable = 0;
        //Boolean b = Boolean.TRUE;
        while (rs.next()) 
        {
        	model.addRow(new Object[]{rs.getString(1).toString(),rs.getString(2).toString(),rs.getString(3).toString(),rs.getString(4).toString(),Boolean.TRUE,rs.getString(6).toString()});
		}
        table = new JTable(model) {
            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                if(getValueAt(row, column) instanceof Boolean) {
                    return super.getDefaultRenderer(Boolean.class);
                } else {
                    return super.getCellRenderer(row, column);
                }
            }

            @Override
            public TableCellEditor getCellEditor(int row, int column) {
                if(getValueAt(row, column) instanceof Boolean) {
                    return super.getDefaultEditor(Boolean.class);
                } else {
                    return super.getCellEditor(row, column);
                }
            }
        };
        table.setAutoCreateRowSorter(true);
        
        //table.setBackground(Color.DARK_GRAY);
        //table.setForeground(Color.WHITE);
        
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        pane = new JScrollPane(table);
        pane.setBounds(20, 100, 950, 400);
        chk.setBounds(770, 70, 90, 30);
        chk.setFont(new Font("SansSerif", Font.BOLD, 10));
        chk.setBackground(Color.CYAN);
        
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        table.getColumnModel().getColumn(0).setMaxWidth(70);
        //table.getColumnModel().getColumn(0).setHeaderValue("Sl.No.");
        
        table.getColumnModel().getColumn(1).setMaxWidth(200);
        //table.getColumnModel().getColumn(1).setHeaderValue("Test Script Name");
        table.getColumnModel().getColumn(2).setMaxWidth(400);
        //table.getColumnModel().getColumn(2).setHeaderValue("Short Description");
        table.getColumnModel().getColumn(3).setMaxWidth(100);
        //table.getColumnModel().getColumn(3).setHeaderValue("Browser");
        table.getColumnModel().getColumn(4).setMaxWidth(100);
        //table.getColumnModel().getColumn(4).setHeaderValue("Execute?");
        table.getColumnModel().getColumn(5).setMaxWidth(100);
        //table.getColumnModel().getColumn(5).setHeaderValue("Dependency");
              
        
        //p1 = new JPasswordField();
        //p2 = new JPasswordField();
        //tf5 = new JTextField();
        
        btn1 = new JButton("Run As Batch");
        btn2 = new JButton("<< Back");
        btn3 = new JButton("Verify Test Case Dependency");
        btn4 = new JButton("Update Build");
        btn5 = new JButton("Schedule Test Suite");
       
        
        cb.addActionListener(this);
        
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        btn5.addActionListener(this);
        chk.addActionListener(this);
           
       
        l1.setBounds(100, 30, 400, 30); //Header label
        cb.setBounds(350, 30, 300, 30);
        
        btn1.setBounds(800, 550, 150, 30);
        btn1.setBackground(new Color(64,64,64));
        btn1.setForeground(new Color(255,255,255));
        btn1.setFont(new Font("Serif", Font.BOLD, 15));
        btn1.setToolTipText("Run selected test scripts as a batch.");
        
        btn2.setBounds(50, 550, 100, 30);
        btn2.setBackground(new Color(64,64,64));
        btn2.setForeground(new Color(255,255,255));
        btn2.setFont(new Font("Serif", Font.BOLD, 15));
        btn2.setToolTipText("Go Back to Framework Home window.");
        
        btn3.setBounds(710, 30, 250, 30);
        btn3.setBackground(new Color(64,64,64));
        btn3.setForeground(new Color(255,255,255));
        btn3.setFont(new Font("Serif", Font.BOLD, 15));
              
        btn3.setToolTipText("Click to auto-select the dependent test scripts.");
        btn4.setBounds(580, 550, 170, 30);
        btn4.setBackground(new Color(64,64,64));
        btn4.setForeground(new Color(255,255,255));
        btn4.setFont(new Font("Serif", Font.BOLD, 15));
        
        btn4.setToolTipText("Add updated *.java files to the build if modified. So that while execution updated code will be executed.");
        btn5.setBounds(200, 550, 170, 30);
        btn5.setBackground(new Color(64,64,64));
        btn5.setForeground(new Color(255,255,255));
        btn5.setFont(new Font("Serif", Font.BOLD, 15));
        
        btn5.setToolTipText("Launch the Windows Task Scheduler and Create a Basic Task and Schedule the BATCH_RUN.bat file.");
             
        
        add(l1);
        add(l2);
        add(cb);
        add(pane);
        add(btn1);
        add(btn2);
        add(btn3);
        add(chk);
        add(btn4);
        add(btn5);
        
        getContentPane().setBackground(new Color(102,178,255));
        setBackground(new Color(102,178,255));
        setVisible(true);
        
    }
   
    public void actionPerformed(ActionEvent e) 
     {
    	
    	try
    	{
    	
    	if(e.getSource() == cb)
        {
    		Sel_Module = cb.getSelectedItem().toString();
    		//System.out.println("Selected Value is:"+Sel_Module);
    		//System.out.println("JTable row number is:"+table.getRowCount());
    		int total_row_count_tobedeleted = table.getRowCount();
    		model = (DefaultTableModel) table.getModel();
    		int rowCount = model.getRowCount();
    		//Remove rows one by one from the end of the table
    		for (int i = rowCount - 1; i >= 0; i--) {
    		    model.removeRow(i);
    		}
    		
    		//table = null;
    		//model = null;
    		//model = new DefaultTableModel(new Object[]{"Sl.No.#", "Test Script Name", "Short Description","Browser","Execute?","Dependency"}, 0);
    		
    		DB.Connect(DataSetup.Logininfo);
            rs = DB.GetAllExcelValues("EXE_"+Sel_Module);
     
            int C = rs.getMetaData().getColumnCount();
            while (rs.next()) 
            {
            	model.addRow(new Object[]{rs.getString(1).toString(),rs.getString(2).toString(),rs.getString(3).toString(),rs.getString(4).toString(),Boolean.TRUE,rs.getString(6).toString()});
    		}
            table = new JTable(model) {
                @Override
                public TableCellRenderer getCellRenderer(int row, int column) {
                    if(getValueAt(row, column) instanceof Boolean) {
                        return super.getDefaultRenderer(Boolean.class);
                    } else {
                        return super.getCellRenderer(row, column);
                    }
                }

                @Override
                public TableCellEditor getCellEditor(int row, int column) {
                    if(getValueAt(row, column) instanceof Boolean) {
                        return super.getDefaultEditor(Boolean.class);
                    } else {
                        return super.getCellEditor(row, column);
                    }
                }
            };
            
            
        }
    	else if (e.getSource() == btn1)
        { //Run as Batch button
    		
    	 	try
        	{
    	 		String content = "";
    	 		if(!System.getenv().toString().contains("Program Files (x86)"))
    	 		{
    	 			 content = "set path=C:\\Program Files\\Java\\jdk1.7.0_97\\bin; \n   java -classpath run.jar; SOURCE_CODE.SFDC.RunTestSuiteAsBatch exit";
    	 			 //System.out.println("32bit");
    	 			 File file = new File("BATCH_RUN.bat");
    	             file.createNewFile();
    	             BufferedWriter wr = new BufferedWriter(new FileWriter(file));
    	             wr.write(content);
    	             wr.close();
    	 			
    	 		}
    	 		else
    	 		{
    	 		 content = "set path=C:\\Program Files (x86)\\Java\\jdk1.7.0_97\\bin; \n   java -classpath run.jar; SOURCE_CODE.SFDC.RunTestSuiteAsBatch exit";
   	 			 File file = new File("BATCH_RUN.bat");
   	             file.createNewFile();
   	             BufferedWriter wr = new BufferedWriter(new FileWriter(file));
   	             wr.write(content);
   	             wr.close();
    	 			
    	 			//System.out.println("64bit");
    	 		}
    	 		
    	 		// 1. Read all the data from JTable and store them to relevant excel sheet of GlobalData.xls 
    	 		int rc = model.getRowCount();
    	 		String tc_execute_state = "no";
    	 		
    	 		DB.Connect(DataSetup.Logininfo);
	 			DB.UpdateXLCell("Setup",Sel_Module, "Functional_Area", "SL_NO", "Setup1");
	 			
    	 		
    	 		for(int i=0;i<rc;i++)
                {
    	 			//System.out.println(model.getValueAt(i, 3).toString());
    	 			//System.out.println(model.getValueAt(i, 4).toString());
    	 			if(model.getValueAt(i, 4).toString().equalsIgnoreCase("true"))
    	 			{
    	 				tc_execute_state = "yes";
    	 			}
    	 			else
    	 			{
    	 				tc_execute_state = "no";
    	 			}
    	 			System.out.println(tc_execute_state+" "+Integer.toString(i+1));
    	 			DB.Connect(DataSetup.Logininfo);
    	 			DB.UpdateXLCell("EXE_"+Sel_Module,model.getValueAt(i, 3).toString(), "Browser", "slno", Integer.toString(i+1));
    	 			DB.Connect(DataSetup.Logininfo);
    	 			DB.UpdateXLCell("EXE_"+Sel_Module,tc_execute_state, "IsSelected", "slno", Integer.toString(i+1));
    	 			
                }
    	 		
    	 		
    	 		this.setVisible(false);
    	 		Runtime rt = Runtime.getRuntime();
    	 		rt.exec("cmd /c start BATCH_RUN.bat");
    	 		System.exit(0);
    	 		//Process proc = rt.exec("cmd /C " + "" +"BATCH_RUN.bat");
        	
        		
        	}catch (Exception e1) {
       		 e1.printStackTrace();
    		}	
        }
        else if(e.getSource() == btn2)
        {
        	this.setVisible(false);
        	new GUIFORFRAMEWORK();
        }
        else if(e.getSource() == btn4)
        	//Updating the build
        {
        	
        	
        	try
        	{
        		String content1 = "";
        		if(!System.getenv().toString().contains("Program Files (x86)"))
    	 		{
    	 			//This LOC is for 32 bit Windows 
        			DB.Connect(DataSetup.Logininfo);
        			String Home_Dir = DB.ReadXLData("Setup", "Path_chromedriver_exe", "SL_NO", "Setup1");
        			content1 = "set path="+Home_Dir.substring(0, Home_Dir.indexOf("chromedriver.exe")).replace("/", "\\")+"apache-ant-1.9.6\\bin;C:\\Program Files\\Java\\jdk1.7.0_97\\bin\r\n";
        			content1=content1 + "@echo off\r\n";
        			
        			DB.Connect(DataSetup.Logininfo);
        			Home_Dir = DB.ReadXLData("Setup", "Path_Home_Directory", "SL_NO", "Setup1");
        			
        			content1=content1 + "\r\ncd "+Home_Dir.replace("/", "\\") + "\r\n";
        			content1=content1 + "\r\ncall ant -buildfile build.xml create_run_jar"+ "\r\n" + "\r\n"+"exit";
        		
        			File file = new File("runant.bat");
    	            file.createNewFile();
    	            BufferedWriter wr = new BufferedWriter(new FileWriter(file));
    	            
    	            wr.write(content1);
    	            wr.close();
    	 			
    	 		}
    	 		else
    	 		{
    	 			//This LOC is for 64 bit Windows
    	 			DB.Connect(DataSetup.Logininfo);
        			String Home_Dir = DB.ReadXLData("Setup", "Path_chromedriver_exe", "SL_NO", "Setup1");
        			content1 = "set path="+Home_Dir.substring(0, Home_Dir.indexOf("chromedriver.exe")).replace("/", "\\")+"apache-ant-1.9.6\\bin;C:\\Program Files (x86)\\Java\\jdk1.7.0_97\\bin\r\n";
        			content1=content1 + "@echo off\r\n";
        			
        			DB.Connect(DataSetup.Logininfo);
        			Home_Dir = DB.ReadXLData("Setup", "Path_Home_Directory", "SL_NO", "Setup1");
        			
        			content1=content1 + "\r\ncd "+Home_Dir.replace("/", "\\") + "\r\n";
        			content1=content1 + "\r\ncall ant -buildfile build.xml create_run_jar"+ "\r\n" + "\r\n"+"exit";
        		
        			File file = new File("runant.bat");
    	            file.createNewFile();
    	            BufferedWriter wr = new BufferedWriter(new FileWriter(file));
    	            
    	            wr.write(content1);
    	            wr.close();
    	 		
    	 		}
        		
        		
        	//Thread.sleep(3000L);	
        	Runtime runtime = Runtime.getRuntime();
        	Process process;
			process = runtime.exec("runant.bat");
			
			
			BufferedReader stdInput = new BufferedReader(
				    new InputStreamReader( process.getInputStream() ));
			
				String s,to ;
				to = "";
								
				
			    JProgressBar progressBar = new JProgressBar();
				while ((s = stdInput.readLine()) != null) {
					l2.setText("Please wait. Build creation in progress...");
				    //System.out.println("s is:"+s);
				    to = to + s;
				        
				}
							
				if(to.trim().contains("BUILD SUCCESSFUL"))
				{
					JOptionPane.showMessageDialog(null, "Build Created Successfully!");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Build Created with Error!");
					System.out.println(to.trim());
				}
				
        		
        	}catch (Exception e1) {
       		 e1.printStackTrace();
    		}	
        	
        }
        else if(e.getSource() == btn5)
        {
        	// 1. Read all the data from JTable and store them to relevant excel sheet of GlobalData.xls 
	 		int rc = model.getRowCount();
	 		String tc_execute_state = "no";
	 		
	 		try
	 		{
	 		DB.Connect(DataSetup.Logininfo);
 			DB.UpdateXLCell("Setup",Sel_Module, "Functional_Area", "SL_NO", "Setup1");
 			
	 		
	 		for(int i=0;i<rc;i++)
            {
	 			
	 			if(model.getValueAt(i, 4).toString().equalsIgnoreCase("true"))
	 			{
	 				tc_execute_state = "yes";
	 			}
	 			else
	 			{
	 				tc_execute_state = "no";
	 			}
	 			System.out.println(tc_execute_state+" "+Integer.toString(i+1));
	 			DB.Connect(DataSetup.Logininfo);
	 			DB.UpdateXLCell("EXE_"+Sel_Module,model.getValueAt(i, 3).toString(), "Browser", "slno", Integer.toString(i+1));
	 			DB.Connect(DataSetup.Logininfo);
	 			DB.UpdateXLCell("EXE_"+Sel_Module,tc_execute_state, "IsSelected", "slno", Integer.toString(i+1));
	 			
            }
	 		
	 		
	 		//this.setVisible(false);
	 		Runtime rt = Runtime.getRuntime();
	 		rt.exec("cmd /c start taskschd.msc");
	 		
	 		}catch(Exception e2)
	 		{
	 			e2.printStackTrace();
	 		}
        }
        else if(e.getSource() == chk)
        {
        	int rc = model.getRowCount();
        	
        	if(chk.isSelected())
        	{
        		for(int i=0;i<rc;i++)
                {
               	 model.setValueAt(Boolean.TRUE, i, 4);               	 
                }
        	}
        	else
        	{
        		for(int i=0;i<rc;i++)
                {
               	 model.setValueAt(Boolean.FALSE, i, 4);               	 
                }
        	}
        	
        	
        }
        else if(e.getSource() == btn3)
        {
        	
        	//Velow code is verified as working but user need to click multiple times on this button
        	for(int a=0;a<5;a++)
        	{
        	int rc = model.getRowCount();
        	String[][] replica_JTable = new String[rc][6];
        	Boolean[] execute = new Boolean[rc];
        	
        	
             
             for(int i=0;i<rc;i++)
             {
            	 replica_JTable[i][0] = model.getValueAt(i, 0).toString();
            	 replica_JTable[i][1] = model.getValueAt(i, 1).toString();
            	 replica_JTable[i][2] = model.getValueAt(i, 2).toString();
            	 replica_JTable[i][3] = model.getValueAt(i, 3).toString();
            	 replica_JTable[i][4] = model.getValueAt(i, 4).toString();
            	 replica_JTable[i][5] = model.getValueAt(i, 5).toString();
             }
             al_dep = new ArrayList();
             for(int i=0;i<rc;i++)
             {
            	if (replica_JTable[i][4].equalsIgnoreCase("true"))
            	{
            		String[] dependency = replica_JTable[i][5].split(",");
            		            		
            		for(int j=0;j<dependency.length;j++)
            		{
            			
            			al_dep.add(Integer.parseInt(dependency[j]));
            			
            		}
            	}
            	
            	
             }
             Set<Integer> uniquedep = new HashSet<Integer>(al_dep);
             for(int uv:uniquedep)
             {
            	 if(uv>0)
            	 {
            		 //System.out.println("UV is:"+uv);
            		 model.setValueAt(Boolean.TRUE, uv-1, 4);
            	 }
            	 
             }
        	}
        	
        }
        }
    	catch(Exception e1)
    	{
    		e1.printStackTrace();
    		
    	}
    	
    } 

    
}
