package org.bookstore.controller;

import java.util.*;

import org.bookstore.entity.Book;
import org.bookstore.services.BookService;
import org.bookstore.services.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.bookstore.entity.MyBookList;

@Controller
public class BookController {
	@Autowired
	private BookService ser;

	@Autowired
	private MyBookListService myBookService;

	@GetMapping("/")
	public String home() // create link
	{
		return "home"; // return to the home page.
	}

	@GetMapping("/book_register")
	public String bookRegister() {
		return "bookRegister"; // return to the book register page.
	}

	@GetMapping("/available_books")
	public ModelAndView getAllBook() {
		List<Book> list = ser.getAllBook();
		return new ModelAndView("bookList", "book", list);
	}

	@PostMapping("/save")
	public String addBook(@ModelAttribute Book b) {
		ser.save(b);
		return "redirect:/available_books";

	}

	@GetMapping("/my_books")
	public String getMyBooks(Model model) {
		List<MyBookList> list = myBookService.getAllMyBooks();
		model.addAttribute("book", list);
		return "myBooks";
	}

	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id") int id) {
		Book b = ser.getBookById(id);
		MyBookList mb = new MyBookList(b.getId(), b.getName(), b.getAuthor(), b.getPrice());
		myBookService.saveMyBooks(mb);
		return "redirect:/my_books";
	}

	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id, Model model) {
		Book b = ser.getBookById(id);
		model.addAttribute("book", b);
		return "bookEdit";
	}

	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") int id) {
		ser.deleteById(id);
		return "redirect:/available_books";
	}

	@RequestMapping("/admin")
	public String admin() {
		return "admin";
	}

	@RequestMapping("/admin_home")
	public String adminHome() {
		return "adminHome";
	}

	@RequestMapping("/book_category")
	public String bookCategory() {
		return "bookCategory";
	}

	@RequestMapping("/view_category")
	public String viewCategory() {
		return "viewCategory";
	}

}
