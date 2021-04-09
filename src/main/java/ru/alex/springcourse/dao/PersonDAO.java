package ru.alex.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.alex.springcourse.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;

    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Person> index()  {
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";
            ResultSet resultset = statement.executeQuery(SQL);
            while(resultset.next()) {
                Person person = new Person();

                person.setId(resultset.getInt("id"));
                person.setName(resultset.getString("name"));
                person.setAge(resultset.getInt("age"));
                person.setEmail(resultset.getString("email"));

                people.add(person);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return people;
    }

    public Person show(int id) {
//        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
        return  null;
    }

    public void save(Person person) {
//        person.setId(++PEOPLE_COUNT);
//        people.add(person);

        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO Person VALUES(" + 1
                    + ",'" + person.getName()
                    + "'," + person.getAge()
                    + ",'" + person.getEmail()
                    + "')";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, Person updatedPerson) {
//        Person personToBeUpdated = show(id);
//
//        personToBeUpdated.setName(updatedPerson.getName());
//        personToBeUpdated.setAge(updatedPerson.getAge());
//        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
//        people.removeIf(p -> p.getId() == id);
    }
}


/*


-- create table Person(
--     id int,
--     name varchar,
--     age int,
--     email varchar
-- );
--
-- insert into Person values (1,'Dustin Henderson', 12, 'dustin.henderson@hawkins.com');
-- insert into Person values (2,'Lucas Sinclair', 12, 'lucas.sinclair@hawkins.com');
-- insert into Person values (3,'Mike Wheeler', 12, 'mike.wheeler@hawkins.com');
-- insert into Person values (4,'Will Byers', 12, 'will.byers@hawkins.com');

select * from person;

//    private List<Person> people;

//    {
//        people = new ArrayList<>();
//
//        people.add(new Person(++PEOPLE_COUNT, "Dustin Henderson", 12, "dustin@hawkins.com"));
//        people.add(new Person(++PEOPLE_COUNT, "Lucas Sinclair", 12, "lucas@hawkins.com"));
//        people.add(new Person(++PEOPLE_COUNT, "Mike Wheeler", 12, "mike@hawkins.comm"));
//        people.add(new Person(++PEOPLE_COUNT, "Will Byers", 12, "will@hawkins.com"));
//    }
 */