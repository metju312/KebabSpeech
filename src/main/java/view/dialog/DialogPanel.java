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
import java.awt.*;
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
        endButton.setEnabled(false);
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
        //mockWork();
        //TODO uncomment
        formPanelList.get(0).setBackground(Color.CYAN);
        Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    playFormDialog(formPanelList.get(0));
                }
            }, 500);
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

        //Pokoloruj forma
        colorFormPanel(formPanel);

        //Powiedz pierwszego prompta
        dialogController.speechText(formPanel.prompt);

        //Nagraj odpowiedź
        List<String> recordedTextList = dialogController.recordAndGetTextList();

        //Odegraj tego samego forma jeśli no input
        if(isTextOnTheList("", recordedTextList)){
            dialogController.speechText(formPanel.noInputLabel);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    playFormDialog(findFormById(formPanel.id));
                }
            }, 100);
        }

        //Wybranie opcji
        logger.log(Level.INFO,"Chooing option...");
        logger.log(Level.INFO,"Recorded: [" + normalizeText(recordedTextList.toString()) + "], Options:");
        for (int i = 0; i < formPanel.options.size(); i++) {
            logger.log(Level.INFO,"[" + normalizeText(formPanel.options.get(i))+"]");
            //pętla po wszystkich odpowiedziach od googla
            for (String recordedText : recordedTextList) {
                if(normalizeText(recordedText).equals(normalizeText(formPanel.options.get(i)))){
                    //TODO dodanie do zamówienia
                    formPanel.colorOption(formPanel.options.get(i));
                }
            }
        }

        //przejście do goto używając gotoName
        for (int i = 0; i < formPanel.gotoNames.size(); i++) {
            logger.log(Level.INFO,"Goto option:" + formPanel.gotoNames.get(i));
            //pętla po wszystkich odpowiedziach od googla
            String recordedText = "";
            if(isTextOnTheList(normalizeText(formPanel.gotoNames.get(i).cond), recordedTextList)){
                recordedText = normalizeText(formPanel.gotoNames.get(i).cond);
            }

            if(normalizeText(formPanel.gotoNames.get(i).cond).equals(normalizeText(recordedText)) || formPanel.gotoNames.size()==1){
                logger.log(Level.INFO,"Goto option Matched!");
                Timer timer = new Timer();
                int finalI = i;
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        logger.log(Level.INFO,"Try to start next dialog!: " + formPanel.gotoNames.get(finalI).form);
                        playFormDialog(findFormById(formPanel.gotoNames.get(finalI).form));
                    }
                }, 200);
            }
        }

        if(formPanel.id.equals("KoniecForm")){
            endDialog();
            startButton.setEnabled(true);
            endButton.setBackground(Color.GREEN);
        }
    }

    private boolean isTextOnTheList(String text, List<String> list) {
        for (String s : list) {
            if (normalizeText(text).equals(normalizeText(s))){
                return true;
            }
        }
        return false;
    }

    private void colorFormPanel(FormPanel formPanel) {
        removeAllFormPanelsColors();
        formPanel.setBackground(Color.CYAN);
    }

    private void removeAllFormPanelsColors() {
        for (FormPanel formPanel : formPanelList) {
            formPanel.setBackground(null);
        }
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
