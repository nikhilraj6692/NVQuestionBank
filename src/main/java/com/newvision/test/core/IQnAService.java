package com.newvision.test.core;

import java.util.List;

import com.newvision.test.domain.Question;
import com.newvision.test.utils.QnAException;

public interface IQnAService {
	public List<Question> fetchQuestions(String questionId, List<String> tags);

	public void postQuestion(Question requestObj);

	public void editQuestion(Question requestObj) throws QnAException;
}
