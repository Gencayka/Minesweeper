package ru.chayka.minesweeper.view.mainframe.otherelements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.eventsystem.EventSystemLogger;
import ru.chayka.minesweeper.eventsystem.events.model.GameTimeDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.GameTimeDtoEventListener;

import javax.swing.*;
import java.awt.*;

public class GameTimer
        implements GameTimeDtoEventListener {
    private static final Logger log = LoggerFactory.getLogger(GameTimer.class.getName());

    private final JLabel jLabel;

    public GameTimer() {
        jLabel = new JLabel();

        jLabel.setFont(new Font("Serif", Font.PLAIN, 26));
        jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    }

    public JLabel getJLabel() {
        return jLabel;
    }

    @Override
    public void acceptEvent(GameTimeDtoEvent event) {
        EventSystemLogger.logEventAccepting(log, this, event);
        jLabel.setText(Integer.toString(event.getGameTime()));
    }
}