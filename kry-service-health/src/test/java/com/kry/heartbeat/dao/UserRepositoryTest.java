package com.kry.heartbeat.dao;

import com.kry.heartbeat.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByEmailShouldReturnExistingUser() {
        User user = new User();
        user.setName("name");
        user.setEmail("email");
        userRepository.save(user);

        User email = userRepository.findByEmail("email");

        assertThat(email).isNotNull();
        assertThat(email.getName()).isEqualTo(user.getName());
    }
    @Test
    void findByEmailShouldReturnNullForNonExistingUser() {

        User email = userRepository.findByEmail("email");

        assertThat(email).isNull();
    }
}
