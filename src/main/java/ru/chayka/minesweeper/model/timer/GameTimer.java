package ru.chayka.minesweeper.model.timer;

import ru.chayka.minesweeper.eventsystem.events.model.GameTimeDtoEvent;
import ru.chayka.minesweeper.eventsystem.senders.model.GameTimeDtoEventSender;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    private Timer timer;
    private int gameTime;
    private long initTime;
    public static final int GAME_TIME_MAX = 999;

    private final GameTimeDtoEventSender gameTimeDtoEventSender;

    public GameTimer() {
        gameTime = 0;
        initTime = 0;

        gameTimeDtoEventSender = new GameTimeDtoEventSender();
    }

    public int getGameTime() {
        return gameTime;
    }

    public GameTimeDtoEventSender getGameTimeDtoEventSender() {
        return gameTimeDtoEventSender;
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
        gameTimeDtoEventSender.notifyAllListeners(new GameTimeDtoEvent(gameTime));
    }

    private class GameTimerTask extends TimerTask {
        @Override
        public void run() {
            if (gameTime < GAME_TIME_MAX) {
                //к времени прибаляется 1, т.к. в оригинальном сапере отсчет времени
                // начинался с 1, а не с 0
                gameTime = Math.toIntExact((System.currentTimeMillis() - initTime) / 1000) + 1;
                gameTimeDtoEventSender.notifyAllListeners(new GameTimeDtoEvent(gameTime));
            }
        }
    }
}
