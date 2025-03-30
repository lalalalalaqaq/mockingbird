package com.orange.mockingbird.controller;

import com.orange.mockingbird.entity.User;
import com.orange.mockingbird.model.ApiResponse;
import com.orange.mockingbird.service.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User controller.
 */
@RestController
public class UserController {

    /**
     * user service.
     */
    private final IUserService userService;

    /**
     * init.
     *
     * @param userService2 user service
     */
    public UserController(final IUserService userService2) {
        this.userService = userService2;
    }

    /**
     * find user list.
     *
     * @return list
     */
    @GetMapping("user/list")
    public ApiResponse<List<User>> list() {
        return ApiResponse.success(userService.findAll());
    }

    /**
     * Get user by id.
     *
     * @param id the id of the user
     * @return the user information
     */
    @GetMapping("user/{id}")
    public ApiResponse<User> getUserById(@PathVariable Integer id) {
        return ApiResponse.success(userService.getUserById(id));

    }

    /**
     * Register a new user.
     *
     * @param user the user to register
     * @return the registered user
     */
    @PostMapping("user/register")
    public ApiResponse<User> register(@RequestBody User user) {
        return ApiResponse.success(userService.register(user));
    }

    /**
     * Update an existing user.
     *
     * @param user the user to update
     * @return the updated user
     */
    @PutMapping("user/update")
    public ApiResponse<User> update(@RequestBody User user) {
        return ApiResponse.success(userService.update(user));
    }

    /**
     * Delete a user by id.
     *
     * @param id the id of the user to delete
     * @return the result of the deletion
     */
    @DeleteMapping("user/delete/{id}")
    public ApiResponse<Void> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ApiResponse.success(null);
    }

    /**
     * User login.
     *
     * @param username the username
     * @param password the password
     * @return the login result
     */
    @PostMapping("user/login")
    public ApiResponse<User> login(@RequestParam String username, @RequestParam String password) {
        return ApiResponse.success(userService.login(username, password));
    }

    /**
     * Assign role to user.
     *
     * @param userId the user id
     * @param roleId the role id
     * @return the result of the assignment
     */
    @PostMapping("user/assignRole")
    public ApiResponse<Void> assignRole(@RequestParam Integer userId, @RequestParam Integer roleId) {
        userService.assignRole(userId, roleId);
        return ApiResponse.success(null);
    }
}