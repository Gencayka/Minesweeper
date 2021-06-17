package ru.chayka.minesweeper.eventsystem.senders.view;

import ru.chayka.minesweeper.eventsystem.events.view.MinefieldButtonPressedEvent;
import ru.chayka.minesweeper.eventsystem.listeners.controller.MinefieldButtonPressedEventListener;
import ru.chayka.minesweeper.eventsystem.senders.MinesweeperEventSender;

public final class MinefieldButtonPressedEventSender
        extends MinesweeperEventSender<MinefieldButtonPressedEventListener, MinefieldButtonPressedEvent> {
    @Override
    protected void notifyOneListener(MinefieldButtonPressedEventListener listener, MinefieldButtonPressedEvent event) {
        listener.acceptEvent(event);
    }
}
