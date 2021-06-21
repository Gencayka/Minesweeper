package ru.chayka.minesweeper.eventsystem.senders.view;

import ru.chayka.minesweeper.eventsystem.events.view.MvcDifficultyModeButtonPressedEvent;
import ru.chayka.minesweeper.eventsystem.listeners.controller.MvcDifficultyModeButtonPressedEventListener;
import ru.chayka.minesweeper.eventsystem.senders.MvcEventSender;

public final class MvcDifficultyModeButtonPressedEventSender
        extends MvcEventSender<MvcDifficultyModeButtonPressedEventListener, MvcDifficultyModeButtonPressedEvent> {
    @Override
    protected void notifyOneListener(MvcDifficultyModeButtonPressedEventListener listener, MvcDifficultyModeButtonPressedEvent event) {
        listener.acceptEvent(event);
    }
}
