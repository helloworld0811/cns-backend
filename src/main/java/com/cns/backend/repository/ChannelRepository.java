package com.cns.backend.repository;

import com.cns.backend.model.CNSChannel;
import com.cns.backend.model.Category;
import com.cns.backend.model.ChannelType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by hku on 06.04.17.
 */
@Transactional
public interface ChannelRepository extends CrudRepository<CNSChannel, Long> {


    @Query("SELECT c FROM CNSChannel c WHERE categoryId=:categoryId")
    List<CNSChannel> findByCategoryId(@Param("categoryId") Long categoryId);

      @Query("SELECT c FROM CNSChannel c WHERE isManual=:isManual")
    List<CNSChannel> findByManual(@Param("isManual") Boolean isManual);

    @Query("SELECT c FROM CNSChannel c WHERE channelType=:channelType")
    List<CNSChannel> findByChannelType(@Param("channelType") ChannelType channelType);
}
