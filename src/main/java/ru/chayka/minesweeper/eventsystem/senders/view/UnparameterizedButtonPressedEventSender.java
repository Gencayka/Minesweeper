package ru.chayka.minesweeper.eventsystem.senders.view;

import ru.chayka.minesweeper.eventsystem.events.view.UnparameterizedButtonPressedEvent;
import ru.chayka.minesweeper.eventsystem.listeners.controller.UnparameterizedButtonPressedEventListener;
import ru.chayka.minesweeper.eventsystem.senders.MinesweeperEventSender;

public final class UnparameterizedButtonPressedEventSender
        extends MinesweeperEventSender
        <UnparameterizedButtonPressedEventListener,
                UnparameterizedButtonPressedEvent> {
    @Override
    protected void notifyOneListener(
            UnparameterizedButtonPressedEventListener listener,
            UnparameterizedButtonPressedEvent event) {
        listener.acceptEvent(event);
    }
}
