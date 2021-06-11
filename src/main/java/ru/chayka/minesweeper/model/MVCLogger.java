package ru.chayka.minesweeper.model;

import org.slf4j.Logger;

public class MVCLogger {
    public static void logObserverRegistration(Logger log, Object model, Object observer) {
        log.debug("Observer {} is registered for {}",
                observer.getClass().getSimpleName(),
                model.getClass().getSimpleName());
    }

    public static void logObserverRemoving(Logger log, Object model, Object observer) {
        log.debug("Observer {} is removed for {}",
                observer.getClass().getSimpleName(),
                model.getClass().getSimpleName());
    }

    public static void logObserversNotification(Logger log, Object model, Object observer) {
        log.debug("Model component {} notified view component {}",
                model.getClass().getSimpleName(),
                observer.getClass().getSimpleName());
    }

    public static void logDtoSending(Logger log, Object model, Object observer) {
        log.debug("Model component {} sent DTO to view component {}",
                model.getClass().getSimpleName(),
                observer.getClass().getSimpleName());
    }
}
