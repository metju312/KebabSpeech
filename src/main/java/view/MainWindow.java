package view;

import generated.Vxml;
import model.dao.DishTemplateDao;
import model.dao.IngredientDao;
import model.dao.IngredientTemplateDao;
import model.entity.DishTemplate;
import model.entity.Ingredient;
import model.entity.IngredientTemplate;
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
import java.util.logging.Logger;
import java.util.List;

public class MainWindow extends JFrame {
    private static final Logger LOGGER = Logger.getLogger(MainWindow.class.getName());
    private static MainWindow instance = null;
    private DishTemplateDao dishTemplateDao = new DishTemplateDao();

    private int mainWindowWidth = 1280;
    private int mainWindowHeight = 850;

    public OrderPanel orderPanel = new OrderPanel();
    public OrderListPanel orderListPanel = new OrderListPanel();
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

    private void initDatabaseTemplates(){
        DishTemplate d1 = addDishTemplate("Kola", 2.5f);
        DishTemplate d2 = addDishTemplate("Herbata", 2.5f);
        DishTemplate d3 = addDishTemplate("Kebab", 8.5f);
        IngredientTemplate i1 = addIngredientTemplate("Sałata", 2f, d3);
        IngredientTemplate i2 = addIngredientTemplate("Mieszany", 2f, d3);
        dishTemplateDao.create(d1);
        dishTemplateDao.create(d2);
        dishTemplateDao.create(d3);

//
//        //dishTemplates
//        dishTemplateDao.create(new DishTemplate("Herbata",2.2f));
//        DishTemplate kebab = new DishTemplate("Kebab",8.5f);
//        List<IngredientTemplate> ingredientTemplates = new ArrayList<>();
//        IngredientTemplate salata = new IngredientTemplate("Sałata",2f);
//        IngredientTemplate mieszany = new IngredientTemplate("Mieszany",2f);
//        salata.setDishTemplate(kebab);
//        mieszany.setDishTemplate(kebab);
//        ingredientTemplates.add(salata);
//        ingredientTemplates.add(mieszany);
//        kebab.setIngredientTemplates(ingredientTemplates);
//        kebab = dishTemplateDao.create(kebab);


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
