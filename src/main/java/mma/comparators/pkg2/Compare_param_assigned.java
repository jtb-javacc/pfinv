package mma.comparators.pkg2;

/**
 * Simple program aimed at comparing performance of methods with assigned parameters of primitive types (
 * which are passed by value, but that the compiler flags with "parameter should not be assigned".<br>
 *
 * @author Marc MAZAS - 2021
 *
 * @version 1.0 : creation <br>
 *
 */
@SuppressWarnings({
    "static-method", "unused", "javadoc"
})
public class Compare_param_assigned {

  private static final long NBCALLS = 10000000; // 100000000 without profiling
  private static final long NBLOOPS = 100;      // 100 without profiling
  private static long       total   = 0;

  public Compare_param_assigned() {
    //
  }

  public static void main(final String[] aArgs) {
    
    System.out.println("Starting...          ; mx   ; mxn  ; mxf  ; mx_f ; mxf_f");
    
    final Compare_param_assigned pt = new Compare_param_assigned();

    run_public(pt);
    System.gc();
    run_protected(pt);
    System.gc();
    run_package(pt);
    System.gc();
    run_private(pt);

    System.out.println("total = " + total);
  }
  
  private static void run_public(final Compare_param_assigned pt) {
    
    long et;
    long bt;
    long tp;
    long ctr;
    final long[] tpa = new long[5];
    
    for (int k = 0; k < NBLOOPS; k++) {
      int l = 0;
      
      bt = System.currentTimeMillis();
      ctr = 0;
      for (long j = 0; j < NBCALLS; j++) {
        pt.mu(j);
        ctr += j;
      }
      et = System.currentTimeMillis();
      tp = et - bt;
      tpa[l++] += tp;
      total += (ctr > 0 ? 1 : -1);
      
      bt = System.currentTimeMillis();
      ctr = 0;
      for (long j = 0; j < NBCALLS; j++) {
        pt.mun(j);
        ctr += j;
      }
      et = System.currentTimeMillis();
      tp = et - bt;
      tpa[l++] += tp;
      total += (ctr > 0 ? 1 : -1);
      
      bt = System.currentTimeMillis();
      ctr = 0;
      for (long j = 0; j < NBCALLS; j++) {
        pt.muf(j);
        ctr += j;
      }
      et = System.currentTimeMillis();
      tp = et - bt;
      tpa[l++] += tp;
      total += (ctr > 0 ? 1 : -1);
      
      bt = System.currentTimeMillis();
      ctr = 0;
      for (long j = 0; j < NBCALLS; j++) {
        pt.mu_f(j);
        ctr += j;
      }
      et = System.currentTimeMillis();
      tp = et - bt;
      tpa[l++] += tp;
      total += (ctr > 0 ? 1 : -1);
      
      bt = System.currentTimeMillis();
      ctr = 0;
      for (long j = 0; j < NBCALLS; j++) {
        pt.muf_f(j);
        ctr += j;
      }
      et = System.currentTimeMillis();
      tp = et - bt;
      tpa[l++] += tp;
      total += (ctr > 0 ? 1 : -1);
      
    }
    System.out.print("results for public   ; ");
    for (int m = 0; m < 5; m++) {
      System.out.print(tpa[m] + " ; ");
    }
    System.out.println();
  }
  
  private static void run_protected(final Compare_param_assigned pt) {

    long et;
    long bt;
    long tp;
    long ctr;
    final long[] tpa = new long[5];

    for (int k = 0; k < NBLOOPS; k++) {
      int l = 0;

      bt = System.currentTimeMillis();
      ctr = 0;
      for (long j = 0; j < NBCALLS; j++) {
        pt.mo(j);
        ctr += j;
      }
      et = System.currentTimeMillis();
      tp = et - bt;
      tpa[l++] += tp;
      total += (ctr > 0 ? 1 : -1);

      bt = System.currentTimeMillis();
      ctr = 0;
      for (long j = 0; j < NBCALLS; j++) {
        pt.mon(j);
        ctr += j;
      }
      et = System.currentTimeMillis();
      tp = et - bt;
      tpa[l++] += tp;
      total += (ctr > 0 ? 1 : -1);

      bt = System.currentTimeMillis();
      ctr = 0;
      for (long j = 0; j < NBCALLS; j++) {
        pt.mof(j);
        ctr += j;
      }
      et = System.currentTimeMillis();
      tp = et - bt;
      tpa[l++] += tp;
      total += (ctr > 0 ? 1 : -1);

      bt = System.currentTimeMillis();
      ctr = 0;
      for (long j = 0; j < NBCALLS; j++) {
        pt.mo_f(j);
        ctr += j;
      }
      et = System.currentTimeMillis();
      tp = et - bt;
      tpa[l++] += tp;
      total += (ctr > 0 ? 1 : -1);

      bt = System.currentTimeMillis();
      ctr = 0;
      for (long j = 0; j < NBCALLS; j++) {
        pt.mof_f(j);
        ctr += j;
      }
      et = System.currentTimeMillis();
      tp = et - bt;
      tpa[l++] += tp;
      total += (ctr > 0 ? 1 : -1);

    }
    System.out.print("results for protected; ");
    for (int m = 0; m < 5; m++) {
      System.out.print(tpa[m] + " ; ");
    }
    System.out.println();
  }
  
  private static void run_package(final Compare_param_assigned pt) {

    long et;
    long bt;
    long tp;
    long ctr;
    final long[] tpa = new long[5];

    for (int k = 0; k < NBLOOPS; k++) {
      int l = 0;

      bt = System.currentTimeMillis();
      ctr = 0;
      for (long j = 0; j < NBCALLS; j++) {
        pt.mp(j);
        ctr += j;
      }
      et = System.currentTimeMillis();
      tp = et - bt;
      tpa[l++] += tp;
      total += (ctr > 0 ? 1 : -1);

      bt = System.currentTimeMillis();
      ctr = 0;
      for (long j = 0; j < NBCALLS; j++) {
        pt.mpn(j);
        ctr += j;
      }
      et = System.currentTimeMillis();
      tp = et - bt;
      tpa[l++] += tp;
      total += (ctr > 0 ? 1 : -1);

      bt = System.currentTimeMillis();
      ctr = 0;
      for (long j = 0; j < NBCALLS; j++) {
        pt.mpf(j);
        ctr += j;
      }
      et = System.currentTimeMillis();
      tp = et - bt;
      tpa[l++] += tp;
      total += (ctr > 0 ? 1 : -1);

      bt = System.currentTimeMillis();
      ctr = 0;
      for (long j = 0; j < NBCALLS; j++) {
        pt.mp_f(j);
        ctr += j;
      }
      et = System.currentTimeMillis();
      tp = et - bt;
      tpa[l++] += tp;
      total += (ctr > 0 ? 1 : -1);

      bt = System.currentTimeMillis();
      ctr = 0;
      for (long j = 0; j < NBCALLS; j++) {
        pt.mpf_f(j);
        ctr += j;
      }
      et = System.currentTimeMillis();
      tp = et - bt;
      tpa[l++] += tp;
      total += (ctr > 0 ? 1 : -1);

    }
    System.out.print("results for package  ; ");
    for (int m = 0; m < 5; m++) {
      System.out.print(tpa[m] + " ; ");
    }
    System.out.println();
  }
  
  private static void run_private(final Compare_param_assigned pt) {

    long et;
    long bt;
    long tp;
    long ctr;
    final long[] tpa = new long[5];

    for (int k = 0; k < NBLOOPS; k++) {
      int l = 0;

      bt = System.currentTimeMillis();
      ctr = 0;
      for (long j = 0; j < NBCALLS; j++) {
        pt.mi(j);
        ctr += j;
      }
      et = System.currentTimeMillis();
      tp = et - bt;
      tpa[l++] += tp;
      total += (ctr > 0 ? 1 : -1);

      bt = System.currentTimeMillis();
      ctr = 0;
      for (long j = 0; j < NBCALLS; j++) {
        pt.min(j);
        ctr += j;
      }
      et = System.currentTimeMillis();
      tp = et - bt;
      tpa[l++] += tp;
      total += (ctr > 0 ? 1 : -1);

      bt = System.currentTimeMillis();
      ctr = 0;
      for (long j = 0; j < NBCALLS; j++) {
        pt.mif(j);
        ctr += j;
      }
      et = System.currentTimeMillis();
      tp = et - bt;
      tpa[l++] += tp;
      total += (ctr > 0 ? 1 : -1);

      bt = System.currentTimeMillis();
      ctr = 0;
      for (long j = 0; j < NBCALLS; j++) {
        pt.mi_f(j);
        ctr += j;
      }
      et = System.currentTimeMillis();
      tp = et - bt;
      tpa[l++] += tp;
      total += (ctr > 0 ? 1 : -1);

      bt = System.currentTimeMillis();
      ctr = 0;
      for (long j = 0; j < NBCALLS; j++) {
        pt.mif_f(j);
        ctr += j;
      }
      et = System.currentTimeMillis();
      tp = et - bt;
      tpa[l++] += tp;
      total += (ctr > 0 ? 1 : -1);

    }
    System.out.print("results for private  ; ");
    for (int m = 0; m < 5; m++) {
      System.out.print(tpa[m] + " ; ");
    }
    System.out.println();
  }
  
  /* public */
  
  public void mu(long aI) {
    aI++;
  }
  
  public void mun(final long aI) {
    long i = aI;
    i++;
  }

  public final void muf(long aI) {
    aI++;
  }

  public void mu_f(final long aI) {
    long i = aI;
    i++;
  }

  public final void muf_f(final long aI) {
    long i = aI;
    i++;
  }
  
  /* protected */

  protected void mo(long aI) {
    aI++;
  }

  protected void mon(final long aI) {
    long i = aI;
    i++;
  }

  protected final void mof(long aI) {
    aI++;
  }
  
  protected void mo_f(final long aI) {
    long i = aI;
    i++;
  }
  
  protected final void mof_f(final long aI) {
    long i = aI;
    i++;
  }
  
  /* package */
  
  void mp(long aI) {
    aI++;
  }
  
  void mpn(final long aI) {
    long i = aI;
    i++;
  }
  
  final void mpf(long aI) {
    aI++;
  }
  
  void mp_f(final long aI) {
    long i = aI;
    i++;
  }

  final void mpf_f(final long aI) {
    long i = aI;
    i++;
  }
  
  /* private */
  
  private void mi(long aI) {
    aI++;
  }
  
  private void min(final long aI) {
    long i = aI;
    i++;
  }
  
  private final void mif(long aI) {
    aI++;
  }
  
  private void mi_f(final long aI) {
    long i = aI;
    i++;
  }
  
  private final void mif_f(final long aI) {
    long i = aI;
    i++;
  }

}
