package edu.grinnell.csc207.util;

/** BFCalculator class. */
public class BFCalculator {
  /** last computed value is equal to 0. */
  private BigFraction lastValue = new BigFraction("0");

  /**
   * Setting last computed value.
   *
   * @param val BigFraction
   */
  public void setLastValue(BigFraction val) {
    this.lastValue = val;
  } // setLastValue(BigFraction val)

  /**
   * Gets the last computed value.
   *
   * @return BigFraction
   */
  public BigFraction get() {
    return lastValue;
  } // get()

  /**
   * Adds last computed value to val.
   *
   * @param val BigFraction
   */
  public void add(BigFraction val) {
    lastValue = lastValue.add(val);
  } // add(BigFraction val)

  /**
   * Subtracts last computed value from val.
   *
   * @param val BigFraction
   */
  public void subtract(BigFraction val) {
    lastValue = lastValue.subtract(val);
  } // subtract(BigFraction val)

  /**
   * Multiplies last computed value to val.
   *
   * @param val
   */
  public void multiply(BigFraction val) {
    lastValue = lastValue.multiply(val);
  } // multiply(BigFraction val)

  /**
   * Divides last computed value by val.
   *
   * @param val
   */
  public void divide(BigFraction val) {
    lastValue = lastValue.divide(val);
  } // divide(BigFraction val)

  /** Clears last computed value, setting it to 0. */
  public void clear() {
    lastValue = lastValue.subtract(lastValue);
  } // clear()
} // BFCalculator class
