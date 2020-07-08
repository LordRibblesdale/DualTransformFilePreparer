package ui;

import tools.Controller;
import ui.button.WLAction;
import ui.panel.CompressionPanel;
import ui.panel.DecompressionPanel;

import javax.swing.*;
import java.awt.*;

public class FrameWindow extends JFrame {
  private JPanel bottomPanel;
  private JLabel notificationLabel;

  private JButton panelButton;
  private WLAction button;

  private JTabbedPane mainTabbedPanel;
  private CompressionPanel compressionPanel;
  private DecompressionPanel decompressionPanel;

  // Add a menu for update asking

  public FrameWindow(String title) {
    super(title);

    if (Controller.isLookAndFeelEnabled()) {
      try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        JOptionPane.showMessageDialog(
            FrameWindow.this,
            e.getMessage(),
            "LookAndFeel UserController00 V: " + e.getStackTrace()[0].getLineNumber(),
            JOptionPane.ERROR_MESSAGE);
      }

      notificationLabel = new JLabel();
      add(notificationLabel, BorderLayout.PAGE_END);
    }

    panelButton = new JButton(new WLAction());

    mainTabbedPanel = new JTabbedPane();
    mainTabbedPanel.addChangeListener(changeListener_stateChanged -> {
      panelButton.setText(mainTabbedPanel.getTitleAt(mainTabbedPanel.getSelectedIndex()));
      // validate already included
    });

    compressionPanel = new CompressionPanel();
    mainTabbedPanel.addTab(Controller.getLanguageText("compressionTab"), compressionPanel);

    decompressionPanel = new DecompressionPanel();
    mainTabbedPanel.addTab(Controller.getLanguageText("decompressionTab"), decompressionPanel);

    panelButton.setText(mainTabbedPanel.getTitleAt(mainTabbedPanel.getSelectedIndex()));

    add(mainTabbedPanel);

    add(panelButton, BorderLayout.PAGE_END);

    setMinimumSize(new Dimension(
        (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.2),
        (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.55)));
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setVisible(true);
  }

  public void setFileText(String text) {
    compressionPanel.setLocationFileText(text);
  }

  public void setApproxFileText(String text) {
    decompressionPanel.setApproxLocationTextFile(text);

    validate();
  }

  public void setDetailFileText(String text) {
    decompressionPanel.setDetailLocationTextFile(text);

    validate();
  }

  public void setFloatingApproxFileText(String text) {
    decompressionPanel.setFloatingApproxLocationTextFile(text);
  }

  public void setFloatingDetailFileText(String text) {
    decompressionPanel.setFloatingDetailLocationTextFile(text);
  }

  public void setTextBottomPanel(String s) {
    notificationLabel.setText(s);

    validate();
  }

  public int getTabIndex() {
    return mainTabbedPanel.getSelectedIndex();
  }
}
