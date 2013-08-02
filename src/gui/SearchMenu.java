/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import io.MYSQLEngine;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import system.DataStructures;

/**
 *
 * @author jonathanrainer
 */
public class SearchMenu
{
    private DataStructures dataStructure;
    private Template template;
    private JFrame mainFrame;
    private JPanel contentPane;
    private JComboBox problemLocation1CB;
    private JComboBox problemLocation2CB;
    private JComboBox problemLocation3CB;
    private JComboBox problemLocation4CB;
    private JButton searchByLocation;
    private JTextField descriptionText;
    private JButton searchByDescription;
    private JComboBox allocationSearch;
    private JButton searchByAllocation;
    
    
    public SearchMenu(MYSQLEngine mysqlEngine)
    {
        template = new Template();
        mainFrame = template.giveTemplatedJFrame("NWNE Nexus - Search");
        contentPane = template.giveMenuTemplatedJPanel("NWNE Nexus - Search");
        mainFrame.add(contentPane);
        JLabel title = new JLabel(template.headingString("Select Search Criteria:", 2));
        GridBagConstraints titleConstraints = template.
                createGridBagConstraints(0, 1, GridBagConstraints.BOTH, 10, 10,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                5);
        contentPane.add(title, titleConstraints);
        problemLocation1CB = new JComboBox();
        GridBagConstraints problemLocation1Constraints = template.
                createGridBagConstraints(1, 2, GridBagConstraints.BOTH, 10, 10,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        contentPane.add(problemLocation1CB, problemLocation1Constraints);
        problemLocation2CB = new JComboBox();
        GridBagConstraints problemLocation2Constraints = template.
                createGridBagConstraints(2, 2, GridBagConstraints.BOTH, 10, 10,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        contentPane.add(problemLocation2CB, problemLocation2Constraints);
        problemLocation3CB = new JComboBox();
        GridBagConstraints problemLocation3Constraints = template.
                createGridBagConstraints(3, 2, GridBagConstraints.BOTH, 10, 10,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        
        contentPane.add(problemLocation3CB, problemLocation3Constraints);
        problemLocation4CB = new JComboBox();
        GridBagConstraints problemLocation4Constraints = template.
                createGridBagConstraints(4, 2, GridBagConstraints.BOTH, 10, 10,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        contentPane.add(problemLocation4CB, problemLocation4Constraints);
        searchByLocation = new JButton(template.headingString("Search By Location", 2));
        GridBagConstraints searchByLocationConstraints = template.
                createGridBagConstraints(1, 3, GridBagConstraints.BOTH, 10, 10,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        contentPane.add(searchByLocation, searchByLocationConstraints);
        descriptionText = new JTextField("Enter search criteria here:");
        GridBagConstraints descriptionTextConstraints = template.
                createGridBagConstraints(1, 5, GridBagConstraints.BOTH, 10, 10,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                4);
        contentPane.add(descriptionText, descriptionTextConstraints);
        searchByDescription = new JButton(template.headingString("Search Description", 2));
        GridBagConstraints searchByDescriptionConstraints = template.
                createGridBagConstraints(1, 6, GridBagConstraints.BOTH, 10, 10,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        contentPane.add(searchByDescription, searchByDescriptionConstraints);
        allocationSearch = new JComboBox();
        allocationSearch.addItem("Please Choose One");
        allocationSearch.addItem("Control Office");
        allocationSearch.addItem("Control Office (Admin)");
        allocationSearch.addItem("Day Stewards");
        allocationSearch.addItem("Night Stewards");
        allocationSearch.addItem("AV");
        allocationSearch.addItem("Finance");
        allocationSearch.addItem("Hospitality");
        allocationSearch.addItem("Information");
        allocationSearch.addItem("IT Support");
        allocationSearch.addItem("Logistics");
        allocationSearch.addItem("Production");
        allocationSearch.addItem("Site Crew");
        allocationSearch.addItem("WigWam");
        allocationSearch.addItem("Andy (Showground)");
        allocationSearch.addItem("Jackie (Cleaners)");
        allocationSearch.addItem("Other");
        GridBagConstraints allocationSearchConstraints = template.
                createGridBagConstraints(1, 7, GridBagConstraints.BOTH, 10, 10,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        contentPane.add(allocationSearch, allocationSearchConstraints);
        searchByAllocation = new JButton(template.headingString("Search By Allocation", 2));
        GridBagConstraints searchByAllocationConstraints = template.
                createGridBagConstraints(1, 8, GridBagConstraints.BOTH, 10, 10,
                new Insets(0, 0, 0, 0), GridBagConstraints.CENTER, 0.0, 0.0, 1,
                1);
        contentPane.add(searchByAllocation, searchByAllocationConstraints);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public JFrame getMainFrame()
    {
        return mainFrame;
    }

    public JPanel getContentPane()
    {
        return contentPane;
    }

    public DataStructures getDataStructure()
    {
        return dataStructure;
    }

    public JComboBox getProblemLocationCB1()
    {
        return problemLocation1CB;
    }

    public JComboBox getProblemLocationCB2()
    {
        return problemLocation2CB;
    }

    public JComboBox getProblemLocationCB3()
    {
        return problemLocation3CB;
    }

    public JComboBox getProblemLocationCB4()
    {
        return problemLocation4CB;
    }

    public JButton getSearchByLocation()
    {
        return searchByLocation;
    }

    public JButton getSearchByDescription()
    {
        return searchByDescription;
    }

    public JButton getSearchByAllocation()
    {
        return searchByAllocation;
    }

    public JTextField getDescriptionText()
    {
        return descriptionText;
    }

    public JComboBox getAllocationSearch()
    {
        return allocationSearch;
    }
    
    
    
}
