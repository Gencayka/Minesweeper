package ru.chayka.minesweeper.view.mainframe.otherelements;

import ru.chayka.minesweeper.view.mainframe.otherelements.smilebutton.SmileButton;

import javax.swing.*;
import java.awt.*;

public class OtherElementsPanel {
    private final JPanel jPanel;

    private final FlagsCounter flagsCounter;
    private final SmileButton smileButton;
    private final GameTimer gameTimer;

    public OtherElementsPanel() {
        jPanel = new JPanel();

        jPanel.setLayout(new GridLayout());

        flagsCounter = new FlagsCounter();
        jPanel.add(flagsCounter.getJLabel());

        smileButton = new SmileButton();

        JPanel smileButtonPanel = new JPanel();
        smileButtonPanel.add(smileButton.getJButton());
        jPanel.add(smileButtonPanel);

        gameTimer = new GameTimer();
        jPanel.add(gameTimer.getJLabel());
    }

    public JPanel getJPanel() {
        return jPanel;
    }

    public FlagsCounter getFlagsCounter() {
        return flagsCounter;
    }

    public SmileButton getSmileButton() {
        return smileButton;
    }

    public GameTimer getGameTimer() {
        return gameTimer;
    }
}