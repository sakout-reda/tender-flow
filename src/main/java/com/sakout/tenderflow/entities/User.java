package com.sakout.tenderflow.entities;

import com.sakout.tenderflow.util.UuidUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tender-flow-user")
public class User {
    @Id
    @SequenceGenerator(name = "SEQ_USER", sequenceName = "SEQ_USER", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER")
    private Long id;
    @Column
    private String uuid;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private LocalDateTime birthday;
    @UpdateTimestamp
    private LocalDateTime status;
    @CreationTimestamp
    private LocalDateTime creation;
    @Column
    private String phone;
    @ManyToOne
    private Reference gender;

    @ManyToOne
    private Reference companyName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "link_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "reference_id"))
    public List<Reference> roles;

    @OneToMany(mappedBy = "portalUser")
    private List<UserAction> userActions;


    @PrePersist
    public void prePersist() {
        String hashedUuid = UuidUtils.getHashedUuid(this.creation);
        this.setUuid(hashedUuid);
    }
}
