package com.orange.mockingbird.service;

import com.orange.mockingbird.entity.User;

import java.util.List;

/**
 * user service.
 */
public interface IUserService {

    /**
     * find all user.
     *
     * @return list
     */
    List<User> findAll();

    /**
     * Get user by id.
     *
     * @param id the id of the user
     * @return the user information
     * @throws IllegalArgumentException if the user does not exist
     */
    User getUserById(Integer id);

    /**
     * Register a new user.
     *
     * @param user the user to register
     * @return the registered user
     * @throws IllegalArgumentException if the username or phone is invalid
     */
    User register(User user);

    /**
     * Update an existing user.
     *
     * @param user the user to update
     * @return the updated user
     * @throws IllegalArgumentException if the user does not exist or the phone is invalid
     */
    User update(User user);

    /**
     * Delete a user by id.
     *
     * @param id the id of the user to delete
     * @throws IllegalArgumentException if the user does not exist
     */
    void delete(Integer id);

    /**
     * User login.
     *
     * @param username the username
     * @param password the password
     * @return the user information
     * @throws IllegalArgumentException if the username or password is invalid
     */
    User login(String username, String password);

    /**
     * Assign role to user.
     *
     * @param userId the user id
     * @param roleId the role id
     * @throws IllegalArgumentException if the user or role does not exist
     */
    void assignRole(Integer userId, Integer roleId);
}