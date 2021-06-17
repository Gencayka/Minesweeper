package ru.chayka.minesweeper.eventsystem.senders.view;

import ru.chayka.minesweeper.eventsystem.events.view.DifficultyModeButtonPressedEvent;
import ru.chayka.minesweeper.eventsystem.listeners.controller.DifficultyModeButtonPressedEventListener;
import ru.chayka.minesweeper.eventsystem.senders.MinesweeperEventSender;

public final class DifficultyModeButtonPressedEventSender
        extends MinesweeperEventSender<DifficultyModeButtonPressedEventListener, DifficultyModeButtonPressedEvent> {
    @Override
    protected void notifyOneListener(DifficultyModeButtonPressedEventListener listener, DifficultyModeButtonPressedEvent event) {
        listener.acceptEvent(event);
    }
}
