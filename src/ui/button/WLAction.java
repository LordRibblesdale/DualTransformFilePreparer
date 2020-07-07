package ui.button;

import tools.Controller;
import wavelet.HaarCodec;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class WLAction extends AbstractAction {
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (Controller.getTabIndex()) {
      case 0 -> HaarCodec.compressFile();
      case 1 -> HaarCodec.decompressFile();
    }
  }
}
