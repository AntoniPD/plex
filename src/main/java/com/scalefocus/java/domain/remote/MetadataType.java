package com.scalefocus.java.domain.remote;

public enum MetadataType {
  UNKNOWN(-1),
  MOVIE(1),
  SERIES(2),
  SEASON(3),
  EPISODE(4)
  ;

  MetadataType(int i) {
  }
}
