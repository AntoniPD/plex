package com.scalefocus.java.bootstrap;

import com.scalefocus.java.domain.local.Role;
import com.scalefocus.java.domain.local.RoleName;
import com.scalefocus.java.repository.local.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

  private final RoleRepository roleRepository;

  public BootstrapData(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }


  @Override
  public void run(String... args) throws Exception {
      Role adminRole = new Role();
      adminRole.setName(RoleName.ROLE_ADMIN);
      Role userRole = new Role();
      userRole.setName(RoleName.ROLE_USER);
      roleRepository.save(adminRole);
      roleRepository.save(userRole);
  }
}