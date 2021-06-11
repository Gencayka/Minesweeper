package ru.chayka.minesweeper.view.minesweeperFrame.otherElements;

import ru.chayka.minesweeper.observerInterfaces.observers.view.FlagCounterObserver;
import ru.chayka.minesweeper.view.compositeClasses.ViewItem;

import javax.swing.*;
import java.awt.*;

public class FlagsCounter
        implements FlagCounterObserver {
    private final ViewItem viewItem;
    private final JLabel jLabel;

    public FlagsCounter() {
        viewItem = new ViewItem(this);
        jLabel = new JLabel();

        jLabel.setFont(new Font("Serif", Font.PLAIN, 26));
    }

    public ViewItem getViewComponent() {
        return viewItem;
    }

    public JLabel getJLabel() {
        return jLabel;
    }

    public void update(int numOfRemainingMines) {
        jLabel.setText(Integer.toString(numOfRemainingMines));
    }
}
