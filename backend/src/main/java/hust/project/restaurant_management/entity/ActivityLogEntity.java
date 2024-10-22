package hust.project.restaurant_management.entity;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityLogEntity {
    private Long id;

    private Long userId;

    private String userName;

    private String action;

    private Double amount;

    private Instant createdAt;
}
