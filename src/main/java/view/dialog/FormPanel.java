package view.dialog;

import generated.Vxml;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FormPanel extends JPanel {
    public Vxml.Form form = null;
    public Vxml.Form.Field field = null;
    public String id = "";
    public String prompt = "";
    public String fieldName = "";
    public String noInputLabel = "";
    public String noMatchLabel = "";
    public List<String> options = new ArrayList<>();
    public List<JPanel> optionPanels = new ArrayList<>();
    public List<String> gotoForms = new ArrayList<>();
    public List<GotoName> gotoNames = new ArrayList<>();



    public FormPanel(Vxml.Form form) {
        this.form = form;
        this.field = form.getField();
        this.id = form.getId();
        System.out.println("\nForm: " + id);
        this.fieldName = field.getName();
        this.prompt = field.getPrompt();
        if(field.getGrammar() != null){
            this.options = field.getGrammar().getRule().getOneOf().getItem();
            this.noInputLabel = field.getNoinput().toString();
            this.noMatchLabel = field.getNomatch().toString();


            //pierwszy goto ręcznie:
            GotoName firstGotoName = new GotoName();
            firstGotoName.cond = cutCond(field.getFilled().getIf().getCond());
            firstGotoName.form = ((Vxml.Form.Field.Filled.If.Goto)field.getFilled().getIf().getGotoOrElseifOrElse().get(0)).getNext().substring(1);
            gotoNames.add(firstGotoName);

            //iteracja  zbiera od pierwszego elseif - pomija pierwsze goto
            for (int i = 1; i < field.getFilled().getIf().getGotoOrElseifOrElse().size(); i = i+2) {
                Object object =  field.getFilled().getIf().getGotoOrElseifOrElse().get(i);

                GotoName gotoName = new GotoName();

                //elseif cond
                try{
                    Vxml.Form.Field.Filled.If.Elseif elseifForm = (Vxml.Form.Field.Filled.If.Elseif) object;
                    gotoName.cond = cutCond(elseifForm.getCond());
                } catch (Exception e){

                }

                //GOTO form
                object =  field.getFilled().getIf().getGotoOrElseifOrElse().get(i+1);
                try{
                    Vxml.Form.Field.Filled.If.Goto gotoForm = (Vxml.Form.Field.Filled.If.Goto) object;
                    gotoName.form = gotoForm.getNext().substring(1);
                } catch (Exception e){

                }

                if(gotoName.form != null && gotoName.cond != null){
                    gotoNames.add(gotoName);
                }
            }

            setupPanel();
        }
    }

    private String cutCond(String cond){
        cond = cond.substring(cond.indexOf("'") + 1);
        cond = cond.substring(0, cond.length() - 1);
        return cond;
    }

    private void setupPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(new MigLayout());
        add(new JLabel(prompt), "wrap, span, center");
        for (int i = 0; i < options.size(); i++) {
            JPanel loopPanel = new JPanel();
            loopPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            loopPanel.add(new JLabel(options.get(i)));
            add(loopPanel);
            optionPanels.add(loopPanel);
        }
    }

    public void colorOption(String optionName) {
        System.out.println("kkk color option");
        for (int i = 0; i < options.size(); i++) {
            System.out.println("options.get(i):" + options.get(i));
            System.out.println("optionName: " + optionName);
            if(normalizeText(options.get(i)).equals(normalizeText(optionName))){
                System.out.println("kkk color option IFFF");
                optionPanels.get(i).setBackground(Color.green);
            }
        }
    }

    private String normalizeText(String text){
        return text.toLowerCase();
    }
}
