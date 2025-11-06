package com.betfair.simulator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class Event {
    @JsonProperty("eventId")
    private String eventId;
    
    @JsonProperty("eventName")
    private String eventName;
    
    @JsonProperty("countryCode")
    private String countryCode;
    
    @JsonProperty("timezone")
    private String timezone;
    
    @JsonProperty("openDate")
    private Instant openDate;
    
    // Getters y Setters
    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }
    
    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }
    
    public String getCountryCode() { return countryCode; }
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }
    
    public String getTimezone() { return timezone; }
    public void setTimezone(String timezone) { this.timezone = timezone; }
    
    public Instant getOpenDate() { return openDate; }
    public void setOpenDate(Instant openDate) { this.openDate = openDate; }
}