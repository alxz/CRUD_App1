package ru.alex.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.alex.springcourse.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person( ++PEOPLE_COUNT, "Dustin"));
        people.add(new Person(++PEOPLE_COUNT, "Will"));
        people.add(new Person(++PEOPLE_COUNT, "Mike"));
        people.add(new Person(++PEOPLE_COUNT, "Lucas"));
        people.add(new Person(++PEOPLE_COUNT, "Eleven"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);

    }
}
