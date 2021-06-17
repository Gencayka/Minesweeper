package ru.chayka.minesweeper.view.mainframe.menu.gamemenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.eventsystem.EventSystemLogger;
import ru.chayka.minesweeper.eventsystem.events.model.DifficultyModesDtoEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.DifficultyModesDtoEventListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GameMenu
        implements DifficultyModesDtoEventListener {
    private static final Logger log = LoggerFactory.getLogger(GameMenu.class.getName());

    private final JMenu jMenu;

    private final NewGameMenuButton newGameMenuButton;
    private final HighScoresMenuButton highScoresMenuButton;
    private final JMenuItem exitButton;
    private List<SetDifficultyMenuButton> setDifficultyMenuButtons;

    public GameMenu() {
        jMenu = new JMenu("Game");

        newGameMenuButton = new NewGameMenuButton();
        highScoresMenuButton = new HighScoresMenuButton();

        exitButton = new JMenuItem("Exit");
        exitButton.addActionListener(event -> System.exit(0));
    }

    public NewGameMenuButton getNewGameMenuButton() {
        return newGameMenuButton;
    }

    public List<SetDifficultyMenuButton> getSetDifficultyMenuButtons() {
        return setDifficultyMenuButtons;
    }

    public HighScoresMenuButton getHighScoresMenuButton() {
        return highScoresMenuButton;
    }

    public JMenu getJMenu() {
        return jMenu;
    }

    private void formGameMenu(String[] difficultyModeNames, int initDifficultyModelIndex) {
        jMenu.add(newGameMenuButton.getJMenuItem());
        jMenu.addSeparator();

        setDifficultyMenuButtons = new ArrayList<>();
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

        jMenu.add(highScoresMenuButton.getJMenuItem());
        jMenu.addSeparator();

        jMenu.add(exitButton);
    }

    @Override
    public void acceptEvent(DifficultyModesDtoEvent event) {
        EventSystemLogger.logEventAccepting(log, this, event);
        formGameMenu(event.getNames(), event.getInitDifficultyModelIndex());
    }
}