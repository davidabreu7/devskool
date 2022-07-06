package com.devsuperior.dslearn.controller;

import com.devsuperior.dslearn.dto.NotificationDto;
import com.devsuperior.dslearn.service.NotificationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public ResponseEntity<Page<NotificationDto>> notificationsForCurrentUser(Pageable pageable) {
        return ResponseEntity.ok(notificationService.notificationForCurrentUser(pageable));
    }
}
