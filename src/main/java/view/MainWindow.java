package view;

import model.dao.IngredientDao;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class MainWindow extends JFrame {
    private static final Logger LOGGER = Logger.getLogger(MainWindow.class.getName());
    private static MainWindow instance = null;

    private int mainWindowWidth = 1000;
    private int mainWindowHeight = 700;

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
        sql();

    }

    private void sql() {
        Ingredient ingredient = new Ingredient();
        ingredient.setName("Salata");
        IngredientDao ingredientDao = new IngredientDao(ingredient);
        ingredientDao.create(ingredient);
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
