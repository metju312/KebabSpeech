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
    private IngredientTemplateDao ingredientTemplateDao = new IngredientTemplateDao();
    private InvoiceDao invoiceDao = new InvoiceDao();

    private int mainWindowWidth = 1280;
    private int mainWindowHeight = 970;

    public OrderPanel orderPanel = new OrderPanel();
    public OrderListPanel orderListPanel = new OrderListPanel(orderPanel);
    public DialogPanel dialogPanel = new DialogPanel(orderPanel, orderListPanel);

    private JScrollPane dialogPanelScrollPane = new JScrollPane(dialogPanel);
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
        DishTemplate kebab = addDishTemplate("Kebab", 8.5f);
        DishTemplate danieDania = addDishTemplate("Danie dnia", 10f);
        DishTemplate danieSzefaKuchni = addDishTemplate("Danie szefa kuchni", 14f);
        DishTemplate talerzKrola = addDishTemplate("Talerz Króla", 16f);
        DishTemplate kawa = addDishTemplate("Kawa", 3.5f);
        DishTemplate herbata = addDishTemplate("Herbata", 2.5f);
        DishTemplate woda = addDishTemplate("woda", 2.5f);
        DishTemplate pepsi = addDishTemplate("pepsi", 2.5f);
        DishTemplate kola = addDishTemplate("kola", 2.5f);
        DishTemplate d4 = addDishTemplate("Kola", 2.5f);
        DishTemplate d5 = addDishTemplate("pepsi", 2.5f);
        DishTemplate d6 = addDishTemplate("Fanta", 2.5f);
        IngredientTemplate i1 = addIngredientTemplate("Baranina", 2.4f, kebab);
        IngredientTemplate i2 = addIngredientTemplate("Kurczak", 2f, kebab);
        IngredientTemplate i3 = addIngredientTemplate("Mieszane", 2.2f, kebab);
        IngredientTemplate i4 = addIngredientTemplate("Tortilla", 2f, kebab);
        IngredientTemplate i5 = addIngredientTemplate("Bułka", 2f, kebab);
        IngredientTemplate i6 = addIngredientTemplate("łagodny", 2f, kebab);
        IngredientTemplate i7 = addIngredientTemplate("ostry", 2f, kebab);
        IngredientTemplate i8 = addIngredientTemplate("Mieszany", 2f, kebab);
        IngredientTemplate s1 = addIngredientTemplate("Grecka", 1.3f, null);
        IngredientTemplate s2 = addIngredientTemplate("Jarzynowa", 1.1f, null);
        IngredientTemplate s3 = addIngredientTemplate("Pieczarkowa", 1.3f, null);
        IngredientTemplate s4 = addIngredientTemplate("Rybna", 1.2f, null);
        IngredientTemplate poj1 = addIngredientTemplate("Mała", 0.2f, null);
        IngredientTemplate poj2 = addIngredientTemplate("średnia", 1.2f, null);
        IngredientTemplate poj3 = addIngredientTemplate("duża", 2.2f, null);
        IngredientTemplate typ1 = addIngredientTemplate("Butelka", 1.1f, null);
        IngredientTemplate typ2 = addIngredientTemplate("Puszka", 0.4f, null);
        ingredientTemplateDao.create(i2);
        ingredientTemplateDao.create(i3);
        ingredientTemplateDao.create(i4);
        ingredientTemplateDao.create(i7);
        ingredientTemplateDao.create(i8);
        ingredientTemplateDao.create(s1);
        ingredientTemplateDao.create(s2);
        ingredientTemplateDao.create(s3);
        ingredientTemplateDao.create(s4);
        ingredientTemplateDao.create(poj1);
        ingredientTemplateDao.create(poj2);
        ingredientTemplateDao.create(poj3);
        ingredientTemplateDao.create(typ1);
        ingredientTemplateDao.create(typ1);
        ingredientTemplateDao.create(typ2);
        dishTemplateDao.create(danieDania);
        dishTemplateDao.create(danieSzefaKuchni);
        dishTemplateDao.create(talerzKrola);
        dishTemplateDao.create(kawa);
        dishTemplateDao.create(herbata);
        dishTemplateDao.create(woda);
        dishTemplateDao.create(pepsi);
        dishTemplateDao.create(kola);
        dishTemplateDao.create(kebab);
        dishTemplateDao.create(d4);
        dishTemplateDao.create(d5);
        dishTemplateDao.create(d6);


        //generate mock invoices
        Invoice invoice1 = new Invoice(new Date().getTime());
        Dish dish1 = new Dish(herbata, invoice1);//herbata
        Dish dish2 = new Dish(d4, invoice1);//kola
        Dish dish3 = new Dish(kebab, invoice1);//kebab
        Ingredient ingredient1 = new Ingredient(i1, dish3);//baranian
        Ingredient ingredient2 = new Ingredient(i6, dish3);//łągodny
        Ingredient ingredient3 = new Ingredient(i5, dish3);//bułka
        dish3.getIngredients().add(ingredient1);//baranina
        dish3.getIngredients().add(ingredient2);//łagodny
        dish3.getIngredients().add(ingredient3);//bułka
        invoice1.getDishes().add(dish1);
        invoice1.getDishes().add(dish2);
        invoice1.getDishes().add(dish3);
        invoice1.countAndSetPrice();
        invoiceDao.create(invoice1);

        //generate mock invoices
        Invoice invoice2 = new Invoice(new Date().getTime());
        Dish dish4 = new Dish(herbata, invoice2);//herbata
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
