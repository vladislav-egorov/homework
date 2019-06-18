package ru.otus.momento;

public class AtmCaretaker {
    private AtmMomento atmMomento;

    public AtmCaretaker(AtmMomento atmMomento) {
        this.atmMomento = atmMomento;
    }

    public AtmMomento getAtmMomento() {
        return atmMomento;
    }
}
