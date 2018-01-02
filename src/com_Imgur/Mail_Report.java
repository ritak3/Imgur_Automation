//package com_Imgur;
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Properties;
//
//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import javax.activation.FileDataSource;
//import javax.mail.*;
//import javax.mail.internet.*;
// 
//public class Mail_Report{
// 
//public static void main(String[] args) {
//	
//	Calendar cal = Calendar.getInstance();
//	String currentDate = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss").format(cal.getTime());
// 
//    final String username = "ritak3@gmail.com"; 
//    final String password = "radhika12#"; 
//    final String from = "ritak3@gmail.com"; 
//    final String to = "rita@imgur.com"; 
//  //  final String cc = "kevin@imgur.com"; 
////    final String bcc = "test.bcc.email@helloselenium.com"; 
//    final String subject = "Test Run Report " + currentDate; 
//    final String msg = "Here is the report of test run for the day."; 
// 
//    Properties props = new Properties();
//    props.put("mail.smtp.auth", true);
//    props.put("mail.smtp.starttls.enable", true);
//    props.put("mail.smtp.host", "smtp.gmail.com");
//    props.put("mail.smtp.port", "587");
// 
//    Session session = Session.getInstance(props,
//            new javax.mail.Authenticator() {
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(username, password);
//                }
//            });
// 
//    try {
// 
//        Message message = new MimeMessage(session);
//        message.setFrom(new InternetAddress(from));
//        message.setRecipients(Message.RecipientType.TO,
//                InternetAddress.parse(to));
//        //below code only requires if your want cc email address
////        message.setRecipients(Message.RecipientType.CC,
////                InternetAddress.parse(cc));
////        //below code only requires if your want bcc email address
////        message.setRecipients(Message.RecipientType.BCC,
////                InternetAddress.parse(bcc));
//        message.setSubject(subject);
//        message.setText(msg);
// 
//        MimeBodyPart messageBodyPart = new MimeBodyPart();
// 
//        Multipart multipart = new MimeMultipart();
// 
//        messageBodyPart = new MimeBodyPart();
////        String reportLocation =System.getProperty("user.dir");
////        Calendar cal = Calendar.getInstance();
////		String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime());
//		
//       // String file1 = reportLocation + "//Reports//report_" + currentDate + ".html"; //change to your attachment filepath
//		String file1 = Reporter.reportPath;
//        String fileName1 = "Report "+currentDate; //change to your attachment filename
//        DataSource source1 = new FileDataSource(file1);
//        messageBodyPart.setDataHandler(new DataHandler(source1));
//        messageBodyPart.setFileName(fileName1);
//        multipart.addBodyPart(messageBodyPart);
// 
//        message.setContent(multipart);
// 
//        System.out.println("Sending");
// 
//        Transport.send(message);
// 
//        System.out.println("Done");
// 
//    } catch (MessagingException e) {
//        e.printStackTrace();
//    }
//  }
//}