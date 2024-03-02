package com.sakout.tenderflow.entities;

import com.sakout.tenderflow.enums.Action;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_action")
public class UserAction {
    @Id
    @SequenceGenerator(name = "SEQ_USER_ACTION", sequenceName = "SEQ_USER_ACTION", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER_ACTION")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User portalUser;
    @Column
    @Enumerated(EnumType.STRING)
    private Action action;
    private LocalDateTime dateOfAction;
    private String description;
}
