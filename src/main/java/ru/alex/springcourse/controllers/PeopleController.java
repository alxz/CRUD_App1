package ru.alex.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alex.springcourse.dao.PersonDAO;
import ru.alex.springcourse.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        // Fetch all peoples objects by DAO and pass it to the view(s)
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        // get one account by id using DAO then pass it to the view
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
//        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping("")
    public String create( @ModelAttribute("person") Person person ) {
        personDAO.save(person);
        return "redirect:/people";
    }
}


/*

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }
 */