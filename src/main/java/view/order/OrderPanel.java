package view.order;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class OrderPanel extends JPanel{

    public OrderPanel() {
        setMinimumSize(new Dimension(600,300));
        setLayout(new MigLayout());

        setupTable();
    }

    private void setupTable() {
        Object rowData[][] = { { "Row1-Column1", "Row1-Column2", "Row1-Column3" },
                { "Row2-Column1", "Row2-Column2", "Row2-Column3" } };
        Object columnNames[] = { "Typ produktu", "Nazwa", "Cena" };
        JTable table = new JTable(rowData, columnNames);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }
}
