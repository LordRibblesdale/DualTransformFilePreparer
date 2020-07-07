package ui;

import tools.Controller;
import ui.filter.AWFFilter;
import ui.filter.DWFFilter;

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

         if (filter instanceof AWFFilter awfFilter) {
            fileChooser.addChoosableFileFilter(awfFilter);
         } else if (filter instanceof DWFFilter dwfFilter) {
            fileChooser.addChoosableFileFilter(dwfFilter);
         }
      } else {
         fileChooser.setAcceptAllFileFilterUsed(false);
         fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
      }

      int res = fileChooser.showOpenDialog(Controller.getFrameWindow());

      if (res == JFileChooser.APPROVE_OPTION) {

         if (filter != null) {
            if (filter instanceof AWFFilter) {
               Controller.setFullApproxName(fileChooser.getSelectedFile().getPath());
               Controller.setApproxLocation(fileChooser.getSelectedFile().getParent() + File.separator);
               Controller.setFileName(fileChooser.getSelectedFile().getName());
            } else if (filter instanceof DWFFilter) {
               Controller.setFullDetailName(fileChooser.getSelectedFile().getPath());
               Controller.setDetailLocation(fileChooser.getSelectedFile().getParent() + File.separator);
               Controller.setFileDetailName(fileChooser.getSelectedFile().getName());
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
