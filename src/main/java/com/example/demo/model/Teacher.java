package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Teachers")
@EqualsAndHashCode
public class Teacher {

    @Id
    @Getter
    @Column
    @GeneratedValue
    private Long id;

    @Getter
    @Column
    @NotBlank
    private String lastName;

    @Getter
    @Column
    @NotBlank
    private String firstName;

    @Getter
    @Column
    @Email
    private String email;

    @Getter
    @Column
    private String phone;
}
