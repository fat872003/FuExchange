package com.adkp.fuexchange.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.lang.model.element.Name;
import java.time.LocalDate;

@Data
@NoArgsConstructor(force = true)

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

@Entity
@Table(name = "Staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int staffId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "roleId", referencedColumnName = "roleId")
    private Roles roleId;

    private String firstName;

    private String lastName;

    private String gender;

    @Column(unique = true)
    private String identityCard;

    private String address;

    @Column(unique = true)
    private String phoneNumber;

    private LocalDate dob;

    @Column(nullable = false)
    private String password;

    @Column(name="isActive")
    private boolean active;

    public Staff(Roles roleId, String firstName, String lastName, String gender, String identityCard, String address, String phoneNumber, LocalDate dob, String password, boolean isActive) {
        this.roleId = roleId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.identityCard = identityCard;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.password = password;
        this.active = isActive;
    }
}
