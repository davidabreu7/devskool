package com.devsuperior.dslearn.service;

import com.devsuperior.dslearn.dto.NotificationDto;
import com.devsuperior.dslearn.repositories.NotificationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    private final AuthService authService;

    public NotificationService(NotificationRepository notificationRepository, AuthService authService) {
        this.notificationRepository = notificationRepository;
        this.authService = authService;
    }

    public Page<NotificationDto> notificationForCurrentUser(Pageable pageable) {
        return notificationRepository.findByUser(authService.authenticated(), pageable)
                .map(NotificationDto::new);
    }
}
