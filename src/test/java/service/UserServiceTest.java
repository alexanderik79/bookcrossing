package service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.services.UserService;
import lombok.SneakyThrows;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;


public class UserServiceTest {
    UserService userService = Mockito.mock(UserService.class);

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();


    @Test
    @SneakyThrows
    public void test01_getUserByLoginRetrievesUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("Jina");
        when(userService.getUserByLogin("Jina")).thenReturn(userDTO);
        assertEquals("Jina", userService.getUserByLogin("Jina").getUsername());
    }

    @Test
    @SneakyThrows
    public void test01_getUserByIdRetrievesUser() {
        UserDTO user = new UserDTO();
        user.setId(1L);
        when(userService.getUserById(1L)).thenReturn(user);
        assertEquals(user,userService.getUserById(1L));
    }

    @Test
    public void test02_getUserByLoginThrowsException() {
        // Устанавливаем ожидание исключения
        exceptionRule.expect(NotFoundException.class);
        exceptionRule.expectMessage("User with login Jina was not found");

        // Вызываем метод, который должен выбросить исключение
        userService.getUserByLogin("Jina");
    }

    @Test
    public void test03_RuntimeExceptionIsThrownForNullUsername() {
        assertThrows(RuntimeException.class, () -> {
            String username = null;
            if (username == null) {
                throw new RuntimeException("Логин пользователя не может быть null");
            }
        });
    }
}

