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

        people.add(new Person(++PEOPLE_COUNT, "Dustin Henderson", 12, "dustin@hawkins.com"));
        people.add(new Person(++PEOPLE_COUNT, "Lucas Sinclair", 12, "lucas@hawkins.com"));
        people.add(new Person(++PEOPLE_COUNT, "Mike Wheeler", 12, "mike@hawkins.comm"));
        people.add(new Person(++PEOPLE_COUNT, "Will Byers", 12, "will@hawkins.com"));
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

    public void update(int id, Person updatedPerson) {
        Person personToBeUpdated = show(id);

        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }
}