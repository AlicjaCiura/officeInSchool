package com.example.demo.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PersonDto {

    @Getter
    @Setter
    @NotBlank
    private String name;
    @Getter
    @Setter
    @NotBlank
    private String surname;
    @Getter
    @Setter
    @Email
    private String email;
    @Getter
    @Setter
    private String phone;
    @Getter
    @Setter
    private String occupation;

    @Override
    public String toString() {
        return String.format("%s = [ %s, %s, %s, %s, %s]", "PersonDto", name, surname, email, phone, occupation);
    }
}
