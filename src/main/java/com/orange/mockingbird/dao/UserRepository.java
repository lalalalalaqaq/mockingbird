package com.orange.mockingbird.dao;

import com.orange.mockingbird.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * user dao.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Find user by name.
     *
     * @param name the name of the user
     * @return the user information
     */
    User findByName(String name);
}