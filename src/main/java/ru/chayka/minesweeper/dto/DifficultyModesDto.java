package ru.chayka.minesweeper.dto;

import ru.chayka.minesweeper.model.DifficultyMode;

public class DifficultyModesDto {
    private final String[] names;
    private int initDifficultyModelIndex;

    public DifficultyModesDto() {
        names = new String[DifficultyMode.values().length];

        for (int index = 0; index < DifficultyMode.values().length; index++) {
            names[index] = DifficultyMode.values()[index].toString();
            if (DifficultyMode.values()[index] == DifficultyMode.INITIAL_DIFFICULTY_MODE) {
                initDifficultyModelIndex = index;
            }
        }
    }

    public String[] getNames() {
        return names;
    }

    public int getInitDifficultyModelIndex() {
        return initDifficultyModelIndex;
    }
}
