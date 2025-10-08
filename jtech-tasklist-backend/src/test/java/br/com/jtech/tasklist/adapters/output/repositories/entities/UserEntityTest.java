package br.com.jtech.tasklist.adapters.output.repositories.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {

    @Test
    @DisplayName("Deve construir UserEntity corretamente usando o construtor NoArgsConstructor e Setters")
    void constructorAndSettersTest() {
        // Arrange
        final UUID mockId = UUID.randomUUID();
        final String email = "teste@jtech.com.br";
        final String name = "Angelo Vicente";
        final String password = "hashed_password";

        // Act
        final UserEntity user = new UserEntity();
        user.setId(mockId);
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setTasks(new HashSet<>());

        // Assert
        assertNotNull(user);
        assertEquals(mockId, user.getId());
        assertEquals(email, user.getEmail());
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
        assertTrue(user.getTasks().isEmpty());
    }

    @Test
    @DisplayName("Deve construir UserEntity corretamente usando o construtor AllArgsConstructor")
    void allArgsConstructorTest() {
        // Arrange
        final UUID mockId = UUID.randomUUID();
        final String email = "all@jtech.com.br";
        final String name = "All Constructor";
        final String password = "hash";
        final HashSet<TasklistEntity> tasks = new HashSet<>();

        // Act
        final UserEntity user = new UserEntity(mockId, email, name, password, tasks);

        // Assert
        assertNotNull(user);
        assertEquals(email, user.getEmail());
        assertEquals(tasks, user.getTasks());
    }
}