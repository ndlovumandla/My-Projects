package com.ndlovumandla.customdatastructure;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        RedBlackTree rbTree = new RedBlackTree();
        Random random = new Random();
        int numElements = 1000;
        int knownValue = 1; // The known value we will search for later

        // Inserting elements
        System.out.println("Inserting elements...");

        // Insert the known value into both trees first
        avlTree.insert(knownValue);
        rbTree.insert(knownValue);

        // Measure insertion time for AVL Tree
        long startTime = System.nanoTime();
        for (int i = 0; i < numElements; i++) {
            int value = random.nextInt(10000);
            avlTree.insert(value);
        }
        long endTime = System.nanoTime();
        System.out.println("AVL Tree insertion time: " + (endTime - startTime) + " ns");

        // Measure insertion time for Red-Black Tree
        startTime = System.nanoTime();
        for (int i = 0; i < numElements; i++) {
            int value = random.nextInt(10000);
            rbTree.insert(value);
        }
        endTime = System.nanoTime();
        System.out.println("Red-Black Tree insertion time: " + (endTime - startTime) + " ns");

        // Measure search time for AVL Tree
        startTime = System.nanoTime();
        System.out.println("Searching for " + knownValue + " in AVL Tree: " + avlTree.search(knownValue));
        endTime = System.nanoTime();
        System.out.println("AVL Tree search time: " + (endTime - startTime) + " ns");

        // Measure search time for Red-Black Tree
        startTime = System.nanoTime();
        System.out.println("Searching for " + knownValue + " in Red-Black Tree: " + rbTree.search(knownValue));
        endTime = System.nanoTime();
        System.out.println("Red-Black Tree search time: " + (endTime - startTime) + " ns");

        // Measure deletion time for AVL Tree
        startTime = System.nanoTime();
        avlTree.delete(knownValue);
        endTime = System.nanoTime();
        System.out.println("Deleted " + knownValue + " from AVL Tree. Validation status: " + avlTree.validate());
        System.out.println("AVL Tree deletion time: " + (endTime - startTime) + " ns");

        // Measure deletion time for Red-Black Tree
        startTime = System.nanoTime();
        rbTree.delete(knownValue);
        endTime = System.nanoTime();
        System.out.println("Deleted " + knownValue + " from Red-Black Tree. Validation status: " + rbTree.validate());
        System.out.println("Red-Black Tree deletion time: " + (endTime - startTime) + " ns");

        // Validating both trees
        System.out.println("AVL Tree validation: " + avlTree.validate());
        System.out.println("Red-Black Tree validation: " + rbTree.validate());
    }
}
