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

      setMinimumSize(new Dimension(
          (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.2),
          (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.55)));
      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      setVisible(true);
    }

    mainTabbedPanel = new JTabbedPane();
    //compressionPanel
  }

  public void setTextBottomPanel(String s) {
    notificationLabel.setText(s);

    validate();
  }
}
