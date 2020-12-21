package com.newvision.test.core;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newvision.test.domain.Question;
import com.newvision.test.infra.IQnARepo;
import com.newvision.test.utils.QnAException;

@Service
public class QnAServiceImpl implements IQnAService {

	private static Logger logger = LoggerFactory.getLogger(QnAServiceImpl.class);

	@Autowired
	private IQnARepo quesAnsRepo;

	@Override
	public List<Question> fetchQuestions(String questionId, List<String> tags) {
		logger.trace("QnAServiceImpl.fetchQuestions starts");
		logger.trace("QnAServiceImpl.fetchQuestions ends");
		return quesAnsRepo.fetchQuestions(questionId, tags);
	}

	@Override
	public void postQuestion(@Valid Question requestObj) {
		logger.trace("QnAServiceImpl.postQuestion starts");
		logger.trace("QnAServiceImpl.postQuestion ends");
		quesAnsRepo.postQuestion(requestObj);
	}

	@Override
	public void editQuestion(Question requestObj) throws QnAException {
		logger.trace("QnAServiceImpl.editQuestion starts");
		logger.trace("QnAServiceImpl.editQuestion ends");
		quesAnsRepo.editQuestion(requestObj);
		
	}

}
