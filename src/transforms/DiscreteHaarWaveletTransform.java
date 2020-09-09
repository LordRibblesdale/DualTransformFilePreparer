package transforms;

import tools.Controller;
import ui.filter.DTFFilter;

import java.io.*;

public class DiscreteHaarWaveletTransform {
  public static void compressFile() {
    /*
    if (Controller.getFullName() != null) {
      try {
        BufferedInputStream input = new BufferedInputStream(new FileInputStream(Controller.getFullName()));
        BufferedOutputStream transformedOutput = new BufferedOutputStream(new FileOutputStream(Controller.getFullName() + "_transformed." + DTFFilter.ext));

        while (input.available() > 0) {
          // TODO check if BigDecimal is necessary
          double div1N = 1/4.0;

          double transformedByte;
          long doubleToLong;

          int f2n = input.read();
          int f2n1 = input.read();

          //TODO check here
          if (f2n1 == -1) {
            f2n1 = 0;
          }

          approxOutput.write((byte) ((f2n + f2n1) *0.5f));
          detailOutput.write((byte) (((f2n - f2n1) + 128) *0.5f));

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

          // TODO optimize from O(n^2)
          for (int k = 0; k < inputBytes.length; ++k) {
            transformedByte = 0;

            for (int n = 0; n < inputBytes.length; ++n) {
              transformedByte += inputBytes[n] * Math.cos(div1N * Math.PI * (k + 0.5) * (n + 0.5));
            }

            doubleToLong = Double.doubleToLongBits(transformedByte);

            for (int i = 0; i < outputBytes.length; ++i) {
              outputBytes[i] = (byte)((doubleToLong >> ((7 - i) * 8)) & 0xff);
            }

            transformedOutput.write(outputBytes);
          }

        System.out.println("Finished");

        input.close();
        transformedOutput.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public static void decompressFile() {
    /*
    if (Controller.getFullTransformedName() != null) {
      try {
        BufferedInputStream transformedInput = new BufferedInputStream(new FileInputStream(Controller.getFullTransformedName()));

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

          System.out.println(f2n);
          System.out.println(f2n1);
          System.out.println();

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

          //System.out.println(f2n);
          //System.out.println(f2n1);
          //System.out.println();

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
     */
  }
}
