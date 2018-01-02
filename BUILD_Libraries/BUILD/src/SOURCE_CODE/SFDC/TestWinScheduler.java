package SOURCE_CODE.SFDC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TestWinScheduler {

public static void main(String args[]) throws IOException, InterruptedException {
  // schtasks /create /tn "HowToTask" /tr c:\temp\test.cmd /sc once /st 00:00:00 /sd 2022/01/01 /ru username /rp password

  List<String> commands = new ArrayList<String>();

  commands.add("schtasks.exe");
  commands.add("/CREATE");
  commands.add("/TN");
  commands.add("\"SM_TST1\"");
  commands.add("/TR");
  commands.add("\"D:/WebDriverFWONSFDC/GF_QA_AUTOMATION/BATCH_RUN.bat\"");
  commands.add("/SC");
  commands.add("once");
  commands.add("/ST");
  commands.add("11:39:00");
  commands.add("/SD");
  commands.add("10/15/2015");
  //commands.add("/RU");
  //commands.add("185584");
  //commands.add("/RP");
  //commands.add("Proposal@r3s2");

  ProcessBuilder builder = new ProcessBuilder(commands);
  Process p = builder.start();
  p.waitFor();
  System.out.println(p.exitValue()); // 0 : OK
                                     // 1 : Error
  
//stdout
InputStream is = p.getInputStream();
InputStreamReader isr = new InputStreamReader(is);
BufferedReader br = new BufferedReader(isr);
String line;
while ((line = br.readLine()) != null) {
 System.out.println(line);
}
//stderr
is = p.getErrorStream();
isr = new InputStreamReader(is);
br = new BufferedReader(isr);
while ((line = br.readLine()) != null) {
 System.out.println(line);
}
  }
}