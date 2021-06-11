package ru.chayka.minesweeper.view.minesweeperFrame.minefield;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.dto.MinefieldCellDto;
import ru.chayka.minesweeper.observerInterfaces.observables.view.MinefieldObservable;
import ru.chayka.minesweeper.observerInterfaces.observers.controller.MinefieldObserver;
import ru.chayka.minesweeper.observerInterfaces.observers.view.GameOverNotificatorObserver;
import ru.chayka.minesweeper.observerInterfaces.observers.view.MinefieldActionPerformerObserver;
import ru.chayka.minesweeper.observerInterfaces.observers.view.MinefieldCreatorObserver;
import ru.chayka.minesweeper.view.MVCLogger;
import ru.chayka.minesweeper.view.MouseButton;
import ru.chayka.minesweeper.view.compositeClasses.ViewItem;
import ru.chayka.minesweeper.view.minesweeperFrame.minefield.minefieldButton.MinefieldButton;
import ru.chayka.minesweeper.view.minesweeperFrame.minefield.minefieldButton.MinefieldButtonState;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MinefieldPanel
        implements MinefieldObservable,
        MinefieldActionPerformerObserver, MinefieldCreatorObserver, GameOverNotificatorObserver,
        MouseListener {
    private static final Logger log = LoggerFactory.getLogger(MinefieldPanel.class.getName());

    private final ViewItem viewItem;
    private final JPanel jPanel;

    private final ArrayList<MinefieldObserver> observers;

    public MinefieldButton[][] minefieldButtons;
    private boolean isActive;

    public MinefieldPanel() {
        viewItem = new ViewItem(this);
        jPanel = new JPanel();
        observers = new ArrayList<>();

        jPanel.setBorder(new BorderUIResource.BevelBorderUIResource(BevelBorder.LOWERED));
        isActive = true;
    }

    public ViewItem getViewComposite() {
        return viewItem;
    }

    public JPanel getJPanel() {
        return jPanel;
    }

    public void createNewMinefield(int numOfRows, int numOfColumns) {
        isActive = true;

        jPanel.setLayout(new GridLayout(numOfRows, numOfColumns));
        jPanel.removeAll();
        jPanel.revalidate();

        minefieldButtons = new MinefieldButton[numOfRows][numOfColumns];
        for (int currentRow = 0; currentRow < numOfRows; currentRow++) {
            for (int currentColumn = 0; currentColumn < numOfColumns; currentColumn++) {
                minefieldButtons[currentRow][currentColumn] = new MinefieldButton(currentRow, currentColumn);
                MinefieldButton currentButton = minefieldButtons[currentRow][currentColumn];
                currentButton.addMouseListener(this);
                jPanel.add(currentButton);
            }
        }
        jPanel.repaint();
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(jPanel);
        parentFrame.pack();
    }

    private void deactivateMinefield() {
        isActive = false;
        for (MinefieldButton[] currentRow : minefieldButtons) {
            for (MinefieldButton button : currentRow) {
                button.makeVisuallyDisabled();
            }
        }
    }

    @Override
    public void registerObserver(MinefieldObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            MVCLogger.logObserverRegistration(log, this, observer);
        }
    }

    @Override
    public void removeObserver(MinefieldObserver observer) {
        if (observers.contains(observer)) {
            observers.remove(observer);
            MVCLogger.logObserverRemoving(log, this, observer);
        }
    }

    @Override
    public void notifyObservers(int rowCoordinate, int columnCoordinate,
                                MouseButton mouseButton, MinefieldButtonState minefieldButtonState) {
        for (MinefieldObserver observer : observers) {
            MVCLogger.logObserversNotification(log, this, observer);
            observer.transferToModel(rowCoordinate, columnCoordinate, mouseButton, minefieldButtonState);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MouseButton mouseButton = MouseButton.numCodeToEnum(e.getButton());
        MinefieldButton button = (MinefieldButton) e.getSource();
        if (isActive) {
            notifyObservers(button.getRow(), button.getColumn(), mouseButton, button.getState());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void acceptMinefieldCellDto(MinefieldCellDto dto) {
        MinefieldButton dtoRelatedMinefieldButton = minefieldButtons[dto.getRow()][dto.getColumn()];

        if (dto.isOpenedWithWrongMove()) {
            dtoRelatedMinefieldButton.setState(MinefieldButtonState.MINE_OPENED_WITH_WRONG_MOVE);
        } else {
            if (dto.isOpened()) {
                if (!dto.isMined() && !dto.isFlagged()) {
                    dtoRelatedMinefieldButton.setState(
                            MinefieldButtonState.intToMinefieldButtonState(dto.getNumOfAdjacentMines()));
                } else if (dto.isMined() && !dto.isFlagged()) {
                    dtoRelatedMinefieldButton.setState(MinefieldButtonState.MINE);
                } else if (!dto.isMined() && dto.isFlagged()) {
                    dtoRelatedMinefieldButton.setState(MinefieldButtonState.WRONGLY_FLAGGED_MINE);
                }
            } else {
                if (dto.isFlagged()) {
                    dtoRelatedMinefieldButton.setState(MinefieldButtonState.FLAG);
                } else {
                    dtoRelatedMinefieldButton.setState(MinefieldButtonState.CLOSED);
                }
            }
        }
    }

    @Override
    public void loseTheGame() {
        deactivateMinefield();
    }

    @Override
    public void winTheGame() {
        deactivateMinefield();
    }
}