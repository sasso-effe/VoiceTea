package com.example.voicetuner;

public interface Observer<T extends Observable>{

    public void update(T ob);

}
