package ru.chayka.minesweeper.view.minesweeperFrame.otherElements.smileButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

public enum SmileButtonState {
    DEFAULT("smile.png"),
    PRESSED("smile_pressed.png"),
    DEAD("smile_dead.png"),
    COOL("smile_with_glasses.png");

    private final Logger log = LoggerFactory.getLogger(SmileButtonState.class.getName());

    final ImageIcon icon;

    SmileButtonState(String imageName) {
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/Images/" + imageName));
        if (icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            log.error("Couldn't upload image " + imageName);
            throw new ExceptionInInitializerError();
        }

        this.icon = icon;
    }
}
