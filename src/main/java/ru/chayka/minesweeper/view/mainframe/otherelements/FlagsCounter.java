package ru.chayka.minesweeper.view.mainframe.otherelements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.eventsystem.EventSystemLogger;
import ru.chayka.minesweeper.eventsystem.events.model.FlagCounterDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.FlagCounterDtoEventListener;

import javax.swing.*;
import java.awt.*;

public class FlagsCounter
        implements FlagCounterDtoEventListener {
    private static final Logger log = LoggerFactory.getLogger(FlagsCounter.class.getName());

    private final JLabel jLabel;

    public FlagsCounter() {
        jLabel = new JLabel();

        jLabel.setFont(new Font("Serif", Font.PLAIN, 26));
    }

    public JLabel getJLabel() {
        return jLabel;
    }

    @Override
    public void acceptEvent(FlagCounterDtoEvent event) {
        EventSystemLogger.logEventAccepting(log, this, event);
        jLabel.setText(Integer.toString(event.getNumOfRemainingMines()));
    }
}
