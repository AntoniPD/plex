package com.scalefocus.java.controller;

import com.scalefocus.java.payload.ApiResponse;
import com.scalefocus.java.service.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(DataSourceController.BASE_URL)
public class DataSourceController {
  public static final String BASE_URL = "/api/datasource";

  private DataSourceService dataSourceService;

  @Autowired
  DataSourceController(DataSourceService dataSourceService) {
    this.dataSourceService = dataSourceService;
  }

  @PostMapping("/{name}")
  public ResponseEntity<ApiResponse> changeDataSource(@PathVariable String name) {
    dataSourceService.createDataSource(name);
    return new ResponseEntity<>(new ApiResponse(Boolean.TRUE, "data source added"), HttpStatus.OK);
  }
}
