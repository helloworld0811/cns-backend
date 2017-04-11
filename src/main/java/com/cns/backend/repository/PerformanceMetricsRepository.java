package com.cns.backend.repository;

import com.cns.backend.model.CNSChannel;
import com.cns.backend.model.PerformanceMetrics;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by hku on 06.04.17.
 */
@Transactional
public interface PerformanceMetricsRepository extends CrudRepository<PerformanceMetrics, Long> {

    //ownerChannelId

    @Query("SELECT p FROM PerformanceMetrics p WHERE ownerChannelId=:ownerChannelId")
    List<CNSChannel> findByOwnerChannel(@Param("ownerChannelId") Long ownerChannelId);

}
