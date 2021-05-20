package ru.mail.polis.homework.collections.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

/**
 * Написать программу, которая из текста (стрим строк), возвращает 10 самых популярных слов (В порядке убывания частоты).
 * Словом считается последовательность символов из букв и цифр от пробела до пробела или знака препинания (.,!:-?;).
 * (Посмотрите статические методы в классе Character)
 * <p>
 * В исходном стриме строка - это строка из книги, которая может содержать в себе много слов.
 * <p>
 * Если слов в стриме меньше 10, то вывести все слова. Если слова имеют одинаковое количество упоминаний, то выводить
 * в лексикографическом порядеке.
 * Слова надо сравнивать без учета регистра.
 * 3 балла
 */
public class WordFrequency {

    /**
     * Задачу можно решить без единого условного оператора, только с помощью стримов.
     */
    public static List<String> wordFrequency(Stream<String> lines) {
        return lines
                .map(String::toLowerCase)
                .map(str -> str.split("[.,!:-?;]?\\s|[.,!:-?;]"))
                .flatMap(Arrays::stream)
                .collect(Collectors.groupingBy(str -> str, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(comparing(Map.Entry<String, Long>::getValue).reversed().thenComparing(Map.Entry::getKey))
                .limit(10)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }


}
