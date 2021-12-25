package com.revature.movietweeter.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import com.revature.movietweeter.model.Like;
import com.revature.movietweeter.model.Review;
import com.revature.movietweeter.model.User;

public class LikeDAO {

	@PersistenceContext
	private EntityManager em;
	
	//get like by Id

	@Transactional
	public Like getLikeById(int id) {
		Session session = em.unwrap(Session.class);
		Like like = session.get(Like.class, id);
		return like;

	}

	// get all likes by review

	@Transactional
	public List<Like> getLikesByReview(String id) {
		List<Like> listOfLikesByReview = em.createQuery("FROM Like l WHERE r.Id = :id", Like.class)
				.setParameter("id", id).getResultList();
		return listOfLikesByReview;
	}

	// post new like for review

	@Transactional
	public Like postLike(int id, User liker, Review review) {

		Like likeToPost = new Like(liker, review);
		em.persist(likeToPost);

		return likeToPost;
	}

	// delete like

	@Transactional
	public void deleteLikeById(int id) {

		Session session = em.unwrap(Session.class);

		Like likeToDelete = session.get(Like.class, id);

		session.remove(likeToDelete); // remove belongs to JPA, delete belongs to Hibernate

	}

}
