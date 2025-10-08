package br.com.jtech.tasklist.adapters.output.repositories.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TasklistEntityTest {

    @Test
    @DisplayName("Deve construir TasklistEntity corretamente com o construtor NoArgsConstructor")
    void noArgsConstructorAndSettersTest() {
        // Arrange
        final UUID taskId = UUID.randomUUID();
        final UserEntity mockUser = UserEntity.builder().id(UUID.randomUUID()).email("user@test.com").build();
        final LocalDateTime now = LocalDateTime.now();

        // Act
        final TasklistEntity task = new TasklistEntity();
        task.setId(taskId);
        task.setTitle("Comprar leite");
        task.setDescription("Leite integral");
        task.setCompleted(true);
        task.setListName("Pessoal");
        task.setUser(mockUser);
        task.setCreatedAt(now);
        task.setUpdatedAt(now);

        // Assert
        assertNotNull(task);
        assertEquals(taskId, task.getId());
        assertEquals("Comprar leite", task.getTitle());
        assertTrue(task.isCompleted());
        assertEquals("Pessoal", task.getListName());
        assertEquals(mockUser.getId(), task.getUser().getId());
        assertEquals(now, task.getCreatedAt());
        assertEquals(now, task.getUpdatedAt());
    }

    @Test
    @DisplayName("Deve inicializar 'completed' como false por padrão")
    void defaultCompletedValueTest() {
        // Act
        final TasklistEntity task = new TasklistEntity();

        // Assert
        assertFalse(task.isCompleted());
    }

    // Teste para o construtor que criamos para o Service
    @Test
    @DisplayName("Deve construir TasklistEntity com construtor parcial para criação")
    void partialConstructorTest() {
        // Arrange
        final UserEntity mockUser = UserEntity.builder().id(UUID.randomUUID()).email("user@test.com").build();

        // Act
        new TasklistEntity();
        final TasklistEntity task = TasklistEntity.builder()
                .title("Pagar contas")
                .listName("Trabalho")
                .user(mockUser)
                .completed(false)
                .build();

        // Assert
        assertEquals("Pagar contas", task.getTitle());
        assertEquals("Trabalho", task.getListName());
        assertFalse(task.isCompleted());
        assertNull(task.getId()); // ID deve ser nulo antes de ser persistido
        assertNull(task.getCreatedAt()); // Datas são preenchidas pelo AuditingEntityListener
    }
}
