package com.scalefocus.java.repository.remote;

import com.scalefocus.java.domain.remote.MetaDataItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MetaDataItemRepository extends JpaRepository<MetaDataItem, Long> {
  @Query(nativeQuery = true, value = "SELECT * FROM metadata_items WHERE metadata_type <= :typeMaxVal")
  List<MetaDataItem> findAll(@Param("typeMaxVal") Integer typeMaxVal);
}
