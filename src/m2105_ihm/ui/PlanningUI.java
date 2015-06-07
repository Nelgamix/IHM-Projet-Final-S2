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
import m2105_ihm.nf.Contact;
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
	
	this.setLayout(new BorderLayout());
	this.add(ficheEvt, BorderLayout.CENTER);
    }
    /**
     * Ajoute une entrée dans la liste de événements
     * @param title texte affiché dans la liste pour un contact
     * @param Contact objet contact associé
     */
    public boolean ajouterEvt(Evenement evt) {
        if (evt == null) { return false; }
        
        ficheEvt.ajouterEvenementList(evt);
            
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
        return this.ficheEvt.getSelectedEvenement();
    }
    
    public Contact getSelectedPtc() {    
        return this.ficheEvt.getSelectedParticipant();
    }
    
    public void removeEvenement(Evenement evt) {
	this.ficheEvt.removeEvenement(evt);
    }
    
    public void removeContactEvenement(Contact c) {
	this.ficheEvt.removeContactEvenement(c);
    }
    
    public void setEvenementSelected() {
	this.controleur.setEvtSelected(true);
    }
}
