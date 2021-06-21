package ru.chayka.minesweeper.view.mainframe.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.eventsystem.MvcEventSystemLogger;
import ru.chayka.minesweeper.eventsystem.events.model.MvcDifficultyModesDtoEvent;
import ru.chayka.minesweeper.eventsystem.events.view.MvcUnparameterizedButtonPressedEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.MvcDifficultyModesDtoEventListener;
import ru.chayka.minesweeper.eventsystem.senders.view.MvcUnparameterizedButtonPressedEventSender;
import ru.chayka.minesweeper.view.UnparameterizedButton;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GameMenu
        implements MvcDifficultyModesDtoEventListener {
    private static final Logger log = LoggerFactory.getLogger(GameMenu.class.getName());

    private final JMenu jMenu;

    private final JMenuItem newGameButton;
    private final List<SetDifficultyMenuButton> setDifficultyMenuButtons;
    private final JMenuItem highScoresMenuButton;
    private final JMenuItem exitButton;

    private final MvcUnparameterizedButtonPressedEventSender mvcUnparameterizedButtonPressedEventSender;

    public GameMenu() {
        jMenu = new JMenu();
        newGameButton = new JMenuItem();
        setDifficultyMenuButtons = new ArrayList<>();
        highScoresMenuButton = new JMenuItem();
        exitButton = new JMenuItem();

        mvcUnparameterizedButtonPressedEventSender = new MvcUnparameterizedButtonPressedEventSender();

        newGameButton.addActionListener(event ->
                mvcUnparameterizedButtonPressedEventSender.notifyAllListeners(
                        new MvcUnparameterizedButtonPressedEvent(UnparameterizedButton.NEW_GAME)));
        highScoresMenuButton.addActionListener(event ->
                mvcUnparameterizedButtonPressedEventSender.notifyAllListeners(
                        new MvcUnparameterizedButtonPressedEvent(UnparameterizedButton.HIGH_SCORES)));
        exitButton.addActionListener(event ->
                mvcUnparameterizedButtonPressedEventSender.notifyAllListeners(
                        new MvcUnparameterizedButtonPressedEvent(UnparameterizedButton.EXIT)));
    }

    public JMenu getJMenu() {
        return jMenu;
    }

    public List<SetDifficultyMenuButton> getSetDifficultyMenuButtons() {
        return setDifficultyMenuButtons;
    }

    public MvcUnparameterizedButtonPressedEventSender getMvcUnparameterizedButtonPressedEventSender() {
        return mvcUnparameterizedButtonPressedEventSender;
    }

    @Override
    public void acceptEvent(MvcDifficultyModesDtoEvent event) {
        MvcEventSystemLogger.logEventAccepting(log, this, event);
        assembleMenu(event.getNames(), event.getInitDifficultyModelIndex());
    }

    private void assembleMenu(String[] difficultyModeNames, int initDifficultyModelIndex) {
        jMenu.setText("Game");

        newGameButton.setText("New Game");
        jMenu.add(newGameButton);
        jMenu.addSeparator();

        ButtonGroup setDifficultyMenuItemsGroup = new ButtonGroup();
        for (String difficultyModeName : difficultyModeNames) {
            SetDifficultyMenuButton newButtonBuffer =
                    new SetDifficultyMenuButton(difficultyModeName);
            setDifficultyMenuButtons.add(newButtonBuffer);
            jMenu.add(newButtonBuffer.getJRadioButtonMenuItem());
            setDifficultyMenuItemsGroup.add(newButtonBuffer.getJRadioButtonMenuItem());
        }
        setDifficultyMenuButtons.get(initDifficultyModelIndex).getJRadioButtonMenuItem().setSelected(true);
        jMenu.addSeparator();

        highScoresMenuButton.setText("High Scores");
        jMenu.add(highScoresMenuButton);
        jMenu.addSeparator();

        exitButton.setText("Exit");
        jMenu.add(exitButton);

        log.debug("Game Menu is assembled");
    }
}