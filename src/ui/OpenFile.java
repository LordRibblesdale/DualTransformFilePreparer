package ui;

import tools.Controller;
import ui.filter.DTFFilter;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.io.File;

public class OpenFile extends AbstractAction implements ui.Path {
   private FileFilter filter = null;

   public OpenFile() {
      putValue(Action.NAME, Controller.getLanguageText("load"));
      //putValue(Action.LARGE_ICON_KEY, new ImageIcon(OpenFile.class.getResource("Open.png")));
      //putValue(Action.SMALL_ICON, new ImageIcon(OpenFile.class.getResource("Open.png")));
      putValue(Action.SHORT_DESCRIPTION, Controller.getLanguageText("load"));
   }

   public OpenFile(FileFilter filter) {
      this();

      this.filter = filter;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO set if compression & decompression
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setAcceptAllFileFilterUsed(false);

      if (filter != null) {
         fileChooser.setAcceptAllFileFilterUsed(false);

         if (filter instanceof DTFFilter dtfFilter) {
            fileChooser.addChoosableFileFilter(dtfFilter);
         }
      } else {
         fileChooser.setAcceptAllFileFilterUsed(true);
         fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
      }

      int res = fileChooser.showOpenDialog(Controller.getFrameWindow());

      if (res == JFileChooser.APPROVE_OPTION) {
         if (filter != null) {
            if (filter instanceof DTFFilter) {
               Controller.setFullTransformedName(fileChooser.getSelectedFile().getPath());
               Controller.setTransformedLocation(fileChooser.getSelectedFile().getParent() + File.separator);
               Controller.setFileTransformedName(fileChooser.getSelectedFile().getName());
            }
         } else {
            Controller.setFullName(fileChooser.getSelectedFile().getPath());
            Controller.setLocation(fileChooser.getSelectedFile().getParent() + File.separator);
            Controller.setFileName(fileChooser.getSelectedFile().getName());
         }
      } else if (res != JFileChooser.CANCEL_OPTION) {
         JOptionPane.showMessageDialog(Controller.getFrameWindow(), Controller.getLanguageText("errorLoadingFile"), "Error I/O", JOptionPane.ERROR_MESSAGE);
      }
   }
}
