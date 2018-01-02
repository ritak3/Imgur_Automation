package SOURCE_CODE.SFDC;

public class ExitUponTestScriptStepFails extends Exception{
	
	public ExitUponTestScriptStepFails(String problem)
	{
		super(problem);
	}
}
