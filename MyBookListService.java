package org.bookstore.services;

import java.util.List;

import org.bookstore.entity.MyBookList;
import org.bookstore.repository.MyBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyBookListService 
{
	@Autowired
	private MyBookRepository mybook;
	
	public void saveMyBooks(MyBookList book)
	{
		mybook.save(book);
		
	}
	public List<MyBookList> getAllMyBooks()
	{
		return mybook.findAll();
	}
	public void deleteById(int id)
	{
		mybook.deleteById(id);
	}
}
