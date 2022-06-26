package com.devsuperior.dslearn.dto;

import java.time.Instant;

public class NotificationDto {

    private Long id;
    private String text;
    private Instant moment;
    private boolean read;
    private String route;
    private UserDto user;

    public NotificationDto() {
    }

    public NotificationDto(Long id, String text, Instant moment, boolean read, String route, UserDto user) {
        this.id = id;
        this.text = text;
        this.moment = moment;
        this.read = read;
        this.route = route;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "NotificationDto{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", moment=" + moment +
                ", read=" + read +
                ", route='" + route + '\'' +
                ", user=" + user +
                '}';
    }
}
