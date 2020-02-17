package com.scalefocus.java.domain.remote;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "metadata_items")
@JsonIgnoreProperties( {"hibernateLazyInitializer", "handler"})
public class MetaDataItem {

  @Id
  private Long id;

  @Column(name = "parent_id")
  private Long parentId;

  @Column(name = "metadata_type")
  @Enumerated(EnumType.ORDINAL)
  private MetadataType metadataType;

  private String title;

  private Double rating;

  private String summary;

  @Column(name = "tags_genre")
  private String genre;

  @Column(name = "tags_director")
  private String director;

  @Column(name = "tags_writer")
  private String writer;

  @Column(name = "tags_star")
  private String star;

  private Long year;

  @EqualsAndHashCode.Exclude
  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "metadata_item_id")
  private Set<MediaItem> mediaItems = new HashSet<>();
}
