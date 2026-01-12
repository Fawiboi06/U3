package controller;

//only imports what is strictly necessary from view-package
import Model.Cake;
import Model.Filling;
import Model.MenuItem;
import Model.Order;
import Model.Pastry;
import view.CustomCakeFrame;
import view.MainFrame;
import view.ButtonType;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Controller for the bakery application.
 * Handles user actions from the GUI and coordinates between view and model.
 *
 * @author Rei
 */
public class Controller {
    private MainFrame view;
    private CustomCakeFrame newCakeType;

    private ButtonType currentLeftMenu = ButtonType.NoChoice;

    private Cake[] cakes;
    private Pastry[] pastries;

    private Order currentOrder;
    private Order[] orderHistory;
    private int orderHistoryCount;

    public Controller() {
        view = new MainFrame(1000, 500, this);
        initModelData();

        view.enableAllButtons();
        view.disableAddMenuButton();
        view.disableViewSelectedOrderButton();
    }

    /**
     * Creates the initial menu (cakes + pastries) and prepares order storage.
     *
     * @author Rei
     */
    private void initModelData() {
        // Initial cakes (G-krav U3I10)
        cakes = new Cake[3];

        cakes[0] = new Cake(
                "Prinsesstarta",
                8,
                25,
                Arrays.asList(Filling.VANILLA, Filling.RASPBERRY, Filling.BLUEBERRY)
        );

        cakes[1] = new Cake(
                "Chokladtarta",
                12,
                30,
                Arrays.asList(Filling.CHOCOLATE, Filling.OREO)
        );

        cakes[2] = new Cake(
                "Frukttarta",
                6,
                20,
                Arrays.asList(Filling.RASPBERRY, Filling.VANILLA, Filling.BLUEBERRY)
        );

        // Initial per-unit items (G-krav U3FG10/U3I14)
        pastries = new Pastry[3];
        pastries[0] = new Pastry("Kanelbulle", 25);
        pastries[1] = new Pastry("Dammsugare", 18);
        pastries[2] = new Pastry("Chokladboll", 15);

        // Orders
        currentOrder = new Order();
        orderHistory = new Order[10];
        orderHistoryCount = 0;

        // Initial GUI state
        updateRightPanelForCurrentOrder();
    }

    /**
     * Receives button events from the GUI and triggers actions.
     *
     * @param button which button was pressed
     * @author Rei
     */
    public void buttonPressed(ButtonType button) {

        switch (button) {
            case Add:
                addItemToOrder(view.getSelectionLeftPanel());
                break;

            case Cake:
                setToCakeMenu();
                break;

            case PerUnitItem:
                setToPerUnitItemMenu();
                break;


            case OrderHistory:
                setToOrderHistoryMenu();
                break;

            case Order:
                placeOrder();
                break;

            case ViewOrder:
                viewSelectedOrder(view.getSelectionLeftPanel());
                break;
        }
    }

    /**
     * Adds selected menu item from left panel into current order.
     *
     * @param selectionIndex selected row index in left panel
     * @author Rei
     */
    public void addItemToOrder(int selectionIndex) {
        if (selectionIndex == -1) {
            return;
        }

        MenuItem selected = getSelectedMenuItem(selectionIndex);
        if (selected == null) {
            return;
        }

        currentOrder.addItem(selected);
        updateRightPanelForCurrentOrder();
    }

    /**
     * Views an order from order history by selection index.
     *
     * @param selectionIndex selected row index in left panel
     * @author Rei
     */
    public void viewSelectedOrder(int selectionIndex) {

        if (selectionIndex == -1 || currentLeftMenu != ButtonType.OrderHistory) {
            return;
        }

        if (selectionIndex < 0 || selectionIndex >= orderHistoryCount) {
            return;
        }

        Order selectedOrder = orderHistory[selectionIndex];
        if (selectedOrder == null) {
            return;
        }

        view.populateRightPanel(toStringArray(selectedOrder.getItems()));
        view.setTextTitleLabelRightPanel("ORDER " + selectedOrder.getId());
        view.setTextCostLabelRightPanel("Total cost of order: " + selectedOrder.getTotalPrice() + " kr");
    }

    /**
     * Shows cake menu in left list.
     *
     * @author Rei
     */
    public void setToCakeMenu() {
        currentLeftMenu = ButtonType.Cake;

        view.populateLeftPanel(createCakeMenuStrings());
        updateRightPanelForCurrentOrder();

        view.enableAllButtons();
        view.disableCakeMenuButton();
        view.disableViewSelectedOrderButton();
    }

    /**
     * Creates strings for cake menu list.
     *
     * @return array of menu strings
     * @author Rei
     */
    private String[] createCakeMenuStrings() {
        if (cakes == null) {
            return new String[0];
        }

        String[] menu = new String[cakes.length];
        for (int i = 0; i < cakes.length; i++) {
            menu[i] = cakes[i].toString(); // includes fillings + price
        }
        return menu;
    }

    /**
     * Shows per-unit item menu in left list.
     *
     * @author Rei
     */
    public void setToPerUnitItemMenu() {
        currentLeftMenu = ButtonType.PerUnitItem;

        view.populateLeftPanel(createPastryMenuStrings());
        updateRightPanelForCurrentOrder();

        view.enableAllButtons();
        view.disablePerUnitItemMenuButton();
        view.disableViewSelectedOrderButton();
    }

    /**
     * Creates strings for per-unit menu list.
     *
     * @return array of menu strings
     * @author Rei
     */
    private String[] createPastryMenuStrings() {
        if (pastries == null) {
            return new String[0];
        }

        String[] menu = new String[pastries.length];
        for (int i = 0; i < pastries.length; i++) {
            menu[i] = pastries[i].toString();
        }
        return menu;
    }

    /**
     * Shows order history in left list.
     *
     * @author Rei
     */
    public void setToOrderHistoryMenu() {
        currentLeftMenu = ButtonType.OrderHistory;

        view.populateLeftPanel(createOrderHistoryStrings());

        view.clearRightPanel();
        view.setTextTitleLabelRightPanel("ORDER DETAILS");
        view.setTextCostLabelRightPanel("");

        view.enableAllButtons();
        view.disableAddMenuButton();
        view.disableOrderButton();

        // ViewOrder button should only be useful here, so keep it enabled:
        // (there is only disable method, so we don't disable it here)
    }

    /**
     * Creates strings for order history list.
     *
     * @return array of order history strings
     * @author Rei
     */
    private String[] createOrderHistoryStrings() {
        String[] result = new String[orderHistoryCount];
        for (int i = 0; i < orderHistoryCount; i++) {
            result[i] = orderHistory[i].toString(); // "Order X - Total: Y kr"
        }
        return result;
    }

    /**
     * Places the current order, saves it to history, and starts a new order.
     *
     * @author Rei
     */
    public void placeOrder() {
        if (currentOrder.getItems().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Order must contain at least one item.");
            return;
        }

        addOrderToHistory(currentOrder);

        JOptionPane.showMessageDialog(view,
                "Order placed!\n" + currentOrder.toString());

        currentOrder = new Order();
        updateRightPanelForCurrentOrder();

        // stay in same menu, but update left panel if user is in history
        if (currentLeftMenu == ButtonType.OrderHistory) {
            view.populateLeftPanel(createOrderHistoryStrings());
        }
    }

    /**
     * Adds an order to history array, expanding if needed.
     *
     * @param order order to save
     * @author Rei
     */
    private void addOrderToHistory(Order order) {
        if (orderHistoryCount >= orderHistory.length) {
            Order[] newArr = new Order[orderHistory.length + 10];
            for (int i = 0; i < orderHistory.length; i++) {
                newArr[i] = orderHistory[i];
            }
            orderHistory = newArr;
        }

        orderHistory[orderHistoryCount] = order;
        orderHistoryCount++;
    }

    /**
     * Updates the right panel to show current order items + total.
     *
     * @author Rei
     */
    private void updateRightPanelForCurrentOrder() {
        view.populateRightPanel(toStringArray(currentOrder.getItems()));
        view.setTextTitleLabelRightPanel("CURRENT ORDER");
        view.setTextCostLabelRightPanel("Total cost: " + currentOrder.getTotalPrice() + " kr");
    }

    /**
     * Returns the selected MenuItem depending on current menu (cake/per-unit).
     *
     * @param selectionIndex index from the left list
     * @return selected MenuItem or null
     * @author Rei
     */
    private MenuItem getSelectedMenuItem(int selectionIndex) {
        if (currentLeftMenu == ButtonType.Cake) {
            if (selectionIndex >= 0 && selectionIndex < cakes.length) {
                return cakes[selectionIndex];
            }
        } else if (currentLeftMenu == ButtonType.PerUnitItem) {
            if (selectionIndex >= 0 && selectionIndex < pastries.length) {
                return pastries[selectionIndex];
            }
        }
        return null;
    }

    /**
     * Converts a list of MenuItems to a String array using their toString().
     *
     * @param items list of MenuItems
     * @return String array for GUI lists
     * @author Rei
     */
    private String[] toStringArray(List<MenuItem> items) {
        String[] result = new String[items.size()];
        for (int i = 0; i < items.size(); i++) {
            result[i] = items.get(i).toString();
        }
        return result;
    }
}

    /**
     * Creates a new cake type and adds it to the cake menu (VG requirement).
     * Uses JOptionPane only (simple level), not advanced GUI logic.
     *
     * @author Rei
     */
