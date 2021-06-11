package ru.chayka.minesweeper.observerInterfaces.observers.view;

import ru.chayka.minesweeper.observerInterfaces.observers.MinesweeperObserver;

public interface RecordNewLeaderNotificatorObserver extends MinesweeperObserver {
    void recordNewLeader(String strDifficulty);
}
