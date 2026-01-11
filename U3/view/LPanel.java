package view;

import javax.swing.*;

public class LPanel extends JPanel {
    private JList<Object> leftPanelList;
    private JButton btnShowPerUnitItem;
    private JButton btnShowCake;
    private JButton btnShowOrderHistory;
    private JButton btnCreateNewCakeType;
    private JButton btnAddSelectionToOrder;
    private JLabel titleLeftPanel;

    private int width;
    private int height;

    private MainFrame mainFrame;

    public LPanel(int width, int height, MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setLayout(null);
        this.width = width;
        this.height = height;
        this.setSize(width, height);
        setLocation(0, 0);
        setUp();
    }

    private void setUp() {
        titleLeftPanel = new JLabel("Make menu choice with buttons");
        titleLeftPanel.setLocation(20, 0);
        titleLeftPanel.setSize(width/2, 20);
        this.add(titleLeftPanel);

        leftPanelList = new JList<>();
        leftPanelList.setLocation(0, 23);
        leftPanelList.setSize(width, height - 100);
        this.add(leftPanelList);

        btnShowCake = new JButton("Cake");
        btnShowCake.setEnabled(true);
        btnShowCake.setSize(width / 5, 30);
        btnShowCake.setLocation(0, height - 75);
        btnShowCake.addActionListener(l -> mainFrame.buttonPressed(ButtonType.Cake));
        this.add(btnShowCake);

        btnShowPerUnitItem = new JButton("PerUnitItems");
        btnShowPerUnitItem.setEnabled(true);
        btnShowPerUnitItem.setSize(width / 5, 30);
        btnShowPerUnitItem.setLocation(width / 5, height - 75);
        btnShowPerUnitItem.addActionListener(l -> mainFrame.buttonPressed(ButtonType.PerUnitItem));
        this.add(btnShowPerUnitItem);

        btnCreateNewCakeType = new JButton("Make Cake");
        btnCreateNewCakeType.setEnabled(true);
        btnCreateNewCakeType.setSize(width / 5, 30);
        btnCreateNewCakeType.setLocation((width / 5)*2, height - 75);
        btnCreateNewCakeType.addActionListener(l -> {
            mainFrame.buttonPressed(ButtonType.MakeCake); //for grade VG: what happens if this button is pressed multiple times?
        });
        this.add(btnCreateNewCakeType);

        btnAddSelectionToOrder = new JButton("Add");
        btnAddSelectionToOrder.setEnabled(true);
        btnAddSelectionToOrder.setSize(width / 5, 30);
        btnAddSelectionToOrder.addActionListener(l -> mainFrame.buttonPressed(ButtonType.Add));
        btnAddSelectionToOrder.setLocation((width / 5)*3, height - 75);
        this.add(btnAddSelectionToOrder);

        btnShowOrderHistory = new JButton("Order history");
        btnShowOrderHistory.setEnabled(true);
        btnShowOrderHistory.setSize(width / 5, 30);
        btnShowOrderHistory.setLocation((width/5)*4, height - 75);
        btnShowOrderHistory.addActionListener(l -> mainFrame.buttonPressed(ButtonType.OrderHistory));
        this.add(btnShowOrderHistory);
    }

    protected JList<Object> getLeftPanelList() {
        return leftPanelList;
    }

    protected JButton getShowOrderHistory() {
        return btnShowOrderHistory;
    }

    protected JButton getBtnShowPerUnitItem() {
        return btnShowPerUnitItem;
    }

    protected JButton getBtnShowCake() { return btnShowCake; }

    protected JButton getBtnAddSelectionToOrder() { return btnAddSelectionToOrder; }

    protected JButton getBtnCreateNewCakeType() { return btnCreateNewCakeType; }


    /**
     * This method sets the information in the panel's list view.
     *
     * @param informationArray An array of String where each element will be shown as
     *                        one line in the panel.
     */
    public void populateList(String[] informationArray){
        leftPanelList.setListData(informationArray);
    }

}
