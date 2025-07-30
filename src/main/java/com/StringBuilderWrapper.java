package com;

public class StringBuilderWrapper {

    private final StringBuilder stringBuilder;
    private final History history;

    public StringBuilderWrapper() {
        stringBuilder = new StringBuilder();
        history = new History();
    }

    public StringBuilderWrapper(String str) {
        stringBuilder = new StringBuilder(str);
        history = new History();
    }

    public StringBuilderWrapper delete(int start, int end) {
        history.save(new StringBuilderMemento(this.toString()));
        stringBuilder.delete(start, end);
        return this;
    }

    public StringBuilderWrapper append(String str) {
        history.save(new StringBuilderMemento(this.toString()));
        stringBuilder.append(str);
        return this;
    }

    public StringBuilderWrapper deleteCharAt(int index) {
        history.save(new StringBuilderMemento(this.toString()));
        stringBuilder.deleteCharAt(index);
        return this;
    }

    public void undo() {
        try {
            String state = history.restore();
            stringBuilder.setLength(0);
            stringBuilder.append(state);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    public String toString() {
        return stringBuilder.toString();
    }
}
