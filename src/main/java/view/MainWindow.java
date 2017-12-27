package view;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class MainWindow extends JFrame {
    private static final Logger LOGGER = Logger.getLogger(MainWindow.class.getName());
    private static MainWindow instance = null;

    private int mainWindowWidth = 1280;
    private int mainWindowHeight = 720;


    private JSplitPane splitPane;

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

        generatePanels();

        getContentPane().add(splitPane, BorderLayout.CENTER);
    }

    private void generatePanels() {
        JPanel panel1 = new JPanel();
        panel1.add(new JButton("button1"));
        JPanel panel2 = new JPanel();
        panel2.add(new JButton("button2"));

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel1, panel2);
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
