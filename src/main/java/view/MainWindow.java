package view;

import generated.Vxml;
import view.dialog.DialogPanel;
import view.order.OrderPanel;
import view.orderList.OrderListPanel;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.awt.*;
import java.io.*;
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

        loadXML();

        getContentPane().add(horizontalSplitPane, BorderLayout.CENTER);
    }

    private void loadXML() {
        try {
            JAXBContext jc = JAXBContext.newInstance(Vxml.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            File file = new File("src/main/java/dialog.xml");
            Vxml rootObject = (Vxml) unmarshaller.unmarshal(file);

            System.out.println("rootObject:");
            System.out.println(rootObject.toString());

            int i = 1;
            for (Vxml.Form form : rootObject.getForm()){
                System.out.println("Form: " + i);
                System.out.println(form.getField().getName());
                System.out.println(form.getField().getPrompt());
                System.out.println(form.getField().getFilled());
                System.out.println(form.getField().getGrammar());
                System.out.println(form.getField().getNoinput());
                i++;
            }

        } catch (JAXBException ex) {
            System.out.println("XML error");
            ex.printStackTrace();
        }


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
