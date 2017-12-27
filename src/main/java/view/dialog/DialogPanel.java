package view.dialog;

import controller.dialog.DialogController;
import model.entity.TypeOfMeat;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DialogPanel extends JPanel {
    private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private JButton startButton;
    private JButton endButton;

    private DialogController dialogController = new DialogController();

    private java.util.List<JPanel> typeOfMeatPanels = new ArrayList<>();

    private List<TypeOfMeat> typeOfMeatList;

    public DialogPanel() {
        setLayout(new MigLayout());

        addElements();
    }

    private void addElements() {
        addStartButton();
        addDialogElements();
        addEndButton();
    }

    private void addDialogElements() {
        typeOfMeatList = new ArrayList<>();
        TypeOfMeat typeOfMeat1 = new TypeOfMeat();
        typeOfMeat1.setName("baranina");
        typeOfMeat1.setPrice(4.20f);
        typeOfMeatList.add(typeOfMeat1);

        TypeOfMeat typeOfMeat2 = new TypeOfMeat();
        typeOfMeat2.setName("wołowina");
        typeOfMeat2.setPrice(5.20f);
        typeOfMeatList.add(typeOfMeat2);
        addGenericElements("Typy mięsa",typeOfMeatList, typeOfMeatPanels);
    }

    private void addGenericElements(String title,List<TypeOfMeat> typeOfMeatList, List<JPanel> typeOfMeatPanels) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setLayout(new MigLayout());
        panel.add(new JLabel(title), "wrap, span, center");
        for (int i = 0; i < typeOfMeatList.size(); i++) {
            JPanel loopPanel = new JPanel();
            loopPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            loopPanel.add(new JLabel(typeOfMeatList.get(i).getName()));
            panel.add(loopPanel);
            typeOfMeatPanels.add(loopPanel);
        }
        add(panel, "wrap");
    }

    private void addEndButton() {
        JPanel panel = new JPanel();
        endButton = new JButton("Koniec");
        panel.add(endButton);
        add(panel, "span, center");
    }

    private void addStartButton() {
        JPanel panel = new JPanel();
        startButton = new JButton("Start");
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
        logger.log(Level.INFO, "Start Dialog");
        startButton.setEnabled(false);
        dialogController.speechText("Podaj rodzaj mięsa.");
        String typeOfMeat = dialogController.recordAndGetText();
        for (int i = 0; i < typeOfMeatList.size(); i++) {
            if(typeOfMeat.equals(typeOfMeatList.get(i))){
                typeOfMeatPanels.get(i).setBackground(Color.green);
            }
        }
        startButton.setEnabled(true);
        endButton.setBackground(Color.RED);
    }
}
