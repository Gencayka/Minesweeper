package ru.chayka.minesweeper.view.mainframe.otherelements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.eventsystem.MvcEventSystemLogger;
import ru.chayka.minesweeper.eventsystem.events.model.MvcGameTimeDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.MvcGameTimeDtoEventListener;

import javax.swing.*;
import java.awt.*;

public class GameTimer
        implements MvcGameTimeDtoEventListener {
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
    public void acceptEvent(MvcGameTimeDtoEvent event) {
        MvcEventSystemLogger.logEventAccepting(log, this, event);
        jLabel.setText(Integer.toString(event.getGameTime()));
    }
}