package ru.chayka.minesweeper.eventsystem.senders.model;

import ru.chayka.minesweeper.eventsystem.events.model.LeaderboardDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.LeaderboardDtoEventListener;
import ru.chayka.minesweeper.eventsystem.senders.MinesweeperEventSender;

public final class LeaderboardDtoEventSender
        extends MinesweeperEventSender<LeaderboardDtoEventListener, LeaderboardDtoEvent> {
    @Override
    protected void notifyOneListener(LeaderboardDtoEventListener listener, LeaderboardDtoEvent event) {
        listener.acceptEvent(event);
    }
}
