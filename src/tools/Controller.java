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

  private static String floatingFullApproxName = null;
  private static String floatingFileApproxName = null;
  private static String floatingApproxLocation = null;

  private static String floatingFullDetailName = null;
  private static String floatingFileDetailName = null;
  private static String floatingDetailLocation = null;

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

  public static String getFloatingFullApproxName() {
    return floatingFullApproxName;
  }

  public static void setFloatingFullApproxName(String floatingFullApproxName) {
    Controller.floatingFullApproxName = floatingFullApproxName;

    frameWindow.setFloatingApproxFileText(floatingFullApproxName);
  }

  public static String getFloatingFileApproxName() {
    return floatingFileApproxName;
  }

  public static void setFloatingFileApproxName(String floatingFileApproxName) {
    Controller.floatingFileApproxName = floatingFileApproxName;
  }

  public static String getFloatingApproxLocation() {
    return floatingApproxLocation;
  }

  public static void setFloatingApproxLocation(String floatingApproxLocation) {
    Controller.floatingApproxLocation = floatingApproxLocation;
  }

  public static String getFloatingFullDetailName() {
    return floatingFullDetailName;
  }

  public static void setFloatingFullDetailName(String floatingFullDetailName) {
    Controller.floatingFullDetailName = floatingFullDetailName;

    frameWindow.setFloatingDetailFileText(floatingFullDetailName);
  }

  public static String getFloatingFileDetailName() {
    return floatingFileDetailName;
  }

  public static void setFloatingFileDetailName(String floatingFileDetailName) {
    Controller.floatingFileDetailName = floatingFileDetailName;
  }

  public static String getFloatingDetailLocation() {
    return floatingDetailLocation;
  }

  public static void setFloatingDetailLocation(String floatingDetailLocation) {
    Controller.floatingDetailLocation = floatingDetailLocation;
  }
}
