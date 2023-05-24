package com.example.test;

class EventItem {
    private String eventType;
    private int eventAge;

    public EventItem(String eventType, int eventAge) {
        this.eventType = eventType;
        this.eventAge = eventAge;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getEventAge() {
        return eventAge;
    }

    public void setEventAge(int eventAge) {
        this.eventAge = eventAge;
    }

    @Override
    public String toString() {
        return "EventItem{" +
                "eventType='" + eventType + '\'' +
                ", eventAge=" + eventAge +
                '}';
    }
}