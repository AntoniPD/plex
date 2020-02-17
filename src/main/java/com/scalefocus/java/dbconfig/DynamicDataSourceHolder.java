package com.scalefocus.java.dbconfig;

public class DynamicDataSourceHolder {
  private static String routeKey;

    /**
     * Get the key of the current thread's data source routing
     */
    public static String getRouteKey()
    {
      return routeKey;
    }

    /**
     * key that binds the current thread data source routing
     * You must call the removeRouteKey() method to delete it after using it
     */
    public static void  setRouteKey(String key)
    {
        routeKey = key;
    }

    /**
     * Delete the key of the data source routing bound to the current thread
     */
    public static void removeRouteKey()
    {
        routeKey = null;
    }
}
