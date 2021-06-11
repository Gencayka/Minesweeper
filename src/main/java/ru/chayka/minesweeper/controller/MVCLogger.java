package ru.chayka.minesweeper.controller;

import org.slf4j.Logger;

public class MVCLogger {
    public static void logCommandTransferring(Logger log, Object controller, Object model) {
        log.debug("Controller component {} transferred command to model component {}",
                controller.getClass().getSimpleName(),
                model.getClass().getSimpleName());
    }
}
