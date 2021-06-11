package ru.chayka.minesweeper.model.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.model.MVCLogger;
import ru.chayka.minesweeper.observerInterfaces.observables.model.GameTimerObservable;
import ru.chayka.minesweeper.observerInterfaces.observers.view.GameTimerObserver;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameTimer
        implements GameTimerObservable {
    private static final Logger log = LoggerFactory.getLogger(GameTimer.class.getName());

    private final ArrayList<GameTimerObserver> observers;

    private Timer timer;
    private int gameTime;
    private long initTime;
    public static final int GAME_TIME_MAX = 999;

    public GameTimer() {
        observers = new ArrayList<>();
        gameTime = 0;
        initTime = 0;
    }

    public int getGameTime() {
        return gameTime;
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
        notifyObservers();
    }

    @Override
    public void registerObserver(GameTimerObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            MVCLogger.logObserverRegistration(log, this, observer);
        }
    }

    @Override
    public void removeObserver(GameTimerObserver observer) {
        if (observers.contains(observer)) {
            observers.remove(observer);
            MVCLogger.logObserverRemoving(log, this, observer);
        }
    }

    @Override
    public void notifyObservers() {
        for (GameTimerObserver observer : observers) {
            MVCLogger.logObserversNotification(log, this, observer);
            observer.update(gameTime);
        }
    }

    private class GameTimerTask extends TimerTask {
        @Override
        public void run() {
            if (gameTime < GAME_TIME_MAX) {
                //к времени прибаляется 1, т.к. в оригинальном сапере отсчет времени
                // начинался с 1, а не с 0
                gameTime = Math.toIntExact((System.currentTimeMillis() - initTime) / 1000) + 1;
                notifyObservers();
            }
        }
    }
}
