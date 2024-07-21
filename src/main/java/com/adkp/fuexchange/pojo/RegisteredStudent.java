package com.adkp.fuexchange.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "RegisteredStudent")
public class RegisteredStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int registeredStudentId;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "studentId", referencedColumnName = "studentId")
    private Student studentId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "roleId", referencedColumnName = "roleId")
    private Roles roleId;

    private String password;

    @Column(name = "isActive")
    private boolean active;

    private String deliveryAddress;

    @OneToMany(mappedBy = "registeredStudentId", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonBackReference
    private List<Orders> orderId;

    @OneToOne(mappedBy = "registeredStudentId", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonBackReference
    private Cart cartId;

    @OneToMany(mappedBy = "studentSendId", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonBackReference
    private List<ChatMessage> studentSendId;

    @OneToMany(mappedBy = "studentReceiveId", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonBackReference
    private List<ChatMessage> studentReceiveId;

    public RegisteredStudent(Student studentId, Roles roleId, String password, boolean active, String deliveryAddress) {
        this.studentId = studentId;
        this.roleId = roleId;
        this.password = password;
        this.active = active;
        this.deliveryAddress = deliveryAddress;
    }
}
