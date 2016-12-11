import java.lang.*;

public class Zad1 {
  public static void main(String[] args) {
    long start;

    start = System.nanoTime();
    int[] tab1 = new int[10000];
    long t1 = System.nanoTime() - start;
    System.out.println(t1 + "ns");

    start = System.nanoTime();
    int[] tab2 = new int[100000];
    long t2 = System.nanoTime() - start;
    System.out.println(t2 + "ns");

    start = System.nanoTime();
    int[] tab3 = new int[1000000];
    long t3 = System.nanoTime() - start;
    System.out.println(t3 + "ns");
  }
}
