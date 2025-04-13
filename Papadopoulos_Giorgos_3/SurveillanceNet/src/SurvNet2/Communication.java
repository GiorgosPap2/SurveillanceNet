package SurvNet2;

public class Communication 
{
	private String number1;
	private String number2;
	private int year = 0;
	private int month = 0;
	private int day = 0;
	
	public Communication( String num1, String num2, int y, int m, int d )
	{
		number1 = num1;
		number2 = num2;
		year = y;
		month = m;
		day = d;
	}
	
	
	public String getNumber1() {
		return number1;
	}


	public void setNumber1(String number1) {
		this.number1 = number1;
	}


	public String getNumber2() {
		return number2;
	}


	public void setNumber2(String number2) {
		this.number2 = number2;
	}
	
}
