package com.project.controller;

import com.project.entity.Book;
import com.project.entity.ReaderPerson;
import com.project.services.ReaderPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/people")
public class ReaderRersonController {

    @Autowired
    private ReaderPersonService readerPersonService;
    @GetMapping()
    public String index(Model model){
        model.addAttribute("people",readerPersonService.findAll());
        return "library/all_reader_person";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person",readerPersonService.findOne(id));
        List<Book> books=readerPersonService.getBooks(id);
        model.addAttribute("books",books);
        return "library/show_reader_person";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") ReaderPerson person){
        return "library/new_person";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid ReaderPerson person, BindingResult result, Model model){
        if (result.hasErrors())
            return "library/new_person";

        readerPersonService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable("id") int id, Model model){
        model.addAttribute("person",readerPersonService.findOne(id));
        return "library/update_person";
    }

    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("person") @Valid ReaderPerson person,BindingResult result,@PathVariable("id") int id){
        if (result.hasErrors())
            return "library/update_person";
        readerPersonService.update(id,person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        readerPersonService.delete(id);
        return "redirect:/people";
    }
}
