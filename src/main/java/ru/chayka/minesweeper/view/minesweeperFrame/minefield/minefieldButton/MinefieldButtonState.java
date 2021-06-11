package ru.chayka.minesweeper.view.minesweeperFrame.minefield.minefieldButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

public enum MinefieldButtonState {
    EMPTY("empty.png"),
    ONE("number1.png"),
    TWO("number2.png"),
    THREE("number3.png"),
    FOUR("number4.png"),
    FIVE("number5.png"),
    SIX("number6.png"),
    SEVEN("number7.png"),
    EIGHT("number8.png"),
    CLOSED("closed.png", "empty.png"),
    FLAG("flag.png"),
    MINE("mine.png"),
    WRONGLY_FLAGGED_MINE("wrongly_flagged_mine.png"),
    MINE_OPENED_WITH_WRONG_MOVE("opened_mine.png");

    private final Logger log = LoggerFactory.getLogger(MinefieldButtonState.class.getName());
    private static final Logger staticLog = LoggerFactory.getLogger(MinefieldButtonState.class.getName());

    final ImageIcon icon;
    final ImageIcon pressedIcon;

    MinefieldButtonState(String imageName) throws ExceptionInInitializerError {
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/Images/" + imageName));
        if (icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            log.error("Couldn't upload image " + imageName);
            throw new ExceptionInInitializerError();
        }

        this.icon = icon;
        pressedIcon = icon;
    }

    MinefieldButtonState(String imageName, String pressedImageName) throws ExceptionInInitializerError {
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/Images/" + imageName));
        ImageIcon pressedIcon = new ImageIcon(this.getClass().getResource("/Images/" + pressedImageName));

        if (icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            log.error("Couldn't upload image " + imageName);
            throw new ExceptionInInitializerError();
        } else if (pressedIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            log.error("Couldn't upload image " + pressedImageName);
            throw new ExceptionInInitializerError();
        }

        this.icon = icon;
        this.pressedIcon = pressedIcon;
    }

    public static MinefieldButtonState intToMinefieldButtonState(int number) {
        switch (number) {
            case 0:
                return MinefieldButtonState.EMPTY;
            case 1:
                return MinefieldButtonState.ONE;
            case 2:
                return MinefieldButtonState.TWO;
            case 3:
                return MinefieldButtonState.THREE;
            case 4:
                return MinefieldButtonState.FOUR;
            case 5:
                return MinefieldButtonState.FIVE;
            case 6:
                return MinefieldButtonState.SIX;
            case 7:
                return MinefieldButtonState.SEVEN;
            case 8:
                return MinefieldButtonState.EIGHT;
            default:
                staticLog.warn("Attempt to get invalid minefield button state");
                return MinefieldButtonState.EMPTY;
        }
    }

    public boolean checkIfStateIsNumbered() {
        return this == ONE ||
                this == TWO ||
                this == THREE ||
                this == FOUR ||
                this == FIVE ||
                this == SIX ||
                this == SEVEN ||
                this == EIGHT;
    }
}
