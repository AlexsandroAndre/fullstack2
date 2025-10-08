package br.com.jtech.tasklist.adapters.input.protocols.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO de requisição para autenticação (Login)")
public class AuthRequest {

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "Email deve ser válido.")
    @Schema(description = "O endereço de email do usuário", example = "joao.silva@exemplo.com")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    @Schema(description = "A senha para autenticação", example = "senhaForte123")
    private String password;
}