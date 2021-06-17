package ru.chayka.minesweeper.view.leaderboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.eventsystem.events.view.UnparameterizedButtonPressedEvent;
import ru.chayka.minesweeper.eventsystem.senders.view.UnparameterizedButtonPressedEventSender;
import ru.chayka.minesweeper.view.UnparameterizedButton;

import javax.swing.*;

public class ResetResultsButton {
    private static final Logger log = LoggerFactory.getLogger(ResetResultsButton.class.getName());

    private final JButton jButton;

    private final UnparameterizedButtonPressedEventSender unparameterizedButtonPressedEventSender;

    public ResetResultsButton() {
        jButton = new JButton("Reset results");

        unparameterizedButtonPressedEventSender = new UnparameterizedButtonPressedEventSender();

        jButton.setFocusable(false);
        jButton.addActionListener(event ->
                unparameterizedButtonPressedEventSender.notifyAllListeners(
                        new UnparameterizedButtonPressedEvent(UnparameterizedButton.RESET_HIGH_SCORES)));
    }

    public JButton getJButton() {
        return jButton;
    }

    public UnparameterizedButtonPressedEventSender getUnparameterizedButtonPressedEventSender() {
        return unparameterizedButtonPressedEventSender;
    }
}
