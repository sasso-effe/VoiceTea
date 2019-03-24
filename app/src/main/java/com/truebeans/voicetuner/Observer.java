package com.truebeans.voicetuner;

public interface Observer<T extends Observable>{

    void update(T ob);

}
