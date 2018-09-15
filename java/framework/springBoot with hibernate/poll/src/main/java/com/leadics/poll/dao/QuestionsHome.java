package com.leadics.poll.dao;
// Generated Sep 3, 2018 10:00:06 PM by Hibernate Tools 5.3.1.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.query.Query;

import com.leadics.poll.dto.Questions;
import com.leadics.poll.util.HibernateUtil;

/**
 * Home object for domain model class Questions.
 * @see xx.yy.Questions
 * @author Hibernate Tools
 */
public class QuestionsHome {


	
	private static final Log log = LogFactory.getLog(QuestionsHome.class);
	private  Session session = HibernateUtil.getCurrentSession();
	public List<Questions> getQuestionsByPoll(int pollId) {
		Query query = session.createQuery("from com.leadics.poll.dto.Questions q where q.pollId=: pollId");
		query.setParameter("pollId", pollId); 
		return query.list();
	}
	

}
