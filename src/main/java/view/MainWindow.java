package view;

import view.dialog.DialogPanel;
import view.order.OrderPanel;
import view.orderList.OrderListPanel;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class MainWindow extends JFrame {
    private static final Logger LOGGER = Logger.getLogger(MainWindow.class.getName());
    private static MainWindow instance = null;

    private int mainWindowWidth = 1280;
    private int mainWindowHeight = 720;

    public OrderPanel orderPanel = new OrderPanel();
    public OrderListPanel orderListPanel = new OrderListPanel();
    public DialogPanel dialogPanel = new DialogPanel();


    private JSplitPane verticalSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, orderPanel, orderListPanel);
    private JSplitPane horizontalSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, verticalSplitPane, dialogPanel);

    public static MainWindow getInstance() {
        if (instance == null) {
            instance = new MainWindow();
        }
        return instance;
    }

    private MainWindow() {
        super("Kebab Speech");
        setMainWindowValues();
        setMainWindowLayout();


        getContentPane().add(horizontalSplitPane, BorderLayout.CENTER);
    }


    private void sql() {
//        Ingredient ingredient = new Ingredient();
//        ingredient.setName("Salata");
//        IngredientDao ingredientDao = new IngredientDao(ingredient);
//        ingredientDao.create(ingredient);
    }

    private void setMainWindowValues() {
        setSize(mainWindowWidth, mainWindowHeight);
        centerWindow();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void centerWindow() {
        setLocationRelativeTo(null);
    }

    private void setMainWindowLayout() {
        setLayout(new BorderLayout());
    }


}
