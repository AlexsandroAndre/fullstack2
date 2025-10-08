package br.com.jtech.tasklist.adapters.input.protocols.auth;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthRequestTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    static void setup() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    static void close() {
        if (validatorFactory != null) {
            validatorFactory.close();
        }
    }

    @Test
    @DisplayName("Deve ser válido quando todos os campos são preenchidos corretamente")
    void validAuthRequestTest() {
        // Arrange
        final AuthRequest request = new AuthRequest("valid@email.com", "senha123");

        // Act
        final var violations = validator.validate(request);

        // Assert
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName("Deve ser inválido quando o email está em branco")
    void invalidAuthRequest_BlankEmailTest() {
        // Arrange
        final AuthRequest request = new AuthRequest("", "senha123");

        // Act
        final var violations = validator.validate(request);

        // Assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("email é obrigatório")));
    }

    @Test
    @DisplayName("Deve ser inválido quando a senha está em branco")
    void invalidAuthRequest_BlankPasswordTest() {
        // Arrange
        final AuthRequest request = new AuthRequest("valid@email.com", "");

        // Act
        final var violations = validator.validate(request);

        // Assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("senha é obrigatória")));
    }

    @Test
    @DisplayName("Deve ser inválido quando o formato do email é incorreto")
    void invalidAuthRequest_InvalidEmailFormatTest() {
        // Arrange
        final AuthRequest request = new AuthRequest("email-invalido", "senha123");

        // Act
        final var violations = validator.validate(request);

        // Assert
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Email deve ser válido")));
    }
}