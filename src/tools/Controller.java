package tools;

import ui.FrameWindow;
import update.UpdateRepo;

public class Controller {
  private static boolean isInitialized = false;

  private static final int BASE_RELEASE = 100;
  private static final int INCREMENTAL_RELEASE = 0;
  private static final int RELEASE = BASE_RELEASE + INCREMENTAL_RELEASE;
  private final static String applicationName = "Wavelet File Manager";

  private static Settings settings;
  private static UpdateRepo updates;

  private static FrameWindow frameWindow;

  // TODO optimise double variables usage (example: File()?)
  private static String fullName = null;
  private static String fileName = null;
  private static String location = null;

  private static String fullTransformedName = null;
  private static String fileTransformedName = null;
  private static String transformedLocation = null;

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

  public static int getTabIndex() {
    return frameWindow.getTabIndex();
  }

  public static void setStatusNotification(String text) {
    frameWindow.setTextBottomPanel(text);
  }

  public static void checkUpdates() {
    updates.start();
  }

  // TODO change all names

  public static String getFullName() {
    return fullName;
  }

  public static void setFullName(String fullName) {
    Controller.fullName = fullName;

    frameWindow.setFileText(fullName);
  }

  public static String getFileName() {
    return fileName;
  }

  public static void setFileName(String fileName) {
    Controller.fileName = fileName;
  }

  public static String getLocation() {
    return location;
  }

  public static void setLocation(String location) {
    Controller.location = location;
  }

  public static String getFullTransformedName() {
    return fullTransformedName;
  }

  public static void setFullTransformedName(String fullTransformedName) {
    Controller.fullTransformedName = fullTransformedName;

    frameWindow.setTransformedFileText(fullTransformedName);
  }

  public static String getFileTransformedName() {
    return fileTransformedName;
  }

  public static void setFileTransformedName(String fileTransformedName) {
    Controller.fileTransformedName = fileTransformedName;
  }

  public static String getTransformedLocation() {
    return transformedLocation;
  }

  public static void setTransformedLocation(String transformedLocation) {
    Controller.transformedLocation = transformedLocation;
  }
}
