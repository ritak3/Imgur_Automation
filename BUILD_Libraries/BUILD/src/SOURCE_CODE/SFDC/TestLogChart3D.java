package SOURCE_CODE.SFDC;

import java.awt.Color;
import java.io.*; 

import org.jfree.chart.ChartFactory; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PiePlot3D; 
import org.jfree.data.general.DefaultPieDataset; 
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartColor;

public class TestLogChart3D 
{
   public static void main( String[ ] args )throws Exception 
   {
      DefaultPieDataset dataset = new DefaultPieDataset( );             
      dataset.setValue( "Pass" , new Double( 2 ) );             
      dataset.setValue( "Fail" , new Double( 2 ) );
      
      
      JFreeChart chart = ChartFactory.createPieChart3D( 
         "Test Results" ,  // chart title                   
         dataset ,         // data 
         true ,            // include legend                   
         true, 
         false);
      

      final PiePlot3D plot = ( PiePlot3D ) chart.getPlot( );             
      plot.setStartAngle( 270 );             
      plot.setForegroundAlpha( 0.60f );             
      plot.setInteriorGap( 0.02 );        
      plot.setSectionPaint("Pass", new Color(57,138,23));
      plot.setSectionPaint("Fail", new Color(230,61,78));
      
      int width = 220; /* Width of the image */             
      int height = 160; /* Height of the image */                             
      File pieChart3D = new File( "Chart3D_TestResult.png" );                           
      ChartUtilities.saveChartAsPNG(pieChart3D , chart , width , height);
      //ChartUtilities.saveChartAsJPEG( pieChart3D , chart , width , height );   
   }
}