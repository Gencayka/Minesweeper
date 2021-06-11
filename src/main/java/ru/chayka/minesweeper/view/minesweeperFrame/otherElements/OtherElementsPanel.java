package ru.chayka.minesweeper.view.minesweeperFrame.otherElements;

import ru.chayka.minesweeper.view.compositeClasses.ViewComposite;
import ru.chayka.minesweeper.view.minesweeperFrame.otherElements.smileButton.SmileButton;

import javax.swing.*;
import java.awt.*;

public class OtherElementsPanel {
    private final ViewComposite viewComposite;
    private final JPanel jPanel;

    public OtherElementsPanel() {
        viewComposite = new ViewComposite(this);
        jPanel = new JPanel();

        jPanel.setLayout(new GridLayout());

        FlagsCounter flagsCounter = new FlagsCounter();
        jPanel.add(flagsCounter.getJLabel());
        viewComposite.addViewComponent(flagsCounter.getViewComponent());

        SmileButton smileButton = new SmileButton();
        viewComposite.addViewComponent(smileButton.getViewComponent());

        JPanel smileButtonPanel = new JPanel();
        smileButtonPanel.add(smileButton.getJButton());
        jPanel.add(smileButtonPanel);

        GameTimerView gameTimerView = new GameTimerView();
        jPanel.add(gameTimerView.getJLabel());
        viewComposite.addViewComponent(gameTimerView.getViewComponent());
    }

    public ViewComposite getViewComponent() {
        return viewComposite;
    }

    public JPanel getJPanel() {
        return jPanel;
    }
}