package com.example.voicetuner;

import java.util.LinkedList;
import java.util.List;

public abstract class Observable<T extends Observer> {
    private List<T> obs;

    public Observable() {
        obs = new LinkedList<>();
    }

    public void addOb(T ob) {
        obs.add(ob);
    }

    public void removeOb(T ob) {
        obs.remove(ob);
    }

    public void notifyObservers() {
        obs.forEach(ob -> ob.update(this));
    }

}
