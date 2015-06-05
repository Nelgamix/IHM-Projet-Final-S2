/*
 * Module 2105 : module IHM : Carnet d'adresse
 */
package m2105_ihm.ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author IUT2
 */
/**
 * 
 * @class ZoneDessinUI
 * Zone d'édition d'un logo
 */
public class ZoneDessinUI extends Canvas  {

    /*
     * Liste des points définissant le logo
     */
    private Polygon polygon;
    int count = 0;
    
    public ZoneDessinUI() {
        super();
                
        polygon = new Polygon();
        
        /*
         * Abonne le canvas aux évènements souris
         */
        
        /** TP 2 : à compléter **/
    }
    
    /**
     * Dessine le contenu du canvas, c'est-à-dire l'icone
     * @param g un contexte graphique
     */
    @Override
    public void paint(Graphics g) {
        Dimension dim = getSize();

        /** TP 2 : à modifier et compléter **/
        this.setBackground(Color.white);
        
        g.setColor(Color.blue);
        g.drawRect(0, 0, dim.width-1, dim.height-1);
        /*
         * Dessine une diagonale en fonction de la taille du canvas
         */
        count %= 6;
        switch(count) {
            case 1:
                g.setColor(Color.red);
                break;
            case 2:
                g.setColor(Color.blue);
                break;
            case 3:
                g.setColor(Color.yellow);
                break;
            case 4:
                g.setColor(Color.magenta);
                break;
            case 5:
                g.setColor(Color.lightGray);
                break;
            default:
                g.setColor(Color.gray);
                break;
        }
        
        g.drawPolygon(polygon);
        
        g.fillPolygon(polygon);
    }

    /**
     * Efface le dessin
     */
    public void effacer() {
        
        /** TP 2 : à compléter **/
        polygon.reset();
        this.repaint();
        
    }
        
    /**
     * Affecte le logo avec un ensemble de points
     * @param points tableau de points
     */
    public void setPoints(Point [] dessin) {
        
        /** TP 2 : à compléter **/
        count = 0;
        
        for (Point p : dessin) {
            polygon.addPoint(p.x, p.y);
            count++;
            System.out.println("Count2:" + count);
        }
        this.repaint();
    }

    /**
     * Retourne les points définissant l'icône
     * @return tableau d'entiers
     */
    public Point [] getPoints() {
        Point [] res;
        
        res = new Point[polygon.npoints];
        for(int i = 0; i < res.length; i++) {
            res[i] = new Point(polygon.xpoints[i], polygon.ypoints[i]);
        }
        
        return res;
    }
    
    public void addPoint(Point p) {
        this.polygon.addPoint(p.x, p.y);
        count++;
        System.out.println("Count: " + count);
    }
    
    /*
     * Taille fixe
     */
    @Override
    public Dimension getSize() {
        return new Dimension(150, 150);        
    }          
    @Override
    public Dimension getMinimumSize() {
        return new Dimension(150, 150);        
    }          
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(150, 150);        
    }          
}