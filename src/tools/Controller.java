package tools;

import ui.FrameWindow;

public class Controller {
  private static boolean isInitialized = false;

  private static final int BASE_RELEASE = 1;
  private static final int INCREMENTAL_RELEASE = 0;
  private static final int RELEASE = BASE_RELEASE + INCREMENTAL_RELEASE;

  private static Settings settings;

  private static FrameWindow frameWindow;


  public static void initialize() {
    if (!isInitialized) {
      settings = new Settings();

      frameWindow = new FrameWindow();
    }
  }

  public static String getLanguageText(String keyword) {
    return settings.getResourceBundle().getString(keyword);
  }

  public static FrameWindow getFrameWindow() {
    // TODO find another way to give component
    return frameWindow;
  }

  public static void setStatusNotification(String text) {
    frameWindow.setTextBottomPanel(text);
  }
}
