package SOURCE_CODE.SFDC;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableColumn;

import USER_SPACE.TestPrerequisite.DataSetup;

 
public class GUI_FOR_PREREQUISITE extends JFrame implements ActionListener
{
    //TabbedPane and Parent Container
	JPanel jp1,jp2,jp3,jp4;
    JTabbedPane jtp;
    
    //First Tab related variables
	JLabel l,l1, l2, l3, l4, l5, l6, l7, l8,l10,l11,l12,l13,l14,l14a,l15,l16,l17,l18,l19,l20,l21,l22;
    JTextField tf1, tf2, tf4, tf5, tf6, tf7, tf8,tf10,tf11,tf12,tf13,tf15,tf16,tf17,tf18,tf19,tf20,tf21,tf22;
    JComboBox cb; 
    JButton btn1, btn2,btn3,btn4,btn5;
    JButton save,back,clear,save_next1,next,buttonnotinuse1,buttonnotinuse2,buttonnotinuse3,buttonnotinuse4;
    JButton save_next,back2,clear2,next2,next3,back3,clear3,back4,clear4,submitallsetup;
    Icon normal; 
    JCheckBox chkb,chkb1;
    BufferedImage myPicture,myPicture1;
    //Second Tab related variables
    JLabel L1,L2;
    JTable table;
    JScrollPane pane;
    Border bGreyLine;
       
    
    ResultSet rs;
    int RowCount;
   
    public GUI_FOR_PREREQUISITE() throws Exception{
        
    	//setSize(600, 600);
    	//UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
    	 
        setTitle("SETTING UP PRE-REQUISITE");
        
        jtp = new JTabbedPane();
        setSize(800, 700);
        
        
       
        
        setResizable(true);       
            
        setLocationRelativeTo(null);
        
        getContentPane().add(jtp);
        jp1 = new JPanel();
        jp1.setOpaque(true);
        
        //jp1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        
        //jp1.setSize(400,400);
        jp2 = new JPanel();
        jp2.setBackground(Color.PINK);
        //jp2.setSize(400,400);
        jp3 = new JPanel();
        jp3.setBackground(Color.PINK);
        //jp3.setSize(400,400);
        
        jp4 = new JPanel();
        jp4.setBackground(Color.PINK);
        
        l = new JLabel("Please provide below information first time:");
        l.setForeground(Color.blue);
        l.setFont(new Font("TimesRoman", Font.ITALIC, 20));
        
        //Second Tab Components
        L1 = new JLabel("Please verify testuser information details:");
        L1.setForeground(Color.blue);
        L1.setFont(new Font("TimesRoman", Font.ITALIC, 20));
        
        //Third Tab Components
        //L1 = new JLabel("Enable Email Notification Upon Completion of a Test Script ?");
        //L1.setForeground(Color.blue);
        //L1.setFont(new Font("Serif", Font.BOLD, 20));
               
        l14 = new JLabel(normal);
        myPicture = ImageIO.read(new File("Outlook_2013_Logo.png"));
        l14 = new JLabel(new ImageIcon(myPicture));
        
        l14a = new JLabel(normal);
        myPicture1 = ImageIO.read(new File("Selenium_JIRA.png"));
        l14a = new JLabel(new ImageIcon(myPicture1));
        
        chkb = new JCheckBox("Enable Email Notification on test script execution completion ?");
        chkb.setFont(new Font("TimesRoman", Font.ITALIC, 20));
        chkb.setForeground(Color.blue);
        
        
        chkb1 = new JCheckBox("Enable Selenium-JIRA Integration ?");
        chkb1.setFont(new Font("TimesRoman", Font.ITALIC, 20));
        chkb1.setForeground(Color.blue);
        
        
        //chkb.setVisible(true);
        table = new JTable(35, 6); //You may increase the number of row if you want to add more test users
               
        //table.setBackground(Color.WHITE);
        //table.setForeground(Color.BLACK);
        
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        pane = new JScrollPane(table);
        pane.setBounds(40, 100, 700, 300);
        
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(0).setHeaderValue("SL.NO.");
        
        table.getColumnModel().getColumn(1).setMaxWidth(200);
        table.getColumnModel().getColumn(1).setHeaderValue("URL");
        table.getColumnModel().getColumn(2).setMaxWidth(100);
        table.getColumnModel().getColumn(2).setHeaderValue("NAME");
        table.getColumnModel().getColumn(3).setMaxWidth(400);
        table.getColumnModel().getColumn(3).setHeaderValue("USERNAME");
        table.getColumnModel().getColumn(4).setMaxWidth(100);
        table.getColumnModel().getColumn(4).setHeaderValue("PASSWORD");
        table.getColumnModel().getColumn(5).setMaxWidth(100);
        table.getColumnModel().getColumn(5).setHeaderValue("PROFILE");
        
      //Reading all the rows from the Loginfo sheet of GlobalData.xls and putting the same in the UI.
        DB.Connect(DataSetup.Logininfo);
        rs = DB.GetAllExcelValues("LoginInfo");
        int Columns = rs.getMetaData().getColumnCount();
        RowCount=0;
        int row_counter_jtable = 0;
        
        while (rs.next()) {
		 	for(int i=1;i<=Columns;i++)
		 	{
		 		if(i==1)
		 		{
		 			table.setValueAt(rs.getString(i).toString(),row_counter_jtable ,0 );
		 			 			
		 		}
		 		else if(i==2)
		 		{
		 			table.setValueAt(rs.getString(i).toString(),row_counter_jtable ,1 );
		 		}
		 		else if(i==3)
		 		{
		 			table.setValueAt(rs.getString(i).toString(),row_counter_jtable ,2 );
		 		}
		 		else if(i==4)
		 		{
		 			table.setValueAt(rs.getString(i).toString(),row_counter_jtable ,3 );
		 		}
		 		else if(i==5)
		 		{
		 			table.setValueAt(rs.getString(i).toString(),row_counter_jtable ,4 );
		 		}
		 		else if(i==6)
		 		{
		 			table.setValueAt(rs.getString(i).toString(),row_counter_jtable ,5 );
		 		}
		 		
		 		//System.out.println(rs.getString(i));
		 	}
		 	RowCount++;
		 	row_counter_jtable++;
	    }
        System.out.println("RowCount:"+RowCount);
        
          
        
        //Labels for Tab1
        l1 = new JLabel("TEST ENVIRONMENT:");
        l1.setFont(new Font("TimesRoman",Font.BOLD,11));
        l2 = new JLabel("MODULE / FUNCTIONAL AREA:");
        l2.setFont(new Font("TimesRoman",Font.BOLD,11));
        l3 = new JLabel("BROWSER TO SELECT:");
        l3.setFont(new Font("TimesRoman",Font.BOLD,11));
        l4 = new JLabel("LOCATE PATH OF firefox.exe:");
        l4.setFont(new Font("TimesRoman",Font.BOLD,11));
        l5 = new JLabel("LOCATE PATH OF chromedriver:");
        l5.setFont(new Font("TimesRoman",Font.BOLD,11));
        l6 = new JLabel("LOCATE PATH OF IEDriver:");
        l6.setFont(new Font("TimesRoman",Font.BOLD,11));
        l7 = new JLabel("LOCATE PROJECT HOME DIRECTORY:");
        l7.setFont(new Font("TimesRoman",Font.BOLD,11));
        l8 = new JLabel("LOCATE TESTLOG TEMPLATE:");
        l8.setFont(new Font("TimesRoman",Font.BOLD,11));
        
        //LABELS FOR TAB3
        l10 = new JLabel("EMAIL HOST:");
        l10.setFont(new Font("TimesRoman",Font.BOLD,11));
        l11 = new JLabel("FROM EMAIL-ID :");
        l11.setFont(new Font("TimesRoman",Font.BOLD,11));
        l12 = new JLabel("TO EMAIL-ID :");
        l12.setFont(new Font("TimesRoman",Font.BOLD,11));
        l13 = new JLabel("CC EMAIL-ID :");
        l13.setFont(new Font("TimesRoman",Font.BOLD,11));
        
        
        //LABELS TAB4
        l15 = new JLabel("JIRA BASE URL:");
        l15.setFont(new Font("TimesRoman",Font.BOLD,11));
        l16 = new JLabel("PROXY PORT:");
        l16.setFont(new Font("TimesRoman",Font.BOLD,11));
        l17 = new JLabel("PROXY IP:");
        l17.setFont(new Font("TimesRoman",Font.BOLD,11));
        l18 = new JLabel("JIRA USERNAME:");
        l18.setFont(new Font("TimesRoman",Font.BOLD,11));
        l19 = new JLabel("JIRA PASSWORD:");
        l19.setFont(new Font("TimesRoman",Font.BOLD,11));
        l20 = new JLabel("ID OF STATUS TODO:");
        l20.setFont(new Font("TimesRoman",Font.BOLD,11));
        l21 = new JLabel("ID OF STATUS INPROGRESS:");
        l21.setFont(new Font("TimesRoman",Font.BOLD,11));
        l22 = new JLabel("ID OF STATUS DONE:");
        l22.setFont(new Font("TimesRoman",Font.BOLD,11));
        

        //l1.setBounds(0, 10, 20, 30);
        
        
        tf1 = new JTextField();
        tf1.setBackground(new Color(224,224,224));
        tf2 = new JTextField();
        tf2.setBackground(new Color(224,224,224));
        String[] Browsers = { "chrome", "ie", "ff"};
        cb  = new JComboBox(Browsers);
      
        tf4 = new JTextField();
        tf4.setBackground(new Color(224,224,224));
        tf5 = new JTextField();
        tf5.setBackground(new Color(224,224,224));
        tf6 = new JTextField();
        tf6.setBackground(new Color(224,224,224));
        tf7 = new JTextField();
        tf7.setBackground(new Color(224,224,224));
        tf8 = new JTextField();
        tf8.setBackground(new Color(224,224,224));
        
        tf10 = new JTextField();
        tf11 = new JTextField();
        tf12 = new JTextField();
        tf13 = new JTextField();
        
        tf15 = new JTextField();
        tf16 = new JTextField();
        tf17 = new JTextField();
        tf18 = new JTextField();
        tf19 = new JTextField();
        tf20 = new JTextField();
        tf21 = new JTextField();
        tf22 = new JTextField();
        
        
        tf10.setBackground(Color.WHITE);
        tf11.setBackground(Color.WHITE);
        tf12.setBackground(Color.WHITE);
        tf13.setBackground(Color.WHITE);
        
        tf15.setBackground(Color.WHITE);
        tf16.setBackground(Color.WHITE);
        tf17.setBackground(Color.WHITE);
        tf18.setBackground(Color.WHITE);
        tf19.setBackground(Color.WHITE);
        tf20.setBackground(Color.WHITE);
        tf21.setBackground(Color.WHITE);
        tf22.setBackground(Color.WHITE);
        
        
        btn1 = new JButton("Browse");
        btn2 = new JButton("Browse");
        btn3 = new JButton("Browse");
        btn4 = new JButton("Browse");
        btn5 = new JButton("Browse");

        save = new JButton("SAVE & NEXT >>");
        clear = new JButton("CLEAR");
        back = new JButton("<< BACK");
        next = new JButton("NEXT >>");
        
        buttonnotinuse1 = new JButton("");
        buttonnotinuse2 = new JButton("");
        buttonnotinuse3 = new JButton("");
        buttonnotinuse4 = new JButton("");
        
        save_next = new JButton("Save & Next >>");
        save_next1 = new JButton("Save & Next >>");
        back2 = new JButton("<< Back");
        clear2 = new JButton("Clear");
        next2 = new JButton("Next >>");
        next3 = new JButton("Next >>");
        
        back3 = new JButton("<< Back");
        clear3 = new JButton("Clear");
        back4 = new JButton("<< Back");
        clear4 = new JButton("Clear");
        submitallsetup = new JButton("Save & Close");
        
        
        
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        btn5.addActionListener(this);
        save_next.addActionListener(this);
        save_next1.addActionListener(this);
        back2.addActionListener(this);
        clear2.addActionListener(this);
        next2.addActionListener(this);
        next.addActionListener(this);
        next3.addActionListener(this);
        chkb.addActionListener(this);
        chkb1.addActionListener(this);
        back3.addActionListener(this);
        clear3.addActionListener(this);
        back4.addActionListener(this);
        clear4.addActionListener(this);
        submitallsetup.addActionListener(this);
        
        
        
        save.addActionListener(this);
        clear.addActionListener(this);
        back.addActionListener(this);
        //next.addActionListener(this);
        
        
        
        btn1.setBackground(new Color(64,64,64));
        btn1.setForeground(new Color(255,255,255));
        btn1.setFont(new Font("Serif", Font.BOLD, 15));
        
        btn2.setBackground(new Color(64,64,64));
        btn2.setForeground(new Color(255,255,255));
        btn2.setFont(new Font("Serif", Font.BOLD, 15));
        
        btn3.setBackground(new Color(64,64,64));
        btn3.setForeground(new Color(255,255,255));
        btn3.setFont(new Font("Serif", Font.BOLD, 15));
        
        
        btn4.setBackground(new Color(64,64,64));
        btn4.setForeground(new Color(255,255,255));
        btn4.setFont(new Font("Serif", Font.BOLD, 15));
        
        btn5.setBackground(new Color(64,64,64));
        btn5.setForeground(new Color(255,255,255));
        btn5.setFont(new Font("Serif", Font.BOLD, 15));
        
        save.setBackground(new Color(64,64,64));
        save.setForeground(new Color(255,255,255));
        save.setFont(new Font("Serif", Font.BOLD, 15));
        
        clear.setBackground(new Color(64,64,64));
        clear.setForeground(new Color(255,255,255));
        clear.setFont(new Font("Serif", Font.BOLD, 15));
        
        back.setBackground(new Color(64,64,64));
        back.setForeground(new Color(255,255,255));
        back.setFont(new Font("Serif", Font.BOLD, 15));
        
        next.setBackground(new Color(64,64,64));
        next.setForeground(new Color(255,255,255));
        next.setFont(new Font("Serif", Font.BOLD, 15));
        
        save_next.setBackground(new Color(64,64,64));
        save_next.setForeground(new Color(255,255,255));
        save_next.setFont(new Font("Serif", Font.BOLD, 15));
        
        save_next1.setBackground(new Color(64,64,64));
        save_next1.setForeground(new Color(255,255,255));
        save_next1.setFont(new Font("Serif", Font.BOLD, 15));
        
        back2.setBackground(new Color(64,64,64));
        back2.setForeground(new Color(255,255,255));
        back2.setFont(new Font("Serif", Font.BOLD, 15));
        
        next2.setBackground(new Color(64,64,64));
        next2.setForeground(new Color(255,255,255));
        next2.setFont(new Font("Serif", Font.BOLD, 15));
        
        next3.setBackground(new Color(64,64,64));
        next3.setForeground(new Color(255,255,255));
        next3.setFont(new Font("Serif", Font.BOLD, 15));
        
        clear2.setBackground(new Color(64,64,64));
        clear2.setForeground(new Color(255,255,255));
        clear2.setFont(new Font("Serif", Font.BOLD, 15));
        
        buttonnotinuse1.setBackground(new Color(102,178,255));
        buttonnotinuse2.setBackground(new Color(102,178,255));
        buttonnotinuse3.setBackground(new Color(102,178,255));
        buttonnotinuse4.setBackground(new Color(102,178,255));
        
               
        
        jp1.setLayout(new BorderLayout());
        jp2.setLayout(new BorderLayout());
        jp3.setLayout(new BorderLayout());
        jp4.setLayout(new BorderLayout());
        bGreyLine = BorderFactory.createLineBorder(new Color(255,255,255), 5, true);
        jp1.setBorder(bGreyLine);
        jp2.setBorder(bGreyLine);
        jp3.setBorder(bGreyLine);
        jp4.setBorder(bGreyLine);
        //jp1.setBackground(Color.LIGHT_GRAY);
        
        l.setBounds(60, 5, 500, 100);
        L1.setBounds(60, 5, 500, 100);
        
        l14.setBounds(50, 20, 50, 50);
        
        l14a.setBounds(210, 50, 400, 100);
        
        chkb.setBounds(100, 20, 600, 50);
        chkb.setBackground(Color.PINK);
        
        chkb1.setBounds(100, 20, 330, 50);
        chkb1.setBackground(Color.PINK);
        
        l1.setBounds(60, 110, 200, 30); //First Line Label
        l2.setBounds(60, 150, 200, 30);
        l3.setBounds(60, 190, 200, 30);
        l4.setBounds(60, 230, 200, 30);
        l5.setBounds(60, 270, 200, 30);
        l6.setBounds(60, 310, 200, 30);
        l7.setBounds(60, 350, 235, 30);
        l8.setBounds(60, 390, 200, 30);
        
        l10.setBounds(150, 150, 200, 30);
        l11.setBounds(150, 190, 200, 30);
        l12.setBounds(150, 230, 200, 30);
        l13.setBounds(150, 270, 200, 30);
        
        //Labels For TAB4
        //90, 110, 200, 30
        l15.setBounds(90, 150, 200, 30); //First Line Label
        l16.setBounds(90, 190, 200, 30);
        l17.setBounds(90, 230, 200, 30);
        l18.setBounds(90, 270, 200, 30);
        l19.setBounds(90, 310, 200, 30);
        l20.setBounds(90, 350, 235, 30);
        l21.setBounds(90, 390, 200, 30);
        l22.setBounds(90, 430,200,30);
        
        
        tf1.setBounds(280, 110, 300, 30);
        tf2.setBounds(280, 150, 300, 30);
        cb.setBounds(280, 190, 300, 30);
        tf4.setBounds(280, 230, 300, 30);
        tf5.setBounds(280, 270, 300, 30);
        tf6.setBounds(280, 310, 300, 30);
        tf7.setBounds(280, 350, 300, 30);
        tf8.setBounds(280, 390, 300, 30);
        
        tf10.setBounds(250, 150, 300, 30);
        tf11.setBounds(250, 190, 300, 30);
        tf12.setBounds(250, 230, 300, 30);
        tf13.setBounds(250, 270, 300, 30);
        
        //280, 110, 300, 30
        tf15.setBounds(280, 150, 300, 30);
        tf16.setBounds(280, 190, 300, 30);
        tf17.setBounds(280, 230, 300, 30);
        tf18.setBounds(280, 270, 300, 30);
        tf19.setBounds(280, 310, 300, 30);
        tf20.setBounds(280, 350, 300, 30);
        tf21.setBounds(280, 390, 300, 30);
        tf22.setBounds(280, 430, 300, 30);
        
        
        btn1.setBounds(600, 230, 80, 27);
        btn2.setBounds(600, 270, 80, 27);
        btn3.setBounds(600, 310, 80, 27);
        btn4.setBounds(600, 350, 80, 27);
        btn5.setBounds(600, 390, 80, 27);
        
        back.setBounds(100, 500, 100, 40);
        clear.setBounds(260, 500, 100, 40);
        save.setBounds(400, 500, 160, 40);
        next.setBounds(600, 500, 100, 40);
        buttonnotinuse1.setBounds(550, 500, 100, 40);
        buttonnotinuse2.setBounds(550, 500, 100, 40);
        buttonnotinuse3.setBounds(550, 500, 100, 40);
        buttonnotinuse4.setBounds(550, 500, 100, 40);
        
        back2.setBounds(100, 500, 100, 40);
        clear2.setBounds(260, 500, 100, 40);
        save_next.setBounds(400, 500, 140, 40);
        save_next1.setBounds(400, 500, 140, 40);
        next2.setBounds(580, 500, 100, 40);
        next3.setBounds(580, 500, 100, 40);
        
        
        back3.setBounds(100, 500, 100, 40);
        back3.setBackground(new Color(64,64,64));
        back3.setForeground(new Color(255,255,255));
        back3.setFont(new Font("Serif", Font.BOLD, 15));
        
        clear3.setBounds(250, 500, 100, 40);
        clear3.setBackground(new Color(64,64,64));
        clear3.setForeground(new Color(255,255,255));
        clear3.setFont(new Font("Serif", Font.BOLD, 15));
        
        back4.setBounds(100, 520, 100, 40);
        back4.setBackground(new Color(64,64,64));
        back4.setForeground(new Color(255,255,255));
        back4.setFont(new Font("Serif", Font.BOLD, 15));
        
        clear4.setBounds(320, 520, 100, 40);
        clear4.setBackground(new Color(64,64,64));
        clear4.setForeground(new Color(255,255,255));
        clear4.setFont(new Font("Serif", Font.BOLD, 15));
        
        submitallsetup.setBounds(550, 520, 150, 40);
        submitallsetup.setBackground(new Color(64,64,64));
        submitallsetup.setForeground(new Color(255,255,255));
        submitallsetup.setFont(new Font("Serif", Font.BOLD, 15));
        
        
        
        //Setting up Font of the textfields
        tf1.setFont(new Font("Arial", Font.BOLD, 12));
        tf2.setFont(new Font("Arial", Font.BOLD, 12));
        cb.setFont(new Font("Arial", Font.BOLD, 12));
        tf4.setFont(new Font("Arial", Font.BOLD, 12));
        tf5.setFont(new Font("Arial", Font.BOLD, 12));
        tf6.setFont(new Font("Arial", Font.BOLD, 12));
        tf7.setFont(new Font("Arial", Font.BOLD, 12));
        tf8.setFont(new Font("Arial", Font.BOLD, 12));
        
        
        
        
        
        //Setting up Default Value
        DB.Connect(DataSetup.Logininfo);
        tf1.setText(DB.ReadXLData("Setup","Test_Environment", "SL_NO", "Setup1"));
        tf2.setText(DB.ReadXLData("Setup", "Functional_Area", "SL_NO", "Setup1"));
        //cb.setSelectedIndex(0);
        cb.setSelectedItem(DB.ReadXLData("Setup", "Browser", "SL_NO", "Setup1"));
        
        DB.Connect(DataSetup.Logininfo);
        tf4.setText(DB.ReadXLData("Setup", "Path_firefox_exe", "SL_NO", "Setup1"));
        tf5.setText(DB.ReadXLData("Setup", "Path_chromedriver_exe", "SL_NO", "Setup1"));
        tf6.setText(DB.ReadXLData("Setup", "Path_IEDriver_exe", "SL_NO", "Setup1"));
        tf7.setText(DB.ReadXLData("Setup", "Path_Home_Directory", "SL_NO", "Setup1"));
        tf8.setText(DB.ReadXLData("Setup", "Path_TestLog_Template", "SL_NO", "Setup1"));

        
        tf10.setText(DB.ReadXLData("Setup", "Email_Host", "SL_NO", "Setup1"));
        tf11.setText(DB.ReadXLData("Setup", "Emailid_FROM", "SL_NO", "Setup1"));
        tf12.setText(DB.ReadXLData("Setup", "Emailid_TO", "SL_NO", "Setup1"));
        tf13.setText(DB.ReadXLData("Setup", "Emailid_CC1", "SL_NO", "Setup1"));
        
        DB.Connect(DataSetup.Logininfo);
        tf15.setText(DB.ReadXLData("JIRA_Integration", "JIRA_BASE_URL", "SL_NO", "INT1"));
        tf16.setText(DB.ReadXLData("JIRA_Integration", "PROXY_PORT", "SL_NO", "INT1"));
        tf17.setText(DB.ReadXLData("JIRA_Integration", "PROXY_IP", "SL_NO", "INT1"));
        tf18.setText(DB.ReadXLData("JIRA_Integration", "JIRA_USERNAME", "SL_NO", "INT1"));
        tf19.setText(DB.ReadXLData("JIRA_Integration", "JIRA_PASSWORD", "SL_NO", "INT1"));
        tf20.setText(DB.ReadXLData("JIRA_Integration", "ID_OF_STATUS_TODO", "SL_NO", "INT1"));
        tf21.setText(DB.ReadXLData("JIRA_Integration", "ID_OF_STATUS_INPROGRESS", "SL_NO", "INT1"));
        tf22.setText(DB.ReadXLData("JIRA_Integration", "ID_OF_STATUS_DONE", "SL_NO", "INT1"));
        
        
        
        DB.Connect(DataSetup.Logininfo);
        if (DB.ReadXLData("Setup", "Email_IsEnabled", "SL_NO", "Setup1").equalsIgnoreCase("Yes"))
        {
        	System.out.println("Email_IsEnabled column value is set as Yes in the Setup sheet of GlobalData.xls");
        	//chkb.setEnabled(true);
        	chkb.setSelected(true);
       	
        	tf10.setEditable(true);
  		   	tf10.setBackground(Color.WHITE);
  		   	tf11.setEditable(true);
  		   	tf11.setBackground(Color.WHITE);
  		   	tf12.setEditable(true);
  		   	tf12.setBackground(Color.WHITE);
  		   	tf13.setEditable(true);
  		   	tf13.setBackground(Color.WHITE);
        	
        }
        else if(DB.ReadXLData("Setup", "Email_IsEnabled", "SL_NO", "Setup1").equalsIgnoreCase("No"))
        {
        	System.out.println("Email_IsEnabled column value is set as No in the Setup sheet of GlobalData.xls");
        	//chkb.setEnabled(false);
        	chkb.setSelected(false);

 		    tf10.setEditable(false);
 	        tf10.setBackground(Color.LIGHT_GRAY);
 			tf11.setEditable(false);
 			tf11.setBackground(Color.LIGHT_GRAY);
 			tf12.setEditable(false);
 			tf12.setBackground(Color.LIGHT_GRAY);
 			tf13.setEditable(false);
 			tf13.setBackground(Color.LIGHT_GRAY);
 		
        	
        }
        DB.Connect(DataSetup.Logininfo);  
        if (DB.ReadXLData("Setup", "JIRA_Zephyr_Integration_IsEnabled", "SL_NO", "Setup1").equalsIgnoreCase("Yes"))
        {
        	System.out.println("True");
        	chkb1.setSelected(true);
       	
        	tf15.setEditable(true);
  		   	tf15.setBackground(new Color(204,255,255));
  		   	tf16.setEditable(true);
		   	tf16.setBackground(new Color(204,255,255));
		   	tf17.setEditable(true);
		   	tf17.setBackground(new Color(204,255,255));
		   	tf18.setEditable(true);
		   	tf18.setBackground(new Color(204,255,255));
		   	tf19.setEditable(true);
		   	tf19.setBackground(new Color(204,255,255));
		   	tf20.setEditable(true);
		   	tf20.setBackground(new Color(204,255,255));
		   	tf21.setEditable(true);
		   	tf21.setBackground(new Color(204,255,255));
		   	tf22.setEditable(true);
		   	tf22.setBackground(new Color(204,255,255));
		   	
  		   	
        	
        }
        else if(DB.ReadXLData("Setup", "JIRA_Zephyr_Integration_IsEnabled", "SL_NO", "Setup1").equalsIgnoreCase("No"))
        {
        	System.out.println("False");
        	chkb1.setSelected(false);

        	tf15.setEditable(false);
  		   	tf15.setBackground(Color.LIGHT_GRAY);
  		   	tf16.setEditable(false);
		   	tf16.setBackground(Color.LIGHT_GRAY);
		   	tf17.setEditable(false);
		   	tf17.setBackground(Color.LIGHT_GRAY);
		   	tf18.setEditable(false);
		   	tf18.setBackground(Color.LIGHT_GRAY);
		   	tf19.setEditable(false);
		   	tf19.setBackground(Color.LIGHT_GRAY);
		   	tf20.setEditable(false);
		   	tf20.setBackground(Color.LIGHT_GRAY);
		   	tf21.setEditable(false);
		   	tf21.setBackground(Color.LIGHT_GRAY);
		   	tf22.setEditable(false);
		   	tf22.setBackground(Color.LIGHT_GRAY);
 		
        	
        }
        //Order of Adding the components must be maintained here 
        jp1.add(l);
        jp2.add(L1);
        jp2.add(pane);
        jp1.add(l1);
        jp1.add(tf1);
        jp1.add(l2);
        jp1.add(tf2);
        jp1.add(l3);
        jp1.add(cb);
        jp1.add(l4);
        jp1.add(tf4);
        jp1.add(btn1);
        jp1.add(l5);
        jp1.add(tf5);
        jp1.add(btn2);
        jp1.add(l6);
        jp1.add(tf6);
        jp1.add(btn3);
        jp1.add(l7);
        jp1.add(tf7);
        jp1.add(btn4);
        jp1.add(l8);
        jp1.add(tf8);
        jp1.add(btn5);
        
        
        jp1.add(back);
        jp1.add(save);
        jp1.add(clear);
        jp1.add(next);
        jp1.add(buttonnotinuse1);
        
        jp2.add(back2);
        jp2.add(clear2);
        jp2.add(save_next);
        jp2.add(next2);
        jp2.add(buttonnotinuse2);
               
        jp3.add(l14);
        jp3.add(chkb);
        jp4.add(chkb1);
        jp3.add(l10);
        jp3.add(tf10);
        jp3.add(l11);
        jp3.add(tf11);
        jp3.add(l12);
        jp3.add(tf12);
        jp3.add(l13);
        jp3.add(tf13);
        jp3.add(l14);
        jp3.add(back3);
        jp3.add(clear3);
        jp3.add(next3);
        jp3.add(save_next1);
        jp3.add(buttonnotinuse3);
        
        jp4.add(submitallsetup);
        jp4.add(l14a);
        jp4.add(back4);
        jp4.add(clear4);
        
        jp4.add(l15);
        jp4.add(tf15);
        jp4.add(l16);
        jp4.add(tf16);
        jp4.add(l17);
        jp4.add(tf17);
        jp4.add(l18);
        jp4.add(tf18);
        jp4.add(l19);
        jp4.add(tf19);
        jp4.add(l20);
        jp4.add(tf20);
        jp4.add(l21);
        jp4.add(tf21);
        jp4.add(l22);
        jp4.add(tf22);
        jp4.add(buttonnotinuse4);
        //chkb.setSelected(false);
        
        //tf10.setEditable(false);
        //tf10.setBackground(Color.LIGHT_GRAY);
		//tf11.setEditable(false);
		//tf11.setBackground(Color.LIGHT_GRAY);
		//tf12.setEditable(false);
		//tf12.setBackground(Color.LIGHT_GRAY);
		//tf13.setEditable(false);
		//tf13.setBackground(Color.LIGHT_GRAY);
        //jp2.add(pane, BorderLayout.CENTER);
        jtp.setBackground(Color.WHITE);
        
        jtp.addTab("1. SETUP - FILE PATH", jp1);
        jtp.addTab("2. SETUP - LOGININFO", jp2);
        jtp.addTab("3. SETUP - EMAIL NOFITICATION", jp3);
        jtp.addTab("4. SETUP - JIRA INTEGRATION", jp4);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) 
    {
   	
   	try
   	{
   	
       if (e.getSource() == btn1)
       {
    	   JFileChooser fileChooser = new JFileChooser();
    	   
           // For Directory
           //fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    
           // For File
           fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    
           fileChooser.setAcceptAllFileFilterUsed(false);
    
           int rVal = fileChooser.showOpenDialog(null);
           if (rVal == JFileChooser.APPROVE_OPTION) {
             tf4.setText(fileChooser.getSelectedFile().toString());
           }
          // System.out.println(tf4.getText());
       	
       }
       else if(e.getSource() == btn2)
       {
    	   JFileChooser fileChooser = new JFileChooser();
    	   
           // For Directory
           //fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    
           // For File
           fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    
           fileChooser.setAcceptAllFileFilterUsed(false);
    
           int rVal = fileChooser.showOpenDialog(null);
           if (rVal == JFileChooser.APPROVE_OPTION) {
             tf5.setText(fileChooser.getSelectedFile().toString());
           }
           //System.out.println(tf5.getText());
       }
       else if(e.getSource() == btn3)
       {
    	   JFileChooser fileChooser = new JFileChooser();
    	   
           // For Directory
           //fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    
           // For File
           fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    
           fileChooser.setAcceptAllFileFilterUsed(false);
    
           int rVal = fileChooser.showOpenDialog(null);
           if (rVal == JFileChooser.APPROVE_OPTION) {
             tf6.setText(fileChooser.getSelectedFile().toString());
           }
           //System.out.println(tf6.getText());
       }
       else if(e.getSource() == btn4)
       {
    	   JFileChooser fileChooser = new JFileChooser();
    	   
           // For Directory
           fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    
           // For File
          // fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    
           fileChooser.setAcceptAllFileFilterUsed(false);
    
           int rVal = fileChooser.showOpenDialog(null);
           if (rVal == JFileChooser.APPROVE_OPTION) {
             tf7.setText(fileChooser.getSelectedFile().toString());
           }
          //System.out.println(tf7.getText());
       }
       else if(e.getSource() == btn5)
       {
    	   JFileChooser fileChooser = new JFileChooser();
    	   
           // For Directory
           fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    
           // For File
           //fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    
           fileChooser.setAcceptAllFileFilterUsed(false);
    
           int rVal = fileChooser.showOpenDialog(null);
           if (rVal == JFileChooser.APPROVE_OPTION) {
             tf8.setText(fileChooser.getSelectedFile().toString());
           }
           //System.out.println(tf8.getText());
       }
       else if(e.getSource() == back)
       {
    	   this.setVisible(false);
    	   new GUIFORFRAMEWORK();   
       }
       else if(e.getSource() == clear)
       {
    	   tf1.setText("");
    	   tf2.setText("");
    	   //cb.getSelectedItem().toString().trim();
    	   tf4.setText("");
    	   tf5.setText("");
    	   tf6.setText("");
    	   tf7.setText("");
    	   tf8.setText("");
       }
       else if(e.getSource() == next)
       {
    	   jtp.setSelectedIndex(1);
       }
       
       else if(e.getSource() == save)
       {
    	   String test_env = tf1.getText();
    	   String module = tf2.getText();
    	   String browser = cb.getSelectedItem().toString().trim();
    	   String path_firefox_exe = tf4.getText();
    	   String path_chrome_driver = tf5.getText();
    	   String path_IEDriver = tf6.getText();
    	   String path_Home_Dir = tf7.getText();
    	   String path_testLogTemplate = tf8.getText();
    	   
    	   path_firefox_exe = path_firefox_exe.replace("\\", "/");
    	   path_chrome_driver = path_chrome_driver.replace("\\", "/");
    	   path_IEDriver = path_IEDriver.replace("\\", "/");
    	   path_Home_Dir = path_Home_Dir.replace("\\","/");
    	   path_testLogTemplate = path_testLogTemplate.replace("\\", "/");
    	   
    	   DB.Connect(DataSetup.Logininfo);
    	   DB.UpdateXLCell("Setup", test_env ,"Test_Environment", "SL_NO", "Setup1");
           DB.UpdateXLCell("Setup", module ,"Functional_Area", "SL_NO", "Setup1");
           DB.UpdateXLCell("Setup", browser,"Browser", "SL_NO", "Setup1");
       	   DB.UpdateXLCell("Setup", path_firefox_exe ,"Path_firefox_exe", "SL_NO", "Setup1");
           DB.UpdateXLCell("Setup", path_chrome_driver ,"Path_chromedriver_exe", "SL_NO", "Setup1");
           DB.UpdateXLCell("Setup", path_IEDriver,"Path_IEDriver_exe", "SL_NO", "Setup1");
           DB.UpdateXLCell("Setup", path_Home_Dir,"Path_Home_Directory", "SL_NO", "Setup1");
           DB.UpdateXLCell("Setup", path_testLogTemplate ,"Path_TestLog_Template", "SL_NO", "Setup1");
           jtp.setSelectedIndex(1);
           
           //System.exit(0);
           
       }
       else if(e.getSource() == back2)
       {
    	   jtp.setSelectedIndex(0);
       }
       else if(e.getSource() == next2)
       {
    	   jtp.setSelectedIndex(2);
       }
       else if(e.getSource() == next3)
       {
    	   jtp.setSelectedIndex(3);
       }
       else if(e.getSource() == clear2)
       {
    	   
    	   //table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
    	   
    	   table.setColumnSelectionInterval(0, 0);
    	   table.setRowSelectionInterval(0, 0);
    	  
    	   int count_row = table.getRowCount();
    	   System.out.println("count_row:"+count_row);
    	   for(int j=0;j<count_row;j++)
    	   {
    		   	   table.setValueAt(null, j, 0);
    		   	   table.setValueAt(null, j, 1);
    		   	   table.setValueAt(null, j, 2);
    		   	   table.setValueAt(null, j, 3);
    		   	   table.setValueAt(null, j, 4);
    		   	   table.setValueAt(null, j, 5);
    		   	   
    		   	   //DB.Connect(DataSetup.Logininfo);
    		   	   //DB.UpdateXLCell("Logininfo", "", "SL_NO", "URL", "https://login.salesforce.com");
    		   	   
    		   	   
    	   }
    	       	      	   
       }
       if (e.getSource() == chkb)
       {
    	   if(chkb.isSelected())
    	   {
    		   tf10.setEditable(true);
    		   tf10.setBackground(Color.WHITE);
    		   tf11.setEditable(true);
    		   tf11.setBackground(Color.WHITE);
    		   tf12.setEditable(true);
    		   tf12.setBackground(Color.WHITE);
    		   tf13.setEditable(true);
    		   tf13.setBackground(Color.WHITE);
    	   }
    	   else
    	   {
    		    tf10.setEditable(false);
    	        tf10.setBackground(Color.LIGHT_GRAY);
    			tf11.setEditable(false);
    			tf11.setBackground(Color.LIGHT_GRAY);
    			tf12.setEditable(false);
    			tf12.setBackground(Color.LIGHT_GRAY);
    			tf13.setEditable(false);
    			tf13.setBackground(Color.LIGHT_GRAY);
    		   
    	   }
    	   
    	   
       }
       if (e.getSource() == chkb1)
       {
    	   if(chkb1.isSelected())
    	   {
    		tf15.setEditable(true);
     		tf15.setBackground(new Color(204,255,255));
     		tf16.setEditable(true);
   		   	tf16.setBackground(new Color(204,255,255));
   		   	tf17.setEditable(true);
   		   	tf17.setBackground(new Color(204,255,255));
   		   	tf18.setEditable(true);
   		   	tf18.setBackground(new Color(204,255,255));
   		   	tf19.setEditable(true);
   		   	tf19.setBackground(new Color(204,255,255));
   		   	tf20.setEditable(true);
   		   	tf20.setBackground(new Color(204,255,255));
   		   	tf21.setEditable(true);
   		   	tf21.setBackground(new Color(204,255,255));
   		   	tf22.setEditable(true);
   		   	tf22.setBackground(new Color(204,255,255));
    	   }
    	   else
    	   {
    		    
    		tf15.setEditable(false);
     		tf15.setBackground(Color.LIGHT_GRAY);
     		tf16.setEditable(false);
   		   	tf16.setBackground(Color.LIGHT_GRAY);
   		   	tf17.setEditable(false);
   		   	tf17.setBackground(Color.LIGHT_GRAY);
   		   	tf18.setEditable(false);
   		   	tf18.setBackground(Color.LIGHT_GRAY);
   		   	tf19.setEditable(false);
   		   	tf19.setBackground(Color.LIGHT_GRAY);
   		   	tf20.setEditable(false);
   		   	tf20.setBackground(Color.LIGHT_GRAY);
   		   	tf21.setEditable(false);
   		   	tf21.setBackground(Color.LIGHT_GRAY);
   		   	tf22.setEditable(false);
   		   	tf22.setBackground(Color.LIGHT_GRAY);
    	   }
    	   
    	   
       }
       if (e.getSource() == back3)
       {
    	   jtp.setSelectedIndex(1); 
       }
       if (e.getSource() == back4)
       {
    	   jtp.setSelectedIndex(2); 
       }
       if (e.getSource() == clear3)
       {
    	   tf10.setText("");
    	   tf11.setText("");
    	   tf12.setText("");
    	   tf13.setText("");
       }
       if (e.getSource() == clear4)
       {
    	   tf15.setText("");
    	   tf16.setText("");
    	   tf17.setText("");
    	   tf18.setText("");
    	   tf19.setText("");
    	   tf20.setText("");
    	   tf21.setText("");
    	   tf22.setText("");
       }
       if (e.getSource() == save_next1)
       {
    	   DB.Connect(DataSetup.Logininfo);
    	   
    	   if(chkb.isSelected())
    	   {
    		   DB.Connect(DataSetup.Logininfo);
    		   DB.UpdateXLCell("Setup", "Yes", "Email_IsEnabled", "SL_NO", "Setup1");  
    		   DB.Connect(DataSetup.Logininfo);
    		   DB.UpdateXLCell("Setup", tf10.getText(), "Email_Host", "SL_NO", "Setup1");
        	   DB.Connect(DataSetup.Logininfo);
    		   DB.UpdateXLCell("Setup", tf11.getText(), "Emailid_FROM", "SL_NO", "Setup1");
    		   DB.Connect(DataSetup.Logininfo);
    		   DB.UpdateXLCell("Setup", tf12.getText(), "Emailid_TO", "SL_NO", "Setup1");
    		   DB.Connect(DataSetup.Logininfo);
    		   DB.UpdateXLCell("Setup", tf13.getText(), "Emailid_CC1", "SL_NO", "Setup1");
    	   }
    	   else
    	   {
    		   DB.Connect(DataSetup.Logininfo);
    		   DB.UpdateXLCell("Setup", "No", "Email_IsEnabled", "SL_NO", "Setup1");
    		   System.out.println("Email Enabler no checked");
    	   }
    	   
    	   jtp.setSelectedIndex(3);
       }
       if (e.getSource() == submitallsetup)
       {
    	   
    	   if(chkb1.isSelected())
    	   {
    		   DB.Connect(DataSetup.Logininfo);
    		   DB.UpdateXLCell("Setup", "Yes", "JIRA_Zephyr_Integration_IsEnabled", "SL_NO", "Setup1");    	
    		   DB.Connect(DataSetup.Logininfo);
    		   DB.UpdateXLCell("JIRA_Integration", tf15.getText(), "JIRA_BASE_URL", "SL_NO", "INT1");
    		   DB.Connect(DataSetup.Logininfo);
    		   DB.UpdateXLCell("JIRA_Integration", tf16.getText(), "PROXY_PORT", "SL_NO", "INT1");
    		   DB.Connect(DataSetup.Logininfo);
    		   DB.UpdateXLCell("JIRA_Integration", tf17.getText(), "PROXY_IP", "SL_NO", "INT1");
    		   DB.Connect(DataSetup.Logininfo);
    		   DB.UpdateXLCell("JIRA_Integration", tf18.getText(), "JIRA_USERNAME", "SL_NO", "INT1");
    		   DB.Connect(DataSetup.Logininfo);
    		   DB.UpdateXLCell("JIRA_Integration", tf19.getText(), "JIRA_PASSWORD", "SL_NO", "INT1");
    		   DB.Connect(DataSetup.Logininfo);
    		   DB.UpdateXLCell("JIRA_Integration", tf20.getText(), "ID_OF_STATUS_TODO", "SL_NO", "INT1");
    		   DB.Connect(DataSetup.Logininfo);
    		   DB.UpdateXLCell("JIRA_Integration", tf21.getText(), "ID_OF_STATUS_INPROGRESS", "SL_NO", "INT1");
    		   DB.Connect(DataSetup.Logininfo);
    		   DB.UpdateXLCell("JIRA_Integration", tf22.getText(), "ID_OF_STATUS_DONE", "SL_NO", "INT1");
    		   
    	   }
    	   else
    	   {
    		   DB.Connect(DataSetup.Logininfo);
    		   DB.UpdateXLCell("Setup", "No", "JIRA_Zephyr_Integration_IsEnabled", "SL_NO", "Setup1");
    		   System.out.println("Jira integration not checked ");
    	   }
    	   		   
		   
		   this.setVisible(false);
		   new GUIFORFRAMEWORK();
		   
       }
       else if(e.getSource() == save_next)
       {
    	   
    	   //table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
    	   
    	   table.setColumnSelectionInterval(0, 0);
    	   table.setRowSelectionInterval(0, 0);
    	  
    	   int count_row = table.getRowCount();
    	   System.out.println("count_row:"+count_row);
    	   for(int j=0;j<count_row;j++)
    	   {
    		   if(table.getValueAt(j, 0) != null)
    		   {
    			   DB.Connect(DataSetup.Logininfo);
    			   System.out.println("J = "+j+", "+"RowCount="+RowCount);
    			   if(j<RowCount)
    			   {
    				   
    			   		DB.UpdateXLCell("Logininfo", table.getValueAt(j, 0).toString(), "SL_NO", "SL_NO", String.valueOf(j+1));
    			   		DB.UpdateXLCell("Logininfo", table.getValueAt(j, 1).toString(), "URL", "SL_NO", String.valueOf(j+1));
    			   		DB.UpdateXLCell("Logininfo", table.getValueAt(j, 2).toString(), "Name", "SL_NO", String.valueOf(j+1));
    			   		DB.UpdateXLCell("Logininfo", table.getValueAt(j, 3).toString(), "Username", "SL_NO", String.valueOf(j+1));
    			   		DB.UpdateXLCell("Logininfo", table.getValueAt(j, 4).toString(), "Password", "SL_NO", String.valueOf(j+1));
    			   		DB.UpdateXLCell("Logininfo", table.getValueAt(j, 5).toString(), "Profiles", "SL_NO", String.valueOf(j+1));
    			   
    			   		System.out.println(table.getValueAt(j, 0));
    			   		System.out.println(table.getValueAt(j, 1));
    			   		System.out.println(table.getValueAt(j, 2));
    			   		System.out.println(table.getValueAt(j, 3));
    			   		System.out.println(table.getValueAt(j, 4));
    			   		System.out.println(table.getValueAt(j, 5));
    			   }
    			   else
    			   {
    				   
    				    DB.Insert_A_Row_IN_Excel("Logininfo", 6, table.getValueAt(j, 0)+";"+table.getValueAt(j, 1)+";"+table.getValueAt(j, 2)+";"+table.getValueAt(j, 3)+";"+table.getValueAt(j, 4)+";"+table.getValueAt(j, 5));

    				   	System.out.println(table.getValueAt(j, 0));
    			   		System.out.println(table.getValueAt(j, 1));
    			   		System.out.println(table.getValueAt(j, 2));
    			   		System.out.println(table.getValueAt(j, 3));
    			   		System.out.println(table.getValueAt(j, 4));
    			   		System.out.println(table.getValueAt(j, 5));
    			   		
    			      
    			   }
    		   }
    	   }
    	   
    	   jtp.setSelectedIndex(2);
    	   
       }
   	}
   	catch(Exception e1)
   	{
   		e1.printStackTrace();
   		
   	}
   	
   } 
   
    public static void main(String[] args) throws Exception {
         
    	new GUI_FOR_PREREQUISITE();
    	
    	//tp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //tp.setVisible(true);
         
    }
}