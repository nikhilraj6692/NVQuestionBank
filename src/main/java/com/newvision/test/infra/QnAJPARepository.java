package com.newvision.test.infra;

import org.springframework.data.repository.CrudRepository;

import com.newvision.test.domain.Question;

public interface QnAJPARepository extends CrudRepository<Question, String>{

}
