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
 * A generic implementation of a linked queue
 *
 * @param <T> The type of data to be contained in the queue
 */
public class LinkedQueue<T> implements QueueADT<T> {
  private int n;
  protected Node<T> front;
  protected Node<T> back;

  /**
   * Adds the given data to this queue; every addition to a queue is made at the back
   *
   * @param data the data to add
   */
  @Override public void enqueue(T data) {
    Node<T> newNode = new Node<>(data);
    if (n == 0) {
      front = newNode;
    } else {
      back.setNext(newNode);
    }
    back = newNode;
    n++;
  }

  /**
   * Removes and returns the item from this queue that was least recently added
   *
   * @return the item from this queue that was least recently added
   * @throws NoSuchElementException if this queue is empty
   */
  @Override public T dequeue() throws NoSuchElementException {
    if (n == 0)
      throw new NoSuchElementException("Queue is empty.");

    T toReturn = front.getData();
    front = front.getNext();
    n--;
    return toReturn;
  }

  /**
   * Returns the item least recently added to this queue without removing it
   *
   * @return the item least recently added to this queue
   * @throws NoSuchElementException if this queue is empty
   */
  @Override public T peek() throws NoSuchElementException {
    if (n == 0)
      throw new NoSuchElementException("Queue is empty");

    return front.getData();
  }

  /**
   * Checks whether the queue contains any elements
   *
   * @return true if this queue is empty; false otherwise
   */
  @Override public boolean isEmpty() {
    return n == 0;
  }

  /**
   * Returns the number of items in this queue
   *
   * @return the number of items in this queue
   */
  @Override public int size() {
    return n;
  }

  /**
   * Returns a string representation of this queue, beginning at the front (least recently added) of
   * the queue and ending at the back (most recently added). An empty queue is represented as an
   * empty string; otherwise items in the queue are represented using their data separated by
   * spaces
   *
   * @return the sequence of items in FIFO order, separated by spaces
   */
  @Override public String toString() {
    String toReturn = "";
    if (n == 0) {
      return toReturn;
    }
    Node<T> curNode = front;
    for (int i = 0; i < n - 1; i++) {
      toReturn += curNode.getData() + " ";
      curNode = curNode.getNext();
    }
    toReturn += curNode.getData();
    return toReturn;
  }
}
