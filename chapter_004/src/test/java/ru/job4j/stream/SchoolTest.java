package ru.job4j.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import static ru.job4j.stream.School.*;

public class SchoolTest {
    @Test
    public void isScoreMoreThan70AndLessThan100Test() {
        List<Student> students = Arrays.asList(
                new Student("Ivanov", 100),
                new Student("Petrov", 70),
                new Student("Sidorov", 1)
        );

        List<Student> result = collect(students, att -> att.getScore() >= 70 & att.getScore() <= 100);
        List<Student> expected = Arrays.asList(
                new Student("Ivanov", 100),
                new Student("Petrov", 70)
        );
        assertThat(result, is(expected));
    }

    @Test
    public void isScoreMoreThan50AndLessThan70Test() {
        List<Student> students = Arrays.asList(
                new Student("Ivanov", 100),
                new Student("Petrov", 70),
                new Student("Sobolev", 55),
                new Student("Sidorov", 1)
        );

        List<Student> result = collect(students, att -> att.getScore() >= 50 & att.getScore() < 70);
        List<Student> expected = Arrays.asList(
                new Student("Sobolev", 55)
        );
        assertThat(result, is(expected));
    }

    @Test
    public void isScoreMoreThan0AndLessThan50Test() {
        List<Student> students = Arrays.asList(
                new Student("Ivanov", 100),
                new Student("Petrov", 70),
                new Student("Sobolev", 55),
                new Student("Sidorov", 1)
        );

        List<Student> result = collect(students, att -> att.getScore() > 0 & att.getScore() < 50);
        List<Student> expected = Arrays.asList(
                new Student("Sidorov", 1)
        );
        assertThat(result, is(expected));
    }

    @Test
    public void collectStudentsToMapTest() {
        List<Student> students = Arrays.asList(
                new Student("Ivanov", 100),
                new Student("Petrov", 70),
                new Student("Sobolev", 1),
                new Student("Sidorov", 1)
        );

        Map<String, Student> result = collectStudentsToMapWithDupKeyError(students);
        Map<String, Student> expected = new HashMap<>();
        expected.put("Ivanov", new Student("Ivanov", 100));
        expected.put("Petrov", new Student("Petrov", 70));
        expected.put("Sobolev", new Student("Sobolev", 1));
        expected.put("Sidorov", new Student("Sidorov", 1));
        assertThat(result, is(expected));
    }

    @Test(expected = IllegalStateException.class)
    public void whenMapHasDuplicateKeyWithoutMergeFunctionThenRuntimeException() {
        List<Student> students = Arrays.asList(
                new Student("Ivanov", 100),
                new Student("Petrov", 70),
                new Student("Sobolev", 1),
                new Student("Sobolev", 1)
        );

        Map<String, Student> result = collectStudentsToMapWithDupKeyError(students);
        Map<String, Student> expected = new HashMap<>();
        expected.put("Ivanov", new Student("Ivanov", 100));
        expected.put("Petrov", new Student("Petrov", 70));
        expected.put("Sobolev", new Student("Sobolev", 1));
        assertThat(result, is(expected));
    }

    @Test
    public void whenMapHasDuplicateKeyThenMergeFunctionHandlesCollision() {
        List<Student> students = Arrays.asList(
                new Student("Ivanov", 100),
                new Student("Petrov", 70),
                new Student("Sobolev", 1),
                new Student("Sobolev", 1)
        );

        Map<String, Student> result = collectStudentsToMapWithDupKey(students);
        Map<String, Student> expected = new HashMap<>();
        expected.put("Ivanov", new Student("Ivanov", 100));
        expected.put("Petrov", new Student("Petrov", 70));
        expected.put("Sobolev", new Student("Sobolev", 1));
        assertThat(result, is(expected));
    }
}