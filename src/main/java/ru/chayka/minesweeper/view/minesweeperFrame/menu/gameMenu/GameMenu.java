package ru.chayka.minesweeper.view.minesweeperFrame.menu.gameMenu;

import ru.chayka.minesweeper.dto.DifficultyModesDto;
import ru.chayka.minesweeper.observerInterfaces.observers.view.DifficultyModesDtoSenderObserver;
import ru.chayka.minesweeper.view.compositeClasses.ViewComposite;

import javax.swing.*;

public class GameMenu
        implements DifficultyModesDtoSenderObserver {
    private final ViewComposite viewComposite;
    private final JMenu jMenu;

    private final NewGameMenuButton newGameMenuButton;
    private final HighScoresMenuButton highScoresMenuButton;
    private final JMenuItem exitButton;

    public GameMenu() {
        viewComposite = new ViewComposite(this);
        jMenu = new JMenu("Game");

        newGameMenuButton = new NewGameMenuButton();
        viewComposite.addViewComponent(newGameMenuButton.getViewComponent());

        highScoresMenuButton = new HighScoresMenuButton();
        viewComposite.addViewComponent(highScoresMenuButton.getViewComponent());

        exitButton = new JMenuItem("Exit");
        exitButton.addActionListener(event -> System.exit(0));
    }

    public ViewComposite getViewComponent() {
        return viewComposite;
    }

    public JMenu getJMenu() {
        return jMenu;
    }

    private void formGameMenu(String[] difficultyModeNames, int initDifficultyModelIndex) {
        jMenu.add(newGameMenuButton.getJMenuItem());
        jMenu.addSeparator();

        SetDifficultyMenuButton[] setDifficultyMenuButtons = new SetDifficultyMenuButton[difficultyModeNames.length];
        ButtonGroup setDifficultyMenuItemsGroup = new ButtonGroup();
        for (int index = 0; index < difficultyModeNames.length; index++) {
            setDifficultyMenuButtons[index] = new SetDifficultyMenuButton(difficultyModeNames[index]);
            jMenu.add(setDifficultyMenuButtons[index].getJRadioButtonMenuItem());
            viewComposite.addViewComponent(setDifficultyMenuButtons[index].getViewComponent());
            setDifficultyMenuItemsGroup.add(setDifficultyMenuButtons[index].getJRadioButtonMenuItem());
        }
        setDifficultyMenuButtons[initDifficultyModelIndex].getJRadioButtonMenuItem().setSelected(true);
        jMenu.addSeparator();

        jMenu.add(highScoresMenuButton.getJMenuItem());
        jMenu.addSeparator();

        jMenu.add(exitButton);
    }

    @Override
    public void acceptDifficultyModesDto(DifficultyModesDto dto) {
        formGameMenu(dto.getNames(), dto.getInitDifficultyModelIndex());
    }
}