package com.ctytech.flierly.iam.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "iam_role_privileges", indexes = {
        @Index(name = "idx_role_id", columnList = "roleId")
})
public class RolePrivileges {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long roleId;

    @ManyToMany
    @JoinTable(
            name = "iam_role_privileges",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id")
    )
    private List<Privilege> privileges;
}
