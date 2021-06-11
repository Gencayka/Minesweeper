package ru.chayka.minesweeper.view;

import org.slf4j.Logger;

public class MVCLogger {
    public static void logObserverRegistration(Logger log, Object view, Object observer) {
        log.debug("Observer {} is registered for {}",
                observer.getClass().getSimpleName(),
                view.getClass().getSimpleName());
    }

    public static void logObserverRemoving(Logger log, Object view, Object observer) {
        log.debug("Observer {} is removed for {}",
                observer.getClass().getSimpleName(),
                view.getClass().getSimpleName());
    }

    public static void logObserversNotification(Logger log, Object view, Object observer) {
        log.debug("View component {} notified controller component {}",
                view.getClass().getSimpleName(),
                observer.getClass().getSimpleName());
    }
}
