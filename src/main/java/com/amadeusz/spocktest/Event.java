package com.amadeusz.spocktest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
class Event {

    @Getter @Setter private UUID id;
    @Getter @Setter private String title;

}
