package com.scalefocus.java.repository.local;

import com.scalefocus.java.domain.local.Role;
import com.scalefocus.java.domain.local.RoleName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(RoleName roleName);
}
