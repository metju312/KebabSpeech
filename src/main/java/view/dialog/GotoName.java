package view.dialog;

public class GotoName {
    public String form;
    public String cond;

    public GotoName(String form, String cond) {
        this.form = form;
        this.cond = cond;
    }

    public GotoName() {
    }

    @Override
    public String toString() {
        return "GotoName{" +
                "form='" + form + '\'' +
                ", cond='" + cond + '\'' +
                '}';
    }
}
