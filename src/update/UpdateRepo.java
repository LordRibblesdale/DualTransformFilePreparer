package update;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import tools.Controller;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.*;
import java.nio.file.Files;
import java.util.ArrayList;

import static update.Status.CANCELLED;
import static update.Status.COMPLETED;
import static update.Status.DOWNLOADING;

public class UpdateRepo {
   private Timer t = null;

   public UpdateRepo(int current) {
      // TODO fix here
      String url = "https://api.github.com/repos/lordribblesdale/WaveletFileCompression/releases/latest";
      String downloadUrl = "https://github.com/lordribblesdale/WaveletFileCompression/releases/latest";
      String json;
      String jsonVersion;
      String jsonBody;

      // from StackOverflow
      CloseableHttpClient httpClient = HttpClientBuilder.create().build();
      HttpGet request = new HttpGet(url);
      request.addHeader("content-type", "application/json");
      try {
         HttpResponse result = httpClient.execute(request);
         json = EntityUtils.toString(result.getEntity(), "UTF-8");
         jsonVersion = json;
         jsonVersion = json.substring(jsonVersion.indexOf("tag_name\":\"") + 11);
         jsonVersion = jsonVersion.substring(0 , jsonVersion.indexOf("\",\""));

         jsonBody = json;
         jsonBody = json.substring(jsonBody.indexOf("\"body\":\"") +8);
         jsonBody = jsonBody.substring(0, jsonBody.indexOf("\"") -4);

         ArrayList<String> replace = new ArrayList<>();

         int index;
         while (jsonBody.contains("\\r\\n")) {
            index = jsonBody.indexOf("\\r\\n");
            replace.add(jsonBody.substring(0, index));
            replace.add("\n");

            jsonBody = jsonBody.substring(index+4);
         }

         try {
            if (Integer.parseInt(jsonVersion) > current) {
               StringBuilder update = new StringBuilder()
                   .append(Controller.getLanguageText("newUpdate"))
                   .append("\n")
                   .append(Controller.getLanguageText("newVersion"))
                   .append(" ")
                   .append(jsonVersion)
                   .append("\n");

               for (String s : replace) {
                  update.append(s);
               }

               update.append(jsonBody)
                   .append("\n")
                   .append(Controller.getLanguageText("askDownloadUpdate"));

               Object[] choice = {
                       Controller.getLanguageText("downloadNow"),
                       Controller.getLanguageText("openLink"),
                       Controller.getLanguageText("doNothing")
               };

               int option = JOptionPane.showOptionDialog(Controller.getFrameWindow(), update.toString(),
                       Controller.getLanguageText("availableUpdate"),
                       JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, choice, choice[0]);

               if (option != -1) {
                  if (choice[option] == choice[0]) {
                     downloadUpdate(json, jsonVersion);
                  } else if (choice[option] == choice[1]) {
                     try {
                        Desktop.getDesktop().browse(new URI(downloadUrl));
                     } catch (IOException | URISyntaxException e1) {
                        e1.printStackTrace();
                     }
                  }
               }
            }

            Controller.setStatusNotification(Controller.getLanguageText("updateChecked"));
         } catch (MalformedURLException | NumberFormatException ex) {
            Controller.setStatusNotification(Controller.getLanguageText("errorReadingUpdate"));
         }
      } catch (IOException e) {
         Controller.setStatusNotification(Controller.getLanguageText("errorReadingUpdate"));

         if (e instanceof UnknownHostException) {
            Controller.setStatusNotification(Controller.getLanguageText("checkConnection"));
         }
      }
   }

   private void downloadUpdate(String json, String jsonVersion) throws MalformedURLException {
      json = json.substring(json.lastIndexOf("https://github.com/LordRibblesdale/CotecchioEditor/releases/download/"));
      json = json.substring(0, json.indexOf("\""));

      ProgressRenderer bar = new ProgressRenderer(Controller.getFrameWindow(),
          Controller.getLanguageText("downloadStatus"),
          json);

      Download update = new Download(new URL(json), jsonVersion, true);

      final float[] oldDownload = {0};
      final float[] newDownload = {0};

      t = new Timer(250, ActionListener_ActionPerformed -> {
         String statusString = switch (update.getStatus()) {
            case DOWNLOADING -> Controller.getLanguageText("downloadingStatus");
            case COMPLETED -> Controller.getLanguageText("completedStatus");
            default -> "ERROR";
         };

         newDownload[0] = ((float) update.getDownloaded() / 1000000);
         bar.setNote(statusString + " - " + ((newDownload[0] + "MB") + " - " + (((newDownload[0] - oldDownload[0]) * 1000 / 30 + "KB/s"))));

         oldDownload[0] = newDownload[0];

         bar.setProgress((int) update.getProgress());

         if (update.getStatus() == COMPLETED) {
            bar.close();
            update.stop();

            if (t != null) {
               t.stop();
            }

            File downloaded = new File(update.getName());
            String newRename = downloaded.getName().substring(0, downloaded.getName().length()-4);
            downloaded.renameTo(new File(newRename)); //TODO Fix here

            try {
               Files.copy((new File("settings.set")).toPath(), new FileOutputStream((new File("settings.set")).getName() + ".old." + jsonVersion));
            } catch (IOException e1) {
               e1.printStackTrace();
            }


            int c = JOptionPane.showConfirmDialog(Controller.getFrameWindow(), Controller.getLanguageText("updateRestart"),
                    Controller.getLanguageText("updateDownloaded"),
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

            if (c == JOptionPane.OK_OPTION) {
               try {
                  Runtime.getRuntime().exec("java -jar " + newRename);
                  System.exit(0);
               } catch (IOException e1) {
                  e1.printStackTrace();
               }
            }
         } else if (update.getStatus() == CANCELLED || bar.isCanceled()) {
            if (t != null) {
               t.stop();
               update.stop();
            }

            try {
               System.out.println(update.getName());

               synchronized (UpdateRepo.this) {
                  UpdateRepo.this.wait(1000);
               }

               Files.delete((new File(update.getName())).toPath());
            } catch (IOException | InterruptedException e1) {
               e1.printStackTrace();
            }
         }
      });

      t.start();
   }
}
