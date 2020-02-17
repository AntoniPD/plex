package com.scalefocus.java.repository.remote;

import com.scalefocus.java.domain.remote.MediaStream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaStreamRepository extends JpaRepository<MediaStream, Long> {
}
