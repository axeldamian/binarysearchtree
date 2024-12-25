package com.leibnix;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class that uses the JUnit 4 framework.
 */
public class BinaryTreeTests {

  /**
   * Test to verify that BinaryTree Heighth is correct.
   */
  @Test
  public void testCheckHeight() {

    // Creating an instance of the BinaryTree String class
    BinaryTree<String> b = new BinaryTree<String>();

    // Input parameters
    String param1 = "a";
    String param2 = "b";
    
    // Calling the sum method with parameters param1 and param2
    b.add(param1);
    b.add(param2);

    // Expected result of adding param1 and param2
    int expectedResult = 2;

     // Verification of the expected result
    assertEquals(expectedResult, b.getHeight());
  }

  /**
   * Test to verify that BinaryTree Heighth is correct with Integers.
   */
  @Test
  public void testCheckHeightWithIntegers() {

    // Creating an instance of the BinaryTree int class
    BinaryTree<Integer> b = new BinaryTree<Integer>();

    // Input parameters
    Integer param1 = 1;
    Integer param2 = 2;
    Integer param3 = 3;
    Integer param4 = 4;
    
    // Calling the sum method with parameters
    b.add(param1);
    b.add(param2);
    b.add(param3);
    b.add(param4);

    // Expected result of adding parameters
    int expectedResult = 4;

     // Verification of the expected result
    assertEquals(expectedResult, b.getHeight());
  }

}