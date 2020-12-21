package com.newvision.test.infra;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.newvision.test.constants.ApplicationConstants;
import com.newvision.test.domain.Counter;
import com.newvision.test.domain.Question;
import com.newvision.test.utils.QnAException;

@Repository
public class QnARepoImpl implements IQnARepo {

	private static Logger logger = LoggerFactory.getLogger(QnARepoImpl.class);

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private QnAJPARepository qnaRepository;

	@Autowired
	private CounterJPARespository counterRepository;

	@Override
	public List<Question> fetchQuestions(String questionId, List<String> tags) {
		logger.trace("QnARepoImpl.fetchQuestions starts");
		Criteria criteria = new Criteria();

		if (StringUtils.isNotBlank(questionId)) {
			criteria.and(ApplicationConstants.ID).is(questionId);
		}

		if (!CollectionUtils.isEmpty(tags)) {
			criteria.and(ApplicationConstants.TAGS).in(tags);
		}

		Query query = new Query().addCriteria(criteria);

		logger.trace("QnARepoImpl.fetchQuestions ends");
		return mongoTemplate.find(query, Question.class);
	}

	@Override
	public void postQuestion(Question requestObj) {
		logger.trace("QnARepoImpl.postQuestion starts");
		logger.trace("QnARepoImpl.postQuestion ends");

		// prepare unique id
		Counter counter = setUniqueId(requestObj);

		qnaRepository.save(requestObj);

		// increment and save
		counter.setCount(counter.getCount() + 1);
		counterRepository.save(counter);

	}

	@Override
	public void editQuestion(Question requestObj) throws QnAException {
		logger.trace("QnARepoImpl.editQuestion starts");

		if (qnaRepository.findById(requestObj.getQuestionId()).isPresent()) {
			qnaRepository.save(requestObj);
		} else {
			throw new QnAException(ApplicationConstants.QUES_ID_NOT_EXISTS);
		}
		logger.trace("QnARepoImpl.editQuestion ends");
	}

	private Counter setUniqueId(Question requestObj) {
		logger.trace("QnARepoImpl.setUniqueId starts");
		Optional<Counter> counter = counterRepository.findById(ApplicationConstants.QUESTION_ID);
		int count = 0;
		if (counter.isPresent()) {
			count = counter.get().getCount() + 1;
			requestObj.setQuestionId(ApplicationConstants.QUES
					+ LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy")) + count);
		}
		logger.trace("QnARepoImpl.setUniqueId ends");
		return counter.get();
	}

}
