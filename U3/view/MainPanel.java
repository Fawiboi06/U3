package view;

import javax.swing.*;

public class MainPanel extends JPanel {
    private LPanel lPanel;
    private RPanel rPanel;


    public MainPanel(int width, int height, MainFrame mainFrame) {
        super(null);
        this.setSize(width, height);

        lPanel = new LPanel(width / 2 +60, height, mainFrame);
        add(lPanel);

        rPanel = new RPanel(width / 2 +70, height, mainFrame);
        add(rPanel);
    }

    protected LPanel getLeftPanel() {
        return lPanel;
    }

    protected RPanel getRightPanel() {
        return rPanel;
    }

}
