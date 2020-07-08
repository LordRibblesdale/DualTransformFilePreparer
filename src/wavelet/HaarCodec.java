package wavelet;

//import org.tukaani.xz.LZMA2Options;
//import org.tukaani.xz.XZOutputStream;
import tools.Controller;

import java.io.*;

public class HaarCodec {
  private static double DIV_1_SQRT2 = Math.sqrt(2);

  public static void compressFile() {
    if (Controller.getFullName() != null) {
      try {
        BufferedInputStream input = null;
        BufferedOutputStream approxOutput;
        BufferedOutputStream detailOutput;
        BufferedOutputStream floatApproxValueOutput;
        BufferedOutputStream floatDetailValueOutput;
        //private static XZOutputStream approxOutput;
        //private static XZOutputStream detailOutput;

        input = new BufferedInputStream(new FileInputStream(Controller.getFullName()));
        approxOutput = new BufferedOutputStream(new FileOutputStream(Controller.getLocation() + Controller.getFileName() + ".awf"));
        detailOutput = new BufferedOutputStream(new FileOutputStream(Controller.getLocation() + Controller.getFileName() + ".dwf"));
        floatApproxValueOutput = new BufferedOutputStream(new FileOutputStream(Controller.getLocation() + Controller.getFileName() + ".fav"));
        floatDetailValueOutput = new BufferedOutputStream(new FileOutputStream(Controller.getLocation() + Controller.getFileName() + ".fdv"));
        //approxOutput = new XZOutputStream(new BufferedOutputStream(new FileOutputStream(Controller.getFileDirectory() + Controller.getFileName() + ".awf")), new LZMA2Options(LZMA2Options.MODE_NORMAL));
        //detailOutput = new XZOutputStream(new BufferedOutputStream(new FileOutputStream(Controller.getFileDirectory() + Controller.getFileName() + ".dwf")), new LZMA2Options(LZMA2Options.MODE_NORMAL));


        while (input.available() > 0) {
          int f2n = input.read();
          int f2n1 = input.read();

          //TODO check here
          if (f2n1 == -1) {
            f2n1 = 0;
          }

          System.out.println(f2n);
          System.out.println(f2n1);
          System.out.println((f2n + f2n1) *0.5f);
          System.out.println((f2n - f2n1) *0.5f);
          //System.out.println();

          // Save conditions > 127 && < -128
          approxOutput.write((byte) ((f2n + f2n1) *0.5f));
          floatApproxValueOutput.write((f2n + f2n1) % 2 == 0 ? 0 : 1);

          detailOutput.write((byte) (((f2n - f2n1) + 128) *0.5f));
          // TODO demonstrate duplicate here (floatApprox is equal to floatDetail)
          floatDetailValueOutput.write((f2n - f2n1) % 2 == 0 ? 0 : 1);

          // TODO save as [-128,127] to [0, 255]
        }

        System.out.println("Finished");

        input.close();
        approxOutput.close();
        detailOutput.close();
        floatApproxValueOutput.close();
        floatDetailValueOutput.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {

    }
  }

  public static void decompressFile() {
    if (Controller.getFullApproxName() != null && Controller.getFullDetailName() != null) {
      try {
        int lastNullByteCount = 0;

        BufferedInputStream approxInput;
        BufferedInputStream detailInput;
        BufferedInputStream floatApproxValueInput;
        BufferedInputStream floatDetailValueInput;
        BufferedOutputStream output;

        approxInput = new BufferedInputStream(new FileInputStream(Controller.getFullApproxName()));
        detailInput = new BufferedInputStream(new FileInputStream(Controller.getFullDetailName()));
        floatApproxValueInput = new BufferedInputStream(new FileInputStream(Controller.getFloatingFullApproxName()));
        floatDetailValueInput = new BufferedInputStream(new FileInputStream(Controller.getFloatingFullDetailName()));
        output = new BufferedOutputStream(new FileOutputStream(Controller.getApproxLocation() + "test"));

        while (approxInput.available() > 0 && detailInput.available() > 0) {
          int floatApproxValue = floatApproxValueInput.read();
          int floatDetailValue = floatDetailValueInput.read();

          // TODO check if *2 is necessary
          int fn = (int) ((approxInput.read() + 0.5f*floatApproxValue)*2);
          int dn = (int) ((detailInput.read() + 0.5f*floatDetailValue)*2) -128;

          /*
          if (f2n1 == -1) {
            f2n1 = 0;
            ++lastNullByteCount;
          }
           */

          //System.out.println(fn);
          //System.out.println(dn);

          float f2n = (0.5f*(fn + dn));
          float f2n1 = (0.5f*(fn - dn));

          if (f2n < 0) {
            f2n += 256;
          } else if (f2n > 256) {
            f2n -= 256;
          }

          if (f2n1 < 0) {
            f2n1 += 256;
          } else if (f2n1 > 255) {
            f2n1 -= 256;
          }

          System.out.println(f2n);
          System.out.println(f2n1);
          System.out.println();

          output.write((byte) f2n);
          output.write((byte) f2n1);

          //output.write((byte) (f2n - (int) f2n == 0.5f ? f2n +1 : f2n));
          //output.write((byte) (f2n1 - (int) f2n1 == 0.5f ? f2n1 +1 : f2n1));
        }

        System.out.println("Finished");

        approxInput.close();
        detailInput.close();
        floatApproxValueInput.close();
        floatDetailValueInput.close();
        output.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {

    }
  }
}
