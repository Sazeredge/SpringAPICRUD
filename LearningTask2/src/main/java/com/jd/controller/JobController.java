package com.jd.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jd.dao.JobDAO;
import com.jd.model.Job;

@RestController
@CrossOrigin
public class JobController {
	
	JobDAO jobDAO = new JobDAO();
	
	@PostMapping("/save")
	public ResponseEntity<Job> insertJob(Job job) {
		jobDAO.create(job);
		return new ResponseEntity<Job>(HttpStatus.OK);
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<Job>> getJob() {
		List<Job> job = (List<Job>) jobDAO.getItems();
		return new ResponseEntity<List<Job>>(job, HttpStatus.OK);
	}
	
	@GetMapping("/getid")
	public ResponseEntity<Job> getJobByID(Job j) {
		Job job = jobDAO.getItem(j.getId());
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Job> update(@PathVariable Long id, Job j) {
		j.setId(id);
		jobDAO.update(j);
		return new ResponseEntity<Job>(HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Job> delete(@PathVariable Long id) {
		jobDAO.delete(id);
		return new ResponseEntity<Job>(HttpStatus.OK);
	}
}
