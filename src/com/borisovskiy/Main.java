package com.borisovskiy;

import com.borisovskiy.hierarhy.Animal;

import java.util.Comparator;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Animal[] animals = AnimalArrayWrapper.AnimalGenerator.generateRandomAnimals();
        AnimalArrayWrapper<Animal> wrapper = new AnimalArrayWrapper<>(animals);
        wrapper.printArray();

        for (int i = 0; i < 4; i++) {
            wrapper.add(AnimalArrayWrapper.AnimalGenerator.generateRandomAnimal());
        }

        System.out.println("-----sort by date-----");
        AnimalArrayWrapper<Animal> wrapperSortByDate = wrapper.sort(Comparator.comparing(Animal::getDateOfBirth));
        wrapperSortByDate.printArray();

        System.out.println("-----REMOVE by index------");
        wrapperSortByDate.removeByIndex(new Random().nextInt(animals.length));
        wrapperSortByDate.printArray();

        System.out.println("-----REMOVE------");
        wrapperSortByDate.remove(AnimalArrayWrapper.AnimalGenerator.generateRandomAnimal());
        wrapperSortByDate.printArray();

        System.out.println("-----sort by descending weight-----");
        AnimalArrayWrapper<Animal> wrapperByReverseWeight = wrapper.sort((o1, o2) -> Integer.compare(o2.getWeight(), o1.getWeight()));
        wrapperByReverseWeight.printArray();

        Animal bigAnimal = wrapperByReverseWeight.findMaxWeight();
        System.out.print("-----big Animal-----\n" + bigAnimal + "\n");

        System.out.println("-----sort by ascending weight-----");
        AnimalArrayWrapper<Animal> wrapperByWeight = wrapper.sort(Comparator.comparing(Animal::getWeight));
        wrapperByWeight.printArray();

        Animal smallAnimal = wrapperByWeight.findMinWeight();
        System.out.print("------small Animal-----\n" + smallAnimal + "\n");

        wrapper.findAvgWeight();
    }
}
