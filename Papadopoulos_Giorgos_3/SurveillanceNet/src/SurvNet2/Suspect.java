package SurvNet2;

import java.util.*;

public class Suspect 
{
	private String name = new String();
	private String codeName = new String();
	private String country = new String();
	private String city = new String();
	private ArrayList<String> phoneNums = new ArrayList<>();
	private ArrayList<Suspect> partnersList = new ArrayList<>();
	
	
	public Suspect( String name, String codeName, String country, String city )
	{
		this.name = name;
		this.codeName = codeName;
		this.country = country;
		this.city = city;
	}
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getCodeName() {
		return codeName;
	}



	public ArrayList<String> getPhoneNums() {
		return phoneNums;
	}

	public void setPhoneNums(ArrayList<String> phoneNums) {
		this.phoneNums = phoneNums;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}


	public ArrayList<Suspect> getPartnersList() {
		return partnersList;
	}

	public void setPartnersList(ArrayList<Suspect> partnersList) {
		this.partnersList = partnersList;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	
	
	
	public void addNumber(String number)
	{
		phoneNums.add(number);
	}
	
	public void Partners( String num1, String num2, Suspect s2 )
	{
		boolean flag = false;
		if ( phoneNums.contains(num1))
		{
			if ( s2.phoneNums.contains(num2))
			{
				flag = true;
			}
		}
		else if ( phoneNums.contains(num2))
		{
			if ( s2.phoneNums.contains(num1))
			{
				flag = true;
			}
		}
		if ( flag )
		{
			partnersList.add(s2);
			s2.partnersList.add(this);
		}
		Set<Suspect> set = new HashSet<>(partnersList);
		partnersList.clear();
		partnersList.addAll(set);

	}
	
	public boolean isConnectedTo(Suspect aSuspect) 
	{
		for ( Suspect s : partnersList )
		{
			if ( s.getName().equals(aSuspect.getName()))
			{
				return true;
			}
		}
		return false;
		
	}
	
	public ArrayList<Suspect> getCommonPartners(Suspect aSuspect) 
	{
		ArrayList<Suspect> returnList = new ArrayList<>();
		
		for ( Suspect s1part : partnersList )
		{
			for ( Suspect aSuspart : aSuspect.getPartnersList())
			{
				if ( s1part.getName().equals(aSuspart.getName()))
				{
					returnList.add(aSuspart);
				}
			}
		}
		Set<Suspect> set = new HashSet<>(returnList);
		returnList.clear();
		returnList.addAll(set);
		return returnList;
	}
	
	public ArrayList<Suspect> SuggestedPartners( Suspect sus2 )
	{
		ArrayList<Suspect> possibleSus = new ArrayList<>();
		if ( this.isConnectedTo(sus2)) 
		{
			ArrayList<Suspect> commonSus = this.getCommonPartners(sus2);
			for ( Suspect posus : commonSus )
			{
				for ( Suspect sus2part : sus2.partnersList )
				{
					if ( !(posus.name.equals(sus2part.name)))
					{
						if ( !(sus2part.name.equals(name)))
						{
							possibleSus.add(sus2part);
						}
					}
				}
			}
		}
		return possibleSus;
	}
}