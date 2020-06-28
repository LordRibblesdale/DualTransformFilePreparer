package ui.panel;

import javax.swing.*;

public class CompressionPanel extends JPanel {
  /* JLabel for input title
   * JLabel for file location - JButton for input
   *
   * if something loaded
   *  JLabel directory
   *  JLabel output Approximation file
   *  JLabel output Details file
   *
   * JLabel for file output location - JButton for output
   * -> should all files be compressed? JCheckBox
   *
   * J_Component_ for choosing algorithm type
   * -> if useful, J_Component_ for choosing compression ratio
   * JSpinner for thread number (if process splittable into different buffers
   *
   * AbstractButton WLAction for action (compression or decompression)
   */

    public CompressionPanel() {
        super();
    }
}
