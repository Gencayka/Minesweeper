package ru.chayka.minesweeper.view.mainframe.minefield.minefieldbutton;

import javax.swing.*;
import java.awt.*;

public class MinefieldButton
        extends JButton {
    private MinefieldButtonState state;
    private final int row;
    private final int column;
    private final static int SIZE = 16;

    public MinefieldButton(int row, int column) {
        setState(MinefieldButtonState.CLOSED);
        this.row = row;
        this.column = column;

        setPreferredSize(new Dimension(SIZE, SIZE));
        setRolloverEnabled(false);
        setBorderPainted(false);
    }

    public void setState(MinefieldButtonState state) {
        this.state = state;
        setIcon(state.icon);
        setPressedIcon(state.pressedIcon);
    }

    public MinefieldButtonState getState() {
        return state;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void makeVisuallyDisabled() {
        setPressedIcon(state.icon);
    }
}
