/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author jonathanrainer
 */
public class Template {
    
    ClassLoader cldr = this.getClass().getClassLoader();
    
    java.net.URL nwlogo   = cldr.getResource("gui/nwlogo.png");
    ImageIcon newWineLogo = new ImageIcon(nwlogo);
    
    public Template()
    {
        
    }
    
    public JFrame giveTemplatedJFrame(String title)
    {
        JFrame templatedFrame = new JFrame(title);
        GridBagLayout layout = new GridBagLayout();
        templatedFrame.setLayout(layout);
        templatedFrame.setSize(600,650);
        JLabel logo = new JLabel(newWineLogo);
        GridBagConstraints logoConstraints = createGridBagConstraints(
                0, 0, GridBagConstraints.BOTH, 0, 0, new Insets(0,0,0,0), 
                GridBagConstraints.CENTER, 0.0, 0.0, 1, 1);
        templatedFrame.add(logo, logoConstraints);
        templatedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return templatedFrame;
    }
    
    public GridBagConstraints createGridBagConstraints(int gridx, int gridy, 
            int fill, int ipadx, int ipady, Insets insets, int anchor,
            double weightx, double weighty, int gridheight, int gridwidth)
    {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.fill = fill;
        constraints.ipadx = ipadx;
        constraints.ipady = ipady;
        constraints.insets = insets;
        constraints.anchor = anchor;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        constraints.gridheight = gridheight;
        constraints.gridwidth = gridwidth;
        return constraints;
    }
    
}
