package update;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ProgressRenderer extends ProgressMonitor {
   ProgressRenderer(Component mainWindow, String title, String message) {
      super(mainWindow, message, title ,0, 100);
   }
}
