package com.sakout.tenderflow.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "entity_type", discriminatorType = DiscriminatorType.STRING)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Reference {
    @Id
    @SequenceGenerator(name = "SEQ_REFERENCE", sequenceName = "SEQ_REFERENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_REFERENCE")
    private Long id;
    private String code;
    private String label;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Reference parent;
}
