/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import system.Ticket;

/**
 *
 * @author jonathanrainer
 */
public class ResultsBox
{
    private JFrame mainFrame;
    private JPanel resultsPane;
    private JList resultsArea;
    private JPanel buttonsPane;
    private JButton submitButton;
    private JButton exitButton;
    private Template template;
    
    public ResultsBox(Ticket[] results, String type)
    {
        template = new Template();
        
        mainFrame = new JFrame("Results of Query - Find" + type + " Tickets");
        mainFrame.setSize(new Dimension(250, 450));
        mainFrame.setLayout(new GridLayout(3,1));
        
        resultsPane = new JPanel();
        buttonsPane = new JPanel();
        
        resultsArea = new JList();
        submitButton = new JButton(template.headingString("Mark Selected as Duplicates", 3));
        exitButton = new JButton(template.headingString("Exit", 3));
        
        resultsPane.add(resultsArea);
        buttonsPane.add(submitButton);
        buttonsPane.add(exitButton);
        
        mainFrame.add(new JLabel(template.headingString("Results", 2), JLabel.CENTER));
        mainFrame.add(resultsPane);
        mainFrame.add(buttonsPane);
        mainFrame.pack();
        mainFrame.setVisible(true);
        switch(type)
        {
            case "Duplicate":
                resultsArea.setListData(results);
                resultsArea.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                break;
            case "Unprinted":
                break;
        }
        
        
        
    }
}
