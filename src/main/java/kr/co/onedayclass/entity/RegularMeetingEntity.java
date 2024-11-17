package kr.co.onedayclass.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "regular_meetings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegularMeetingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "meeting_id", nullable = false)
    private MeetingEntity meeting;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime meetingDate;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String entryFee;

    @Column(nullable = false)
    private Integer participantCount;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
