package com.project.controller;

import com.project.DAO.BookDAO;
import com.project.DAO.ReaderPersonDAO;
import com.project.entity.Book;
import com.project.entity.ReaderPerson;
import com.project.services.BooksService;
import com.project.services.ReaderPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {


    @Autowired
    private BooksService booksService;
    @Autowired
    private ReaderPersonService readerPersonService;
    @GetMapping()
    public String index(Model model){
        model.addAttribute("books",booksService.findAll());
        return "library/list_of_books";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "library/new_book";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult result){
        if (result.hasErrors())
            return "library/new_book";
        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id,Model model,@ModelAttribute("person") ReaderPerson reader){
        model.addAttribute("book",booksService.findOne(id));
        model.addAttribute("owner",booksService.getOwner(id));
        if (booksService.getOwner(id)==null)
            model.addAttribute("readers",readerPersonService.findAll());
        return "library/show_book";
    }

    @PatchMapping("/{id}/choosing")
    public String chooseOwner(@PathVariable("id") int id,@ModelAttribute("person") ReaderPerson reader){
        booksService.choosePerson(id,reader);
        return "redirect:/books";
    }

    @PatchMapping("/{id}")
    public String deletePersonFromBook(@PathVariable("id") int id){
        booksService.deleteBookFromPerson(id);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable("id") int id,Model model){
        model.addAttribute("book",booksService.findOne(id));
        return "library/update_book";
    }

    @PatchMapping("/{id}/update")
    public String editBook(@PathVariable("id") int id,@ModelAttribute("book") @Valid Book book,BindingResult result){
        if (result.hasErrors())
            return "library/update_book";
        booksService.update(id,book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteBook(@PathVariable("id") int id){
        booksService.delete(id);
        return "redirect:/books";
    }
}
