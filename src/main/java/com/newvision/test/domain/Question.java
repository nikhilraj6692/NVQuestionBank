package com.newvision.test.domain;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.newvision.test.constants.ApplicationConstants;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Document(collection = "Question")
public class Question {

	@Id
	private String questionId;

	@NotBlank(message = ApplicationConstants.INVALID_TITLE)
	private String title;

	@NotBlank(message = ApplicationConstants.INVALID_BODY)
	private String body;

	@NotEmpty(message = ApplicationConstants.INVALID_TAGGING)
	private List<String> tags;

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", title=" + title + ", body=" + body + ", tags=" + tags + "]";
	}

}
