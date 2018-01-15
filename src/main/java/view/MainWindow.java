package view;

import generated.Vxml;
import model.dao.DishTemplateDao;
import model.dao.IngredientDao;
import model.dao.IngredientTemplateDao;
import model.dao.InvoiceDao;
import model.entity.*;
import view.dialog.DialogPanel;
import view.dialog.FormPanel;
import view.order.OrderPanel;
import view.orderList.OrderListPanel;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;
import java.util.List;

public class MainWindow extends JFrame {
    private static final Logger LOGGER = Logger.getLogger(MainWindow.class.getName());
    private static MainWindow instance = null;
    private DishTemplateDao dishTemplateDao = new DishTemplateDao();
    private InvoiceDao invoiceDao = new InvoiceDao();

    private int mainWindowWidth = 1280;
    private int mainWindowHeight = 850;

    public OrderPanel orderPanel = new OrderPanel();
    public OrderListPanel orderListPanel = new OrderListPanel(orderPanel);
    public DialogPanel dialogPanel = new DialogPanel();



    JScrollPane dialogPanelScrollPane = new JScrollPane(dialogPanel);



    private JSplitPane verticalSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, orderPanel, orderListPanel);
    private JSplitPane horizontalSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, verticalSplitPane, dialogPanelScrollPane);


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
        dialogPanelScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        getContentPane().add(horizontalSplitPane, BorderLayout.CENTER);

        initDatabaseTemplates();
        orderListPanel.refreshTable();
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

    private void initDatabaseTemplates(){
        DishTemplate d2 = addDishTemplate("Herbata", 2.5f);
        DishTemplate d3 = addDishTemplate("Kebab", 8.5f);
        DishTemplate d4 = addDishTemplate("Kola", 2.5f);
        DishTemplate d5 = addDishTemplate("pepsi", 2.5f);
        DishTemplate d6 = addDishTemplate("Fanta", 2.5f);
        IngredientTemplate i1 = addIngredientTemplate("Baranina", 2f, d3);
        IngredientTemplate i2 = addIngredientTemplate("Kurczak", 2f, d3);
        IngredientTemplate i3 = addIngredientTemplate("Mieszane", 2f, d3);
        IngredientTemplate i4 = addIngredientTemplate("Tortilla", 2f, d3);
        IngredientTemplate i5 = addIngredientTemplate("Bułka", 2f, d3);
        IngredientTemplate i6 = addIngredientTemplate("łagodny", 2f, d3);
        IngredientTemplate i7 = addIngredientTemplate("ostry", 2f, d3);
        IngredientTemplate i8 = addIngredientTemplate("Mieszany", 2f, d3);
        dishTemplateDao.create(d2);
        dishTemplateDao.create(d3);
        dishTemplateDao.create(d4);
        dishTemplateDao.create(d5);
        dishTemplateDao.create(d6);


        //generate mock invoices
        Invoice invoice1 = new Invoice(new Date().getTime());
        Dish dish1 = new Dish(d2, invoice1);//herbata
        Dish dish2 = new Dish(d4, invoice1);//kola
        Dish dish3 = new Dish(d3, invoice1);//kebab
        invoice1.getDishes().add(dish1);
        invoice1.getDishes().add(dish2);
        invoice1.getDishes().add(dish3);
        invoice1.countAndSetPrice();
        invoiceDao.create(invoice1);

        //generate mock invoices
        Invoice invoice2 = new Invoice(new Date().getTime());
        Dish dish4 = new Dish(d2, invoice2);//herbata
        invoice2.getDishes().add(dish4);
        invoice2.countAndSetPrice();
        invoiceDao.create(invoice2);
    }

    private IngredientTemplate addIngredientTemplate(String name, float price, DishTemplate dishTemplate) {
        IngredientTemplate ingredientTemplate = new IngredientTemplate(name, price, dishTemplate);
        dishTemplate.getIngredientTemplates().add(ingredientTemplate);
        return ingredientTemplate;
    }

    private DishTemplate addDishTemplate(String name, float price) {
        DishTemplate dishTemplate = new DishTemplate(name, price);
        return dishTemplate;
    }
}
