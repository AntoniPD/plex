package com.scalefocus.java.domain.remote;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "media_streams")
public class MediaStream {
  @Id
  private Long id;

  private String codec;

  private String language;

  @Column(name = "stream_type_id")
  private StreamType streamType;
}
