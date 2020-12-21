package com.newvision.test.infra;

import org.springframework.data.repository.CrudRepository;

import com.newvision.test.domain.Counter;

public interface CounterJPARespository extends CrudRepository<Counter, String>{

}
