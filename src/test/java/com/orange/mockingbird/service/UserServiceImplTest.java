package com.orange.mockingbird.service;

import com.orange.mockingbird.dao.UserRepository;
import com.orange.mockingbird.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {


    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void GIVEN_void_WHEN_findAll_THEN_return_all() {
        // given
        User e1 = new User(1, "test123", "test123", "test123", 1);
        User e2 = new User(2, "test321", "test321", "test321", 2);
        List<User> t = List.of(e1, e2);
        when(userRepository.findAll()).thenReturn(t);

        // when
        List<User> all = userService.findAll();

        // then
        assertEquals(2, all.size());
        assertEquals("test123", all.get(0).getName());
        assertEquals("test321", all.get(1).getName());
        assertEquals("test123", all.get(0).getPassword());
        verify(userRepository).findAll();
    }


    @Test
    void GIVEN_id_WHEN_getUserById_THEN_return_user() {
        // given
        Optional<User> optionalUser = Optional.of(new User(999, "test123", "test321", "test111", 2));
        when(userRepository.findById(1)).thenReturn(optionalUser);

        // when
        User user = userService.getUserById(1);

        // then
        assertEquals(999, user.getId());
        assertEquals("test123", user.getName());
        assertEquals("test321", user.getPhone());
        assertEquals("test111", user.getPassword());
        assertEquals(2, user.getRoleId());
    }

    @Test
    void GIVEN_noExistId_WHEN_getUserById_THEN_throw_IllegalArgumentException() {
        // given
        Integer invalidId = 1;
        Optional<User> optionalUser = Optional.empty();
        when(userRepository.findById(invalidId)).thenReturn(optionalUser);

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.getUserById(invalidId);
        });

        // then
        assertEquals("User not found", exception.getMessage());
        verify(userRepository, times(1)).findById(invalidId);
    }

    @Test
    void GIVEN_user_WHEN_register_THEN_return_user() {
        // given
        User user = new User(1, "test123", "13999999999", "test111", 2);
        when(userRepository.save(user)).thenReturn(user);


        // when
        User result = userService.register(user);

        // then
        assertEquals(user, result);
    }


    @Test
    void GIVEN_notValidUser_WHEN_register_THEN_throw_IllegalArgumentException() {
        // given
        User user1 = new User(1, "", "13999999999", "test111", 2);
        User user2 = new User(1, " ", "13999999999", "test111", 2);
        User user3 = new User(1, null, "13999999999", "test111", 2);

        // when
        IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class, () -> userService.register(user1));
        IllegalArgumentException e2 = assertThrows(IllegalArgumentException.class, () -> userService.register(user2));
        IllegalArgumentException e3 = assertThrows(IllegalArgumentException.class, () -> userService.register(user3));


        // then
        assertEquals("Username cannot be empty", e1.getMessage());
        assertEquals("Username cannot be empty", e2.getMessage());
        assertEquals("Username cannot be empty", e3.getMessage());

        verify(userRepository, times(0)).save(any());
    }

    @Test
    void GIVEN_notValidPhone_WHEN_register_THEN_throw_IllegalArgumentException() {

        // given
        User user1 = new User(1, "test3", "132", "test111", 2);
        User user2 = new User(1, "test3", null, "test111", 2);
        User user3 = new User(1, "test1", "11999999999", "test111", 2);

        // when
        IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class, () -> userService.register(user1));
        IllegalArgumentException e2 = assertThrows(IllegalArgumentException.class, () -> userService.register(user2));
        IllegalArgumentException e3 = assertThrows(IllegalArgumentException.class, () -> userService.register(user3));


        // then
        assertEquals("Invalid phone number", e1.getMessage());
        assertEquals("Invalid phone number", e2.getMessage());
        assertEquals("Invalid phone number", e3.getMessage());

        verify(userRepository, times(0)).save(any());
    }


}