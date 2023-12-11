package com.project.tel_book.events;

import lombok.Data;

@Data
public class DataChangeEvent {

    private String eventType;
    private String eventData;


    public DataChangeEvent(String eventType, String eventData) {
        this.eventType = eventType;
        this.eventData = eventData;
    }
}