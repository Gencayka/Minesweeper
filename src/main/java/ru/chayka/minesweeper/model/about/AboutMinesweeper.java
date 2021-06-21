package ru.chayka.minesweeper.model.about;

import ru.chayka.minesweeper.eventsystem.events.model.MvcAboutDtoEvent;
import ru.chayka.minesweeper.eventsystem.senders.model.MvcAboutDtoEventSender;

public class AboutMinesweeper {
    private final MvcAboutDtoEventSender mvcAboutDtoEventSender;

    private final String text = "Here could be your advertising";

    public AboutMinesweeper() {
        mvcAboutDtoEventSender = new MvcAboutDtoEventSender();
    }

    public MvcAboutDtoEventSender getMvcAboutDtoEventSender() {
        return mvcAboutDtoEventSender;
    }

    public void sendAboutTextToView() {
        mvcAboutDtoEventSender.notifyAllListeners(new MvcAboutDtoEvent(text));
    }
}
