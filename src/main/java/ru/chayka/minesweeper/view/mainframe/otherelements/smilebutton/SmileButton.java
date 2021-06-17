package ru.chayka.minesweeper.view.mainframe.otherelements.smilebutton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.eventsystem.EventSystemLogger;
import ru.chayka.minesweeper.eventsystem.events.model.GameOverEvent;
import ru.chayka.minesweeper.eventsystem.events.model.MinefieldDtoEvent;
import ru.chayka.minesweeper.eventsystem.events.view.UnparameterizedButtonPressedEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.GameOverEventListener;
import ru.chayka.minesweeper.eventsystem.listeners.view.MinefieldDtoEventListener;
import ru.chayka.minesweeper.eventsystem.senders.view.UnparameterizedButtonPressedEventSender;
import ru.chayka.minesweeper.view.UnparameterizedButton;

import javax.swing.*;
import java.awt.*;

public class SmileButton
        implements GameOverEventListener, MinefieldDtoEventListener {
    private static final Logger log = LoggerFactory.getLogger(SmileButton.class.getName());

    private final JButton jButton;

    private final UnparameterizedButtonPressedEventSender unparameterizedButtonPressedEventSender;

    public SmileButton() {
        jButton = new JButton();
        unparameterizedButtonPressedEventSender = new UnparameterizedButtonPressedEventSender();

        jButton.addActionListener(event ->
                unparameterizedButtonPressedEventSender.notifyAllListeners(
                        new UnparameterizedButtonPressedEvent(UnparameterizedButton.SMILE)));
        jButton.addActionListener(event -> setState(SmileButtonState.DEFAULT));

        jButton.setPreferredSize(new Dimension(26, 26));
        jButton.setFocusPainted(false);
        jButton.setBorderPainted(false);

        setState(SmileButtonState.DEFAULT);
        jButton.setPressedIcon(SmileButtonState.PRESSED.icon);
    }

    public JButton getJButton() {
        return jButton;
    }

    public void setState(SmileButtonState state) {
        jButton.setIcon(state.icon);
    }

    public UnparameterizedButtonPressedEventSender getUnparameterizedButtonPressedEventSender() {
        return unparameterizedButtonPressedEventSender;
    }

    @Override
    public void acceptEvent(GameOverEvent event) {
        EventSystemLogger.logEventAccepting(log, this, event);
        if (event.isWon()) {
            setState(SmileButtonState.COOL);
        } else {
            setState(SmileButtonState.DEAD);
        }
    }

    @Override
    public void acceptEvent(MinefieldDtoEvent event) {
        EventSystemLogger.logEventAccepting(log, this, event);
        setState(SmileButtonState.DEFAULT);
    }
}