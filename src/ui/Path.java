package ui;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface Path {
   String base = System.getProperty("user.dir") + File.separator;
   String defaultGameName = base + (new SimpleDateFormat("yyyy-MM-dd HH.mm")).format(new Date()) + ".bin";
   String logPath = base + "log.txt";
   String setPath = base + "settings.set";
}
