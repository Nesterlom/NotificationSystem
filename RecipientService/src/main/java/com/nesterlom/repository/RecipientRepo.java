package com.nesterlom.repository;


import com.nesterlom.entity.Recipient;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipientRepo extends JpaRepository<Recipient, Integer> {

    List<Recipient> findAllByUserId(Integer userId);

    //LIMIT and OFFSET

    @Query(nativeQuery = true, value = "SELECT * from recipients LIMIT ?1 offset ?2")
    Recipient[] findPortion(Integer limit, Integer offset);

    @Query(nativeQuery = true, value = "SELECT * from recipients LIMIT ?1 offset ?2")
    List<Recipient> findPortion2(Integer limit, Integer offset);

    //todo check count querys
}