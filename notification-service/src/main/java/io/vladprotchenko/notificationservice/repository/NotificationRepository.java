package io.vladprotchenko.notificationservice.repository;

import io.vladprotchenko.notificationservice.model.entity.Notification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Optional<Notification> findByIdAndSenderId(Long notificationId, UUID senderId);

    @Query("""
            SELECT n
            FROM Notification n
            WHERE
            n.status = 'R'
                OR
            n.status = 'N' AND n.createdAt < :newDateTime
                OR
            n.status = 'P' AND n.createdAt  < :pendingDateTime
            """)
    List<Notification> findNotificationsByStatusAndCreatedAt(
            @Param("pendingDateTime") LocalDateTime pendingDateTime,
            @Param("newDateTime") LocalDateTime newDateTime,
            Pageable pageable
    );

}
