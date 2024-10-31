package com.ndlovumandla.customdatastructure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {
    @Test
    void testInsertAndSearch() {
        AVLTree avl = new AVLTree();
        avl.insert(30);
        avl.insert(20);
        avl.insert(40);
        avl.insert(10);
        avl.insert(25);
        
        assertTrue(avl.search(20));
        assertTrue(avl.search(25));
        assertFalse(avl.search(5));
    }

    @Test
    void testDeleteAndValidate() {
        AVLTree avl = new AVLTree();
        avl.insert(30);
        avl.insert(20);
        avl.insert(40);
        avl.insert(10);
        avl.insert(25);
        
        avl.delete(20);
        assertFalse(avl.search(20));
        assertTrue(avl.validate());
    }
}
