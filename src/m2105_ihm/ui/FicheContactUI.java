/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import m2105_ihm.nf.*;  
import m2105_ihm.ui.CarnetUI;
/**
 *
 * @author IUT2
 */
public class FicheContactUI extends JPanel {

    private CarnetUI         carnet;
    //private Contact cA;
    
    private JTextField  champNom;
    private JTextField  champPrenom;
    private JComboBox   champDisponibilite;
    private JTextField  champEmail;
    private JTextField  champTel;
    private JComboBox   champRegion;
    private JComboBox    champDateJour;
    private JComboBox    champDateAnnee;
    private JComboBox   champDateMois;
    private JButton     bouton1,bouton2;
    private JCheckBox    champHobbyC;
    private JCheckBox    champHobbyS;
    private JCheckBox    champHobbyM;
    private JCheckBox    champHobbyL;
    private JPanel      identite;
    private JPanel      dispohobby;
    private JPanel      boutons;
    private JPanel      dates;
    private JPanel      hobbylist;

    /**
     * Formulaire pour saisir les informations relatives à un contact
     */
    public FicheContactUI(CarnetUI carnet) {
        super();
        
        this.carnet      = carnet;
              
        initUIComponents();
        initListeners();
    }
    

    private void initUIComponents() {      
        
        this.setLayout(new BorderLayout());
	
	JPanel global = new JPanel(new GridLayout(2, 1, 20, 20));
	this.add(global, BorderLayout.CENTER);
        
        identite = new JPanel();
        identite.setLayout(new GridLayout(6,2));
	identite.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                    "Identité du contact"),
                BorderFactory.createEmptyBorder(10,10,10,10)));
        global.add(identite);
        
        dispohobby = new JPanel();
        dispohobby.setLayout(new GridLayout(2, 2));
	dispohobby.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                    "Ses disponibilités et hobbies"),
                BorderFactory.createEmptyBorder(10,10,10,10)));
        global.add(dispohobby);
        
        boutons = new JPanel();
        boutons.setLayout(new BorderLayout());
        this.add(boutons, BorderLayout.SOUTH);
        
        dates = new JPanel();
        dates.setLayout(new GridLayout(1,6));
       
        hobbylist = new JPanel();
        hobbylist.setLayout(new GridLayout(2,2));

        
        identite.add(new JLabel("Nom :"));
        champNom = new JTextField(30);
        identite.add(champNom);
        
        identite.add(new JLabel("Prenom :"));
        champPrenom = new JTextField(30);
        identite.add(champPrenom);
        
        identite.add(new JLabel("Email :")); 
        champEmail = new JTextField(50);
        identite.add(champEmail);
        
        identite.add(new JLabel("Téléphone :"));
        champTel = new JTextField(15);
        identite.add(champTel);
        
        identite.add(new JLabel("Region :"));
        Region [] items3 = Region.values();               
        champRegion = new JComboBox(items3);
        identite.add(champRegion);

        identite.add(new JLabel("Date de naissance :"));
        identite.add(dates);
         
        dispohobby.add(new JLabel("Disponibilité préférée :"));
        DispoSortie [] items = DispoSortie.values();               
        champDisponibilite = new JComboBox(items);
        dispohobby.add(champDisponibilite);
        
        dispohobby.add(new JLabel("Hobbies :"));
        champHobbyS = new JCheckBox(Hobby.SPORT.name());
        hobbylist.add(champHobbyS);
        champHobbyM = new JCheckBox(Hobby.MUSIQUE.name());
        hobbylist.add(champHobbyM);
        champHobbyL = new JCheckBox(Hobby.LECTURE.name());
        hobbylist.add(champHobbyL);
        champHobbyC = new JCheckBox(Hobby.CINEMA.name());
        hobbylist.add(champHobbyC);
        
        dispohobby.add(hobbylist);
       
        dates.add(new JLabel("Jour :"));
        Integer[] items5 = new Integer[31] ;
        for (int i=0; i<31; i++) {
            items5[i]=i+1;
        }
        champDateJour = new JComboBox(items5);
        dates.add(champDateJour);

        dates.add(new JLabel("Mois :"));
        Mois [] items4 = Mois.values();
        champDateMois = new JComboBox(items4);
        dates.add(champDateMois);
        
        dates.add(new JLabel("Annee :"));
        Integer[] items6 = new Integer[100] ;
        for (int i=0; i<100; i++) {
            items6[i]=i+1916;
        }
        champDateAnnee = new JComboBox(items6);
        dates.add(champDateAnnee);
        
        bouton1 = new JButton("Annuler");
        bouton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                carnet.setContactModified(false);
                System.out.println("Annuler");
            }
        });
        boutons.add(bouton1,BorderLayout.WEST);
        
        bouton2 = new JButton("Valider");
        bouton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                carnet.setContactModified(true);
                System.out.println("Valider");
            }
        });
        
        boutons.add(bouton2,BorderLayout.EAST);
        
    }
    
    public boolean setValues(Contact contact) {
        if (contact == null) { return false; }

        champNom.setText(contact.getNom());        
        champPrenom.setText(contact.getPrenom());
        champDisponibilite.setSelectedItem(contact.getDisponibilite());
        champEmail.setText(contact.getEmail());

        /*for (Hobby h : contact.getHobbies()){
            if (h==Hobby.CINEMA){
                champHobbyC.setSelected(true);
            } else if (h==Hobby.LECTURE){
                champHobbyL.setSelected(true);
            } else if (h == Hobby.SPORT){
                champHobbyS.setSelected(true);
            } else if (h==Hobby.MUSIQUE){
                champHobbyM.setSelected(true);
            } 
        }*/
        
        champHobbyC.setSelected(false);
        champHobbyL.setSelected(false);
        champHobbyM.setSelected(false);
        champHobbyS.setSelected(false);
      
        Hobby[] hooobby = contact.getHobbies();
        for (Hobby h : hooobby) {
            if (h == Hobby.CINEMA) {
                champHobbyC.setSelected(true);
            } else if (h == Hobby.LECTURE) {
                champHobbyL.setSelected(true);
            } else if (h == Hobby.MUSIQUE) {
                champHobbyM.setSelected(true);
            } else if (h == Hobby.SPORT) {
                champHobbyS.setSelected(true);
            }
        }
      
        champTel.setText(contact.getNumeroTelephone());
        champRegion.setSelectedItem(contact.getRegion());
        champDateJour.setSelectedItem(contact.getDateNaissanceJour());
        champDateMois.setSelectedItem(contact.getDateNaissanceMois());
        champDateAnnee.setSelectedItem(contact.getDateNaissanceAnnee());
        
        //cA = contact;
        
        return true;

    }
    
    public boolean getValues(Contact contact) {
        if (contact == null) { return false; }
        
        contact.setNom(champNom.getText());        
        contact.setPrenom(champPrenom.getText());
        contact.setDisponibilite((DispoSortie) champDisponibilite.getSelectedItem());
        contact.setEmail(champEmail.getText());

        /*for (Hobby h : contact.getHobbies()){ 
            contact.removeHobby(h);
            if (champHobbyC.isSelected()){
                contact.addHobby(Hobby.CINEMA);
            } else if (champHobbyL.isSelected()){
                contact.addHobby(Hobby.LECTURE);
            } else if (champHobbyS.isSelected()){
                contact.addHobby(Hobby.SPORT);
            } else if (champHobbyM.isSelected()){
                contact.addHobby(Hobby.MUSIQUE);
            } 
        }*/
        
        for (Hobby h : contact.getHobbies()) {
            contact.removeHobby(h);
        }
        
        if (champHobbyS.isSelected()) {
            contact.addHobby(Hobby.SPORT);
        }
        if (champHobbyL.isSelected()) {
            contact.addHobby(Hobby.LECTURE);
        }
        if (champHobbyC.isSelected()) {
            contact.addHobby(Hobby.CINEMA);
        }
        if (champHobbyM.isSelected()) {
            contact.addHobby(Hobby.MUSIQUE);
        }

        contact.setNumeroTelephone(champTel.getText());
        contact.setRegion((Region) champRegion.getSelectedItem());
        contact.setDateNaissance((Integer)champDateJour.getSelectedItem(), (Mois)champDateMois.getSelectedItem(), (Integer)champDateAnnee.getSelectedItem());
        
        return true;
    }
    
    /**
     * Initialise la gestion des événements
     */
    private void initListeners() {
        /** TP 5 : à compléter **/ 
    }    
}