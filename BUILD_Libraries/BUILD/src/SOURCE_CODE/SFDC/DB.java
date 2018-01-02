package SOURCE_CODE.SFDC;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.sql.rowset.serial.SerialBlob;


public class DB {
	
	public static Connection con;
	
	public static Connection Connect(String ExcelFileName) throws Exception{
		
		
		if(con!=null)
		{
			con.close();
			con=null;
		}
		
		Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" ); 
		
		if(ExcelFileName.contains("xlsm"))
		 con = DriverManager.getConnection("jdbc:odbc:DRIVER={Microsoft Excel Driver (*.xls, *.xlsx, *.xlsm, *.xlsb)};DBQ=" + ExcelFileName +";readOnly=false");
		else
		 con = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=" + ExcelFileName +";readOnly=false");
		return con;
		
	}
	
	public static String ReadXLData(String SheetName,String ColumnNameTobeRead,String KeyColumnName,String KeyColumnValue) throws Exception
	{
			
		
	    	 int index=1;
	    	
			 Statement st = con.createStatement();
			 ResultSet rs= st.executeQuery("select * from ["+SheetName+"$] where "+KeyColumnName+"='"+KeyColumnValue+"'");
			 while(index>0){
				 rs.next();
				 index--;
			 }
			 
			 String	 value=rs.getString(ColumnNameTobeRead);
			 
			 //rs.close();rs=null;
			 //st.close(); st=null;
			 //con.close();con=null;
			 return value==null?"":value;
	}
	public static ResultSet GetAllExcelValues(String SheetName) throws Exception
	{			
		
		
	    	 int index=1;
	    	
			 Statement st = con.createStatement();
			 ResultSet rs= st.executeQuery("select * from ["+SheetName+"$]");
			
			 return rs;
		    	
		
	}    
	public static void UpdateXLCell(String SheetName, String NewValue, String ColumnNameTobeUpdated,String KeyColumnName,String KeyColumnValue) throws Exception
	{
		try 
		{
		//System.out.println(SheetName+"|"+NewValue+"|"+ColumnNameTobeUpdated+"|"+KeyColumnName+"|"+KeyColumnValue);
		if (KeyColumnValue.length()>0 && ColumnNameTobeUpdated.length()>0)
		{
			PreparedStatement ps = con.prepareStatement("UPDATE ["+SheetName+"$] SET "+ColumnNameTobeUpdated+"=? WHERE "+KeyColumnName+"=?");
		
			ps.setString(1, NewValue);
			ps.setString(2, KeyColumnValue);
			//ps.setNString(1, NewValue);
			//ps.setNString(1, KeyColumnValue);
			ps.executeUpdate();
	    
			//ps.close();ps=null;
			//con.close();con=null;
		}
		else
		{
			System.out.println("The value of param KeyColumnValue or ColumnNameTobeUpdated is blank");
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	} 
	public static void UpdateXLCellWithLargeString(String SheetName, String NewValue, String ColumnNameTobeUpdated,String KeyColumnName,String KeyColumnValue) throws Exception
	{
		try 
		{
		//System.out.println(SheetName+"|"+NewValue+"|"+ColumnNameTobeUpdated+"|"+KeyColumnName+"|"+KeyColumnValue);
		if (KeyColumnValue.length()>0 && ColumnNameTobeUpdated.length()>0)
		{
		//System.out.println(SheetName+"|"+NewValue+"|"+ColumnNameTobeUpdated+"|"+KeyColumnName+"|"+KeyColumnValue);
		PreparedStatement ps = con.prepareStatement("UPDATE ["+SheetName+"$] SET "+ColumnNameTobeUpdated+"=? WHERE "+KeyColumnName+"=?");
		
		//ps.setString(1, NewValue);
		InputStream is = new ByteArrayInputStream(NewValue.getBytes());
		final byte[] utf8Bytes = NewValue.getBytes("UTF-8");
		//System.out.println("Total byte length: "+utf8Bytes.length); // prints "11"
		ps.setAsciiStream(1, is,utf8Bytes.length);
		
		//ps.setAsciiStream(parameterIndex, x, length);
		ps.setString(2, KeyColumnValue);
        //ps.setNString(1, NewValue);
        //ps.setNString(1, KeyColumnValue);
		ps.executeUpdate();
	    
		//ps.close();ps=null;
	    //con.close();con=null;
		}
		else 
		{
			System.out.println("The value of param KeyColumnValue or ColumnNameTobeUpdated is blank");
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	} 
	public static void UpdateXLCellWithApend(String SheetName, String NewValue, String ColumnNameTobeUpdated,String KeyColumnName,String KeyColumnValue) throws Exception
	{
		try 
		{
		String s = ReadXLData(SheetName,ColumnNameTobeUpdated,KeyColumnName,KeyColumnValue);
		
		if (!s.equals(""))
		{
			NewValue = s + "," + NewValue;	
		}
		
		
		
		//System.out.println(SheetName+"|"+NewValue+"|"+ColumnNameTobeUpdated+"|"+KeyColumnName+"|"+KeyColumnValue);
		PreparedStatement ps = con.prepareStatement("UPDATE ["+SheetName+"$] SET "+ColumnNameTobeUpdated+"=? WHERE "+KeyColumnName+"=?");
		
		
		ps.setString(1, NewValue);
		ps.setString(2, KeyColumnValue);
        
		
		ps.executeUpdate();
	    
		ps.close();ps=null;
	    //con.close();con=null;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}  
	public static void InsertXLRowInTestLogs(Connection c,String SheetName,String Steps,String TestCaseName,String Details,String Results) throws Exception
	{
		//Connection c = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=" + "SM.xls" +";readOnly=false");
		try 
		{
		PreparedStatement ps = c.prepareStatement("insert into ["+SheetName+"$] values(?,?,?,?,?)");
		
		ps.setString(1,Steps);
		ps.setString(2, TestCaseName);
		ps.setString(3, Details);
		ps.setString(4, Results);
		ps.setString(5, "");
		
		ps.executeUpdate();
	    
		ps.close();
		c.close();
		}catch(Exception e) 
		{
			e.printStackTrace();
		} 
	}     
	
	public static void InsertXLRowInSummaryTestLogs(Connection c,String SheetName,String TestCaseName,String Description,String Status,String StartofExecution,String EndofExecution,String Duration,String Browser,String Profiles) throws Exception
	{
		//Connection c = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=" + "SM.xls" +";readOnly=false");
		try 
		{
		PreparedStatement ps = c.prepareStatement("insert into ["+SheetName+"$] values(?,?,?,?,?,?,?,?)");
		
		ps.setString(1,TestCaseName);
		ps.setString(2,Description);
		ps.setString(3,Status);
		ps.setString(4,StartofExecution);
		ps.setString(5,EndofExecution);
		ps.setString(6,Duration);
		ps.setString(7,Browser);
		ps.setString(8,Profiles);
		
		
		
		ps.executeUpdate();
	    
		ps.close();
		c.close();
		}catch(Exception e) 
		{
			e.printStackTrace();
		} 
	}  
	public static void Insert_A_Row_IN_Excel(String SheetName,int NoOFColumn,String EntireRow_SemicolonSeparated) throws Exception
	{
		try 
		{
			
			//Generating a String of ? symbol that will be concatenated inside preparedstatement.
			String symbol = "";
			
			//System.out.println("NoOFColumn:"+NoOFColumn);
			for(int a=1;a<=NoOFColumn;a++)
			{
				if(symbol.equals(""))
				{
					symbol = "?";
				}
				else
				{
					symbol = symbol + ",?";	
				}
				 
			}
			System.out.println(symbol);
			
			
			
		//System.out.println(SheetName+"|"+NewValue+"|"+ColumnNameTobeUpdated+"|"+KeyColumnName+"|"+KeyColumnValue);
			
			if (EntireRow_SemicolonSeparated.length()>0)
			{
			
				PreparedStatement ps = con.prepareStatement("insert into ["+SheetName+"$] values("+symbol+")");
		
				//Getting each value to arraylist
				List<String> eachcolumnvalue = Arrays.asList(EntireRow_SemicolonSeparated.split(";"));
				int index_prepstatement=1;
				for(int index=0;index<NoOFColumn;index++)
				{
					ps.setString(index_prepstatement, eachcolumnvalue.get(index));
					index_prepstatement++;
				}
					
			ps.executeUpdate();
	    
			//ps.close();ps=null;
			//con.close();con=null;
		}
		else
		{
			System.out.println("The value of param KeyColumnValue or ColumnNameTobeUpdated is blank");
		}
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	} 
}
