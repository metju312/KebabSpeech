package view.dialog;

import controller.dialog.DialogController;
import generated.Vxml;
import model.dao.DishTemplateDao;
import model.dao.IngredientTemplateDao;
import model.dao.InvoiceDao;
import model.entity.Dish;
import model.entity.DishTemplate;
import model.entity.IngredientTemplate;
import model.entity.Invoice;
import net.miginfocom.swing.MigLayout;
import view.order.OrderPanel;
import view.orderList.OrderListPanel;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DialogPanel extends JPanel {
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private OrderPanel orderPanel;
    private OrderListPanel orderListPanel;

    private JButton startButton;
    private JButton endButton;
    private DialogController dialogController = new DialogController();
    private List<FormPanel> formPanelList = new ArrayList<>();

    private InvoiceDao invoiceDao = new InvoiceDao();
    private DishTemplateDao dishTemplateDao = new DishTemplateDao();
    private IngredientTemplateDao ingredientTemplateDao = new IngredientTemplateDao();

    private List<DishTemplate> dishTemplates = dishTemplateDao.findAll();
    private List<IngredientTemplate> ingredientTemplates = ingredientTemplateDao.findAll();

    private Invoice invoice;

    public DialogPanel(OrderPanel orderPanel, OrderListPanel orderListPanel) {
        this.orderPanel = orderPanel;
        this.orderListPanel = orderListPanel;
        setLayout(new MigLayout());
        addElements();
    }

    private void addElements() {
        addStartButton();
        generateAndAddFormPanelsFromXML();
        addEndButton();
    }

    private void addEndButton() {
        JPanel panel = new JPanel();
        endButton = new JButton("Zakończono zamówienie");
        panel.add(endButton);
        add(panel, "span, center");
    }

    private void addStartButton() {
        JPanel panel = new JPanel();
        startButton = new JButton("Kolejne zamówienie");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startDialog();
            }
        });
        panel.add(startButton);
        add(panel, "span, center");
    }

    private void startDialog() {
        startButton.setEnabled(false);
        invoice = new Invoice();
        orderPanel.refreshTable(invoice);
        //TODO delete
        mockWork();
        //TODO uncomment
        //playFormDialog(formPanelList.get(0));
    }

    private void mockWork() {
        Timer timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                Dish dish = new Dish(dishTemplates.get(0), invoice);
                invoice.getDishes().add(dish);
                orderPanel.refreshTable(invoice);
            }
        }, 5000);

        Timer timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            @Override
            public void run() {
                Dish dish = new Dish(dishTemplates.get(1), invoice);
                invoice.getDishes().add(dish);
                orderPanel.refreshTable(invoice);
            }
        }, 10000);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                invoice.setDate(new Date().getTime());
                invoice.countAndSetPrice();
                invoiceDao.create(invoice);
                endDialog();
            }
        }, 15000);
    }

    private void endDialog(){
        refresh();
        orderListPanel.refreshTable();
    }

    private void refresh() {
        removeAll();
        addElements();
        repaint();
        revalidate();
        repaint();
    }

    private void playFormDialog(FormPanel formPanel) {
        logger.log(Level.INFO, "Start FormPanel: " + formPanel.id);
        dialogController.speechText(formPanel.prompt);

        String recordedText = dialogController.recordAndGetText();
        if(recordedText.equals("")){
            dialogController.speechText(formPanel.noInputLabel);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    playFormDialog(findFormById(formPanel.id));
                }
            }, 100);
        }

        //wybranie opcji
        logger.log(Level.INFO,"Chooing option...");
        for (int i = 0; i < formPanel.options.size(); i++) {
            logger.log(Level.INFO,"Recorded: [" + normalizeText(recordedText) + "] ,option: [" + normalizeText(formPanel.options.get(i))+"]");
            if(normalizeText(recordedText).equals(normalizeText(formPanel.options.get(i)))){
                //TODO dodanie do zamówienia
                formPanel.colorOption(formPanel.options.get(i));
            }
        }

        //przejście do goto używając gotoName
        for (int i = 0; i < formPanel.gotoNames.size(); i++) {
            if(normalizeText(formPanel.gotoNames.get(i).cond).equals(normalizeText(recordedText))){
                Timer timer = new Timer();
                int finalI = i;
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        playFormDialog(findFormById(formPanel.gotoNames.get(finalI).form));
                    }
                }, 100);
            }
        }

//        if(end){
//            startButton.setEnabled(true);
//            endButton.setBackground(Color.RED);
//        }
    }

    private FormPanel findFormById(String formId) {
        for (FormPanel formPanel : formPanelList) {
            if(formPanel.id.equals(formId)){
                return formPanel;
            }
        }
        return null;
    }


    private void generateAndAddFormPanelsFromXML() {
        try {
            JAXBContext jc = JAXBContext.newInstance(Vxml.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            File file = new File("src/main/java/dialog.xml");
            Vxml rootObject = (Vxml) unmarshaller.unmarshal(file);
            for (Vxml.Form form : rootObject.getForm()){
                FormPanel formPanel = new FormPanel(form);
                formPanelList.add(formPanel);
                add(formPanel, "wrap");
            }
        } catch (JAXBException ex) {
            System.out.println("XML Unmarshaller error");
            ex.printStackTrace();
        }
    }

    private String normalizeText(String text){
        return text.toLowerCase();
    }
}
