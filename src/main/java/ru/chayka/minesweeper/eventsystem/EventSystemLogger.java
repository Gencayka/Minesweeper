package ru.chayka.minesweeper.eventsystem;

import org.slf4j.Logger;
import ru.chayka.minesweeper.eventsystem.events.MinesweeperEvent;
import ru.chayka.minesweeper.eventsystem.listeners.MinesweeperEventListener;

public class EventSystemLogger {
    public static void logListenerAddingResult(
            Logger log,
            boolean isSuccessful,
            Object sender,
            MinesweeperEventListener listener) {
        if (isSuccessful) {
            log.debug("Listener {} is added for {}",
                    listener.getClass().getSimpleName(),
                    sender.getClass().getSimpleName());
        } else {
            log.debug("Attempt to add {} for {}, but it was added already",
                    listener.getClass().getSimpleName(),
                    sender.getClass().getSimpleName());
        }
    }

    public static void logListenerRemovingResult(
            Logger log,
            boolean isSuccessful,
            Object sender,
            MinesweeperEventListener listener) {
        if (isSuccessful) {
            log.debug("Listener {} is added for {}",
                    listener.getClass().getSimpleName(),
                    sender.getClass().getSimpleName());
        } else {
            log.debug("Attempt to remove {} from {}, but it wasn't added",
                    listener.getClass().getSimpleName(),
                    sender.getClass().getSimpleName());
        }
    }

    public static void logEventAccepting(
            Logger log,
            MinesweeperEventListener listener,
            MinesweeperEvent event) {
        log.debug("{} is accepted by {}",
                event.getClass().getSimpleName(),
                listener.getClass().getSimpleName());
    }
}
