package com.sakout.tenderflow.entities;

import com.sakout.tenderflow.enums.BidsStatus;
import com.sakout.tenderflow.enums.TenderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "bids")
public class Bids {
    @Id
    @SequenceGenerator(name = "SEQ_BIDS", sequenceName = "SEQ_BIDS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BIDS")
    private Long id;
    private String uuid;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @ManyToOne
    private Tender tender;
    private double bidAmout;
    private LocalDateTime submissionDate;
    @Enumerated(EnumType.STRING)
    private BidsStatus status;
    private double score;

}
