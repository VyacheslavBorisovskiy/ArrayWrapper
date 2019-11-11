package com.borisovskiy;

import com.borisovskiy.exceptions.AnimalNotFoundException;
import com.borisovskiy.hierarhy.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class AnimalArrayWrapper<T extends Animal> {
    private Animal[] animals;

    public AnimalArrayWrapper(T[] animals) {
        this.animals = animals;
    }

    public void add(T animal) {
        Animal[] tmp = new Animal[animals.length + 1];
        System.arraycopy(animals, 0, tmp, 0, animals.length);
        tmp[animals.length] = animal;
        animals = tmp;
    }

    public void removeByIndex(int index) {
        Animal[] tmp = new Animal[animals.length - 1];
        if (index < animals.length) {
            for (int j = 0; j < index; j++) {
                tmp[j] = animals[j];
            }
            for (int k = index; k < animals.length - 1; k++) {
                tmp[k] = animals[k + 1];
            }
        } else throw new IndexOutOfBoundsException();
        animals = tmp;
    }

    public void remove(T animal) {
        boolean isAnimalExist = false;
        for (int i = 0; i < animals.length; i++) {
            if (animals[i].equals(animal)) {
                isAnimalExist = true;
                break;
            }
        }
        if (!isAnimalExist) throw new AnimalNotFoundException();

        Animal[] tmp = new Animal[animals.length - 1];
        for (int i = 0; i < animals.length; i++) {
            if (animals[i].equals(animal)) {
                for (int j = 0; j < i; j++) {
                    tmp[j] = animals[j];
                }
                for (int k = i; k < animals.length - 1; k++) {
                    tmp[k] = animals[k + 1];
                }
                break;
            }
        }
        animals = tmp;
    }

    public AnimalArrayWrapper<Animal> sort(Comparator<Animal> comparator) {
        Animal[] tmp = new Animal[animals.length];
        for (int i = 0; i < animals.length; i++) {
            tmp[i] = animals[i];
        }
        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp.length - 1; j++) {
                if (comparator.compare(tmp[j], tmp[j + 1]) > 0) {
                    Animal tmpObj = tmp[j];
                    tmp[j] = tmp[j + 1];
                    tmp[j + 1] = tmpObj;
                }
            }
        }

        return new AnimalArrayWrapper<>(tmp);
    }

    public void printArray() {
        System.out.println(Arrays.toString(animals));
    }

    public Animal findMaxWeight() {
        Animal bigAnimal = null;
        for (int i = 0; i < animals.length - 1; i++) {
            bigAnimal = animals[0];
            if (bigAnimal.getWeight() < animals[i + 1].getWeight()) {
                bigAnimal = animals[i].findMaxWeight(animals[i + 1]);
                animals[i + 1] = animals[0];
                animals[0] = bigAnimal;
            }
        }
        return bigAnimal;
    }

    public Animal findMinWeight() {
        Animal minAnimal = null;
        for (int i = 0; i < animals.length - 1; i++) {
            minAnimal = animals[0];
            if (minAnimal.getWeight() > animals[i + 1].getWeight()) {
                minAnimal = animals[i].findMinWeight(animals[i + 1]); // i + 1
                animals[i + 1] = animals[0];
                animals[0] = minAnimal;
            }
        }
        return minAnimal;
    }

    public void findAvgWeight() {
        double sum = 0;
        if ((animals.length % 2) != 0) {
            for (int i = 0; i < animals.length - 2; i++) {
                sum += animals[i].findAvgWeight(animals[i + 1]);
                i++;
            }
            sum += (double) (animals[animals.length - 1].getWeight()) / 2;
        } else {
            for (int i = 0; i < animals.length - 1; i++) {
                sum += animals[i].findAvgWeight(animals[i + 1]);
                i++;
            }
        }
        System.out.println("AVG = " + sum);
    }

    static class AnimalGenerator<T extends Animal> {
        private static final int MAX_ANIMALS = 7;

        static Animal[] generateRandomAnimals() {
            Animal[] animals = new Animal[MAX_ANIMALS];
            for (int i = 0; i < animals.length; i++) {
                Animal animal = generateRandomAnimal();
                animals[i] = animal;
            }
            return animals;
        }

        static Animal generateRandomAnimal() {
            Random random = new Random();
            Animal animal = null;
            switch (random.nextInt(4)) {
                case 0:
                    animal = new Seal(110, Place.WATER, 1989, 3, 20);
                    break;
                case 1:
                    animal = new Dolphin(90, Place.WATER, 2005, 6, 10);
                    break;
                case 2:
                    animal = new Panda(200, Place.LAND, 2010, 8, 15);
                    break;
                case 3:
                    animal = new Giraffe(700, Place.LAND, 2009, 1, 30);
                    break;
            }
            return animal;
        }
    }
}
