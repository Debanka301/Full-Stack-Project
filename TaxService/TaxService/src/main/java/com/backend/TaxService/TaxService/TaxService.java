package com.backend.TaxService.TaxService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.TaxService.Entity.Tax;
import com.backend.TaxService.Entity.TaxInput;
import com.backend.TaxService.Repository.TaxRepository;

@Service
public class TaxService {
	
	@Autowired
	private TaxRepository taxRepo;
	
	public List<Tax> getAllTaxes(){
		return taxRepo.findAll();
	}
	
	public Tax saveTax(TaxInput taxInput) {
		Tax tax= new Tax();
		tax.setEmpId(taxInput.getEmpId());
		tax.setSalary(taxInput.getSalary());
		tax.setTaxPerc(calculateTaxPerc(tax.getSalary()));
		tax.setTaxAmount(calculateTaxAmount(tax.getSalary(), tax.getTaxPerc()));
		tax.setInHand(calculateInHand(tax.getSalary(), tax.getTaxAmount()));
		
		return taxRepo.save(tax);
		
	}
	
	public int calculateTaxPerc(Integer salary) {
		if(salary>=1500000) {
			return 30;
		}
		else if(salary>=1000000 && salary<1500000) {
			return 20;
		}
		else if(salary>=700000 && salary<1000000) {
			return 10;
		}
		else {
			return 5;
		}
	}
	
	public int calculateTaxAmount(Integer salary, Integer taxPerc) {
		return (salary*taxPerc)/100;
	}
	
	public int calculateInHand(Integer salary, Integer taxAmount) {
		return salary-taxAmount;
	}
	
	public Tax getTaxByEmpId(Integer empId) {
		return taxRepo.getTaxByEmpId(empId);
	}

}
