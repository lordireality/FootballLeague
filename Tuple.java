

/**
 * Простая реализация кортежа.
 * К сожалению, я в основном использую C#, где кортежи являются встроенным инструментом
 * @param <X>
 * @param <Y>
 */

public class Tuple<X, Y> { 
  public final X x; 
  public final Y y; 
  public Tuple(X x, Y y) { 
    this.x = x; 
    this.y = y; 
  } 
} 