package ru.chayka.minesweeper.model.difficultyModesDtoSender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.dto.DifficultyModesDto;
import ru.chayka.minesweeper.model.MVCLogger;
import ru.chayka.minesweeper.observerInterfaces.observables.model.DifficultyModesDtoSenderObservable;
import ru.chayka.minesweeper.observerInterfaces.observers.view.DifficultyModesDtoSenderObserver;

import java.util.ArrayList;

public class DifficultyModesDtoSender
        implements DifficultyModesDtoSenderObservable {
    private static final Logger log = LoggerFactory.getLogger(DifficultyModesDtoSender.class.getName());

    private final ArrayList<DifficultyModesDtoSenderObserver> observers;

    public DifficultyModesDtoSender() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(DifficultyModesDtoSenderObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            MVCLogger.logObserverRegistration(log, this, observer);
        }
    }

    @Override
    public void removeObserver(DifficultyModesDtoSenderObserver observer) {
        if (observers.contains(observer)) {
            observers.remove(observer);
            MVCLogger.logObserverRemoving(log, this, observer);
        }
    }

    @Override
    public void sendDifficultyModesDto() {
        for (DifficultyModesDtoSenderObserver observer : observers) {
            MVCLogger.logDtoSending(log, this, observer);
            observer.acceptDifficultyModesDto(new DifficultyModesDto());
        }
    }
}
