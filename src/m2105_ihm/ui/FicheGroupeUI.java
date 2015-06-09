/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import m2105_ihm.nf.*;
import m2105_ihm.nf.Contact;
import m2105_ihm.nf.GroupeContacts;
/**
 *
 * @author IUT2
 */
public class FicheGroupeUI extends javax.swing.JPanel {
    /*
     * Composants graphiques constituants l'interface
     */
    private CarnetUI     carnet;
    private ZoneDessinUI zoneDessin;
    private JTextField  champNom;
    private DefaultTableModel model;
    private JTable      tableContacts;
    private JList       champSymboles;
    private JButton     boutonEffacer, annuler, valider;
    /**
     * Creates new form CarnetUI
     */
    public FicheGroupeUI(CarnetUI carnet) { 
        super();
       
        this.carnet = carnet;
        
        initUIComponents();    
        initListeners();
    }

    /**
     * Crée et positionne les composants graphiques constituant l'interface
     */
    private void initUIComponents() {
        
        this.setLayout(new BorderLayout());
        
	
	// Déclarations
	JPanel general = new JPanel(new BorderLayout());
	JPanel boutons = new JPanel(new BorderLayout());
	JPanel donnees = new JPanel(new GridBagLayout());
	JPanel image = new JPanel(new BorderLayout());
	JPanel nomSym = new JPanel(new GridBagLayout());
	JPanel tableContact = new JPanel(new BorderLayout());
	GridBagConstraints gbc1 = new GridBagConstraints();
        GridBagConstraints gbc2 = new GridBagConstraints();
	
	
	// Image
	zoneDessin = new ZoneDessinUI();
        image.add(zoneDessin, BorderLayout.CENTER);

	boutonEffacer = new JButton("Effacer");
        image.add(boutonEffacer, BorderLayout.SOUTH);
	
	
	// Nom & Symboles
	gbc2.anchor = GridBagConstraints.FIRST_LINE_END;
	gbc2.fill = GridBagConstraints.NONE;
	gbc2.gridx = 0;
	gbc2.gridy = 0;
        nomSym.add(new JLabel("Nom du groupe :"), gbc2);
        champNom = new JTextField(15);
	
	gbc2.gridx = 0;
	gbc2.gridy = 1;
        nomSym.add(new JLabel("Symboles :"), gbc2);
	
	gbc2.fill = GridBagConstraints.HORIZONTAL;
	gbc2.insets = new Insets(0, 30, 10, 0);
	gbc2.gridx = 1;
	gbc2.gridy = 0;
        nomSym.add(champNom, gbc2);
        
        Symbole [] items2 = Symbole.values();
        champSymboles = new JList(items2);
	gbc2.fill = GridBagConstraints.BOTH;
	gbc2.gridx = 1;
	gbc2.gridy = 1;
        nomSym.add(champSymboles, gbc2);

	
	// Table contacts
        //tableContact.add(new JLabel("Membres du groupe :"));
        String [] colonnes = {"Nom", "Prenom"};
        model = new DefaultTableModel();
        tableContacts = new JTable(model);
        model.setColumnIdentifiers(colonnes);
        tableContact.add(tableContacts.getTableHeader(), BorderLayout.NORTH);
        tableContact.add(tableContacts, BorderLayout.CENTER);
        
	
	// Données
	gbc1.anchor = GridBagConstraints.CENTER;
	gbc1.fill = GridBagConstraints.NONE;
	gbc1.weightx = 0.5;
	gbc1.weighty = 0.2;
	gbc1.gridx = 0;
	gbc1.gridy = 0;
	donnees.add(image, gbc1);
	
	gbc1.anchor = GridBagConstraints.LINE_START;
	gbc1.fill = GridBagConstraints.BOTH;
	gbc1.gridx = 1;
	gbc1.gridy = 0;
	gbc1.weightx = 1;
	donnees.add(nomSym, gbc1);
	
	gbc1.weightx = 1;
	gbc1.weighty = 1;
	gbc1.gridx = 0;
	gbc1.gridy = 1;
	gbc1.gridwidth = 2;
	donnees.add(tableContact, gbc1);
	
	
	// Boutons
	annuler = new JButton("Annuler");
	valider = new JButton("Valider");
	boutons.add(annuler,BorderLayout.WEST);
	boutons.add(valider,BorderLayout.EAST);
	
	
	// Général
	general.add(donnees, BorderLayout.CENTER);
	general.add(boutons, BorderLayout.SOUTH);
	
	this.add(general);
    }

    /**
     * Affecte des valeurs au formulaire de fiche groupe de contacts
     * @param groupe groupe de contacts
     * @return
    */    
    public boolean setValues(GroupeContacts groupe) {
        
        if (groupe == null) { return false; }

        champNom.setText(groupe.getNom());     

        Contact [] membres = groupe.getContacts();    
        String [] ligne = new String[2];
        model.setRowCount(0);
	
        for (Contact m : membres) {
            ligne[0] = m.getNom();
            ligne[1] = m.getPrenom();
            model.addRow(ligne);
        }
        
        Symbole [] symboles = groupe.getSymboles();
        champSymboles.clearSelection();
        
        for (Symbole s : symboles) {
            champSymboles.addSelectionInterval(s.ordinal(), s.ordinal());
        }
        
        zoneDessin.effacer();
        zoneDessin.setPoints(groupe.getPoints());
        
        return true;
    }
    
    /**
     * Retourne les valeurs associées au formulaire de fiche groupe de contacts
     * @return
     */    
    public boolean getValues(GroupeContacts groupe) {
        if (groupe == null) { return false; }
        
        groupe.setNom(champNom.getText());
        groupe.setPoints(zoneDessin.getPoints());
	
	for (Symbole s : groupe.getSymboles()) {
	    groupe.removeSymbole(s);
	}
	
        for (Symbole s : Symbole.values()) {
	    if (champSymboles.isSelectedIndex(s.ordinal())) {
		System.out.println("Symbole " + s.toString() + " selected!");
		groupe.addSymbole(s);
	    }
        }
          
        /*
         * Ne pas s'occuper des membres du groupe car traité via des
         * commandes du menu qui appelera setValues
         */
        
        return true;
    }
    
    /**
     * Initialise la gestion des événements
     */
    public void initListeners() {        
        /*
         * Réagit aux évènements produits par le bouton effacer
         */     
        boutonEffacer.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                zoneDessin.effacer();
            };

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        
        annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                carnet.setGroupeModified(false);
                System.out.println("Annuler");
            }
        });
        
        valider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                carnet.setGroupeModified(true);
                System.out.println("Valider");
            }
        });
        
	zoneDessin.addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent e) {} 
            public void mouseExited(MouseEvent e) {} 
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                
                zoneDessin.addPoint(new Point(x, y));
                zoneDessin.repaint();
            }     
            public void mouseReleased(MouseEvent e) {}     
            public void mouseClicked(MouseEvent e) {}
        });
        
    }    
}
