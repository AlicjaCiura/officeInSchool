package com.example.demo.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "Teachers")
@EqualsAndHashCode
@NoArgsConstructor
@Builder
public class Teacher {

    @Id
    @Getter
    @Column
    @GeneratedValue
    private Long id;

    @Getter
    @Column
    private String lastName;

    @Getter
    @Column
    private String firstName;

    @Getter
    @Column
    @Email
    private  String email;

    @Getter
    @Column
    private String phone;



}
