package ru.chayka.minesweeper.eventsystem.senders.model;

import ru.chayka.minesweeper.eventsystem.events.model.DifficultyModesDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.DifficultyModesDtoEventListener;
import ru.chayka.minesweeper.eventsystem.senders.MinesweeperEventSender;

public final class DifficultyModesDtoEventSender
        extends MinesweeperEventSender<DifficultyModesDtoEventListener, DifficultyModesDtoEvent> {
    @Override
    protected void notifyOneListener(DifficultyModesDtoEventListener listener, DifficultyModesDtoEvent event) {
        listener.acceptEvent(event);
    }
}
