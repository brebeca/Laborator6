package org.example;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton resetBtn = new JButton("Reset");
    JButton exitBtn = new JButton("Exit");

    public ControlPanel(MainFrame frame) {
        this.frame = frame; init();
    }

    /**
     * functia initializeaza un panel cu butoanele save, load etc
     * librarii :java.awt.event.ActionEvent-> pt functiile declansate de butoane
     *           java.awt.event.ActionListener-> pentru declansarea butoanelor
     *           javax.swing.JButton-> pentru creare butoanelor
     *           javax.swing.JPanel-> pentru crearea panel ului la butoane ;
     */
    private void init() {

        setLayout(new GridLayout(1, 4));
        JPanel buttonPanel= new JPanel();
        frame.add(buttonPanel,BorderLayout.NORTH);
        saveBtn.addActionListener(this::save);
        saveBtn.setBounds(10, 10, 70, 30);
        loadBtn.addActionListener(this::load);
        loadBtn.setBounds(100, 10, 70, 30);
        resetBtn.addActionListener(this::reset);
        resetBtn.setBounds(200, 10, 70, 30);
        exitBtn.addActionListener(this::exit);
        exitBtn.setBounds(300, 10, 70, 30);
        frame.add(saveBtn);
        frame.add(loadBtn);
        frame.add(resetBtn);
        frame.add(exitBtn);
        frame.setVisible(true);
        frame.setSize(600,100);
    }

    /**
     * VALBIL PT TOATE FUCNTIILE DE MAI JOS
     * toate functiile de mai jos sunt "actiunile" declansate de apasarea butoanelor
     * librarii: java.awt.event.ActionEvent-> pt "legtura" dintre buton si functionlaitatea sa
     *          java.io.File             -> pt lucru cu fisiere din functiile save si load
     *          java.io.IOException      -> pt exceptiile ce pot aparea in lucuru cu fisiere in functiile save, laod
     *          javax.imageio.ImageIO;   -> pt functiile save, load pt manipularea imaginilor
     * @param e
     */

    private void save(ActionEvent e) {
        try {
            ImageIO.write(frame.canvas.image, "PNG", new File("test.png"));
        } catch (IOException ex) { System.err.println(ex); }
    }
    
    /**
    *se apdateaza image din canvas unde se apeleasa si functia repaint() pt a afisa imaginea
    */
    private void load(ActionEvent e) {
        try {
            frame.canvas.setImage(ImageIO.read(new File(path)));
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    

    private void reset(ActionEvent e) {
            frame.canvas.frame.getContentPane().removeAll();
    }

    private void exit(ActionEvent e) {
        System.exit(0);
    }

}
