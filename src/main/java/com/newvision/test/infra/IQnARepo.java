package com.newvision.test.infra;

import java.util.List;

import com.newvision.test.domain.Question;
import com.newvision.test.utils.QnAException;

public interface IQnARepo {
	public List<Question> fetchQuestions(String questionId, List<String> tags);

	public void postQuestion(Question requestObj);

	public void editQuestion(Question requestObj) throws QnAException;
}
