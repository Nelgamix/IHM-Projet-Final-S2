/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
    private JTable      commejeveux;
    private JList       champSymboles;
    private JButton     bouton1,annuler,valider;
    private JPanel      infosgroupe,contactsgroupe,logogroupe,nomsymboles,boutons,editionlogo,boutonlogo;
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
        
        infosgroupe = new JPanel();
        infosgroupe.setLayout(new GridLayout(1,2));
        this.add(infosgroupe,BorderLayout.NORTH);
        
        contactsgroupe = new JPanel();
        contactsgroupe.setLayout(new BorderLayout());
        this.add(contactsgroupe,BorderLayout.CENTER);
        
        nomsymboles = new JPanel();
        nomsymboles.setLayout(new GridLayout(2,2));
        
        logogroupe = new JPanel();
        logogroupe.setLayout(new GridLayout(2,1));
        
        editionlogo = new JPanel();
        editionlogo.setLayout(new GridBagLayout());
        
        boutonlogo = new JPanel();
        boutonlogo.setLayout(new BorderLayout());
        
        infosgroupe.add(nomsymboles);
        logogroupe.add(editionlogo);
        infosgroupe.add(logogroupe);
        
        boutons = new JPanel();
        boutons.setLayout(new BorderLayout());
        this.add(boutons,BorderLayout.SOUTH);
        
        nomsymboles.add(new JLabel("Nom du groupe :"));
        champNom = new JTextField(15);
        nomsymboles.add(champNom);
        
        zoneDessin = new ZoneDessinUI();
        Point[] points = {new Point(10,10), new Point(50,50), new Point(100, 125), new Point(10,10)};
        zoneDessin.setPoints(points);
        editionlogo.add(zoneDessin);

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
        
        
        
        bouton1 = new JButton("Effacer");
        boutonlogo.add(bouton1,BorderLayout.NORTH);
        logogroupe.add(boutonlogo);

        contactsgroupe.add(new JLabel("Membres du groupe :"));
        String [] colonnes = {"Nom","Prenom"};
        
        model = new DefaultTableModel();
        commejeveux = new JTable(model);
        model.setColumnIdentifiers(colonnes);
        contactsgroupe.add(commejeveux.getTableHeader());
        contactsgroupe.add(commejeveux);
        
        nomsymboles.add(new JLabel("Symboles :"));
        Symbole [] items2 = Symbole.values();
        champSymboles = new JList(items2);
        nomsymboles.add(champSymboles);
        
        annuler = new JButton("Annuler");
        annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                carnet.setGroupeModified(false);
                System.out.println("Annuler");
            }
        });
        boutons.add(annuler,BorderLayout.WEST);
        
        valider = new JButton("Valider");
        valider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                carnet.setGroupeModified(true);
                System.out.println("Valider");
            }
        });
        boutons.add(valider,BorderLayout.EAST);
        
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
        for (Contact m: membres) {
            ligne[0] = m.getNom();
            ligne[1] = m.getPrenom();
            model.addRow(ligne);
        }
        
        Symbole [] symboles = groupe.getSymboles();
        champSymboles.clearSelection();
        
        for (Symbole s: symboles) {
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
        
        for (Symbole s: groupe.getSymboles()) {
           if (champSymboles.isSelectedIndex(s.ordinal())){
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
        bouton1.addMouseListener(new MouseListener() {
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
        
        
        
        
    }    
}
