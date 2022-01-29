package com.example.demo.model;

import lombok.*;

import javax.persistence.*;

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
    private String lastName;

    @Getter
    @Column
    private String firstName;

    @Getter
    @Column
    private String email;

    @Getter
    @Column
    private String phone;
}
