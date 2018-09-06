package com.leadics.poll.rest;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.leadics.poll.dao.QuestionsHome;
import com.leadics.poll.dto.Questions;

@RestController
public class PollController {
	
	@RequestMapping(method=RequestMethod.GET,path="/getPolls")
	public String getPolls() {
		return "rdkjghdfiko";
	}
	@RequestMapping(method=RequestMethod.GET,path="/getPoll")
	public List<Questions> getPoll() {
		List<Questions>  questionsList=new QuestionsHome().getQuestionsByPoll(1);
		System.out.println(questionsList);
		return null;
	}

}
