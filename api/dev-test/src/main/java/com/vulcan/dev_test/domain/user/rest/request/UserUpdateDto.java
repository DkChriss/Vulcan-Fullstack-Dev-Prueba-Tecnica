package com.vulcan.dev_test.domain.user.rest.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {
    Long id;
    @NotEmpty(message = "El nombre del usuario es necesario")
    @NotBlank(message = "El nombre del usuario es necesario")
    @NotNull(message = "El nombre del usuario es necesario")
    String name;
    @NotEmpty(message = "El nombre del usuario es necesario")
    @NotBlank(message = "El nombre del usuario es necesario")
    @NotNull(message = "El nombre del usuario es necesario")
    String username;
    @NotEmpty(message = "La contraseña del usuario es necesario")
    @NotBlank(message = "La contraseña del usuario es necesario")
    @NotNull(message = "La contraseña del usuario es necesario")
    String password;
}
