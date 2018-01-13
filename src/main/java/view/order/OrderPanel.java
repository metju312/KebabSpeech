package view.order;

import model.entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OrderPanel extends JPanel {

    private TableModel tableModel;
    private JTable table;
    private JLabel costLabel = new JLabel();
    private Invoice invoice;

    public OrderPanel() {
        //TODO delete mock Invoice
        createMockInvoice();
        setMinimumSize(new Dimension(600, 300));
        setLayout(new BorderLayout());
        refreshTable();
        setupCostRow();
    }

    private void createMockInvoice() {
        //drinks
        Drink drink1 = new Drink("Pepsi", 2.5f);
        Drink drink2 = new Drink("Fanta", 2.2f);
        List<Drink> drinks = new ArrayList<>();
        drinks.add(drink1);
        drinks.add(drink2);

        //typeOfMeats
        TypeOfMeat typeOfMeat1 = new TypeOfMeat("Baranina", 4.3f);
        List<TypeOfMeat> typeOfMeats = new ArrayList<>();
        typeOfMeats.add(typeOfMeat1);

        //typeOfMeats
        TypeOfMeal typeOfMeal1 = new TypeOfMeal("Kebab", 4.3f);
        List<TypeOfMeal> typeOfMeals = new ArrayList<>();
        typeOfMeals.add(typeOfMeal1);

        //sauces
        Sauce sauce1 = new Sauce("Mieszany", 2.3f);
        List<Sauce> sauces = new ArrayList<>();
        sauces.add(sauce1);

        //additions
        Addition addition1 = new Addition("Sałata", 1.3f);
        Addition addition2 = new Addition("Cebula", 1.1f);
        List<Addition> additions = new ArrayList<>();
        additions.add(addition1);
        additions.add(addition2);

        //dishs
        Dish dish1 = new Dish();
        dish1.setName("Danie obiadowe");
        dish1.setPrice(22.4f);
        dish1.setDrinks(drinks);
        dish1.setSauces(sauces);
        dish1.setAdditions(additions);
        dish1.setTypeOfMeats(typeOfMeats);
        dish1.setTypeOfMeals(typeOfMeals);
        List<Dish> dishes = new ArrayList<>();
        dishes.add(dish1);

        //invoice
        invoice = new Invoice();
        invoice.setPrice(54.2f);
        invoice.setDishes(dishes);
    }

    private void setupCostRow() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Całkowity koszt:"));
        panel.add(costLabel);
        panel.add(new JLabel("zł"));
        costLabel.setText(String.valueOf(invoice.getPrice()));
        add(panel, BorderLayout.SOUTH);
    }

    private void setupTable() {
        List<String> columns = new ArrayList<String>();
        List<String[]> values = new ArrayList<String[]>();
        columns.add("Nazwa produktu");
        columns.add("Kategoria");
        columns.add("Cena");


        for (Dish dish : invoice.getDishes()) {
            //drinks
            for (Drink drink : dish.getDrinks()) {
                values.add(new String[]{drink.getName(), "Napój", String.valueOf(drink.getPrice())});
            }
        }


        tableModel = new DefaultTableModel(values.toArray(new Object[][]{}), columns.toArray());
        table = new JTable();
        table.setModel(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void refreshTable() {
        removeAll();
        setupTable();
        setupCostRow();
    }
}
