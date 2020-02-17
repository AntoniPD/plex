package com.scalefocus.java.dbconfig;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultipleDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
      return DynamicDataSourceHolder.getRouteKey();
    }
}