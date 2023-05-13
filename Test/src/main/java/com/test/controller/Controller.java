package com.test.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.entity.Person;
import com.test.exception.ResourceNotFoundException;
import com.test.repo.PersonRepo;

@RestController
@RequestMapping("/person")
public class Controller {

	@Autowired
	private PersonRepo pRepo;

	@GetMapping("/home")
	public String demo()
	{
		return "Welcome to Person Database";
	}

	@PostMapping("/save")
	public Person save(@RequestBody Person person)
	{
		return pRepo.save(person);
	}

	@GetMapping("/getAll")
	public List<Person> getAllData()
	{
		List<Person> per = pRepo.findAll();
		return per;
	}

	@GetMapping("/getById/{id}")
	public Optional<Person> getSingleData(@PathVariable(value = "id") int id)
	{
		Optional<Person> repo = pRepo.findById(id);
		return repo;
	}

	@DeleteMapping("/deleteById/{id}")
	public String deleteById(@PathVariable (value = "id") int id)
	{
		if(id>0)
		{
			pRepo.deleteById(id);
			return "Person Id"+ id +" Data Removed Successfully";
		}
		return "Person Data Not Persent, Please Enter Valid Person Id";
	}
	
	@PutMapping("/updateById/{id}")
	public Person updateById( @RequestBody Person person , @PathVariable (value = "id") int id)
	{
		Person exiting = this.pRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Data Not Present,Please Enter Valid Data Id"));
		
		exiting.setAddress(person.getAddress());
		exiting.setName(person.getName());
		exiting.setPhno(person.getPhno());
		return this.pRepo.save(exiting);
		
	}

}
