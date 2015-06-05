/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.TreeSelectionModel;

import m2105_ihm.Controleur;
import m2105_ihm.nf.Evenement;

/**
 *
 * @author IUT2
 */
public class PlanningUI extends JPanel {
    /**
     * Creates new form CarnetUI
     */
    private Controleur       controleur;
    private FicheEvtUI       ficheEvt;
    private JList	     list;

    /** 
     * Constructeur : initialise les composants de l'IHM pour les événements
     * @param une instance du controleur
     */
    public PlanningUI(Controleur ctrl) {
        super();
        
        this.controleur = ctrl;
        
        initComponents();        
    }

    /**
     * Crée et place les composants graphiques de l'interface
     */
    private void initComponents() {
        /*
         * Fiche événement
         */        
        ficheEvt = new FicheEvtUI(this);
     
        this.setLayout(new GridBagLayout());
	
	String[] evt = {"Bijour", "Gaga", "Gogo"};
	
	JPanel listPanel = new JPanel();
	listPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                    "Evenements"),
                BorderFactory.createEmptyBorder(10,10,10,10)));
	
	list = new JList(evt);
	list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	list.setLayoutOrientation(JList.VERTICAL);
	list.setVisibleRowCount(-1);
	list.setPreferredSize(new Dimension(150, 400));
	JScrollPane listScroller = new JScrollPane(list);
	listScroller.setPreferredSize(new Dimension(800, 80));
	
	listPanel.add(list);
	
	GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
	this.add(listPanel, gbc);
	
	JPanel pan2 = new JPanel();
	pan2.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                    "Infos"),
                BorderFactory.createEmptyBorder(10,10,10,10)));
	pan2.add(new JLabel("QZFFQ"));
	
	gbc.fill = GridBagConstraints.BOTH;
	gbc.gridx = 1;
        gbc.gridy = 0;
	gbc.weightx = 0.8;
	gbc.weighty = 1;
	
	this.add(pan2, gbc);
    }
    
    /**
     * Ajoute une entrée dans la liste de événements
     * @param title texte affiché dans la liste pour un contact
     * @param Contact objet contact associé
     */
    public boolean ajouterEvt(Evenement evt) {
        if (evt == null) { return false; }
        
        /** Projet à completer **/
            
        return true;
    }
    
    /**
     * Retire une entrée dans l'arbre pour les contacts
     * @param Contact contact à retirer
     */    
    public boolean retirerEvt(Evenement evt) {
        if (evt == null) { return false; }
        
        /** Projet à completer **/
            
        return false;
    }
    
    /*
     * Retourne l'événement sélectionné
     */
    public Evenement getSelectedEvt() {    
        
        /** Projet à completer **/

        return null;
    }
}
