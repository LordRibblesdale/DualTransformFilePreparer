package ui;

import tools.Controller;
import ui.filter.AWFFilter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class OpenFile extends AbstractAction implements ui.Path {
   private ObjectInputStream input;
   private ProgressMonitor pm;
   private String path;

   public OpenFile() {

      putValue(Action.NAME, Controller.getLanguageText("load"));
      //putValue(Action.LARGE_ICON_KEY, new ImageIcon(OpenFile.class.getResource("Open.png")));
      //putValue(Action.SMALL_ICON, new ImageIcon(OpenFile.class.getResource("Open.png")));
      putValue(Action.SHORT_DESCRIPTION, Controller.getLanguageText("load"));
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      try {
         input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(this.path)));
         Object tmp = input.readObject();
         input.close();

      } catch (Exception e2) {
         JOptionPane.showMessageDialog(
             Controller.getFrameWindow(),
             Controller.getLanguageText("errorReadingFile"),
             "Error I/O 02_OpenFile" + e2.getStackTrace()[0].getLineNumber(),
             JOptionPane.ERROR_MESSAGE);

         e2.printStackTrace();
      }
   }

   private String getFile() {
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setAcceptAllFileFilterUsed(false);
      fileChooser.addChoosableFileFilter(new AWFFilter());

      int res = fileChooser.showOpenDialog(Controller.getFrameWindow());

      if (res == JFileChooser.APPROVE_OPTION) {
         return fileChooser.getSelectedFile().getPath();
      } else if (res != JFileChooser.CANCEL_OPTION) {
         JOptionPane.showMessageDialog(Controller.getFrameWindow(), Controller.getLanguageText("errorLoadingFile"), "Error I/O", JOptionPane.ERROR_MESSAGE);
      }

      return null;
   }
}
