package com.amadeusz.spocktest.interactionbased;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class Publisher {

    @Getter @Setter private List<Subscriber> subscribers = new ArrayList<>();
    @Getter @Setter private int messageCount;

    void send(String message) {
        for (Subscriber subscriber : subscribers) {
            try {
                subscriber.receive(message);
            } catch(RuntimeException exception){}
        }
        messageCount++;
    }





}
