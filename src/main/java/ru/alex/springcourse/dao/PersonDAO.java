package ru.alex.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alex.springcourse.models.Person;

import java.util.List;

@Component
public class PersonDAO {

    public static JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index()  {
//        return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper());
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
//        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new PersonMapper())
//                .stream().findAny().orElse(null);
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id},
                                    new BeanPropertyRowMapper<>(Person.class))
                                    .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person VALUES(1, ?, ?, ?)",
                person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? WHERE id=?",
                updatedPerson.getName(), updatedPerson.getAge(), updatedPerson.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
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

/*
-- code for creating and connection to db:

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
 */

/*

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
 */

/*

Methods update/delete:

public Person show(int id) {
//        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);

        Person person = null;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM Person WHERE id=?");

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setEmail(resultSet.getString("email"));
            person.setAge(resultSet.getInt("age"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  person;
    }

    public void save(Person person) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO Person VALUES (1, ?, ?, ?)");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(1, person.getAge());
            preparedStatement.setString(1, person.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, Person updatedPerson) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Person SET name=?, age=?, email=? WHERE id=?");

            preparedStatement.setString(1, updatedPerson.getName());
            preparedStatement.setInt(2, updatedPerson.getAge());
            preparedStatement.setString(3, updatedPerson.getEmail());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

//        Person personToBeUpdated = show(id);
//        personToBeUpdated.setName(updatedPerson.getName());
//        personToBeUpdated.setAge(updatedPerson.getAge());
//        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM Person WHERE id=?");

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


//        people.removeIf(p -> p.getId() == id);
    }

 */