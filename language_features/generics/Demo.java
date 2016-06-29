import java.util.*;

public class Demo {
  // compiler adds ClassCast for you
  public Integer useGeneric() {
    BoxGeneric<Integer> box = new BoxGeneric<>();
    box.set(20);

    return box.get();
  }

  public Object useNonGeneric() {
    BoxOfObject box = new BoxOfObject();
    box.set(20);

    return box.get();
  }

  // compler adds no extra bytecode, but it adds 'signature' attribute to
  // 'fyi' futher compilation activity
  public <T> boolean equalsGeneric(BoxGeneric<T> box1, BoxGeneric<T> box2) {
    return box1.get().equals(box2.get());
  }

  public boolean equalsNonGeneric(BoxOfObject box1, BoxOfObject box2) {
    return box1.get().equals(box2.get());
  }

  public boolean useEqualsGeneric() {
    BoxGeneric<Integer> box1 = new BoxGeneric<>();
    box1.set(10);
    BoxGeneric<Integer> box2 = new BoxGeneric<>();
    box2.set(20);

    return this.<Integer>equalsGeneric(box1, box2);
  }

  // same as use Object as return type, no impact on generated bytecode
  public <T> T genericEcho(T t) {
    return t;
  }

  // the constraint does not affect generated body of bytecode for this method,
  // but the constraint does get generated as 'attribute' of type 'signature' that
  // is associated with this method body such that compiler will complain if
  // a consumer of this method does not obey the rule
  public <N extends Number> void boundedType(BoxGeneric<N> boxOfNumber){
  }

  // compiler does the ClassCast for you
  public static double upperBoundedWildcards(List<? extends Number> list) {
    double s = 0.0;
    for (Number n : list) {
        s += n.doubleValue();
    }
    return s;
  }

  public static void main(String[] args) {
    problem1();
    //problem2(new Object());
    //makeArray();
  }

  static void problem1() {
    ArrayList<Integer> li = new ArrayList<Integer>();
    ArrayList<Float> lf = new ArrayList<Float>();
    ArrayList al = new ArrayList();
    if (li.getClass() == lf.getClass() // evaluates to true
        && li.getClass() == al.getClass()) {
      System.out.println("All Equal");
    }
  }

  //static <T> T problem2(T t) {
  //  return new T(); //causes a compile error
  //}

  // cause compilation error: generic array creation
  //public <T> T[] makeArray(T key) {
  //  return new T[0]; // error
  //}

  void accepts(BoxGeneric<Number> boxOfNumber) {}
  void acceptsWildcard(BoxGeneric<?> boxOfAnything) {}

  // cause compilation error: BoxGeneric<Integer> cannot be converted to BoxGeneric<Number>
  void testAcceptance() {
    BoxGeneric<Integer> boxOfInteger = new BoxGeneric<>();
    boxOfInteger.set(20);

    //accepts(boxOfInteger);   // compilation error

    acceptsWildcard(boxOfInteger);  // pass compilation check
  }
}


// cause compilation error: a generic class may not extend java.lang.Throwable
//class NotAllowedException<T> extends Exception {}


// reference: https://en.wikipedia.org/wiki/Generics_in_Java#Problems_with_type_erasure
// https://docs.oracle.com/javase/tutorial/java/generics/restrictions.html