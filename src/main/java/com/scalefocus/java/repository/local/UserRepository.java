package com.scalefocus.java.repository.local;

import com.scalefocus.java.domain.local.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> getUserByEmail(String email);

  boolean existsByEmail(String email);
}
