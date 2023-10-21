
package SurvNet2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GUI extends JFrame
{
	private JPanel mpanel = new JPanel(); // main panel
	private JButton button;
	private JTextField field;
	private Registry reg;
	
	public GUI( Registry registry)
	{		
		super("Find Suspect");
		
		Container contentPane = this.getContentPane();
		
		field = new JTextField("Please enter suspect's name");
		button = new JButton("Find");
		
		contentPane.add(field);
		contentPane.add(button);
		contentPane.setLayout(new FlowLayout());
		
		this.setVisible(true);
		this.setSize(400,250);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		ButtonListener listener = new ButtonListener();
		button.addActionListener(listener);
		
		reg = registry;
	}
	
	class ButtonListener extends JFrame implements ActionListener
	{
			private Suspect aSuspect;
			
			public void actionPerformed(ActionEvent e)
			{
				ArrayList<Suspect> suspects = reg.getSuspectsList();
				String s = field.getText();
				
				JFrame erframe;
				boolean flag = true;
				JPanel panelerror = new JPanel();
				aSuspect = null;
				
				JFrame susframe;
				
				
				String command = e.getActionCommand();
				
				for ( Suspect sus : suspects)	
				{
					if ( s.equals(sus.getName()))
					{
						flag = false;
						aSuspect = sus;
						break;
					}	
				}
				
				if (command.equals("Find"))
				{
					if ( flag )
					{
						erframe = new JFrame("Message");
						JLabel context = new JLabel("Suspect " + s + " Not Found");
						JButton button = new JButton("OK");
						button.setSize(20, 20);
						
						panelerror.setLayout(new FlowLayout());
						panelerror.add(context);
						
						mpanel.setLayout(new FlowLayout());
						mpanel.add(button);
						
						erframe.setLayout(new BorderLayout(0,5));
						erframe.add(mpanel, BorderLayout.CENTER);
						erframe.add(panelerror, BorderLayout.NORTH);
						erframe.pack();
						erframe.setSize(400,250);
						erframe.setVisible(true);
						erframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
					}
					else
					{
						susframe = new JFrame("Suspect Page");
						Container mainContext = susframe.getContentPane();
						
						
						// panel 1
						JPanel panel1 = new JPanel();
						JTextField name = new JTextField(aSuspect.getName());
						JTextField codeName = new JTextField(aSuspect.getCodeName());
						JTextArea area = new JTextArea(5,20);
						for ( String num : aSuspect.getPhoneNums())
						{
							area.append(num);
							area.append("\n");
						}
						panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
						panel1.add(name);
						panel1.add(codeName);
						panel1.add(area);
						
						
						// panel 2
						JPanel panel2 = new JPanel();
						JTextField number = new JTextField("Enter a number");
						JTextArea smsArea = new JTextArea(6,20);
						JButton findsms = new JButton("Find SMS");
						
						findsms.addActionListener(new ActionListener(){
							
													public void actionPerformed( ActionEvent e)
													{
														String text = number.getText();
														
														String command = e.getActionCommand();
														
														if ( command.equals("Find SMS"))
														{											
															for ( Communication comm : reg.getComms())
															{
																if ( comm instanceof SMS )
																{
																	String comm1 = comm.getNumber1();
																	String comm2 = comm.getNumber2();
																	if ( comm1.equals(text) || comm2.equals(text))
																	{
																		String sms =((SMS)comm).getContext();
																		if (sms.contains("Bomb") || sms.contains("Attack")
																				|| sms.contains("Explosives") || sms.contains("Gun"))
																		{
																			smsArea.append(sms);
																			smsArea.append("\n");
																		}																																																								
																	}
																}
															}
														}
													}
												});
						
						
					panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
					panel2.add(number);
					panel2.add(smsArea);
					panel2.add(findsms);
										
					
					// Panel 3
					JPanel panel3 = new JPanel();
					JTextArea PartArea = new JTextArea(10,20);
					JLabel partners = new JLabel("Partners");
					
					for ( Suspect partner : aSuspect.getPartnersList())
					{
						PartArea.append(partner.getName());
						PartArea.append(", ");
						PartArea.append(partner.getCodeName());
						PartArea.append("\n");
					}
					
					panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
					panel3.add(partners);
					panel3.add(PartArea);
					
					
					// panel 4
					JPanel panel4 = new JPanel();
					JTextArea SugPartArea = new JTextArea(7, 20);
					JLabel Sugpartners = new JLabel("Suggested Partners ----->");
					ArrayList<Suspect> sugPartners = new ArrayList<>();
					
					for ( Suspect sus : reg.getSuspectsList())
					{
						if ( !(sus.getName().equals(aSuspect.getName())))
						{
							ArrayList<Suspect> temp = aSuspect.SuggestedPartners(sus);
							sugPartners.addAll(temp);						
						}
					}
					for ( Suspect partner : sugPartners )
					{
						SugPartArea.append(partner.getName());
						SugPartArea.append(", ");
						SugPartArea.append(partner.getCodeName());
						SugPartArea.append("\n");
					}
					
					panel4.setLayout(new FlowLayout( FlowLayout.CENTER, 10, 10));
					panel4.add(Sugpartners);
					panel4.add(SugPartArea);
					
					
					// panel 5
					JPanel panel5 = new JPanel();
					JTextArea susFromCountry = new JTextArea(5,20);
					susFromCountry.append("Suspects from Spain\n");
					ArrayList<String> str = reg.printSuspectsFromCountry("Spain");
					for ( String suscountry : str )
					{
						susFromCountry.append(suscountry);
						susFromCountry.append("\n");
					}
					panel5.add(susFromCountry);
					panel5.setLayout(new FlowLayout( FlowLayout.CENTER, 10, 10));
					
					Container medCont = new Container();
					JButton returnto = new JButton("Return to Search Screen");
					returnto.addActionListener(new ActionListener() {
													
													public void actionPerformed( ActionEvent e )
													{
														String buttonS = returnto.getText();
														String command = e.getActionCommand();
														
														if ( command.equals(buttonS))
														{
															new GUI( reg );
														}
													}
											});
					medCont.add(panel1);
					medCont.add(panel2);
					medCont.add(panel3);
					medCont.add(panel4);
					medCont.add(panel5);
					medCont.add(returnto);
					medCont.setLayout(new BoxLayout( medCont, BoxLayout.Y_AXIS));
					
					
					
					mainContext.add(medCont);
					mainContext.setLayout(new FlowLayout( FlowLayout.CENTER, 10, 10));
					
					susframe.pack();
					
					susframe.setVisible(true);
					susframe.setMaximumSize(getMaximumSize());
					susframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
				}
			}
		}
	}
	
}


