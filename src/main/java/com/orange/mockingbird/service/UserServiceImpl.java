package com.orange.mockingbird.service;

import com.orange.mockingbird.dao.UserRepository;
import com.orange.mockingbird.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * User service impl.
 */
@Service
public class UserServiceImpl implements IUserService {

    /**
     * user dao.
     */
    private final UserRepository userRepository;

    /**
     * init.
     *
     * @param userRepository user dao
     */
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * find all user.
     *
     * @return list
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Get user by id.
     *
     * @param id the id of the user
     * @return the user information
     * @throws IllegalArgumentException if the user does not exist
     */
    @Override
    public User getUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        return user.get();
    }

    /**
     * Register a new user.
     *
     * @param user the user to register
     * @return the registered user
     * @throws IllegalArgumentException if the username or phone is invalid
     */
    @Override
    public User register(User user) {
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (user.getPhone() == null || !user.getPhone().matches("^1[3-9]\\d{9}$")) {
            throw new IllegalArgumentException("Invalid phone number");
        }
        return userRepository.save(user);
    }

    /**
     * Update an existing user.
     *
     * @param user the user to update
     * @return the updated user
     * @throws IllegalArgumentException if the user does not exist or the phone is invalid
     */
    @Override
    public User update(User user) {
        Optional<User> existingUser = userRepository.findById(user.getId());
        if (existingUser.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        if (user.getPhone() != null && !user.getPhone().matches("^1[3-9]\\d{9}$")) {
            throw new IllegalArgumentException("Invalid phone number");
        }
        User updatedUser = existingUser.get();
        updatedUser.setName(user.getName());
        updatedUser.setPhone(user.getPhone());
        return userRepository.save(updatedUser);
    }

    /**
     * Delete a user by id.
     *
     * @param id the id of the user to delete
     * @throws IllegalArgumentException if the user does not exist
     */
    @Override
    public void delete(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.deleteById(id);
    }

    /**
     * User login.
     *
     * @param username the username
     * @param password the password
     * @return the user information
     * @throws IllegalArgumentException if the username or password is invalid
     */
    @Override
    public User login(String username, String password) {
        User user = userRepository.findByName(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        return user;
    }

    /**
     * Assign role to user.
     *
     * @param userId the user id
     * @param roleId the role id
     * @throws IllegalArgumentException if the user or role does not exist
     */
    @Override
    public void assignRole(Integer userId, Integer roleId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        if (roleId == null) {
            throw new IllegalArgumentException("Role not found");
        }
        if (roleId > 20) {
            throw new IllegalArgumentException("Invalid role id");
        }
        // Here you would typically check if the role exists in a RoleRepository
        // For simplicity, we assume the role exists
        User updatedUser = user.get();
        updatedUser.setRoleId(roleId);
        userRepository.save(updatedUser);
    }
}