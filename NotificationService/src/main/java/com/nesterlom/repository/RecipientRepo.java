package com.nesterlom.repository;

import com.nesterlom.entity.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface RecipientRepo {

    List<Recipient> findAllByUserId(Integer userId);
}
