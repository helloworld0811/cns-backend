package com.cns.backend.repository;

import com.cns.backend.model.CNSChannel;
import com.cns.backend.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by hku on 07.04.17.
 */
@Transactional
public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE channelType=:channelType")
    List<Category> findByChannelType(@Param("channelType") String channelType);

}
