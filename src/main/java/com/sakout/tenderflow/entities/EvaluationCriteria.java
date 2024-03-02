package com.sakout.tenderflow.entities;

import com.sakout.tenderflow.enums.Quality;
import com.sakout.tenderflow.enums.TenderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "evaluation_criteria")
public class EvaluationCriteria {
    @Id
    @SequenceGenerator(name = "SEQ_EVALUATION_CRITERIA", sequenceName = "SEQ_EVALUATION_CRITERIA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EVALUATION_CRITERIA")
    private Long id;
    private String uuid;
    private double price;
    @Enumerated(EnumType.STRING)
    private Quality quality;
    private int weightage;
    private int maxScore;
    private LocalDateTime startDelivery;
    private LocalDateTime endDelivery;
    private int flexDates;


}
