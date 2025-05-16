package org.dzmitry.kapachou.tx.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.Instant;


@Entity
@Table
@Data
@EntityListeners(AuditingEntityListener.class)
public class ProcessRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    @CreatedDate

    @Column(name = "created_at")
    private Instant cratedAt;

    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.OPEN;

    public enum RequestStatus {
        OPEN, APPROVED, DISCARDED;
    }


}
