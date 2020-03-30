package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 800, H = 600;

    BufferedImage image;
    Graphics2D graphics;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame; createOffscreenImage(); init();
    }

    /**
     * uses direct drawing into an offscreen Image filled with white with the tools in the graphics
     * librarii: java.awt.image.BufferedImage -> pt creare si manipulare imaginii
     *           java.awt.*-> pt elemntul de tip Graphics2D
     *
     */
    public void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, W, H);
    }
    public void reset()
    {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, W, H);
    }


    /**
     * initializeaza un mouse pentru acest panel care sa astepte click ul utilozatorului
     * librarii: java.awt.event.MouseAdapter -> pt initalizarea mouseului care "asculta"
     *           java.awt.event.MouseEvent -> preluarea cordonatelor in care s a dat click pt a le transmite fnctiei care va desena :drawShape()
     */
    void init() {
        setPreferredSize(new Dimension(W, H)); //don’t use setSize. Why?
        setBorder(BorderFactory.createEtchedBorder()); //for fun
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawShape(e.getX(), e.getY()); repaint();
            } //Can’t use lambdas, JavaFX does a better job in these cases
        });
    }


    /**
     * functia genreaza niste valori random pt raza si culoarea formai ce va fi desenata
     * apoi cu nr de laturi setat de utilizator preluat din configPanel va apela constructorul clasei RegularPolygon pt a crea forma
     * forma este umpluta cu functia fill cu culoarea setata d efunctia strColor()
     * @param x cordonata y a centului poligonului ce va fi desenat
     * @param y cordonata y a centului poligonului ce va fi desenat
     *
     */
    private void drawShape(int x, int y) {
        Random rand = new Random();
        int radius = rand.nextInt(100);

        int sides =(int) frame.configPanel.sidesField.getValue();

        int r = rand.nextInt(128) + 128;
        int g = rand.nextInt(128) + 128;
        int b = rand.nextInt(128) + 128;
        Color color = Color.getHSBColor(r,g,b);
        graphics.setColor(color);

        if(String.valueOf(frame.configPanel.shape.getSelectedItem())=="Rectangle")
            graphics.fill(new RegularPolygon(x, y, radius, sides));
        else if(String.valueOf(frame.configPanel.shape.getSelectedItem())=="Circle")
            graphics.fill(new Ellipse(x, y, radius));
        else graphics.fill(new Arc2D.Double(x, y, radius, radius, 90, 180, Arc2D.OPEN));
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        repaint();
    }

    @Override
    public void update(Graphics g) { }


    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }

}
