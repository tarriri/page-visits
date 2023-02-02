package com.booking.eventcollectorapi.models;

public enum EntityType {
    Property(0);

    public final int value;

    private EntityType(int value){
        this.value = value;
    }
}
