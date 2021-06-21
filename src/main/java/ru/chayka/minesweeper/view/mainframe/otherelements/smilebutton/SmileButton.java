package ru.chayka.minesweeper.view.mainframe.otherelements.smilebutton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.eventsystem.MvcEventSystemLogger;
import ru.chayka.minesweeper.eventsystem.events.model.MvcGameOverEvent;
import ru.chayka.minesweeper.eventsystem.events.model.MvcMinefieldDtoEvent;
import ru.chayka.minesweeper.eventsystem.events.view.MvcUnparameterizedButtonPressedEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.MvcGameOverEventListener;
import ru.chayka.minesweeper.eventsystem.listeners.view.MvcMinefieldDtoEventListener;
import ru.chayka.minesweeper.eventsystem.senders.view.MvcUnparameterizedButtonPressedEventSender;
import ru.chayka.minesweeper.view.UnparameterizedButton;

import javax.swing.*;
import java.awt.*;

public class SmileButton
        implements MvcGameOverEventListener, MvcMinefieldDtoEventListener {
    private static final Logger log = LoggerFactory.getLogger(SmileButton.class.getName());

    private final JButton jButton;

    private final MvcUnparameterizedButtonPressedEventSender mvcUnparameterizedButtonPressedEventSender;

    public SmileButton() {
        jButton = new JButton();
        mvcUnparameterizedButtonPressedEventSender = new MvcUnparameterizedButtonPressedEventSender();

        jButton.addActionListener(event ->
                mvcUnparameterizedButtonPressedEventSender.notifyAllListeners(
                        new MvcUnparameterizedButtonPressedEvent(UnparameterizedButton.SMILE)));
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

    public MvcUnparameterizedButtonPressedEventSender getMvcUnparameterizedButtonPressedEventSender() {
        return mvcUnparameterizedButtonPressedEventSender;
    }

    public void setState(SmileButtonState state) {
        jButton.setIcon(state.icon);
    }

    @Override
    public void acceptEvent(MvcGameOverEvent event) {
        MvcEventSystemLogger.logEventAccepting(log, this, event);
        if (event.isWon()) {
            setState(SmileButtonState.COOL);
        } else {
            setState(SmileButtonState.DEAD);
        }
    }

    @Override
    public void acceptEvent(MvcMinefieldDtoEvent event) {
        MvcEventSystemLogger.logEventAccepting(log, this, event);
        setState(SmileButtonState.DEFAULT);
    }
}