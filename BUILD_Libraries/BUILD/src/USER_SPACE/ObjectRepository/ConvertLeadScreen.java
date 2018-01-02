package USER_SPACE.ObjectRepository; 
import SOURCE_CODE.SFDC.*; 
import USER_SPACE.TestPrerequisite.DataSetup;

 
 
public class ConvertLeadScreen { 
static SFDCAutomationFW sfdc = DataSetup.sfdc; 
static String RList = ""; 
static String SecName = ""; 
 
 
 
// ************************ Functions for Fields ************************************** 
 
 public static MemberOfField RecordOwnerField() throws Exception{ 
	return sfdc.Field("Record Owner"); 
} 
public static MemberOfField SendEmailtotheOwnerField() throws Exception{ 
	return sfdc.Field("Send Email to the Owner"); 
} 
public static MemberOfField AccountNameField() throws Exception{ 
	return sfdc.Field("Account Name"); 
} 
public static MemberOfField OpportunityNameField() throws Exception{ 
	return sfdc.Field("Opportunity Name"); 
} 
public static MemberOfField ConvertedStatusField() throws Exception{ 
	return sfdc.Field("Converted Status"); 
} 
public static MemberOfField SubjectField() throws Exception{ 
	return sfdc.Field("Subject"); 
} 
public static MemberOfField StatusField() throws Exception{ 
	return sfdc.Field("Status"); 
} 
public static MemberOfField DueDateField() throws Exception{ 
	return sfdc.Field("Due Date"); 
} 
public static MemberOfField PriorityField() throws Exception{ 
	return sfdc.Field("Priority"); 
} 
public static MemberOfField CommentsField() throws Exception{ 
	return sfdc.Field("Comments"); 
} 
public static MemberOfField ReminderField() throws Exception{ 
	return sfdc.Field("Reminder"); 
} 

 
// ************************ Functions & Static Classes for Sections ***************************** // 
 
public static Fields_ConvertLead SEC_ConvertLead() throws Exception{ 
return new Fields_ConvertLead("Convert Lead");
}
public static class Fields_ConvertLead{
Fields_ConvertLead(String SN)
{
SecName = SN;
}
}
public static Fields_TaskInformation SEC_TaskInformation() throws Exception{ 
return new Fields_TaskInformation("Task Information");
}
public static class Fields_TaskInformation{
Fields_TaskInformation(String SN)
{
SecName = SN;
}
public static MemberOfSEC Subject() throws Exception 
{ 
return sfdc._SEC(SecName,"Subject");
}
public static MemberOfSEC Status() throws Exception 
{ 
return sfdc._SEC(SecName,"Status");
}
public static MemberOfSEC DueDate() throws Exception 
{ 
return sfdc._SEC(SecName,"Due Date");
}
public static MemberOfSEC Priority() throws Exception 
{ 
return sfdc._SEC(SecName,"Priority");
}
}
public static Fields_DescriptionInformation SEC_DescriptionInformation() throws Exception{ 
return new Fields_DescriptionInformation("Description Information");
}
public static class Fields_DescriptionInformation{
Fields_DescriptionInformation(String SN)
{
SecName = SN;
}
public static MemberOfSEC Comments() throws Exception 
{ 
return sfdc._SEC(SecName,"Comments");
}
}
public static Fields_Reminder SEC_Reminder() throws Exception{ 
return new Fields_Reminder("Reminder");
}
public static class Fields_Reminder{
Fields_Reminder(String SN)
{
SecName = SN;
}
public static MemberOfSEC Reminder() throws Exception 
{ 
return sfdc._SEC(SecName,"Reminder");
}
}

 
 
 // *************** Functions & Static Classes for Related List ******************** 
 

 
 
public static MemberOfButton ConvertButton() throws Exception{ 
return sfdc.Button("Convert"); 
} 
public static MemberOfButton CancelButton() throws Exception{ 
return sfdc.Button("Cancel"); 
} 
public static MemberOfButton GoButton() throws Exception{ 
return sfdc.Button("Go!"); 
} 
}
