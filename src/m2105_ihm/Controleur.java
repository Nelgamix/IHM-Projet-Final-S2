/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm;

import javax.swing.JFrame;
import m2105_ihm.nf.Contact;
import m2105_ihm.nf.Evenement;
import m2105_ihm.nf.GroupeContacts;
import m2105_ihm.nf.NoyauFonctionnel;
import m2105_ihm.ui.BoiteDialogUI;
import m2105_ihm.ui.CarnetUI;
import m2105_ihm.ui.FenetreUI;
import m2105_ihm.ui.FicheCreationEvt;
import m2105_ihm.ui.PlanningUI;

/**
 *
 * @author IUT2
 */
public class Controleur {
    
    /*
     * Noyau Fonctionnel
     */    
    NoyauFonctionnel nf;
            
    /*
     * Composants
     */
    private CarnetUI carnetUI;
    private FenetreUI fenetre;
    private PlanningUI planningUI;

    /**
     * Constructeur de la fenêtre principale
     */
    public Controleur() {
        initUI();
        initContent();
    }
    
    /**
     * Action créer un nouveau contact
     */
    public void creerContact() {
        
        Contact c = new Contact();
        nf.addContact(c);
        carnetUI.ajouterContact(c);
        
    }

    /**
     * Action supprimer contact
     */
    public void supprimerContact() {
        
        /** TP5 : à compléter **/
        Contact c = carnetUI.getSelectedContact();
        JFrame fen = new JFrame();
        boolean continuer = BoiteDialogUI.afficherConfirmation(fen, c);
        
        if (continuer) {
            nf.removeContact(c);
            carnetUI.retirerContact(c);
        } else {
            // Rien
        }
        
    }
    
    /**
     * Action créer un groupe de contacts
     */
    public void creerGroupe() {
        
        /** TP5 : à compléter **/
        GroupeContacts g = new GroupeContacts();
        nf.addGroupe(g);
        carnetUI.ajouterGroupe(g);
              
    }

    /**
     * Action supprimer un groupe de contacts
     */
    public void supprimerGroupe() {
        
        /** TP5 : à compléter **/
        GroupeContacts g = carnetUI.getSelectedGroupe();
        JFrame fen = new JFrame();
        boolean continuer = BoiteDialogUI.afficherConfirmation(fen, g);
        
        if (continuer) {
            nf.removeGroupe(g);
            carnetUI.retirerGroupe(g);
        } else {
            // Rien
        }
        
    }
    
    /**
     * Crée un nouvel événement
     */
    public void creerEvenement() {
              
        FicheCreationEvt evt = new FicheCreationEvt(planningUI);
	
	evt.setVisible(true);
    }

    /**
     * Supprime un événement existant
     */
    public void supprimerEvenement() {
       
       /** Projet **/
       
    }
    
    /**
     * Ajouter un participant à un événement
     */
    public void ajouterParticipantEvenement() {
    
       /** Projet **/
           
    }

    /**
     * Retire un participant d'un événement
     */
    public void retirerParticipantEvenement() {
    
       /** Projet **/
           
    }

    /**
     * Met à jour la base de données
     */
    public void enregistrer() {
        nf.updateDB();
    }    
        
    /**
     * Quitter l'application sans enregistrer les modifications
     */
    public void quitter() {
        System.exit(0);
    }

    /**
     * Création des composants constituant la fenêtre principale
     */
    private void initUI() {
        /* Onglets */
        carnetUI = new CarnetUI(this);
        planningUI = new PlanningUI(this);

        /* Fenêtre principale */
        fenetre = new FenetreUI(this);
        fenetre.addTab(carnetUI, "Carnet");     // onglet carnet
        fenetre.addTab(planningUI, "Planning"); // onglet planning
        fenetre.afficher();
    }
        
    /**
     * Alimente la liste avec la liste des contacts existants
     */
    private void initContent() {
        nf = new NoyauFonctionnel();
                
        for(Contact c : nf.getContacts()) {
            carnetUI.ajouterContact(c);
        }
        
        for(GroupeContacts g : nf.getGroupes()) {
            carnetUI.ajouterGroupe(g);
        }
        
        for(Evenement e : nf.getEvenements()) {
            planningUI.ajouterEvt(e);
        }
        
        carnetUI.show();
    }
    
    public void setContactSelected(boolean selected) {
        fenetre.setMenuContactSelected(selected);
        
    }
    
    public void setEvtSelected(boolean selected) {
        fenetre.setMenuEvenementSelected(selected);
    }
    
    public void ajouterContactGroupe() {
        JFrame fen = new JFrame();
        GroupeContacts g = BoiteDialogUI.afficherChoixMembreContact(fen, "Choisir groupe:", nf.getGroupes());
        g.addContact(carnetUI.getSelectedContact());
    }
    
    public void retirerContactGroupe() {
         JFrame fen = new JFrame();
        GroupeContacts g = BoiteDialogUI.afficherChoixMembreContact(fen, "Choisir groupe:", nf.getGroupes());
        g.removeContact(carnetUI.getSelectedContact());       
    }
}