package xyz.pixelatedw.mineminenomi.api.util;

public interface INumberRange<N extends Number> {
  boolean isInfinite();
  
  boolean isFixed();
  
  boolean isRange();
  
  N getMin();
  
  N getMax();
}


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\ap\\util\INumberRange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */