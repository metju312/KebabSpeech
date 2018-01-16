package view.orderList;

import model.dao.InvoiceDao;
import model.entity.Invoice;
import view.order.OrderPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderListPanel extends JPanel{

    private TableModel tableModel;
    private JTable table;
    private JLabel costsLabel = new JLabel();
    private List<Invoice> invoices = new ArrayList<>();
    private InvoiceDao invoiceDao = new InvoiceDao();
    private OrderPanel orderPanel;

    public OrderListPanel(OrderPanel orderPanel) {
        this.orderPanel = orderPanel;
        setMinimumSize(new Dimension(600, 300));
        setLayout(new BorderLayout());
        refreshTable();
        setupCostsRow();
    }

    private void loadInvoices() {
        invoices = invoiceDao.findAll();

    }

    private void setupCostsRow() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Całkowity koszt:"));
        panel.add(costsLabel);
        panel.add(new JLabel("zł"));
        add(panel, BorderLayout.SOUTH);

        Float costs = 0f;
        System.out.println("myinvoices:");
        for (Invoice invoice : invoices) {
            costs+=invoice.getPrice();
            System.out.println("next invoice");
            System.out.println(costs);
        }
        costsLabel.setText(costs.toString());
    }

    private void setupTable() {
        List<String> columns = new ArrayList<String>();
        List<String[]> values = new ArrayList<String[]>();
        columns.add("Lp.");
        columns.add("Data");
        columns.add("Koszt");
        Integer lp = 1;

        //invoices
        for (Invoice invoice : invoices) {
            values.add(new String[]{lp.toString(), new Date(invoice.getDate()).toString(), String.valueOf(invoice.getPrice())});
            lp++;
        }

        tableModel = new DefaultTableModel(values.toArray(new Object[][]{}), columns.toArray());
        table = new JTable();
        table.setModel(tableModel);
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    orderPanel.refreshTable(invoices.get(row));
                }
            }
        });
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void refreshTable() {
        removeAll();
        loadInvoices();
        setupTable();
        setupCostsRow();
        repaint();
        revalidate();
        repaint();
    }

}
