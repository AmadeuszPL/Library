package com.amadeusz.spocktest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Publisher {

    private List<Subscriber> subscribers = new ArrayList<>();


    public void add(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void fire(Event event) {
        String id = event.getId().toString();
        for(Subscriber subscriber : subscribers){
            subscriber.receive(event);
        }
    }

}
