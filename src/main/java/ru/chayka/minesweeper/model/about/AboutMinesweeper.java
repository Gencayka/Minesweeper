package ru.chayka.minesweeper.model.about;

import ru.chayka.minesweeper.eventsystem.events.model.AboutDtoEvent;
import ru.chayka.minesweeper.eventsystem.senders.model.AboutDtoEventSender;

public class AboutMinesweeper {
    private final AboutDtoEventSender aboutDtoEventSender;

    private final String text = "Here could be your advertising";

    public AboutMinesweeper() {
        aboutDtoEventSender = new AboutDtoEventSender();
    }

    public AboutDtoEventSender getAboutDtoEventSender() {
        return aboutDtoEventSender;
    }

    public void sendAboutTextToView() {
        aboutDtoEventSender.notifyAllListeners(new AboutDtoEvent(text));
    }
}
