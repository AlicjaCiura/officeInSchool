package com.example.demo.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PersonDto {

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String surname;
    @Getter
    @Setter
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
