package SurvNet2;

public class PhoneCall extends Communication
{
	private String number1 = new String();
	private String number2 = new String();
	private int year = 0;
	private int month = 0;
	private int day = 0;
	private int secs = 0;
	
	public PhoneCall( String num1, String num2, int y, int m, int d, int s)
	{
		super(num1, num2, y, m, d);
		secs =s;
	}
	
	public void printInfo()
	{
		System.out.println("This phone call has the following info\n"
							+ "Between" + number1 + " --- " + number2 + "\n"
							+ "on " + day + "/" + month + "/" + year + "\n"
							+ "Duration: " + secs);
	}

	public int getSecs() {
		return secs;
	}

	public void setSecs(int secs) {
		this.secs = secs;
	}
}
