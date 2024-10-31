package com.ndlovumandla.customdatastructure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RedBlackTreeTest {
    @Test
    void testInsertAndSearch() {
        RedBlackTree rb = new RedBlackTree();
        rb.insert(10);
        rb.insert(20);
        rb.insert(30);
        
        assertTrue(rb.search(20));
        assertTrue(rb.search(10));
        assertFalse(rb.search(40));
    }

    @Test
    void testDeleteAndValidate() {
        RedBlackTree rb = new RedBlackTree();
        rb.insert(10);
        rb.insert(20);
        rb.insert(30);
        
        rb.delete(20);
        assertFalse(rb.search(20));
        assertTrue(rb.validate());
    }
}
