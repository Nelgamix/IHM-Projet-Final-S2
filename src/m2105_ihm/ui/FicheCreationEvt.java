/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package m2105_ihm.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import m2105_ihm.nf.Evenement;
import m2105_ihm.nf.Mois;

/**
 *
 * @author renouft
 */
public class FicheCreationEvt extends JFrame {

    private Evenement ev;
    private JTextField zOS;
    private JComboBox monsterKill;
    private JComboBox monsterCat;
    private JComboBox monsterDog;
    private JComboBox monsterLicorne;
    private JComboBox monsterTiger;
    private JButton valider;
    private JButton annuler;
    protected PlanningUI planning;
    
    public FicheCreationEvt(PlanningUI pl) {
        this.planning = pl;
        
        this.setSize(500, 200);
        this.setAlwaysOnTop(true);
        
        JPanel io = new JPanel(new BorderLayout());
        
        JPanel butttt = new JPanel();
        
        annuler = new JButton("Annulation");
        
        valider = new JButton("Validation");
        
        butttt.add(annuler);
        butttt.add(valider);
        
        io.add(butttt, BorderLayout.SOUTH);
        
        JPanel ios = new JPanel(new GridLayout(3, 2, 10, 10));
        
        JLabel textOS = new JLabel("Intitulé");
        
        zOS = new JTextField();
        
        JPanel dateOS = new JPanel(new GridLayout(1, 3, 5, 5));
        
        Integer[] items1 = new Integer[31] ;
        for (int i=0; i<31; i++) {
            items1[i]=i+1;
        }
        
        Mois [] items2 = Mois.values();
        
        Integer[] items3 = new Integer[100] ;
        for (int i=0; i<100; i++) {
            items3[i]=i+2015;
        }
        
        monsterKill = new JComboBox(items1);
        dateOS.add(monsterKill);
        
        monsterCat = new JComboBox(items2);
        dateOS.add(monsterCat);
        
        monsterDog = new JComboBox(items3);
        dateOS.add(monsterDog);
        
        JPanel heureOS = new JPanel(new GridLayout(1, 2, 5, 5));
        
        Integer[] items4 = new Integer[24] ;
        for (int i=0; i<24; i++) {
            items4[i]=i;
        }
   
        Integer[] items5 = new Integer[60] ;
        for (int i=0; i<60; i++) {
            items5[i]=i;
        }
        
        monsterLicorne = new JComboBox(items4);
        heureOS.add(monsterLicorne);
        
        monsterTiger = new JComboBox(items5);
        heureOS.add(monsterTiger);      
        
        io.add(ios, BorderLayout.CENTER);
        
        ios.add(textOS);
        ios.add(zOS);
        textOS = new JLabel("Date");
        ios.add(textOS);
        ios.add(dateOS);
        textOS = new JLabel("Heure");
        ios.add(textOS);
        ios.add(heureOS);
        
        this.add(io);
        
        initListener();
    }
    
    public void initListener() {
        ev = new Evenement();
        
        annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                quitter();
                System.out.println("Annuler");
            }
        });
        
        valider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                getValues();
                planning.ajouterEvt(ev);
                quitter();
                System.out.println("Valider");
            }
        });
    }
    
    public void getValues() {
        String intitule = zOS.getText();
        int jour = monsterKill.getSelectedIndex();
        Mois mois = (Mois) monsterCat.getSelectedItem();
        int annee = monsterDog.getSelectedIndex() + 2015;
        int heure = monsterLicorne.getSelectedIndex();
        int minute = monsterTiger.getSelectedIndex();
        
        ev.setDate(jour, mois, annee);
        ev.setIntitule(intitule);
        ev.setDateHeure(heure, minute);
        
        System.out.println("Evenement crée: " + ev.getIntitule() + " :: " + ev.getDateAnnee());
    }
    
    public void quitter() {
        this.dispose();
    }
    
    
}
