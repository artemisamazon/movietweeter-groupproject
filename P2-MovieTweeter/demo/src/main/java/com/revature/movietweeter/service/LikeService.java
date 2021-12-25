package com.revature.movietweeter.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.revature.movietweeter.annotation.AuthorizedUser;
import com.revature.movietweeter.dao.LikeDAO;
import com.revature.movietweeter.dao.ReviewDAO;
import com.revature.movietweeter.dto.AddReviewDTO;
import com.revature.movietweeter.dto.ResponseErrorDTO;
import com.revature.movietweeter.exception.InvalidParameterException;
import com.revature.movietweeter.exception.InvalidRatingException;
import com.revature.movietweeter.exception.LikeNotFoundException;
import com.revature.movietweeter.exception.MovieNotFoundException;
import com.revature.movietweeter.model.Review;
import com.revature.movietweeter.model.User;
import com.revature.movietweeter.model.Like;

//Autowiring feature of spring framework enables you to inject the object dependency implicitly. 
//It internally uses setter or constructor injection. Autowiring can't be used to inject primitive 
//and string values. It works with reference only.

@Service
public class LikeService {

	@Autowired
	private LikeDAO ld;

	public List<Like> getLikesByReview(String id) {
		return ld.getLikesByReview(id);
	}

	public void deleteLikeById(int id) throws LikeNotFoundException {
		Like like = ld.getLikeById(id);

		// Check if like that we want to delete actually exists or not
		if (like == null) {
			throw new LikeNotFoundException("There is no like to delete");
		}

		// User does exist, so go ahead and delete user
		ld.deleteLikeById(id);

	}

	public void getLikeById(int id) throws LikeNotFoundException {
		Like like = ld.getLikeById(id);
		if (like == null) {
			throw new LikeNotFoundException("Like does not exist");
		}

	}

}
