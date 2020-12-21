package com.newvision.test.web;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newvision.test.constants.ApplicationConstants;
import com.newvision.test.core.IQnAService;
import com.newvision.test.domain.Question;
import com.newvision.test.utils.QnAException;

@RestController
@RequestMapping(path = "/qna")
public class NVQuestionBankResource {

	private static Logger logger = LoggerFactory.getLogger(NVQuestionBankResource.class);

	@Autowired
	private IQnAService quesAnsService;

	@Autowired
	private MessageSource messageSource;

	/**
	 * Rest resource to fetch all the questions from the service layer.
	 * 
	 * @param questionId
	 * @param tags
	 * @return List<Questions>
	 * @throws QnAException
	 */
	@GetMapping(path = "/questions")
	public List<Question> getQuestions(@RequestParam(name = "questionId", required = false) String questionId,
			@RequestParam(name = "tags", required = false) List<String> tags) throws QnAException {
		logger.trace("NVQuestionBankResource.getQuestions starts");

		// validate question id
		if (StringUtils.isNotBlank(questionId) && !questionId.startsWith(ApplicationConstants.QUES)) {
			throw new QnAException(ApplicationConstants.INVALID_QUESTION_ID);
		}

		List<Question> questionsList = quesAnsService.fetchQuestions(questionId, tags);

		logger.trace("NVQuestionBankResource.getQuestions ends");
		return null != questionsList ? questionsList : Arrays.asList();

	}

	@PostMapping(path = "/question")
	public Response addQuestion(@RequestBody @Valid Question requestObj) {
		logger.trace("NVQuestionBankResource.addQuestion ends");
		if (null != requestObj) {
			quesAnsService.postQuestion(requestObj);
			String message = messageSource.getMessage(ApplicationConstants.QUES_CREATED_SUCCESSFULLY, new Object[] {},
					Locale.getDefault());
			return Response.status(Status.CREATED).entity(message).build();
		}
		logger.trace("NVQuestionBankResource.addQuestion ends");
		return Response.noContent().build();

	}

	@PutMapping(path = "/question")
	public Response editQuestion(@RequestBody @Valid Question requestObj) throws QnAException {
		logger.trace("NVQuestionBankResource.editQuestion ends");
		if (null != requestObj) {
			// validate request
			if (StringUtils.isBlank(requestObj.getQuestionId()) || (StringUtils.isNotBlank(requestObj.getQuestionId())
					&& !requestObj.getQuestionId().startsWith(ApplicationConstants.QUES))) {
				throw new QnAException(ApplicationConstants.INVALID_QUESTION_ID);
			}

			quesAnsService.editQuestion(requestObj);
			String message = messageSource.getMessage(ApplicationConstants.QUES_MODIFIED_SUCCESSFULLY, new Object[] {},
					Locale.getDefault());
			return Response.status(Status.OK).entity(message).build();
		}
		logger.trace("NVQuestionBankResource.editQuestion ends");
		return Response.noContent().build();

	}

}
