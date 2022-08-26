//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P08 - DNA Transcription
// Course:   CS 300 Spring 2022
//
// Author:   Daniel Liu
// Email:    daliu2@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;

/**
 * Test methods to verify your implementation of the methods for P08.
 */
public class DNATester {

  /**
   * Tests the enqueue() and dequeue() methods
   *
   * @return true if and only if the method works correctly
   */
  public static boolean testEnqueueDequeue() {
    LinkedQueue<Character> testQueue = new LinkedQueue<>();
    // 1. Test enqueue()
    String expectedQueue = "A";
    testQueue.enqueue('A');
    if (!expectedQueue.equals(testQueue.toString().trim())) {
      System.out.println("Error: enqueue() failed to add a node to the queue.");
      return false;
    }
    // 2. Test dequeue() return value
    char expectedChar = 'A';
    try {
      if (expectedChar != testQueue.dequeue()) {
        System.out.println("Error: dequeue() failed to remove a node from the queue.");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Error: dequeue() incorrectly threw an exception.");
      return false;
    }
    // 3. Test if dequeue() throws the right exception
    try {
      testQueue.dequeue();
      System.out.println("Error: dequeue() failed to throw an exception when queue is empty.");
      return false;
    } catch (NoSuchElementException e) {
      // Works as intended
    } catch (Exception e) {
      System.out.println("Error: dequeue() threw the incorrect exception.");
      return false;
    }
    return true;
  }

  /**
   * Tests the isEmpty() and size() methods
   *
   * @return true if and only if the method works correctly
   */
  public static boolean testQueueSize() {
    LinkedQueue<Character> testQueue = new LinkedQueue<>();
    // 1. Test isEmpty() on empty queue
    if (!testQueue.isEmpty()) {
      System.out.println("Error: isEmpty() returned the wrong boolean.");
      return false;
    }
    // 2. Test size() on empty queue
    if (testQueue.size() != 0) {
      System.out.println("Error: size() returned the wrong int.");
      return false;
    }
    // 3. Test isEmpty() on populated queue
    testQueue.enqueue('B');
    testQueue.enqueue('C');
    if (testQueue.isEmpty()) {
      System.out.println("Error: isEmpty() returned the wrong boolean.");
      return false;
    }
    // 4. Test size() on populated queue
    if (testQueue.size() != 2) {
      System.out.println("Error: size() returned the wrong int.");
      return false;
    }
    return true;
  }

  /**
   * Tests the transcribeDNA() method
   *
   * @return true if and only if the method works correctly
   */
  public static boolean testTranscribeDNA() {
    DNA testDNA = new DNA("GGAGTCAGTTAAGCGACCGGGACATACTGTCTTGGTAATCTCCGAGCTAGAAAGTCTCTG");
    String mRNA = "CCUCAGUCAAUUCGCUGGCCCUGUAUGACAGAACCAUUAGAGGCUCGAUCUUUCAGAGAC";
    System.out.println(testDNA.transcribeDNA().toString());
    if (testDNA.transcribeDNA().toString().replaceAll(" ", "").equals(mRNA)) {
      return true;
    }
    return false;
  }

  /**
   * Tests the translateDNA() method
   *
   * @return true if and only if the method works correctly
   */
  public static boolean testTranslateDNA() {
    DNA testDNA = new DNA("GGAGTCAGTTAAGCGACCGGGACATACTGTCTTGGTAATCTCCGAGCTAGAAAGTCTCTG");
    System.out.println(testDNA.translateDNA().toString());
    if (testDNA.translateDNA().toString().replaceAll(" ", "").equals("PQSIRWPCMTEPLEARSFRD")) {
      return true;
    }
    return false;
  }

  /**
   * Tests the mRNATranslate() method
   *
   * @return true if and only if the method works correctly
   */
  public static boolean testMRNATranslate() {
    DNA testDNA = new DNA("");
    // 1. Test with no more nucleotides
    LinkedQueue<Character> testmRNA = new LinkedQueue<>();
    testmRNA.enqueue('U');
    testmRNA.enqueue('U');
    testmRNA.enqueue('U');
    testmRNA.enqueue('U');
    testmRNA.enqueue('U');
    LinkedQueue<String> translated = testDNA.mRNATranslate(testmRNA);
    String expectedString = "F";
    if (!expectedString.equals(translated.dequeue())) {
      System.out.println("Error: mRNATranslate() failed to correctly translate the queue.");
      return false;
    }
    // 2. Test with stop codon
    testmRNA = new LinkedQueue<>();
    testmRNA.enqueue('G');
    testmRNA.enqueue('G');
    testmRNA.enqueue('G');
    testmRNA.enqueue('U');
    testmRNA.enqueue('A');
    testmRNA.enqueue('A');
    translated = testDNA.mRNATranslate(testmRNA);
    expectedString = "G";
    if (!expectedString.equals(translated.dequeue())) {
      System.out.println("Error: mRNATranslate() failed to correctly translate the queue.");
      return false;
    }
    return true;
  }

  /**
   * Main method - use this to run your test methods and output the results (ungraded)
   *
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println("transcribeDNA: " + testTranscribeDNA());
    System.out.println("translateDNA: " + testTranslateDNA());
    System.out.println(testEnqueueDequeue() && testQueueSize() && testMRNATranslate());
  }

}
