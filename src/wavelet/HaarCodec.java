package wavelet;

//import org.tukaani.xz.LZMA2Options;
//import org.tukaani.xz.XZOutputStream;
import tools.Controller;

import java.io.*;

public class HaarCodec {
  public static void compressFile() {
    if (Controller.getFullName() != null) {
      try {
        BufferedInputStream input = null;
        BufferedOutputStream approxOutput;
        BufferedOutputStream detailOutput;
        BufferedOutputStream floatingValueOutput;
        //private static XZOutputStream approxOutput;
        //private static XZOutputStream detailOutput;

        input = new BufferedInputStream(new FileInputStream(Controller.getFullName()));
        approxOutput = new BufferedOutputStream(new FileOutputStream(Controller.getLocation() + Controller.getFileName() + ".awf"));
        detailOutput = new BufferedOutputStream(new FileOutputStream(Controller.getLocation() + Controller.getFileName() + ".dwf"));
        floatingValueOutput = new BufferedOutputStream(new FileOutputStream(Controller.getLocation() + Controller.getFileName() + ".fav"));
        //approxOutput = new XZOutputStream(new BufferedOutputStream(new FileOutputStream(Controller.getFileDirectory() + Controller.getFileName() + ".awf")), new LZMA2Options(LZMA2Options.MODE_NORMAL));
        //detailOutput = new XZOutputStream(new BufferedOutputStream(new FileOutputStream(Controller.getFileDirectory() + Controller.getFileName() + ".dwf")), new LZMA2Options(LZMA2Options.MODE_NORMAL));

        int[] indices = new int[8];
        int indexStop = 0;

        while (input.available() > 0) {
          int f2n = input.read();
          int f2n1 = input.read();

          //TODO check here
          if (f2n1 == -1) {
            f2n1 = 0;
          }

          //System.out.println(f2n);
          //System.out.println(f2n1);
          //System.out.println((f2n + f2n1) *0.5f);
          //System.out.println((f2n - f2n1) *0.5f);
          //System.out.println();

          // Save conditions > 127 && < -128
          approxOutput.write((byte) ((f2n + f2n1) *0.5f));
          detailOutput.write((byte) (((f2n - f2n1) + 128) *0.5f));
          // TODO demonstrate duplicate here (floatApprox is equal to floatDetail)

          indices[indexStop++] = (f2n + f2n1) % 2 == 0 ? 0 : 1;

          if (indexStop == 8 || input.available() == 0) {
            int value = 0;

            for (int i = 0; i < indexStop; ++i) {
              int base = 1;

              for (int j = 0; j < i; ++j) {
                base *= 2;
              }

              value += indices[i]*base;
            }

            floatingValueOutput.write(value);

            indexStop = 0;
          }
        }

        System.out.println("Finished");

        input.close();
        approxOutput.close();
        detailOutput.close();
        floatingValueOutput.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {

    }
  }

  public static void decompressFile() {
    if (Controller.getFullApproxName() != null && Controller.getFullDetailName() != null) {
      try {
        BufferedInputStream approxInput;
        BufferedInputStream detailInput;
        BufferedInputStream floatingValueInput;
        BufferedOutputStream output;

        approxInput = new BufferedInputStream(new FileInputStream(Controller.getFullApproxName()));
        detailInput = new BufferedInputStream(new FileInputStream(Controller.getFullDetailName()));
        floatingValueInput = new BufferedInputStream(new FileInputStream(Controller.getFloatingAbsolutePath()));
        output = new BufferedOutputStream(new FileOutputStream(Controller.getApproxLocation() + "test"));

        int indexStop = 0;
        int floatingValue = 0;
        boolean isFirstCycle = true;

        while (approxInput.available() > 0 && detailInput.available() > 0) {
          if (indexStop == 8 || isFirstCycle) {
            floatingValue = floatingValueInput.read();

            isFirstCycle = false;
            indexStop = 0;
          }

          // TODO check if *2 is necessary
          float tmp = 0.5f * ((Integer.parseUnsignedInt(Integer.toBinaryString(floatingValue), 2) >> indexStop++) & 1);
          int fn = (int) ((approxInput.read() + tmp)*2);
          int dn = (int) ((detailInput.read() + tmp)*2) -128;

          //System.out.println(fn);
          //System.out.println(dn);

          float f2n = (0.5f*(fn + dn));
          float f2n1 = (0.5f*(fn - dn));

          if (f2n < 0) {
            f2n += 256;
          } else if (f2n > 255) {
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
        floatingValueInput.close();
        output.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {

    }
  }
}
