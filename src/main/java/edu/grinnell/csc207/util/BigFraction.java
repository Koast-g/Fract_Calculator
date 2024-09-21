package edu.grinnell.csc207.util;

import java.math.BigInteger;

/** BigFraction class. */
public class BigFraction {
  /** Denominator equals to 1. */
  public static final int DENOM = 1;

  /** numerator of the fraction. */
  int numerator;

  /** dominator of the fraction. */
  int denominator;

  /**
   * Find the greatest commmon denominator.
   *
   * @param num integer
   * @param denom integer
   * @return an integer
   */
  private int gcd(int num, int denom) {
    while (denom != 0) {
      int temp = denom;
      denom = num % denom;
      num = temp;
    } // end of while loop
    return num;
  } // gcd(int _numerator, int _denominator)

  /** Method that simplifies the fraction. */
  public void simplify() {
    int gcd = gcd(Math.abs(this.numerator), Math.abs(this.denominator));
    this.numerator /= gcd;
    this.denominator /= gcd;
    if (this.denominator < 0) {
      this.numerator = -this.numerator;
      this.denominator = -this.denominator;
    } // end of if
  } // simplify()

  /**
   * Creates a new BigFraction.
   *
   * @param num numerator of the fraction
   * @param denom denominator of the fraction
   */
  public BigFraction(int num, int denom) {
    this.numerator = num;
    this.denominator = denom;
    simplify();
  } // BigFraction(int _numerator, int _dominator)

  /**
   * Converts string into big fraction.
   *
   * @param str string
   */
  public BigFraction(String str) {
    if (str.contains("/")) {
      String[] frac = str.split("/");
      this.numerator = (Integer.parseInt(frac[0]));
      this.denominator = (Integer.parseInt(frac[1]));
      simplify();
    } else {
      this.numerator = Integer.parseInt(str);
      this.denominator = 1;
    } // end of if
  } // BigFraction(String str)

  /**
   * add this fraction to other fraction.
   *
   * @param addend BigFraction
   * @return a new Fraction
   */
  public BigFraction add(BigFraction addend) {
    int resultNum;
    int resultDenom;

    resultDenom = this.denominator * addend.denominator;
    resultNum = (this.numerator * addend.denominator) + (addend.numerator * this.denominator);

    return new BigFraction(resultNum, resultDenom);
  } // add(BigFraction addend)

  /**
   * subtract this fraction from other fraction.
   *
   * @param subtrahend BigFraction
   * @return a new Fraction
   */
  public BigFraction subtract(BigFraction subtrahend) {
    int resultNum;
    int resultDenom;

    resultDenom = this.denominator * subtrahend.denominator;
    resultNum =
        (this.numerator * subtrahend.denominator) - (subtrahend.numerator * this.denominator);

    return new BigFraction(resultNum, resultDenom);
  } // subtraction(BigFraction subtrahend)

  /**
   * multiplies this fraction to other fraction.
   *
   * @param product BigFraction
   * @return a new Fraction
   */
  public BigFraction multiply(BigFraction product) {
    int resultNum;
    int resultDenom;

    resultDenom = this.denominator * product.denominator;
    resultNum = this.numerator * product.numerator;

    return new BigFraction(resultNum, resultDenom);
  } // multiply(BigFraction product)

  /**
   * divides this fraction by other fraction.
   *
   * @param divisor BigFraction
   * @return a new Fraction
   */
  public BigFraction divide(BigFraction divisor) {
    int resultNum;
    int resultDenom;

    resultDenom = this.denominator * divisor.numerator;
    resultNum = this.numerator * divisor.denominator;

    return new BigFraction(resultNum, resultDenom);
  } // divide(BigFraction divisor)

  /**
   * Converts int into a fraction.
   *
   * @param val int
   * @return a new Fraction
   */
  public static BigFraction int2fraction(int val) {
    return new BigFraction(val, DENOM);
  } // Int2Fraction(int val)

  /**
   * Gets the numerator from this fraction.
   *
   * @return BigInteger
   */
  public BigInteger numerator() {
    return BigInteger.valueOf(this.numerator);
  } // numerator()

  /**
   * Gets the denominator from this fraction.
   *
   * @return BigInteger
   */
  public BigInteger denominator() {
    return BigInteger.valueOf(this.denominator);
  } // denominator()

  /**
   * Converts Big Fraction inot String.
   * @return string of the fraction
   */
  public String toString() {
    if (this.denominator != DENOM) {
      return this.numerator + "/" + denominator;
    } else {
      return Integer.toString(this.numerator);
    } // end of if
  } // toString();
} // BigFraction
