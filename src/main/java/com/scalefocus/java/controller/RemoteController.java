package com.scalefocus.java.controller;

import com.scalefocus.java.domain.remote.MediaItem;
import com.scalefocus.java.domain.remote.MediaStream;
import com.scalefocus.java.domain.remote.MetaDataItem;
import com.scalefocus.java.domain.remote.MetadataType;
import com.scalefocus.java.repository.remote.MediaItemRepository;
import com.scalefocus.java.repository.remote.MediaStreamRepository;
import com.scalefocus.java.repository.remote.MetaDataItemRepository;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RemoteController.BASE_URL)
public class RemoteController {
  public static final String BASE_URL = "/api/remote";


  private final MetaDataItemRepository metaDataItemRepository;

  private final MediaItemRepository mediaItemRepository;

  private final MediaStreamRepository mediaStreamRepository;

  @Autowired
  public RemoteController(MetaDataItemRepository metaDataItemRepository, MediaItemRepository mediaItemRepository, MediaStreamRepository mediaStreamRepository) {
    this.metaDataItemRepository = metaDataItemRepository;
    this.mediaItemRepository = mediaItemRepository;
    this.mediaStreamRepository = mediaStreamRepository;
  }

  @GetMapping("/mediaitems")
  public ResponseEntity<List<MediaItem>> getMediaItems() {
    List<MediaItem> mediaItems = mediaItemRepository.findAll();
    if (mediaItems.isEmpty()) {
      return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(mediaItems, HttpStatus.OK);
  }

  @GetMapping("/metadataitems")
  public ResponseEntity<List<MetaDataItem>> getMetadataItems() {
    List<MetaDataItem> metaDataItems = metaDataItemRepository.findAll(MetadataType.values().length + 1);
    if (metaDataItems.isEmpty()) {
      return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(metaDataItemRepository.findAll(MetadataType.values().length + 1), HttpStatus.OK);
  }

  @GetMapping("/mediastreams")
  public ResponseEntity<List<MediaStream>> getMediaStreams() {
    List<MediaStream> mediaStreams = mediaStreamRepository.findAll();
    if (mediaStreams.isEmpty()) {
      return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(mediaStreams, HttpStatus.OK);
  }
}
