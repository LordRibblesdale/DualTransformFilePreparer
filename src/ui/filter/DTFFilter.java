package ui.filter;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class DTFFilter extends FileFilter {
   public static final String ext = "dtf";

   @Override
   public boolean accept(File f) {
      String s = f.getName();

      int i = s.lastIndexOf(".");

      if (i != -1) {
         String e = s.substring(i+1);

         return e.equalsIgnoreCase(ext);
      }

      return f.isDirectory();
   }

   @Override
   public String getDescription() {
      return "*." + ext;
   }
}
