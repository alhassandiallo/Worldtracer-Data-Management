package com.litige.gui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

import com.litige.business.ClaimBusiness;
import com.litige.business.DeliveryBusiness;
import com.litige.business.DestinationBusiness;
import com.litige.business.FlightBusiness;
import com.litige.business.LuggageBusiness;
import com.litige.business.PilferageBusiness;
import com.litige.business.SendBagBusiness;
import com.litige.dao.GeneralDao;

public class MainWindow extends JFrame{

	/**
	 * Initialize the contents of the frame.
	 */
	private static final String WINDOW_TITLE = "Luggage Claim Management";
	
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenu menuClaim;
	private JMenu menuBag;
	private JMenu menuPilf;
	private JMenu menuFlight;
	private JMenu menuSendBag;
	private JMenu menuDestination;
	private JMenu menuDelivery;
	private JMenu menuHelp;
	
	private JMenuItem menuItemExit;

	private JMenuItem menuItemListClaims;
	private JMenuItem menuItemNewClaim;
	
	private JMenuItem menuItemListBag;
	private JMenuItem menuItemNewBag;
	
	private JMenuItem menuItemListPilf;
	private JMenuItem menuItemNewPilf;
	
	private JMenuItem menuItemListFlight;
	private JMenuItem menuItemNewFlight;
	
	private JMenuItem menuItemListSendBag;
	private JMenuItem menuItemNewSendBag;
	
	private JMenuItem menuItemListDest;
	private JMenuItem menuItemNewDestination;
	
	private JMenuItem menuItemNewDelivery;
	private JMenuItem menuItemListDelivery;
	
	private JMenuItem menuItemHelpContent;
	private JMenuItem menuItemHelpAbout;
	
	private JToolBar toolbar;
	private JButton buttonBag;
	private JButton buttonClaim;
	private JButton buttonPilf;
	private JButton buttonHelp;
	private JButton buttonAbout;
	
	private JPanel panel;
	
	private JTabbedPane tabbedPane;
	
	private GeneralDao dao;
	private LuggageBusiness bagBusiness;
	private DestinationBusiness destinationBusiness;
	private ClaimBusiness claimBusiness;
	private FlightBusiness flightBusiness;
	private SendBagBusiness sendBagBusiness;
	private PilferageBusiness pilferageBusiness;
	private DeliveryBusiness deliveryBusiness;
	
	private BagPanel bagPanel;
	private ClaimPanel claimPanel;
	private PilferagePanel pilfPanel;
	private FlightPanel flightPanel;
	private SendBagPanel sendBagPanel;
	private DestinationPanel destPanel;
	private DeliveryPanel deliveryPanel;
	
	public MainWindow() throws HeadlessException {
		super(WINDOW_TITLE);
		
		setVisible(true);
		setLayout(new GridBagLayout());
		
		createMenus();
		createToolbar();
		createPanel();
		registerEventHandlers();
		
		intializeBusiness();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000, 800);
		setLocationRelativeTo(null);
	}

	private void createMenus() {
		menuBar = new JMenuBar();
		
		menuFile = new JMenu("File");
		menuItemExit = new JMenuItem("Exit");
		menuFile.add(menuItemExit);
		
		menuClaim = new JMenu("Claim");
		menuItemNewClaim = new JMenuItem("New Claim");
		menuItemListClaims = new JMenuItem("List Claims");
		
		menuClaim.add(menuItemNewClaim);
		menuClaim.add(menuItemListClaims);
		
		menuBag = new JMenu("Luggage");
		menuItemNewBag = new JMenuItem("New Luggage");
		menuItemListBag = new JMenuItem("List Bagages");
		
		menuBag.add(menuItemNewBag);
		menuBag.add(menuItemListBag);
		
		menuPilf = new JMenu("Pilferarge");
		menuItemNewPilf = new JMenuItem("New Pilferage");
		menuItemListPilf = new JMenuItem("List Pilferages");
		
		menuPilf.add(menuItemNewPilf);
		menuPilf.add(menuItemListPilf);
		
		menuFlight = new JMenu("Flight");
		menuItemNewFlight = new JMenuItem("New Flight");
		menuItemListFlight = new JMenuItem("List Flights");
		
		menuFlight.add(menuItemNewFlight);
		menuFlight.add(menuItemListFlight);
		
		menuSendBag = new JMenu("Send Bag");
		menuItemNewSendBag = new JMenuItem("New Send Bag");
		menuItemListSendBag = new JMenuItem("List Bag Sent");
		
		menuSendBag.add(menuItemNewSendBag);
		menuSendBag.add(menuItemListSendBag);
		
		menuDestination = new JMenu("Destination");
		menuItemNewDestination = new JMenuItem("New Destination");
		menuItemListDest = new JMenuItem("List Destinations");
		
		menuDestination.add(menuItemNewDestination);
		menuDestination.add(menuItemListDest);
		
		menuDelivery = new JMenu("Delivery");
		menuItemNewDelivery = new JMenuItem("New Delivery");
		menuItemListDelivery = new JMenuItem("List Delivery");
		
		menuDelivery.add(menuItemNewDelivery);
		menuDelivery.add(menuItemListDelivery);
		
		menuHelp = new JMenu("Help");
		menuItemHelpContent = new JMenuItem("Help Content");
		menuItemHelpAbout = new JMenuItem("About");
		
		menuHelp.add(menuItemHelpContent);
		menuHelp.add(menuItemHelpAbout);		
		
		menuBar.add(menuFile);
		menuBar.add(menuClaim);
		menuBar.add(menuBag);
		menuBar.add(menuPilf);
		menuBar.add(menuFlight);
		menuBar.add(menuSendBag);
		menuBar.add(menuDestination);
		menuBar.add(menuDelivery);
		menuBar.add(menuHelp);
		
		setJMenuBar(menuBar);
	}

	private void createToolbar() {
		toolbar = new JToolBar();
		
		buttonBag = new JButton("List Bag");
		buttonBag.setIcon(new ImageIcon(getClass().getResource("/icons/listprod.png")));
		
		buttonClaim = new JButton("List Claim");
		buttonClaim.setIcon(new ImageIcon(getClass().getResource("/icons/listprod.png")));
		
		buttonPilf = new JButton("List Pilferage");
		buttonPilf.setIcon(new ImageIcon(getClass().getResource("/icons/listprod.png")));
		
		buttonHelp = new JButton("Help");
		buttonHelp.setIcon(new ImageIcon(getClass().getResource("/icons/help.png")));
		
		buttonAbout = new JButton("About");
		buttonAbout.setIcon(new ImageIcon(getClass().getResource("/icons/about.png")));
		
		toolbar.add(buttonBag);
		toolbar.addSeparator();
		toolbar.add(buttonClaim);
		toolbar.addSeparator();
		toolbar.add(buttonPilf);
		toolbar.addSeparator();
		toolbar.add(buttonHelp);
		toolbar.addSeparator();
		toolbar.add(buttonAbout);
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1.0;
		
		add(toolbar, constraints);
	}
	
	private void createPanel() {
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;		
		constraints.weighty = 1.0;
		
		tabbedPane = new JTabbedPane();
		
		claimPanel = new ClaimPanel();
		claimPanel.setMainWindow(this);
		
		tabbedPane.add("Claim", claimPanel);
		
		bagPanel = new BagPanel();
		bagPanel.setMainWindow(this);
		
		tabbedPane.add("Luggage", bagPanel);
		
		pilfPanel = new PilferagePanel();
		pilfPanel.setMainWindow(this);
		
		tabbedPane.add("Pilferage", pilfPanel);
		
		flightPanel = new FlightPanel();
		flightPanel.setMainWindow(this);
		
		tabbedPane.add("Flight", flightPanel);
		
		sendBagPanel = new SendBagPanel();
		sendBagPanel.setMainWindow(this);
		
		tabbedPane.add("Send Bag", sendBagPanel);
		
		destPanel = new DestinationPanel();
		destPanel.setMainWindow(this);
		
		tabbedPane.add("Destination", destPanel);
		
		deliveryPanel = new DeliveryPanel();
		deliveryPanel.setMainWindow(this);
		
		tabbedPane.add("Delivery", deliveryPanel);
		
		panel.add(tabbedPane, BorderLayout.CENTER);
		add(panel, constraints);
	}

	private void registerEventHandlers() {
		
		menuItemExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		});
		
		menuItemListClaims.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				claimPanel.listClaims();
			}
		});		
		
		menuItemNewClaim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				claimPanel.createNewClaim();
			}
		});			
		
		menuItemNewBag.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bagPanel.createNewBag();
			}
		});	
		
		menuItemListBag.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bagPanel.listBags();
			}
		});		
		
		menuItemListPilf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pilfPanel.listPilfs();
			}
		});		
		
		menuItemNewPilf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pilfPanel.createNewPilf();
			}
		});			
		
		menuItemNewFlight.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				flightPanel.createNewFlight();
			}
		});	
		
		menuItemListFlight.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				flightPanel.listFlights();
			}
		});
		
		menuItemListSendBag.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sendBagPanel.listSendBags();
			}
		});		
		
		menuItemNewSendBag.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sendBagPanel.createNewSendBag();
			}
		});			
		
		menuItemNewDestination.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				destPanel.createNewDest();
			}
		});	
		
		menuItemListDest.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				destPanel.listDest();
			}
		});
		
		menuItemNewDelivery.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deliveryPanel.createNewDelivery();
			}
		});	
		
		menuItemListDelivery.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deliveryPanel.listDeliveries();
			}
		});
		
		menuItemHelpContent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showHelp();
			}
		});		
		
		menuItemHelpAbout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showAbout();
			}
		});			
		
		buttonBag.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bagPanel.listBags();
			}
		});	
		
		buttonClaim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				claimPanel.listClaims();
			}
		});
		
		buttonPilf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pilfPanel.listPilfs();
			}
		});	
		
		buttonHelp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showHelp();
			}
		});			
		
		buttonAbout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showAbout();
			}
		});			
		
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				try {
					dao.close();					
				} catch (Exception ex) {
					String message = "Error when closing database connection.\n"
							+ "The program will terminate anyway.\n"
							+ "Error:\n"
							+ ex.getMessage();
					JOptionPane.showMessageDialog(MainWindow.this, 
							message, "Error", JOptionPane.ERROR_MESSAGE);			
				} finally {
					dispose();
					System.exit(0);
				}
			}
		});
	}

	protected void showAbout() {
		String aboutInfo = "Luggage Claim Management\n"
				+ "Version 1.0.0\n"
				+ "Copyright (C) 2018 by Diallo Alhassan Boner\n"
				+ "All rights reserved.";
		JOptionPane.showMessageDialog(this, aboutInfo, "About", JOptionPane.INFORMATION_MESSAGE);
	}

	protected void showHelp() {
		URI helpWebpage;
		try {
			helpWebpage = new URI("#");
			Desktop.getDesktop().browse(helpWebpage);
		} catch (URISyntaxException | IOException e) {
			JOptionPane.showMessageDialog(this, "Could not load Help content!");
		}
	}
	
	private void intializeBusiness() {
		dao = new GeneralDao();
		
		try {
			dao.connect();
			bagBusiness = new LuggageBusiness(dao);
			destinationBusiness = new DestinationBusiness(dao);
			claimBusiness = new ClaimBusiness(dao);
			flightBusiness = new FlightBusiness(dao);
			sendBagBusiness = new SendBagBusiness(dao);
			pilferageBusiness = new PilferageBusiness(dao);
			deliveryBusiness = new DeliveryBusiness(dao);
		} catch (Exception ex) {
			String message = "Could not connect to the database!";
			JOptionPane.showMessageDialog(this, 
					message, "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
		
	}
	

}
