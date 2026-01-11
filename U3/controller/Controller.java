package controller;

//only imports what is strictly necessary from view-package
import view.CustomCakeFrame;
import view.MainFrame;
import view.ButtonType;

public class Controller {
    private MainFrame view;
    private CustomCakeFrame newCakeType;
    private ButtonType currentLeftMenu = ButtonType.NoChoice;
    private String [] cakeMenuString; // for test purposes only
    private String [] perUnitItemMenuString; // for test purposes only
    private String [] orderHistoryMenuString; // for test purposes only
    private String [] order1Simulation; // for test purposes only
    private String [] currentOrderArray; // for test purposes only
    private double costCurrentOrder = 0; // for test purposes only
    private int nbrOfOrders = 0; // for test purposes only

    private Cake[] cakes;

    public Controller() {
        view = new MainFrame(1000, 500, this);
        loadStringTestValues(); //for test purposes - remove when not needed more

        setToCakeMenu();
    }

    private void loadStringTestValues() {
        cakeMenuString = new String[0];
        perUnitItemMenuString = new String[0];
        orderHistoryMenuString = new String[0];
        order1Simulation = new String[0];

        currentOrderArray = new String[1];

        cakes = new Cake[3];
        cakes[0] = new Cake("Prinsesstårta", 8, new Filling[]{Filling.VANILJKRAM, Filling.GRADDE, Filling.HALLONSYLT});
        cakes[1] = new Cake("Chokladtårta", 12, new Filling[]{Filling.CHOKLADMOUSSE, Filling.CHOKLADGANACHE, Filling.GRADDE});
        cakes[2] = new Cake("Citronmaräng", 6, new Filling[]{Filling.CITRONKRAM, Filling.MARANG});
    }

        public void buttonPressed(ButtonType button){

        switch (button) {
            case Cake:
                setToCakeMenu();
                break;
            default:
                break;
        }
    }

    public void addItemToOrder(int selectionIndex) {

        if (selectionIndex != -1){
            switch (currentLeftMenu) {
                case Cake:
                    currentOrderArray[nbrOfOrders] = cakeMenuString[selectionIndex].toString();
                    break;
                case PerUnitItem:
                    currentOrderArray[nbrOfOrders] = perUnitItemMenuString[selectionIndex].toString();
                    break;
            }
            nbrOfOrders++; //for test purposes - need to be removed or changed when model for handling orders is implemented
            costCurrentOrder = costCurrentOrder + 100; //for test purposes - replace with calculation of cost when how orders are handled is implemented in model
            view.populateRightPanel(currentOrderArray); //update left panel with new item - this takes a shortcut in updating the entire information in the panel not just adds to the end
            view.setTextCostLabelRightPanel("Total cost of order: " + String.valueOf(costCurrentOrder)); //set the text to show cost of current order
        }

    }

    public void viewSelectedOrder(int selectionIndex){
        System.out.println("Index selection left panel: " + selectionIndex); //for test purposes  - remove when not needed

        if ((selectionIndex != -1) && currentLeftMenu==ButtonType.OrderHistory){
            costCurrentOrder = 100; //for test purposes - replace with calculation of cost when how orders are handled is implemented in model
            view.populateRightPanel(order1Simulation); //update left panel with order details - this takes a shortcut in updating the entire information in the panel not just adds to the end
            view.setTextCostLabelRightPanel("Total cost of order: " + String.valueOf(costCurrentOrder)); //set the text to show cost of current order
        }
    }


    public void setToCakeMenu() {
        currentLeftMenu = ButtonType.Cake;
        cakeMenuString = createCakeMenuStrings();

        view.populateLeftPanel(cakeMenuString);
        view.populateRightPanel(currentOrderArray); //update left panel with new item - this takes a shortcut in updating the entire information in the panel not just adds to the end
        view.setTextCostLabelRightPanel("Total cost of order: " + String.valueOf(costCurrentOrder)); //set the text to show cost of current order
        view.enableAllButtons();
        view.disablePerUnitItemMenuButton();
        view.disableMakeCakeButton();
        view.disableAddMenuButton();
        view.disableOrderHistoryMenuButton();
        view.disableOrderButton();
        view.disableViewSelectedOrderButton();
    }

    private String[] createCakeMenuStrings() {
        if (cakes == null) {
            return new String[0];
        }

        String[] menu = new String[cakes.length];
        for (int i = 0; i < cakes.length; i++) {
            menu[i] = cakes[i].toMenuString();
        }
        return menu;
    }


    public void setToPerUnitItemMenu() {
        currentLeftMenu = ButtonType.PerUnitItem;
        view.populateLeftPanel(perUnitItemMenuString);
        view.populateRightPanel(currentOrderArray); //update left panel with new item - this takes a shortcut in updating the entire information in the panel not just adds to the end
        view.setTextCostLabelRightPanel("Total cost of order: " + String.valueOf(costCurrentOrder)); //set the text to show cost of current order
        view.enableAllButtons();
        view.disablePerUnitItemMenuButton();
        view.disableViewSelectedOrderButton();
    }

    public void setToOrderHistoryMenu() {
        currentLeftMenu = ButtonType.OrderHistory;
        view.clearRightPanel();
        view.populateLeftPanel(orderHistoryMenuString);
        view.enableAllButtons();
        view.disableAddMenuButton();
        view.disableOrderButton();
    }


    public void addNewCake() {
        newCakeType = new CustomCakeFrame(this);
        //For grade VG: Add more code to save the new cake type and update menu.
        view.enableAllButtons();
    }

    public void placeOrder() {
        System.out.println("Pressed Order to create a new order"); //for test purposes - remove when not needed more
        currentOrderArray = new String[10];  // for test purposes - remove when not needed more
        costCurrentOrder = 0;
        view.clearRightPanel(); //Removes information from right panel in GUI
        view.setTextCostLabelRightPanel("TOTAL COST: 0");
        view.enableAllButtons();
        view.disableAddMenuButton();
        view.disableViewSelectedOrderButton();
    }


}
