package SurvNet2;

import java.util.*;

public class Registry
{
	private ArrayList<Suspect> suspectsList = new ArrayList<>();
	private ArrayList<Communication> comms = new ArrayList<>();
	
	
	public ArrayList<Suspect> getSuspectsList() {
		return suspectsList;
	}

	public void setSuspectsList(ArrayList<Suspect> suspectsList) {
		this.suspectsList = suspectsList;
	}

	public ArrayList<Communication> getComms() {
		return comms;
	}

	public void setComms(ArrayList<Communication> comms) {
		this.comms = comms;
	}
	
	
	
	public void  addSuspect(Suspect aSuspect) 
	{
		suspectsList.add(aSuspect);
	}
	
	
	public void addCommunication(Communication aCommunication) 
	{
		comms.add(aCommunication);
		String n1 = aCommunication.getNumber1();
		String n2 = aCommunication.getNumber2();
		
		for ( Suspect s1 : suspectsList )
		{
			for( Suspect s2 : suspectsList)
			{
				if ( !( s1.getName().equals(s2.getName()) ) )
				{
					s1.Partners(n1, n2, s2);
					s2.Partners(n1, n2, s1);
				}
			}
		}
	}

	
	public ArrayList<String> printSuspectsFromCountry(String country)
	{
		ArrayList<String> sfc = new ArrayList<>();
		
		for ( Suspect s : suspectsList )
		{
			if ( s.getCountry().equals(country))
			{
				sfc.add(s.getName() + "( " + s.getCodeName() + " )");
			}
		}
		return sfc;
	}
}