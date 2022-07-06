package com.devsuperior.dslearn.dto;

import com.devsuperior.dslearn.entities.Notification;

import java.time.Instant;

public class NotificationDto {

    private Long id;
    private String text;
    private Instant moment;
    private boolean read;
    private String route;
    private Long userId;

    public NotificationDto() {
    }

    public NotificationDto(Long id, String text, Instant moment, boolean read, String route, Long userId) {
        this.id = id;
        this.text = text;
        this.moment = moment;
        this.read = read;
        this.route = route;
        this.userId = userId;
    }

    public NotificationDto(Notification notification) {
        this.id = notification.getId();
        this.text = notification.getText();
        this.moment = notification.getMoment();
        this.read = notification.isRead();
        this.route = notification.getRoute();
        this.userId = notification.getUser().getId();
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

    public Long getUserId() {
        return userId;
    }

    public void setUser(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "NotificationDto{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", moment=" + moment +
                ", read=" + read +
                ", route='" + route + '\'' +
                ", user=" + userId +
                '}';
    }
}
