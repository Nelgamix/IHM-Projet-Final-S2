/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import m2105_ihm.nf.Evenement;
/**
 *
 * @author IUT2
 */
public class FicheEvtUI extends javax.swing.JPanel {
    
    /*
     * Attributs
     */
    private PlanningUI          planning;
    private JList		list;
    
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
        
        /*ev = new Evenement();
        
        annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                framePrincipale.setVisible(false);
                System.out.println("Annuler");
            }
        });
        
        valider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                setValues(ev);
                System.out.println("Valider");
                framePrincipale.setVisible(false);
            }
        });*/
        
    }
    
    /**
     * Crée et positionne les composants graphiques constituant l'interface
     */    
    private void initUIComponents() {
	this.setLayout(new BorderLayout());
	
	String[] evt = {"Bijour", "Gaga"};
	
	list = new JList(evt);
	list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
	list.setVisibleRowCount(-1);
	JScrollPane listScroller = new JScrollPane(list);
	listScroller.setPreferredSize(new Dimension(250, 80));
	
	this.add(list, BorderLayout.EAST);
	
	this.add(new JLabel("Bijour"), BorderLayout.CENTER);
	
	
	
	
	
	
	
	
	
	
	
	
	
    }

    /**
     * Affecte des valeurs au formulaire pour un événement
     * @param Evenement un événement
     * @return
     */        
    public boolean setValues(Evenement event) {
        if (event == null) { return false; }            
                  
        /** Projet : à compléter **/
            
        return false;
    }

    /**
     * Retourne les valeurs associées au formulaire de fiche événement
     * @param Evenement événement à affecter
     * @return 
     */    
    public boolean getValues(Evenement event) {
        
        if (event == null) { return false; }
        
        /** Projet : à compléter **/
        
        return true;
    }
}
