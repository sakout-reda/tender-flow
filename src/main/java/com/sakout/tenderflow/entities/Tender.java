package com.sakout.tenderflow.entities;

import com.sakout.tenderflow.enums.TenderStatus;
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
@Table(name = "tender")
public class Tender {
    @Id
    @SequenceGenerator(name = "SEQ_TENDER", sequenceName = "SEQ_TENDER", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TENDER")
    private Long id;
    private String uuid;
    private String title;
    private String description;
    private LocalDateTime openingDate;
    private LocalDateTime closingDate;
    @Enumerated(EnumType.STRING)
    private TenderStatus status;
    @ManyToOne
    private EvaluationCriteria evaluationCriteria;

}
