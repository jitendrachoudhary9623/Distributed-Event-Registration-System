package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import Client.Client;
import Model.Event;
import Model.EventList;
import Utility.EventListGenerator;

public class Welcome extends JFrame {
	
	static int BUTTON_HEIGHT=45;
	static int BUTTON_WIDTH=300;
	  JPanel panel = new JPanel();
	   JButton quitButton = new JButton("Exit");
       JButton createButton = new JButton("Create");
       JButton updateButton = new JButton("Update");
	public Welcome() {
	       
	       setTitle("Eventos");
	       setSize(350, 600);
	       setLocationRelativeTo(null);
	       setDefaultCloseOperation(EXIT_ON_CLOSE);
	       init();
	}
	
	public void init(){
		getContentPane().add(panel);

	       panel.setLayout(null);

	       
	       createButton.setBounds(25, 60, BUTTON_WIDTH, BUTTON_HEIGHT);
	       createButton.setBackground(new Color(102, 255, 255));
	       updateButton.setBounds(25, 110, BUTTON_WIDTH, BUTTON_HEIGHT);
	       updateButton.setBackground(new Color(102, 255, 51));
	       quitButton.setBounds(25, 160, BUTTON_WIDTH, BUTTON_HEIGHT);
	       quitButton.setBackground(new Color(255, 102, 102));
	       quitButton.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent event) {
	               System.exit(0);
	          }
	       });
	       
	       JTextArea label=new JTextArea("\n\n  Welcome to Eventos - \n\n  A Token Based Event Registration\n  Program.\n\n\n  Note : Always save your token somewhere \n  without token you will not be able \n  to update your information");
	       	label.setBounds(25, 255, BUTTON_WIDTH, 300);
	       	label.setEditable(false);
	       	label.setBackground(new Color(255, 255,204));
	       	label.setBorder(new LineBorder(Color.black, 1));
	       panel.add(createButton);
	       panel.add(updateButton);
	       
	       panel.add(quitButton);
	      panel.add(label);
	       panel.setBackground(new Color(255, 255,  255));
	       createButton.addActionListener(new ActionListener() {
	    		public void connectForRegistration(int event,String name) throws UnknownHostException, ClassNotFoundException, IOException {
					System.out.println(name);
					String token=Client.registeration(name, event);
					
					   JDialog d = new JDialog(); 
					   
			            // create a label 
			            JTextField l = new JTextField("Here is your Token , Please don't forget this "+token); 
			  
			            d.add(l); 
			  
			            // setsize of dialog 
			            d.setSize(400, 200); 
			            d.setLocation(500, 500);
			            // set visibility of dialog 
			            d.setVisible(true); 
				}
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				createButton.setVisible(false);
				quitButton.setVisible(false);
				updateButton.setVisible(false);
				label.setVisible(false);
				JTextField name=new JTextField("                                                               ");
				JLabel nameLabel=new JLabel("Enter Your name");
				
				JPanel createPanel=new JPanel();
				createPanel.setBackground(new Color(255, 255,  255));
				nameLabel.setBounds(100,100,100,100);
				name.setBounds(50, 60, 100, 500);
				name.setSize(500, 100);
				
				List<JButton> buttons=new ArrayList<>(); 
				List<JLabel> labels=new ArrayList<>();
				List<EventList> eR=EventListGenerator.getEvents();

			
					JButton b1=new JButton(eR.get(0).getEventname()+"\t\nFees : "+eR.get(0).getFees());
					b1.setBounds(50,100,100,100);
					JButton b2=new JButton(eR.get(1).getEventname()+"\t\nFees : "+eR.get(1).getFees());
					b2.setBounds(50,100+100,100,100);
					JButton b3=new JButton(eR.get(2).getEventname()+"\t\nFees : "+eR.get(2).getFees());
					b3.setBounds(50,100+100+100,100,100);
					JButton b4=new JButton("<< Back");
					b4.setBounds(50,0,100,100);
					b4.setBackground(Color.RED);
					b4.setFont(new Font("Courier New", Font.BOLD, 12));
					b4.setForeground(Color.WHITE);
					b1.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							int event_no=1;
							String name1=name.getText().toString();	
							try {
								connectForRegistration(event_no,name1.trim());
							} catch (ClassNotFoundException | IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}

						
					});
					
					b2.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							int event_no=2;
							String name1=name.getText().toString();	
							try {
								connectForRegistration(event_no,name1.trim());
							} catch (ClassNotFoundException | IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
					});
					b3.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							int event_no=2;
							String name1=name.getText().toString();	
							try {
								connectForRegistration(event_no,name1.trim());
							} catch (ClassNotFoundException | IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
				
					b4.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							createButton.setVisible(true);
							quitButton.setVisible(true);
							updateButton.setVisible(true);
							createPanel.setVisible(false);
							label.setVisible(true);

						}
					});
				
					createPanel.setLayout(new FlowLayout());
				createPanel.add(nameLabel);
				createPanel.add(name);
				createPanel.add(b1);
				createPanel.add(b2);
				createPanel.add(b3);
				createPanel.add(b4);
			
				createPanel.setBounds(30, 30, 300, 100);
				createPanel.setSize(300, 550);
				panel.add(createPanel);
			}
		});
	
	       	updateButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					createButton.setVisible(false);
					quitButton.setVisible(false);
					updateButton.setVisible(false);
					label.setVisible(false);

					JTextField token1=new JTextField("                                                                      ");
					JLabel nameLabel=new JLabel("Enter Token ");
					JLabel validity=new JLabel("\n\nToken is - ");

					JPanel createPanel=new JPanel();
					createPanel.setBackground(new Color(255, 255,  255));
					JPanel tokenPanel=new JPanel();
					tokenPanel.setBackground(new Color(255, 255,  255));
					tokenPanel.add(nameLabel);
					tokenPanel.add(token1);
					JButton getEvent=new JButton("Get Event");
					tokenPanel.add(getEvent);
					
					getEvent.setBackground(Color.GREEN);
					getEvent.setFont(new Font("Courier New", Font.BOLD, 12));
					//getEvent.setForeground(Color.WHITE);
					JPanel EventPanel=new JPanel();
					JButton updateEventDetails=new JButton("Update");
					updateEventDetails.setBackground(Color.RED);
					updateEventDetails.setFont(new Font("Courier New", Font.BOLD, 12));
					updateEventDetails.setForeground(Color.WHITE);
					JLabel attendee=new JLabel("Name of Attendee ");
					JTextField attendee_name=new JTextField("Name of Attendee ");
					JLabel event_name=new JLabel("Event Name");
					JLabel event_name_actual=new JLabel("Event Name");
					JLabel event_fees=new JLabel("Event Fees");
					JLabel event_fees_actual=new JLabel("Event Fees");
					JLabel token_actual=new JLabel("InValid                      ");
					
					JButton b4=new JButton("<< BACK");
					tokenPanel.add(b4);
					b4.setBackground(Color.RED);
					b4.setFont(new Font("Courier New", Font.BOLD, 12));
					b4.setForeground(Color.WHITE);
					b4.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							createButton.setVisible(true);
							quitButton.setVisible(true);
							updateButton.setVisible(true);
							createPanel.setVisible(false);
							label.setVisible(true);

						}
					});
					
					EventPanel.add(validity);

					EventPanel.add(token_actual);
					EventPanel.add(attendee);
					EventPanel.add(attendee_name);
					EventPanel.add(event_name);
					EventPanel.add(event_name_actual);
					EventPanel.add(event_fees);
					EventPanel.add(event_fees_actual);
					EventPanel.add(updateEventDetails);
					EventPanel.setVisible(false);
					getEvent.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							validity.setVisible(true);
							EventPanel.setVisible(true);
							String token=token1.getText().toString().trim();
							try {
								Event e1=Client.checkEvent(token);
								if(e1==null) {
									token_actual.setText("   Invalid Token Enter Valid token");
									token_actual.setForeground(Color.RED);
									attendee.setVisible(false);
									attendee_name.setVisible(false);
									event_name.setVisible(false);
									event_name_actual.setVisible(false);
									event_fees.setVisible(false);
									event_fees_actual.setVisible(false);
									updateEventDetails.setVisible(false);
								}
								else {
									token_actual.setText("   Valid Token");
									token_actual.setForeground(Color.GREEN);

									attendee.setVisible(true);
									attendee_name.setVisible(true);
									event_name.setVisible(true);
									event_name_actual.setVisible(true);
									event_fees.setVisible(true);
									event_fees_actual.setVisible(true);
									
									attendee_name.setText(e1.getAttendee()+"                       ");
									event_name_actual.setText(e1.getEventname()+"\t\t registered on "+e1.getDate());;
									event_fees_actual.setText("You Paid a total amount of "+e1.getAmount());
									updateEventDetails.setVisible(true);
									
									updateEventDetails.addActionListener(new ActionListener() {
										
										@Override
										public void actionPerformed(ActionEvent e) {
											// TODO Auto-generated method stub
											String updated=attendee_name.getText().toString().trim();
											String tokens=token1.getText().toString().trim();
											e1.setAttendee(updated);
											try {
												String msg=Client.updateEvents(e1, tokens);
												 JDialog d = new JDialog(); 
												   
										            // create a label 
										            JTextField l = new JTextField(msg); 
										  
										            d.add(l); 
										  
										            // setsize of dialog 
										            d.setSize(400, 200); 
										            d.setLocation(500, 500);
										            // set visibility of dialog 
										            d.setVisible(true); 
											} catch (ClassNotFoundException | IOException e2) {
												// TODO Auto-generated catch block
												e2.printStackTrace();
											}
										}
									});
								}
							} catch (ClassNotFoundException | IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
					//getEvent.setBounds(100, 200, 100, 100);
					//validity.setBounds(400, 400, 100, 300);
					validity.setVisible(false);
					
					//nameLabel.setBounds(100,100,100,100);
					//token1.setBounds(50, 60, 100, 500);
				//	token1.setSize(500, 100);
					createPanel.setLayout(new GridLayout(4, 3,1,10));
					/*createPanel.add(nameLabel);
					createPanel.add(token1);*/
					createPanel.add(tokenPanel);
					//createPanel.add(getEvent);
					createPanel.add(EventPanel);

					createPanel.setBounds(30, 30, 300, 100);
					createPanel.setSize(300, 550);
					panel.add(createPanel);
				}
			});
	
	}
public static void main(String[] args) {
	Welcome w=new Welcome();
	w.setVisible(true);
}
}
