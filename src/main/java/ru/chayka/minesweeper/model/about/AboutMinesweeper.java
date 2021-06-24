package ru.chayka.minesweeper.model.about;

import ru.chayka.minesweeper.eventsystem.events.model.MvcAboutDtoEvent;
import ru.chayka.minesweeper.eventsystem.senders.model.MvcAboutDtoEventSender;
import ru.chayka.minesweeper.model.settings.Settings;

public class AboutMinesweeper {
    private final MvcAboutDtoEventSender mvcAboutDtoEventSender;

    private final String text;

    public AboutMinesweeper() {
        text = Settings.getInstance().getAboutText();

        mvcAboutDtoEventSender = new MvcAboutDtoEventSender();
    }

    public MvcAboutDtoEventSender getMvcAboutDtoEventSender() {
        return mvcAboutDtoEventSender;
    }

    public void sendAboutTextToView() {
        mvcAboutDtoEventSender.notifyAllListeners(new MvcAboutDtoEvent(text));
    }
}
