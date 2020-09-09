package ui.panel;

import tools.Controller;
import ui.OpenFile;
import ui.filter.DTFFilter;

import javax.swing.*;

public class FileManagementPanel extends JPanel {
    private JLabel inputFile;
    private JTextField locationFile = new JTextField();
    private JButton openButton;

    public FileManagementPanel(PanelType type) {
        super();

        switch (type) {
            case DUAL_TRANSFORM -> {
                inputFile = new JLabel(Controller.getLanguageText("outputTransformLabel"));
                openButton = new JButton(new OpenFile());
            }
            case ANTI_DUAL_TRANSFORM -> {
                inputFile = new JLabel(Controller.getLanguageText("inputAntiTransformLabel"));
                openButton = new JButton(new OpenFile(new DTFFilter()));
            }
        }

        locationFile.setEditable(false);

        add(inputFile);
        add(locationFile);
        add(openButton);

        validate();
    }

    public void setLocationFileText(String text) {
        locationFile.setText(text);
        validate();
    }
}
