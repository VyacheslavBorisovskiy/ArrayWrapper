package com.borisovskiy.hierarhy;

import java.time.LocalDate;

public class Animal {
    private int weight;
    private LocalDate dateOfBirth;
    private Place place;

    public Animal(int weight, Place place, int year, int month, int day) {
        this.weight = weight;
        this.place = place;
        dateOfBirth = LocalDate.of(year, month, day);
    }

    public int getWeight() {
        return weight;
    }

    public Place getPlace() {
        return place;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Animal findMaxWeight(Animal other) {
        if (weight >= other.weight) return this;
        else return other;
    }

    public Animal findMinWeight(Animal other) {
        if (weight <= other.weight) {
            return this;
        } else {
            return other;
        }
    }

    public double findAvgWeight(Animal other) {
        return (double) (weight + other.weight) / 2;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
