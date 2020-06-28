package update;

import tools.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UpdateButton extends AbstractAction {
    public UpdateButton() {
        putValue(Action.NAME, Controller.getLanguageText("checkForUpdates"));
        putValue(Action.LARGE_ICON_KEY, new ImageIcon(UpdateButton.class.getResource("Update.png")));
        putValue(Action.SMALL_ICON, new ImageIcon(UpdateButton.class.getResource("Update.png")));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Controller.setStatusNotification(Controller.getLanguageText("checkingUpdates"));

        Controller.checkUpdates();
    }
}
