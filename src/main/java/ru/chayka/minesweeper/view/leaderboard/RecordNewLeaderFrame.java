package ru.chayka.minesweeper.view.leaderboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.observerInterfaces.observables.view.RecordNewLeaderFrameObservable;
import ru.chayka.minesweeper.observerInterfaces.observers.controller.LeaderboardActionsObserver;
import ru.chayka.minesweeper.observerInterfaces.observers.view.RecordNewLeaderNotificatorObserver;
import ru.chayka.minesweeper.view.MVCLogger;
import ru.chayka.minesweeper.view.compositeClasses.ViewComposite;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RecordNewLeaderFrame
        implements RecordNewLeaderNotificatorObserver,
        RecordNewLeaderFrameObservable {
    private static final Logger log = LoggerFactory.getLogger(RecordNewLeaderFrame.class.getName());

    private final ViewComposite viewComposite;
    private final JDialog jDialog;

    private final ArrayList<LeaderboardActionsObserver> observers;

    private final JFrame mainFrame;
    private final NewRecordLabel message;
    private final JTextField textField;

    private final String defaultLeaderName = "Anon";

    public RecordNewLeaderFrame(JFrame mainFrame) {
        viewComposite = new ViewComposite(this);
        jDialog = new JDialog();
        observers = new ArrayList<>();

        this.mainFrame = mainFrame;

        jDialog.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridy = 0;
        message = new NewRecordLabel();
        JPanel labelPanel = new JPanel();
        labelPanel.add(message.getJLabel());
        jDialog.add(labelPanel, gridBagConstraints);

        gridBagConstraints.gridy = 1;
        textField = new JTextField(defaultLeaderName);
        textField.setPreferredSize(new Dimension(150, 18));
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.add(textField);
        jDialog.add(textFieldPanel, gridBagConstraints);

        gridBagConstraints.gridy = 2;
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(event -> notifyObservers());
        okButton.addActionListener(event -> jDialog.setVisible(false));
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        jDialog.add(buttonPanel, gridBagConstraints);

        jDialog.setResizable(false);
        jDialog.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
    }

    public ViewComposite getViewComponent() {
        return viewComposite;
    }

    @Override
    public void registerObserver(LeaderboardActionsObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            MVCLogger.logObserverRegistration(log, this, observer);
        }
    }

    @Override
    public void removeObserver(LeaderboardActionsObserver observer) {
        if (observers.contains(observer)) {
            observers.remove(observer);
            MVCLogger.logObserverRemoving(log, this, observer);
        }
    }

    /*@Override
    public void registerObserver(MinesweeperObserver observer) {
        if (observer instanceof LeaderboardActionsObserver) {
            if (!observers.contains(observer)) {
                observers.add((LeaderboardActionsObserver) observer);
                MVCLogger.logObserverRegistration(log, this, observer);
            }
        } else {
            log.warn("Attempt to register invalid type observer");
        }
    }

    @Override
    public void removeObserver(MinesweeperObserver observer) {
        if (observer instanceof LeaderboardActionsObserver) {
            if (observers.contains(observer)) {
                observers.remove(observer);
                MVCLogger.logObserverRemoving(log, this, observer);
            }
        }
    }*/

    @Override
    public void notifyObservers() {
        for (LeaderboardActionsObserver observer : observers) {
            MVCLogger.logObserversNotification(log, this, observer);
            observer.transferLeaderNameToModel(textField.getText());
        }
    }

    @Override
    public void recordNewLeader(String strDifficulty) {
        message.formMessage(strDifficulty);

        jDialog.setLocationRelativeTo(mainFrame);
        jDialog.pack();
        jDialog.setVisible(true);
    }
}
