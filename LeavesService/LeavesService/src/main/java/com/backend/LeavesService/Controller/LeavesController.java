package com.backend.LeavesService.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.LeavesService.Entity.Leaves;
import com.backend.LeavesService.Service.LeavesServices;

@CrossOrigin("*")
@RestController
public class LeavesController {
	
	@Autowired
	private LeavesServices leavesServices;
	
	@GetMapping("/leaves/all")
	public List<Leaves> getAllLeaves(){
		return leavesServices.getAllLeaves();
	}
	
	@GetMapping("/leaves/{id}")
	public List<Leaves> getLeavesByUserId(@PathVariable Integer id) {
		return leavesServices.getLeaveByUserId(id);
	}
	
	@PostMapping("/leaves/save")
	public Leaves saveLeaves(@RequestBody Leaves leaves) {
		return leavesServices.saveLeaves(leaves);
	}
	
	@PutMapping("/leaves/update/{id}")
	public Leaves updateLeaves(@RequestBody Leaves leaves, @PathVariable Integer id) {
		return leavesServices.updateLeaves(leaves, id);
	}

	@DeleteMapping("/leaves/delete/{id}")
	public String deleteLeaves(@PathVariable Integer id) {
		return leavesServices.deleteLeaves(id);
	}
}
