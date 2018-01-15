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
        setMinimumSize(new Dimension(600, 300));
        setLayout(new BorderLayout());
        setupCostRow();
    }

    private void setupCostRow() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Całkowity koszt:"));
        panel.add(costLabel);
        panel.add(new JLabel("zł"));
        add(panel, BorderLayout.SOUTH);
    }

    private void setupTable() {
        List<String> columns = new ArrayList<String>();
        List<String[]> values = new ArrayList<String[]>();
        columns.add("Lp.");
        columns.add("Nazwa produktu");
        columns.add("Składniki");
        columns.add("Cena");
        Integer lp = 1;

        //dish
        for (Dish dish : invoice.getDishes()) {
            String ingredients = "";
            //drinks
            for (Ingredient ingredient : dish.getIngredients()) {
                ingredients += ingredient.getIngredientTemplate().getName() + ", ";
            }
            if(ingredients.length()>=2){
                ingredients = ingredients.substring(0, ingredients.length() - 2);
            }
            ingredients = ingredients.toLowerCase();
            values.add(new String[]{lp.toString(), dish.getDishTemplate().getName(), ingredients, String.valueOf(dish.getDishTemplate().getPrice())});
            lp++;
        }
        costLabel.setText(String.valueOf(invoice.getPrice()));


        tableModel = new DefaultTableModel(values.toArray(new Object[][]{}), columns.toArray());
        table = new JTable();
        table.setModel(tableModel);
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void refreshTable(Invoice invoice) {
        this.invoice = invoice;
        removeAll();
        setupTable();
        setupCostRow();
        repaint();
        revalidate();
        repaint();
    }

}
