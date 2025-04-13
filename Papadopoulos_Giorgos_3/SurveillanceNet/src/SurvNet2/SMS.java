package SurvNet2;

public class SMS extends Communication
{
	private String number1 = new String();
	private String number2 = new String();
	private int year = 0;
	private int month = 0;
	private int day = 0;
	private String context = new String();
	
	public SMS( String num1, String num2, int y, int m, int d, String c)
	{
		super(num1, num2, y, m, d);
		context = c;
	}
	
	
	public String getContext() {
		return context;
	}


	public void setContext(String context) {
		this.context = context;
	}


	public void printInfo()
	{
		System.out.println("This SMS has the following info\r\n"
							+ "Between" + number1 + " --- " + number2 + "\r\n"
							+ "on " + year + "/" + month + "/" + day + "\r\n"
							+ "Text: " + context);
	}
}
