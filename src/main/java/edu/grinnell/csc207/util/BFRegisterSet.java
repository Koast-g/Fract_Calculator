package edu.grinnell.csc207.util;

/** BFRegisterSet class. */
public class BFRegisterSet {
  /** based number for charachter to integer conversion. */
  public static final int CHARACHTER_TO_INTEGER = 97;

  /** register array. */
  BigFraction[] regArray = new BigFraction[26];

  /**
   * Stores the Bigfraction in the register based on the charachter.
   *
   * @param register character
   * @param val BigFraction
   */
  public void store(char register, BigFraction val) {
    int i = (int) register - CHARACHTER_TO_INTEGER;
    regArray[i] = val;
  } // store(char register, BigFraction val)

  /**
   * Gets the BigFraction value out of the register.
   *
   * @param register charachter
   * @return BigFraction
   */
  public BigFraction get(char register) {
    int i = (int) register - CHARACHTER_TO_INTEGER;
    return regArray[i];
  } // get(char register)
} //BFRegisterSet
