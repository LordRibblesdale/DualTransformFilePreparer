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

  private static String fullName = null;
  private static String fileName = null;
  private static String location = null;

  private static String fullApproxName = null;
  private static String fileApproxName = null;
  private static String approxLocation = null;

  private static String fullDetailName = null;
  private static String fileDetailName = null;
  private static String detailLocation = null;

  private static String floatingAbsolutePath = null;
  private static String floatingValuesFileName = null;
  private static String floatingValuesDirectory = null;

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

  public static String getFullApproxName() {
    return fullApproxName;
  }

  public static void setFullApproxName(String fullApproxName) {
    Controller.fullApproxName = fullApproxName;

    frameWindow.setApproxFileText(fullApproxName);
  }

  public static String getFileApproxName() {
    return fileApproxName;
  }

  public static void setFileApproxName(String fileApproxName) {
    Controller.fileApproxName = fileApproxName;
  }

  public static String getApproxLocation() {
    return approxLocation;
  }

  public static void setApproxLocation(String approxLocation) {
    Controller.approxLocation = approxLocation;
  }

  public static String getFullDetailName() {
    return fullDetailName;
  }

  public static void setFullDetailName(String fullDetailName) {
    Controller.fullDetailName = fullDetailName;

    frameWindow.setDetailFileText(fullDetailName);
  }

  public static String getFileDetailName() {
    return fileDetailName;
  }

  public static void setFileDetailName(String fileDetailName) {
    Controller.fileDetailName = fileDetailName;
  }

  public static String getDetailLocation() {
    return detailLocation;
  }

  public static void setDetailLocation(String detailLocation) {
    Controller.detailLocation = detailLocation;
  }

  public static String getFloatingAbsolutePath() {
    return floatingAbsolutePath;
  }

  public static void setFloatingAbsolutePath(String floatingAbsolutePath) {
    Controller.floatingAbsolutePath = floatingAbsolutePath;

    frameWindow.setFloatingPathToText(floatingAbsolutePath);
  }

  public static String getFloatingValuesFileName() {
    return floatingValuesFileName;
  }

  public static void setFloatingValuesFileName(String floatingValuesFileName) {
    Controller.floatingValuesFileName = floatingValuesFileName;
  }

  public static String getFloatingValuesDirectory() {
    return floatingValuesDirectory;
  }

  public static void setFloatingValuesDirectory(String floatingValuesDirectory) {
    Controller.floatingValuesDirectory = floatingValuesDirectory;
  }
}
