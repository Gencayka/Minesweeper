package ru.chayka.minesweeper.view.compositeClasses;

import ru.chayka.minesweeper.observerInterfaces.observables.model.*;
import ru.chayka.minesweeper.observerInterfaces.observers.controller.AboutButtonObserver;
import ru.chayka.minesweeper.observerInterfaces.observers.controller.LeaderboardActionsObserver;
import ru.chayka.minesweeper.observerInterfaces.observers.controller.MinefieldObserver;
import ru.chayka.minesweeper.observerInterfaces.observers.controller.StartNewGameButtonObserver;

import java.util.ArrayList;

public class ViewComposite extends ViewComponent {
    private final ArrayList<ViewComponent> viewComponents = new ArrayList<>();

    public ViewComposite(Object parentObject) {
        super(parentObject);
    }

    public void addViewComponent(ViewComponent viewComponent) {
        viewComponents.add(viewComponent);
    }

    public void removeViewComponent(ViewComponent viewComponent) {
        viewComponents.remove(viewComponent);
    }

    @Override
    public void registerObserverForAllViewObservables(AboutButtonObserver observer) {
        super.registerObserverForAllViewObservables(observer);
        for (ViewComponent viewComponent : viewComponents) {
            viewComponent.registerObserverForAllViewObservables(observer);
        }
    }

    @Override
    public void registerObserverForAllViewObservables(LeaderboardActionsObserver observer) {
        super.registerObserverForAllViewObservables(observer);
        for (ViewComponent viewComponent : viewComponents) {
            viewComponent.registerObserverForAllViewObservables(observer);
        }
    }

    @Override
    public void registerObserverForAllViewObservables(MinefieldObserver observer) {
        super.registerObserverForAllViewObservables(observer);
        for (ViewComponent viewComponent : viewComponents) {
            viewComponent.registerObserverForAllViewObservables(observer);
        }
    }

    @Override
    public void registerObserverForAllViewObservables(StartNewGameButtonObserver observer) {
        super.registerObserverForAllViewObservables(observer);
        for (ViewComponent viewComponent : viewComponents) {
            viewComponent.registerObserverForAllViewObservables(observer);
        }
    }

    @Override
    public void registerAllViewObserversForObservable(AboutMinesweeperObservable observable) {
        super.registerAllViewObserversForObservable(observable);
        for (ViewComponent viewComponent : viewComponents) {
            viewComponent.registerAllViewObserversForObservable(observable);
        }
    }

    @Override
    public void registerAllViewObserversForObservable(DifficultyModesDtoSenderObservable observable) {
        super.registerAllViewObserversForObservable(observable);
        for (ViewComponent viewComponent : viewComponents) {
            viewComponent.registerAllViewObserversForObservable(observable);
        }
    }

    @Override
    public void registerAllViewObserversForObservable(FlagCounterObservable observable) {
        super.registerAllViewObserversForObservable(observable);
        for (ViewComponent viewComponent : viewComponents) {
            viewComponent.registerAllViewObserversForObservable(observable);
        }
    }

    @Override
    public void registerAllViewObserversForObservable(GameOverNotificatorObservable observable) {
        super.registerAllViewObserversForObservable(observable);
        for (ViewComponent viewComponent : viewComponents) {
            viewComponent.registerAllViewObserversForObservable(observable);
        }
    }

    @Override
    public void registerAllViewObserversForObservable(GameTimerObservable observable) {
        super.registerAllViewObserversForObservable(observable);
        for (ViewComponent viewComponent : viewComponents) {
            viewComponent.registerAllViewObserversForObservable(observable);
        }
    }

    @Override
    public void registerAllViewObserversForObservable(LeaderboardObservable observable) {
        super.registerAllViewObserversForObservable(observable);
        for (ViewComponent viewComponent : viewComponents) {
            viewComponent.registerAllViewObserversForObservable(observable);
        }
    }

    @Override
    public void registerAllViewObserversForObservable(MinefieldActionPerformerObservable observable) {
        super.registerAllViewObserversForObservable(observable);
        for (ViewComponent viewComponent : viewComponents) {
            viewComponent.registerAllViewObserversForObservable(observable);
        }
    }

    @Override
    public void registerAllViewObserversForObservable(MinefieldCreatorObservable observable) {
        super.registerAllViewObserversForObservable(observable);
        for (ViewComponent viewComponent : viewComponents) {
            viewComponent.registerAllViewObserversForObservable(observable);
        }
    }

    @Override
    public void registerAllViewObserversForObservable(RecordNewLeaderNotificatorObservable observable) {
        super.registerAllViewObserversForObservable(observable);
        for (ViewComponent viewComponent : viewComponents) {
            viewComponent.registerAllViewObserversForObservable(observable);
        }
    }
}
