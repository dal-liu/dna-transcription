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
 * This class uses a linked queue to implement DNA transcription. DNA transcription is performed by
 * first transcribing a string of DNA characters to their mRNA complements, and then translating
 * those mRNA characters in groups of three (called "codons") to corresponding amino acids, which
 * finally fold up into proteins. To make this happen, you'll begin with a String containing a
 * sequence of DNA characters (A, C, G, and T) and use this to create a LinkedQueue of Characters.
 * Then, you'll write a method to traverse that LinkedQueue (without an iterator! gasp!) and create
 * a NEW LinkedQueue of mRNA characters (A->U, T->A, C->G, G->C). Finally, given that LinkedQueue of
 * mRNA, you'll use the provided mRNAtoProteinMap to traverse the queue three letters at a time and
 * find the associated amino acid - or if you've found a STOP codon, end your translation and return
 * the string of amino acids.
 */
public class DNA {
  protected static String[][] mRNAtoProteinMap =
    {{"UUU", "F"}, {"UUC", "F"}, {"UUA", "L"}, {"UUG", "L"}, {"UCU", "S"}, {"UCC", "S"},
      {"UCA", "S"}, {"UCG", "S"}, {"UAU", "Y"}, {"UAC", "Y"}, {"UAA", "STOP"}, {"UAG", "STOP"},
      {"UGU", "C"}, {"UGC", "C"}, {"UGA", "STOP"}, {"UGG", "W"}, {"CUU", "L"}, {"CUC", "L"},
      {"CUA", "L"}, {"CUG", "L"}, {"CCU", "P"}, {"CCC", "P"}, {"CCA", "P"}, {"CCG", "P"},
      {"CAU", "H"}, {"CAC", "H"}, {"CAA", "Q"}, {"CAG", "Q"}, {"CGU", "R"}, {"CGC", "R"},
      {"CGA", "R"}, {"CGG", "R"}, {"AUU", "I"}, {"AUC", "I"}, {"AUA", "I"}, {"AUG", "M"},
      {"ACU", "T"}, {"ACC", "T"}, {"ACA", "T"}, {"ACG", "T"}, {"AAU", "N"}, {"AAC", "N"},
      {"AAA", "K"}, {"AAG", "K"}, {"AGU", "S"}, {"AGC", "S"}, {"AGA", "R"}, {"AGG", "R"},
      {"GUU", "V"}, {"GUC", "V"}, {"GUA", "V"}, {"GUG", "V"}, {"GCU", "A"}, {"GCC", "A"},
      {"GCA", "A"}, {"GCG", "A"}, {"GAU", "D"}, {"GAC", "D"}, {"GAA", "E"}, {"GAG", "E"},
      {"GGU", "G"}, {"GGC", "G"}, {"GGA", "G"}, {"GGG", "G"}};
  protected LinkedQueue<Character> DNA;

  /**
   * Creates the DNA queue from the provided String. Each Node contains a single Character from the
   * sequence.
   *
   * @param sequence a String containing the original DNA sequence
   */
  public DNA(String sequence) {
    DNA = new LinkedQueue<>();
    for (int i = 0; i < sequence.length(); i++) {
      DNA.enqueue(sequence.charAt(i));
    }
  }

  /**
   * Creates and returns a new queue of mRNA characters from the stored DNA. The transcription is
   * done one character at a time, as (A->U, T->A, C->G, G->C).
   *
   * @return the queue containing the mRNA sequence corresponding to the stored DNA sequence
   */
  public LinkedQueue<Character> transcribeDNA() {
    LinkedQueue<Character> mRNA = new LinkedQueue<>();
    LinkedQueue<Character> tempDNA = new LinkedQueue<>();
    int size = DNA.size();

    for (int i = 0; i < size; i++) {
      char charToAdd;
      try {
        DNA.peek();
        tempDNA.enqueue(DNA.peek()); // create a deep copy of DNA in tempDNA
        switch (DNA.dequeue()) {
          case 'A':
            charToAdd = 'U';
            break;
          case 'T':
            charToAdd = 'A';
            break;
          case 'C':
            charToAdd = 'G';
            break;
          case 'G':
            charToAdd = 'C';
            break;
          default:
            charToAdd = '\u0000';
            break;
        }
      } catch (Exception e) {
        System.out.println(e.getMessage());
        break;
      }
      mRNA.enqueue(charToAdd); // creates an mRNA strand by de-queuing DNA
    }
    DNA = tempDNA; // Restore the deep copy to the original reference
    return mRNA;
  }

  /**
   * Creates and returns a new queue of amino acids from a provided queue of mRNA characters. The
   * translation is done three characters at a time, according to the static mRNAtoProteinMap
   * provided above. Translation ends either when you run out of mRNA characters OR when a STOP
   * codon is reached (i.e. the three-character sequence corresponds to the word STOP in the map,
   * rather than a single amino acid character).
   *
   * @param mRNA a queue containing the mRNA sequence corresponding to the stored DNA sequence
   * @return the queue containing the amino acid sequence corresponding to the provided mRNA
   * sequence
   */
  public LinkedQueue<String> mRNATranslate(LinkedQueue<Character> mRNA) {
    LinkedQueue<String> translation = new LinkedQueue<>();
    int size = mRNA.size();

    for (int i = 0; i < size; i += 3) { // Iterates once every 3 characters
      String aminoSequence = "";
      for (int j = 0; j < 3; j++) { // Loops within the sequence of 3 characters
        try {
          aminoSequence += mRNA.dequeue();
        } catch (NoSuchElementException e) { // Expected behavior
          break;
        } catch (Exception e) {
          System.out.println(e.getMessage());
          break;
        }
      }
      // Loop through the mRNA to protein map to find a match
      for (String[] mRNAsequence : mRNAtoProteinMap) {
        if (mRNAsequence[0].equals(aminoSequence)) {
          if (mRNAsequence[1].equals("STOP")) {
            return translation;
          } else {
            translation.enqueue(mRNAsequence[1]);
          }
        }
      }
    }
    return translation;
  }

  /**
   * A shortcut method that translates the stored DNA sequence to a queue of amino acids using the
   * other two methods in this class
   *
   * @return the queue containing the amino acid sequence corresponding to the stored DNA sequence,
   * via its mRNA transcription
   */
  public LinkedQueue<String> translateDNA() {
    return mRNATranslate(transcribeDNA());
  }
}
