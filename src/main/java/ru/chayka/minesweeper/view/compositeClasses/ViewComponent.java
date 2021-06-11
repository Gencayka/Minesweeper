package ru.chayka.minesweeper.view.compositeClasses;

import ru.chayka.minesweeper.observerInterfaces.observables.model.*;
import ru.chayka.minesweeper.observerInterfaces.observables.view.*;
import ru.chayka.minesweeper.observerInterfaces.observers.controller.AboutButtonObserver;
import ru.chayka.minesweeper.observerInterfaces.observers.controller.LeaderboardActionsObserver;
import ru.chayka.minesweeper.observerInterfaces.observers.controller.MinefieldObserver;
import ru.chayka.minesweeper.observerInterfaces.observers.controller.StartNewGameButtonObserver;
import ru.chayka.minesweeper.observerInterfaces.observers.view.*;

public abstract class ViewComponent {
    protected final Object parentObject;

    public ViewComponent(Object parentObject) {
        this.parentObject = parentObject;
    }

    public void registerObserverForAllViewObservables(AboutButtonObserver observer) {
        if (this.parentObject instanceof AboutButtonObservable) {
            ((AboutButtonObservable) this.parentObject).registerObserver(observer);
        }
    }

    public void registerObserverForAllViewObservables(LeaderboardActionsObserver observer) {
        if (this.parentObject instanceof HighScoresButtonObservable) {
            ((HighScoresButtonObservable) this.parentObject).registerObserver(observer);
        } else if (this.parentObject instanceof RecordNewLeaderFrameObservable) {
            ((RecordNewLeaderFrameObservable) this.parentObject).registerObserver(observer);
        } else if (this.parentObject instanceof ResetResultsButtonObservable) {
            ((ResetResultsButtonObservable) this.parentObject).registerObserver(observer);
        }
    }

    public void registerObserverForAllViewObservables(MinefieldObserver observer) {
        if (this.parentObject instanceof MinefieldObservable) {
            ((MinefieldObservable) this.parentObject).registerObserver(observer);
        }
    }

    public void registerObserverForAllViewObservables(StartNewGameButtonObserver observer) {
        if (this.parentObject instanceof SetDifficultyButtonObservable) {
            ((SetDifficultyButtonObservable) this.parentObject).registerObserver(observer);
        } else if (this.parentObject instanceof StartNewGameButtonObservable) {
            ((StartNewGameButtonObservable) this.parentObject).registerObserver(observer);
        }
    }

    public void registerAllViewObserversForObservable(AboutMinesweeperObservable observable) {
        if (this.parentObject instanceof AboutMinesweeperObserver) {
            observable.registerObserver((AboutMinesweeperObserver) this.parentObject);
        }
    }

    public void registerAllViewObserversForObservable(DifficultyModesDtoSenderObservable observable) {
        if (this.parentObject instanceof DifficultyModesDtoSenderObserver) {
            observable.registerObserver((DifficultyModesDtoSenderObserver) this.parentObject);
        }
    }

    public void registerAllViewObserversForObservable(FlagCounterObservable observable) {
        if (this.parentObject instanceof FlagCounterObserver) {
            observable.registerObserver((FlagCounterObserver) this.parentObject);
        }
    }

    public void registerAllViewObserversForObservable(GameOverNotificatorObservable observable) {
        if (this.parentObject instanceof GameOverNotificatorObserver) {
            observable.registerObserver((GameOverNotificatorObserver) this.parentObject);
        }
    }

    public void registerAllViewObserversForObservable(GameTimerObservable observable) {
        if (this.parentObject instanceof GameTimerObserver) {
            observable.registerObserver((GameTimerObserver) this.parentObject);
        }
    }

    public void registerAllViewObserversForObservable(LeaderboardObservable observable) {
        if (this.parentObject instanceof LeaderboardObserver) {
            observable.registerObserver((LeaderboardObserver) this.parentObject);
        }
    }

    public void registerAllViewObserversForObservable(MinefieldActionPerformerObservable observable) {
        if (this.parentObject instanceof MinefieldActionPerformerObserver) {
            observable.registerObserver((MinefieldActionPerformerObserver) this.parentObject);
        }
    }

    public void registerAllViewObserversForObservable(MinefieldCreatorObservable observable) {
        if (this.parentObject instanceof MinefieldCreatorObserver) {
            observable.registerObserver((MinefieldCreatorObserver) this.parentObject);
        }
    }

    public void registerAllViewObserversForObservable(RecordNewLeaderNotificatorObservable observable) {
        if (this.parentObject instanceof RecordNewLeaderNotificatorObserver) {
            observable.registerObserver((RecordNewLeaderNotificatorObserver) this.parentObject);
        }
    }
}
