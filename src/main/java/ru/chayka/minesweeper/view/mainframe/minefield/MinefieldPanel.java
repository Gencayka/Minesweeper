package ru.chayka.minesweeper.view.mainframe.minefield;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.chayka.minesweeper.eventsystem.MvcEventSystemLogger;
import ru.chayka.minesweeper.eventsystem.events.model.MvcGameOverEvent;
import ru.chayka.minesweeper.eventsystem.events.model.MvcMinefieldCellDtoEvent;
import ru.chayka.minesweeper.eventsystem.events.model.MvcMinefieldDtoEvent;
import ru.chayka.minesweeper.eventsystem.events.view.MvcMinefieldButtonPressedEvent;
import ru.chayka.minesweeper.eventsystem.listeners.view.MvcGameOverEventListener;
import ru.chayka.minesweeper.eventsystem.listeners.view.MvcMinefieldCellDtoEventListener;
import ru.chayka.minesweeper.eventsystem.listeners.view.MvcMinefieldDtoEventListener;
import ru.chayka.minesweeper.eventsystem.senders.view.MvcMinefieldButtonPressedEventSender;
import ru.chayka.minesweeper.view.MouseButton;
import ru.chayka.minesweeper.view.mainframe.minefield.minefieldbutton.MinefieldButton;
import ru.chayka.minesweeper.view.mainframe.minefield.minefieldbutton.MinefieldButtonState;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MinefieldPanel
        implements MvcMinefieldCellDtoEventListener, MvcMinefieldDtoEventListener, MvcGameOverEventListener,
        MouseListener {
    private static final Logger log = LoggerFactory.getLogger(MinefieldPanel.class.getName());

    private final JPanel jPanel;

    private final MvcMinefieldButtonPressedEventSender mvcMinefieldButtonPressedEventSender;

    public MinefieldButton[][] minefieldButtons;
    private boolean isActive;

    public MinefieldPanel() {
        jPanel = new JPanel();

        jPanel.setBorder(new BorderUIResource.BevelBorderUIResource(BevelBorder.LOWERED));
        isActive = true;

        mvcMinefieldButtonPressedEventSender = new MvcMinefieldButtonPressedEventSender();
    }

    public JPanel getJPanel() {
        return jPanel;
    }

    public MvcMinefieldButtonPressedEventSender getMvcMinefieldButtonPressedEventSender() {
        return mvcMinefieldButtonPressedEventSender;
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
    public void mouseReleased(MouseEvent e) {
        MouseButton mouseButton = MouseButton.numCodeToEnum(e.getButton());
        MinefieldButton button = (MinefieldButton) e.getSource();
        if (isActive) {
            mvcMinefieldButtonPressedEventSender.notifyAllListeners(
                    new MvcMinefieldButtonPressedEvent(
                            button.getRow(),
                            button.getColumn(),
                            mouseButton,
                            button.getState()));
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
    public void acceptEvent(MvcMinefieldCellDtoEvent event) {
        MvcEventSystemLogger.logEventAccepting(log, this, event);

        MinefieldButton dtoRelatedMinefieldButton = minefieldButtons[event.getRow()][event.getColumn()];

        if (event.isOpenedWithWrongMove()) {
            dtoRelatedMinefieldButton.setState(MinefieldButtonState.MINE_OPENED_WITH_WRONG_MOVE);
        } else {
            if (event.isOpened()) {
                if (!event.isMined() && !event.isFlagged()) {
                    dtoRelatedMinefieldButton.setState(
                            MinefieldButtonState.intToMinefieldButtonState(event.getNumOfAdjacentMines()));
                } else if (event.isMined() && !event.isFlagged()) {
                    dtoRelatedMinefieldButton.setState(MinefieldButtonState.MINE);
                } else if (!event.isMined()) {
                    dtoRelatedMinefieldButton.setState(MinefieldButtonState.WRONGLY_FLAGGED_MINE);
                }
            } else {
                if (event.isFlagged()) {
                    dtoRelatedMinefieldButton.setState(MinefieldButtonState.FLAG);
                } else {
                    dtoRelatedMinefieldButton.setState(MinefieldButtonState.CLOSED);
                }
            }
        }
    }

    @Override
    public void acceptEvent(MvcMinefieldDtoEvent event) {
        MvcEventSystemLogger.logEventAccepting(log, this, event);
        createNewMinefield(event.getNumOfRows(), event.getNumOfColumns());
    }

    @Override
    public void acceptEvent(MvcGameOverEvent event) {
        MvcEventSystemLogger.logEventAccepting(log, this, event);
        deactivateMinefield();
    }


    private void createNewMinefield(int numOfRows, int numOfColumns) {
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
}