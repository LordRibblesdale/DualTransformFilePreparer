package ui.panel;

import tools.Controller;
import ui.OpenFile;
import ui.filter.AWFFilter;
import ui.filter.DWFFilter;

import javax.swing.*;

public class DecompressionPanel extends JPanel {
    private final JLabel inputApproxFile = new JLabel(Controller.getLanguageText("approxDecompressionLabel"));
    private final JLabel inputDetailFile = new JLabel(Controller.getLanguageText("detailDecompressionLabel"));
    private final JLabel floatingInputApproxFile = new JLabel(Controller.getLanguageText("floatingApproxFile"));
    private final JLabel floatingInputDetailFile = new JLabel(Controller.getLanguageText("floatingDetailFile"));
    private JTextField locationApproxFile = new JTextField();
    private JTextField locationDetailFile = new JTextField();
    private JTextField floatingLocationApproxFile = new JTextField();
    private JTextField floatingLocationDetailFile = new JTextField();
    // TODO complete here
    private JButton openApproxButton = new JButton(new OpenFile(new AWFFilter()));
    private JButton openDetailButton = new JButton(new OpenFile(new DWFFilter()));

    public DecompressionPanel() {
        super();

        locationApproxFile.setEditable(false);
        locationDetailFile.setEditable(false);

        add(inputApproxFile);
        add(locationApproxFile);
        add(openApproxButton);

        add(inputDetailFile);
        add(locationDetailFile);
        add(openDetailButton);

        validate();
    }

    public void setApproxLocationTextFile(String text) {
        locationApproxFile.setText(text);
        validate();
    }

    public void setDetailLocationTextFile(String text) {
        locationDetailFile.setText(text);
        validate();
    }
}
