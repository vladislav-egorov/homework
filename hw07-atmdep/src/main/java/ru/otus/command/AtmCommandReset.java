package ru.otus.command;

import ru.otus.atm.Atm;

public class AtmCommandReset implements AtmCommand {
    private Atm atm;

    public AtmCommandReset(Atm atm) {
        this.atm = atm;
    }

    @Override
    public void execute() {
        atm.reset();
    }
}
