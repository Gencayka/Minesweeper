package ru.chayka.minesweeper.model.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.eventsystem.events.model.MvcGameTimeDtoEvent;
import ru.chayka.minesweeper.eventsystem.senders.model.MvcGameTimeDtoEventSender;
import ru.chayka.minesweeper.model.eventsystem.EventSystemLogger;
import ru.chayka.minesweeper.model.eventsystem.events.FirstCellOpeningEvent;
import ru.chayka.minesweeper.model.eventsystem.events.GameOverEvent;
import ru.chayka.minesweeper.model.eventsystem.events.MinefieldDtoEvent;
import ru.chayka.minesweeper.model.eventsystem.listeners.FirstCellOpeningEventListener;
import ru.chayka.minesweeper.model.eventsystem.listeners.GameOverEventListener;
import ru.chayka.minesweeper.model.eventsystem.listeners.MinefieldDtoEventListener;
import ru.chayka.minesweeper.model.settings.Settings;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer
        implements MinefieldDtoEventListener, FirstCellOpeningEventListener, GameOverEventListener {
    private static final Logger log = LoggerFactory.getLogger(GameTimer.class.getName());

    private Timer timer;
    private int gameTime;
    private long initTime;

    private final MvcGameTimeDtoEventSender mvcGameTimeDtoEventSender;

    public GameTimer() {
        gameTime = 0;
        initTime = 0;

        mvcGameTimeDtoEventSender = new MvcGameTimeDtoEventSender();
    }

    public int getGameTime() {
        return gameTime;
    }

    public MvcGameTimeDtoEventSender getMvcGameTimeDtoEventSender() {
        return mvcGameTimeDtoEventSender;
    }

    public void startGameTimer() {
        stopGameTimer();
        clearGameTimer();

        initTime = System.currentTimeMillis();
        timer = new Timer();
        timer.scheduleAtFixedRate(new GameTimerTask(), 0, 1000);
    }

    public void stopGameTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }

    public void clearGameTimer() {
        gameTime = 0;
        mvcGameTimeDtoEventSender.notifyAllListeners(new MvcGameTimeDtoEvent(gameTime));
    }

    @Override
    public void acceptEvent(MinefieldDtoEvent event) {
        EventSystemLogger.logEventAccepting(log, this, event);
        stopGameTimer();
        clearGameTimer();
    }

    @Override
    public void acceptEvent(FirstCellOpeningEvent event) {
        EventSystemLogger.logEventAccepting(log, this, event);
        startGameTimer();
    }

    @Override
    public void acceptEvent(GameOverEvent event) {
        EventSystemLogger.logEventAccepting(log, this, event);
        stopGameTimer();
    }

    private class GameTimerTask extends TimerTask {
        @Override
        public void run() {
            if (gameTime < Settings.getInstance().getGameTimeMax()) {
                //к времени прибаляется 1, т.к. в оригинальном сапере отсчет времени
                // начинался с 1, а не с 0
                gameTime = Math.toIntExact((System.currentTimeMillis() - initTime) / 1000) + 1;
                mvcGameTimeDtoEventSender.notifyAllListeners(new MvcGameTimeDtoEvent(gameTime));
            }
        }
    }
}
