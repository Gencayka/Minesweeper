package ru.chayka.minesweeper.view;

public enum MouseButton {
    RMB(1),
    LMB(3),
    MMB(2),
    UNKNOWN(-1);

    private final int numCode;

    MouseButton(int numCode) {
        this.numCode = numCode;
    }

    public static MouseButton numCodeToEnum(int numCode) {
        for (MouseButton mouseButton : MouseButton.values()) {
            if (mouseButton.numCode == numCode) {
                return mouseButton;
            }
        }
        return UNKNOWN;
    }
}
