package tools;

import ui.Path;

import javax.swing.*;
import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class Settings implements Serializable, Path {
    private static final long serialVersionUID = 10L;

    private boolean useLookAndFeel;
    private Locale language;
    private transient ResourceBundle resourceBundle;

    public Settings() {
       Settings tmp;
       try {
          ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(setPath)));
          Object object = in.readObject();
          in.close();
          if (object instanceof Settings) {
             tmp = (Settings) object;

             useLookAndFeel = tmp.isUsingLookAndFeel();
             language = tmp.getLanguage();
             resourceBundle = ResourceBundle.getBundle("MessagesBundle", language);
          }
       } catch (ClassNotFoundException e) {
          e.printStackTrace();
       } catch (IOException e) {
          language = Locale.UK;
          useLookAndFeel = true;
          resourceBundle = ResourceBundle.getBundle("MessagesBundle", language);
          try {
             ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(setPath)));
             out.writeObject(Settings.this);
             out.close();
          } catch (IOException e1) {
             JOptionPane.showMessageDialog(null, "Error saving settings file", "Error I/O", JOptionPane.ERROR_MESSAGE);
          }
       }
   }

   private Locale getLanguage() {
       return language;
   }

   public void setLanguage(Locale language) {
      this.language = language;
   }

   public boolean isUsingLookAndFeel() {
      return useLookAndFeel;
   }

   public void setUseLookAndFeel(boolean useLookAndFeel) {
      this.useLookAndFeel = useLookAndFeel;
   }

   public ResourceBundle getResourceBundle() {
      return resourceBundle;
   }

   public void setResourceBundle(ResourceBundle resourceBundle) {
      this.resourceBundle = resourceBundle;
   }
}
