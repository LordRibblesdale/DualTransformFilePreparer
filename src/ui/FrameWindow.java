package ui;

import tools.Controller;
import ui.button.WLAction;
import ui.panel.CompressionPanel;
import ui.panel.DecompressionPanel;

import javax.swing.*;

public class FrameWindow extends JFrame {

  private JPanel bottomPanel;
  private JLabel notificationLabel;
  private WLAction button;

  private JTabbedPane mainTabbedPanel;
  private CompressionPanel compressionPanel;
  private DecompressionPanel decompressionPanel;

  public FrameWindow() {

    mainTabbedPanel = new JTabbedPane();
    //compressionPanel
  }

  public void setTextBottomPanel(String s) {
    notificationLabel.setText(s);

    validate();
  }
}
