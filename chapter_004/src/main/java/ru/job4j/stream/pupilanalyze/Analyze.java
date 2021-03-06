package ru.job4j.stream.pupilanalyze;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Analyze {

    public static double averageScore(Stream<Pupil> stream) {
        return stream
                .map(pupil -> pupil.getSubjects())
                .flatMap(subjects -> subjects.stream())
                .mapToInt(value -> value.getScore())
                .average()
                .getAsDouble();
    }

    public static List<Tuple> averageScoreBySubject(Stream<Pupil> stream) {
        // берем каждого ученика
        // и считаем средний балл по всем предметам, по которым у него есть запись
        return
                stream
                        .map(entry ->
                                new Tuple(
                                        entry.getName(),
                                        entry.getSubjects()
                                                .stream()
                                                .mapToInt(value -> value.getScore()).average().getAsDouble()))
                        .collect(Collectors.toList());
    }

    public static List<Tuple> averageScoreByPupil(Stream<Pupil> stream) {
        Map<String, Double> map =
                stream
                        .map(pupil -> pupil.getSubjects())
                        .flatMap(subjects -> subjects.stream())
                        .collect(Collectors.groupingBy(
                                Subject::getName,
                                Collectors.averagingInt(Subject::getScore)));

        List<Tuple> result = map.entrySet().stream()
                .map(entry -> new Tuple(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        return result;
    }

    public static Tuple bestStudent(Stream<Pupil> stream) {
        List<Tuple> tupleList =
                stream
                        .map(entry ->
                                new Tuple(
                                        entry.getName(),
                                        entry.getSubjects()
                                                .stream()
                                                .mapToInt(value -> value.getScore()).sum()))
                        .collect(Collectors.toList());
        return tupleList.stream().max(Comparator.comparing(Tuple::getScore)).get();
    }

    public static Tuple bestSubject(Stream<Pupil> stream) {
        Map<String, Double> map =
                stream
                        .map(pupil -> pupil.getSubjects())
                        .flatMap(subjects -> subjects.stream())
                        .collect(Collectors.groupingBy(
                                Subject::getName,
                                Collectors.summingDouble(Subject::getScore)));

        List<Tuple> result = map.entrySet().stream()
                .map(entry -> new Tuple(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return result.stream().max(Comparator.comparing(Tuple::getScore)).get();
    }
}
