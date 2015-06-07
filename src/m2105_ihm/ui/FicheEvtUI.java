/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicListUI;
import m2105_ihm.nf.Contact;
import m2105_ihm.nf.Evenement;
import m2105_ihm.nf.Mois;
/**
 *
 * @author IUT2
 */
public class FicheEvtUI extends javax.swing.JPanel {
    
    /*
     * Attributs
     */
    private PlanningUI			planning;
    private JList			list;
    private DefaultListModel<Evenement> modelEvt = new DefaultListModel<>();
    
    private JTextField zOS;
    private JComboBox monsterKill;
    private JComboBox monsterCat;
    private JComboBox monsterDog;
    private JList participants;
    private DefaultListModel<Contact> modelPtc = new DefaultListModel<>();
    private JButton valider;
    private JButton annuler;
    
    
    /**
     * Creates new form CarnetUI
     */
    public FicheEvtUI(PlanningUI planning) {
        super();
        
        this.planning = planning;
        
        initUIComponents();
        initListeners();
    }

    /**
     * Initialise la gestion des événements
     */
    private void initListeners() {
        this.list.addListSelectionListener(new ListSelectionListener() {

	    @Override
	    public void valueChanged(ListSelectionEvent e) {
		setValues((Evenement)list.getSelectedValue());
		planning.setEvenementSelected();
	    }
	});
	
	this.valider.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		getValues((Evenement)list.getSelectedValue());
	    }
	});
	
	this.annuler.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		setValues((Evenement)list.getSelectedValue());
	    }
	});
    }
    
    /**
     * Crée et positionne les composants graphiques constituant l'interface
     */    
    private void initUIComponents() {
	////////////////
	this.setLayout(new GridBagLayout());
	
	String[] evt = {"Trolololol"};
	
	JPanel panelList = new JPanel(new BorderLayout());
	panelList.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                    "Evenements"),
                BorderFactory.createEmptyBorder(10,10,10,10)));
	
	list = new JList(evt);
	list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	list.setLayoutOrientation(JList.VERTICAL);
	list.setVisibleRowCount(-1);
	//list.setPreferredSize(new Dimension(50, 400));
	
	panelList.add(list, BorderLayout.CENTER);
	
	JPanel panelData = new JPanel(new BorderLayout());
	panelData.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                    "Infos"),
                BorderFactory.createEmptyBorder(10,10,10,10)));
	
	/////////////
        
        JPanel butttt = new JPanel(new BorderLayout());
        
        annuler = new JButton("Annulation");
        valider = new JButton("Validation");
        
        butttt.add(annuler, BorderLayout.WEST);
        butttt.add(valider, BorderLayout.EAST);
        
        panelData.add(butttt, BorderLayout.SOUTH);
        
        JPanel ios = new JPanel(new GridBagLayout());
	GridBagConstraints iosc = new GridBagConstraints();
	iosc.insets = new Insets(5, 0, 5, 0);
        
        JLabel textOS = new JLabel("Intitulé");
        
        zOS = new JTextField();
        
        JPanel dateOS = new JPanel(new GridLayout(1, 3, 5, 5));
        
        Integer[] items1 = new Integer[31] ;
        for (int i=0; i<31; i++) {
            items1[i] = i + 1;
        }
        
        Mois [] items2 = Mois.values();
        
        Integer[] items3 = new Integer[100];
        for (int i=0; i<100; i++) {
            items3[i] = i + 1970;
        }
        
        monsterKill = new JComboBox(items1);
        dateOS.add(monsterKill);
        
        monsterCat = new JComboBox(items2);
        dateOS.add(monsterCat);
        
        monsterDog = new JComboBox(items3);
        dateOS.add(monsterDog);
        
        panelData.add(ios, BorderLayout.NORTH);
        
	iosc.gridx = 0;
	iosc.gridy = 0;
	iosc.weightx = 0.5;
	iosc.anchor = GridBagConstraints.NORTH;
	iosc.fill = GridBagConstraints.HORIZONTAL;
        ios.add(textOS, iosc);
	
	iosc.gridx = 1;
	iosc.gridy = 0;
        ios.add(zOS, iosc);
	
        textOS = new JLabel("Date");
	iosc.gridx = 0;
	iosc.gridy = 1;
        ios.add(textOS, iosc);
	
	iosc.gridx = 1;
	iosc.gridy = 1;
        ios.add(dateOS, iosc);
	
	textOS = new JLabel("Participants");
	iosc.gridx = 0;
	iosc.gridy = 2;
	ios.add(textOS, iosc);
	
	participants = new JList();
	iosc.gridx = 1;
	iosc.gridy = 2;
	ios.add(participants, iosc);
	
	list.setModel(modelEvt);
	this.participants.setModel(modelPtc);
	
	///////
	
	GridBagConstraints gbc = new GridBagConstraints();
	
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
	gbc.weighty = 1;
	add(panelList, gbc);
	
	gbc.anchor = GridBagConstraints.FIRST_LINE_START;
	gbc.fill = GridBagConstraints.BOTH;
	gbc.gridx = 1;
        gbc.gridy = 0;
	gbc.weightx = 1;
	gbc.weighty = 1;
	
	add(panelData, gbc);
    }

    /**
     * Affecte des valeurs au formulaire pour un événement
     * @param Evenement un événement
     * @return
     */        
    public boolean setValues(Evenement event) {
        if (event == null) { return false; }            
                  
        this.zOS.setText(event.getIntitule());
	
	this.monsterKill.setSelectedIndex(event.getDateJour() - 1);
	this.monsterCat.setSelectedItem((Mois)event.getDateMois());
	this.monsterDog.setSelectedIndex(event.getDateAnnee() - 1970);
	
	Contact[] participants = event.getParticipants();
	modelPtc.clear();
	for (Contact c : participants) {
	    modelPtc.addElement(c);
	}
            
        return false;
    }

    /**
     * Retourne les valeurs associées au formulaire de fiche événement
     * @param Evenement événement à affecter
     * @return 
     */    
    public boolean getValues(Evenement event) {
        
        if (event == null) { return false; }
        
	event.setIntitule(zOS.getText());
        event.setDate(this.monsterKill.getSelectedIndex() + 1, (Mois)this.monsterCat.getSelectedItem(), this.monsterDog.getSelectedIndex() + 1970);
	
        return true;
    }
    
    public void ajouterEvenementList(Evenement e) {
	modelEvt.addElement(e);
    }
    
    public Evenement getSelectedEvenement() {
	return (Evenement)list.getSelectedValue();
    }
    
    public void removeEvenement(Evenement evt) {
	modelEvt.removeElement(evt);
    }
    
    public void removeContactEvenement(Contact c) {
	this.modelPtc.removeElement(c);
    }
    
    public Contact getSelectedParticipant() {
	return (Contact)participants.getSelectedValue();
    }
    
    
    
}
