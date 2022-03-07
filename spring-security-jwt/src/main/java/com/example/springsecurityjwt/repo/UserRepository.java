package com.example.springsecurityjwt.repo;

import com.example.springsecurityjwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Date 2022/3/2 15:51
 * @Description
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByUsername(String s);
}
