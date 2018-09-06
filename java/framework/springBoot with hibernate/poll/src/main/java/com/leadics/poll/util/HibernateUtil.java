package com.leadics.poll.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static Session session;

	public static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session getSession() throws HibernateException {

		Session retSession = null;
		try {
			retSession = sessionFactory.openSession();
			retSession.beginTransaction();
		} catch (HibernateException he) {
			System.err.println("Exception while getting session.. ");
			he.printStackTrace();
		}
		if (retSession == null) {
			System.err.println("session is discovered null");
		}
		return retSession;
	}

	public static Session getCurrentSession() throws HibernateException {
		try {
			if (session == null) {
				session = sessionFactory.getCurrentSession();
				session.beginTransaction();
			}
		} catch (HibernateException he) {
			System.err.println("Exception while getting session.. ");
			he.printStackTrace();
		}
		return session;
	}
}
