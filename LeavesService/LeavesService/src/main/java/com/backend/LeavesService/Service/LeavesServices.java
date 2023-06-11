package com.backend.LeavesService.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.LeavesService.Entity.Leaves;
import com.backend.LeavesService.Repository.LeavesRepository;

@Service
public class LeavesServices {
	
	@Autowired
	private LeavesRepository LeavesRepo;
	
	public List<Leaves> getAllLeaves(){
		return LeavesRepo.findAll();
	}
	
	public List<Leaves> getLeaveByUserId(Integer userId) {
		return LeavesRepo.getLeavesByEmployeeId(userId);
	}
	
	public Leaves saveLeaves(Leaves leaves) {
		return LeavesRepo.save(leaves);
	}
	
	public Leaves updateLeaves(Leaves leaves, Integer id) {
		Optional<Leaves> le= LeavesRepo.findById(id);
		Leaves l= le.get();
		
		l.setStartDate(leaves.getStartDate());
		l.setEndDate(leaves.getEndDate());
		l.setReason(leaves.getReason());
		l.setId(leaves.getId());
		
		return LeavesRepo.save(l);
	}
	
	public String deleteLeaves(Integer id) {
		LeavesRepo.deleteById(id);
		return "Leave with id "+id+" is deleted";
	}

}
