package ru.chayka.minesweeper.view.minesweeperFrame.otherElements;

import ru.chayka.minesweeper.observerInterfaces.observers.view.GameTimerObserver;
import ru.chayka.minesweeper.view.compositeClasses.ViewItem;

import javax.swing.*;
import java.awt.*;

public class GameTimerView
        implements GameTimerObserver {
    private final ViewItem viewItem;
    private final JLabel jLabel;

    public GameTimerView() {
        viewItem = new ViewItem(this);
        jLabel = new JLabel();

        jLabel.setFont(new Font("Serif", Font.PLAIN, 26));
        jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    }

    public ViewItem getViewComponent() {
        return viewItem;
    }

    public JLabel getJLabel() {
        return jLabel;
    }

    public void update(int gameTime) {
        jLabel.setText(Integer.toString(gameTime));
    }
}