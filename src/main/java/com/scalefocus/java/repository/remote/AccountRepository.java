package com.scalefocus.java.repository.remote;

import com.scalefocus.java.domain.remote.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
