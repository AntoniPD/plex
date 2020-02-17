package com.scalefocus.java.domain.remote;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "media_items")
public class MediaItem {
  @Id
  private Long id;

  @Column(name = "video_codec")
  private String videoCodec;

  @Column(name = "audio_codec")
  private String audioCodec;

  private String hints;

  private Duration duration;

  @EqualsAndHashCode.Exclude
  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "media_item_id")
  private Set<MediaStream> mediaStreams = new HashSet<>();

}
