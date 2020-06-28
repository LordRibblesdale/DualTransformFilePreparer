package tools;

import ui.FrameWindow;
import update.UpdateRepo;

public class Controller {
  private static boolean isInitialized = false;

  private static final int BASE_RELEASE = 100;
  private static final int INCREMENTAL_RELEASE = 0;
  private static final int RELEASE = BASE_RELEASE + INCREMENTAL_RELEASE;
  private static String applicationName = "Wavelet File Manager";

  private static Settings settings;
  private static UpdateRepo updates;

  private static FrameWindow frameWindow;


  public static void initialize() {
    if (!isInitialized) {
      settings = new Settings();

      updates = new UpdateRepo(RELEASE);
      updates.start();

      frameWindow = new FrameWindow(applicationName);

      isInitialized = true;
    }
  }

  public static String getLanguageText(String keyword) {
    return settings.getResourceBundle().getString(keyword);
  }

  public static boolean isLookAndFeelEnabled() {
    return settings.isUsingLookAndFeel();
  }

  public static FrameWindow getFrameWindow() {
    // TODO find another way to give component
    return frameWindow;
  }

  public static void setStatusNotification(String text) {
    frameWindow.setTextBottomPanel(text);
  }

  public static void checkUpdates() {
    updates.start();
  }
}
