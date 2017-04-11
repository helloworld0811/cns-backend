package com.cns.backend.repository.example;

import com.cns.backend.model.example.User;

import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

@Transactional
public interface UsersRepository extends CrudRepository<User, Long> {

}
