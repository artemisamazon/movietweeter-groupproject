package com.revature.movietweeter.controller;

import com.revature.movietweeter.model.Like;
import com.revature.movietweeter.model.Review;
import com.revature.movietweeter.model.User;

import org.springframework.web.bind.annotation.RestController;
import com.revature.movietweeter.service.LikeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.revature.movietweeter.dto.AddReviewDTO;
import com.revature.movietweeter.exception.LikeNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class LikeController {

	@Autowired
	private LikeService ls;
	
	
	//post likes

	@PostMapping(path = "/likes")
	public ResponseEntity<Object> postLike(@RequestBody LikeDAO dao) {
		
	LIke LikeToPost = (Like) ls.getSession().getAttribute("currentLike");
		
		Like postedLike;
	}

	//delete likes

	@DeleteMapping(path = "/likes/{id}/delete")
	public Object deleteLikeById(@PathVariable("id") int id) {
		try {
			ls.deleteLikeById(id);

			return "Successfully deleted User with id " + id;
		} catch (LikeNotFoundException e) {
			return e.getMessage();
		}
	}
	
	//get like by Id
	
	@GetMapping(path = "/likes/{id}")
	public List<Like> getlikeById() {
		return ls.getLikeById();
	
	
	}
	// get likes by review

	@GetMapping(path = "/reviews/{id}/likes")
	public ResponseEntity<Object> getAllLikesByReview(@PathVariable("id") String id) {
		List<Like> listOfLikesByReview = ls.getLikesByReview(id);
		return ResponseEntity.status(200).body(listOfLikesByReview);

	}

}
