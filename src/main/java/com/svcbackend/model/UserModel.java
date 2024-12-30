package com.svcbackend.model;
import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
public class UserModel {
    @NotBlank
    @Email(message = "El username debe ser un correo válido")
    private String username;

    @NotBlank
    private String password;

    private String role;
    private String name;
    private String sexo;
}
