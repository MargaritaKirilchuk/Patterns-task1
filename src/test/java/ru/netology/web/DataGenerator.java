package ru.netology.web;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;



public class DataGenerator {
    public DataGenerator() {
    }

        public static String city() {
            String[] city = {"Санкт-Петербург", "Москва", "Казань", "Мурманск", "Вологда"};
            int cityNumber = 1;
            int randomIndexCity = (int) (Math.random() * cityNumber);
            String randomCity = city[randomIndexCity];
            return randomCity;
        }

        public static String validDate(int days) {
            LocalDate today = LocalDate.now();
            LocalDate date = today.plusDays(days);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            String validDate= date.format(formatter);
            return validDate;
        }

        public static String name() {
            Faker faker = new Faker(new Locale("ru"));
            return faker.name().fullName();
        }

        public static String phone() {
            Faker faker = new Faker(new Locale("ru"));
            return faker.phoneNumber().phoneNumber();
        }

        public static String invalidDate() {
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            String invalidDate = today.format(formatter);
            return invalidDate;
        }

        public static String lettersInDate() {
            String[] letters = {"aaaa","bbbb","cccc","dddd","eeeee"};
            int lettersNumber = 5;
            int randomIndexLetters = (int) (Math.random() * lettersNumber);
            String randomLetters = letters[randomIndexLetters];
            return randomLetters;
        }
    }

