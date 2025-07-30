package com;

import java.util.Stack;

public class History {
    private final Stack<StringBuilderMemento> history = new Stack<>();

    public void save(StringBuilderMemento memento) {
        history.push(memento);
    }

    public String restore() {
        if (history.isEmpty()) throw new IllegalStateException("History is empty");
        StringBuilderMemento stringBuilderMemento = history.pop();
        return stringBuilderMemento.state();
    }
}
