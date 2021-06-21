package ru.chayka.minesweeper.eventsystem.senders.model;

import ru.chayka.minesweeper.eventsystem.events.model.MvcDifficultyModesDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.MvcDifficultyModesDtoEventListener;
import ru.chayka.minesweeper.eventsystem.senders.MvcEventSender;

public final class MvcDifficultyModesDtoEventSender
        extends MvcEventSender<MvcDifficultyModesDtoEventListener, MvcDifficultyModesDtoEvent> {
    @Override
    protected void notifyOneListener(MvcDifficultyModesDtoEventListener listener, MvcDifficultyModesDtoEvent event) {
        listener.acceptEvent(event);
    }
}
