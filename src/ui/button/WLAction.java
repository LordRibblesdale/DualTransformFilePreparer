package ui.button;

import tools.Controller;
import transforms.DiscreteHaarWaveletTransform;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class WLAction extends AbstractAction {
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (Controller.getTabIndex()) {
      case 0 -> DiscreteHaarWaveletTransform.compressFile();
      //case 1 -> DiscreteHaarWaveletTransform.decompressFile();
    }
  }
}
