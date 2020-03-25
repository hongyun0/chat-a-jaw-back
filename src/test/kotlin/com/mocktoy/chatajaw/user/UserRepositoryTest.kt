package com.mocktoy.chatajaw.user

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
class UserRepositoryTest() {

    @Autowired
    private lateinit var userRepository: UserRepository;

    @Test
    fun `user repository crud test`() {
        val user = User(UUID.randomUUID());
        userRepository.save(user);

        val fetchedUser = userRepository.findById(user.duid);

        assertThat(fetchedUser.get().duid).isEqualTo(user.duid);
        userRepository.delete(fetchedUser.get());
    }


}
