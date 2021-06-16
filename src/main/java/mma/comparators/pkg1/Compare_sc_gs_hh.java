package mma.comparators.pkg1;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * Simple program aimed at comparing various coding techniques performance: string concatenation,
 * getters/setters, hashtables/hashmaps.
 * <p>
 * See: <a href="http://www-128.ibm.com/developerworks/java/library/j-jtp02225.html">Anatomy of a flawed
 * microbenchmark</a> at <a href="http://www.briangoetz.com/pubs.html">Brian Goetz Publications</a>
 * </p>
 * <p>
 * Note: in order to ensure fair results, JIT compilation must occur within the z3 method and not within the
 * others ; this can be checked with the appropriate java flag (ie -XX:+PrintCompilation for Oracle JDK). <br>
 * Ideally, each group of topmethods should be run in different JVM runs, to prevent JIT +/- in sub methods of
 * some top methods impact the same sub methods in other top methods (e.g. java.lang.String::abc() can be
 * called in many top methods an_xxx()).
 *
 * </p>
 *
 * @author Marc MAZAS - 2003-2021
 *
 * @version 1.3 : changed LINE_64 + character comparisons in cx methods, rewrote z3 method, added a4 method,
 *          and adaptated to JDK 1.5 <br>
 *          1.4 : changed initial HashMap sizes, clear global Hashtables and HashMaps, changed all returned
 *          values to an int 1.5 : corrected bug in b2_byte_newString() <br>
 *          1.5 : 06/2021 : changed API marked for removal ; added field buffers ; doubled iterations<br>
 */
public class Compare_sc_gs_hh {
  private static final int                      MAX_LOOP_1  = 400000;
  private static final int                      MAX_LOOP_2  = 200000;
  private static final int                      MAX_LOOP_3  = 10000;
  private static final int                      MAX_LOOP_4  = 1200;
  private static final String                   LINE_64     = "azertyuiopqsdfghjklmwxcvbn1234567890AZERTYUIOPQSDFGHJKLMWXCVBNaz";
  private static final String                   LINE_26     = "azertyuiopqsdfghjklmwxcvbn";
  private static final String                   LINE_36     = "azertyuiopqsdfghjklmwxcvbn1234567890";
  private static final StringBuffer             STSB64      = new StringBuffer(LINE_64);
  private final Object[]                        objArr64    = new Object[LINE_64.length()];
  protected int                                 anInt;
  private final Hashtable<Object, StringBuffer> globDefHT   = new Hashtable<>();
  private final HashMap<Object, StringBuffer>   globDefHM   = new HashMap<>();
  private final Hashtable<Object, StringBuffer> globTunedHT = new Hashtable<>(101);
  private final HashMap<Object, StringBuffer>   globTunedHM = new HashMap<>(128);
  private final Hashtable<Object, StringBuffer> globOverHT  = new Hashtable<>(1001);
  private final HashMap<Object, StringBuffer>   globOverHM  = new HashMap<>(1024);
  private StringBuffer                          sbuf;
  private StringBuilder                         sbui;
  
  /**
   * Empty constructor
   */
  public Compare_sc_gs_hh() {
    for (int i = 0; i < LINE_64.length(); i++) {
      objArr64[i] = Character.valueOf(STSB64.charAt(i));
    }
  }
  
  /*
   * === String / StringBuffer / StringBuilder comparisons ===
   */
  /**
   * Concatenates strings through java.lang.String.concat()
   *
   * @return the concatenated strings length
   */
  public final static int a1_concat_str() {
    int l = 0;
    final String l26 = LINE_26;
    for (int i = 0; i < MAX_LOOP_2; i++) {
      String s = "";
      s = s.concat(LINE_64).concat(l26).concat(LINE_36);
      l += s.length();
    }
    return l;
  }
  
  /**
   * Concatenates strings through the + operator
   *
   * @return the concatenated strings length
   */
  public final static int a2_plus_str() {
    int l = 0;
    final String l26 = LINE_26;
    for (int i = 0; i < MAX_LOOP_2; i++) {
      String s = "";
      s = s + LINE_64 + l26 + LINE_36;
      l += s.length();
    }
    return l;
  }
  
  /**
   * Concatenates strings through a local StringBuffer with default initial allocation and
   * java.lang.StringBuffer.append()
   *
   * @return the concatenated strings length
   */
  public final static int a3_loc_strbuf_def() {
    int l = 0;
    final String l26 = LINE_26;
    for (int i = 0; i < MAX_LOOP_2; i++) {
      final StringBuffer sb = new StringBuffer();
      sb.append(LINE_64).append(l26).append(LINE_36);
      l += sb.length();
    }
    return l;
  }
  
  /**
   * Concatenates strings through a local StringBuffer with tuned initial allocation and
   * java.lang.StringBuffer.append()
   *
   * @return the concatenated strings length
   */
  public final static int a4_loc_strbuf_tuned() {
    int l = 0;
    final String l26 = LINE_26;
    for (int i = 0; i < MAX_LOOP_2; i++) {
      final StringBuffer sb = new StringBuffer(128);
      sb.append(LINE_64).append(l26).append(LINE_36);
      l += sb.length();
    }
    return l;
  }
  
  /**
   * Concatenates strings through a local StringBuilder with default initial allocation and
   * java.lang.StringBuilder.append()
   *
   * @return the concatenated strings length
   */
  public final static int a5_loc_strbui_def() {
    int l = 0;
    final String l26 = LINE_26;
    for (int i = 0; i < MAX_LOOP_2; i++) {
      final StringBuilder sb = new StringBuilder();
      sb.append(LINE_64).append(l26).append(LINE_36);
      l += sb.length();
    }
    return l;
  }
  
  /**
   * Concatenates strings through a local StringBuilder with tuned initial allocation and
   * java.lang.StringBuilder.append()
   *
   * @return the concatenated strings length
   */
  public final static int a6_loc_strbui_tuned() {
    int l = 0;
    final String l26 = LINE_26;
    for (int i = 0; i < MAX_LOOP_2; i++) {
      final StringBuilder sb = new StringBuilder(128);
      sb.append(LINE_64).append(l26).append(LINE_36);
      l += sb.length();
    }
    return l;
  }
  
  /**
   * Concatenates strings through a field StringBuffer with default initial allocation and
   * java.lang.StringBuffer.append()
   *
   * @return the concatenated strings length
   */
  public final int a7_fld_strbuf_def() {
    int l = 0;
    final String l26 = LINE_26;
    for (int i = 0; i < MAX_LOOP_2; i++) {
      sbuf = new StringBuffer();
      sbuf.append(LINE_64).append(l26).append(LINE_36);
      l += sbuf.length();
    }
    return l;
  }
  
  /**
   * Concatenates strings through a field StringBuffer with tuned initial allocation and
   * java.lang.StringBuffer.append()
   *
   * @return the concatenated strings length
   */
  public final int a8_fld_strbuf_tuned() {
    int l = 0;
    final String l26 = LINE_26;
    for (int i = 0; i < MAX_LOOP_2; i++) {
      sbuf = new StringBuffer(128);
      sbuf.append(LINE_64).append(l26).append(LINE_36);
      l += sbuf.length();
    }
    return l;
  }
  
  /**
   * Concatenates strings through a field StringBuilder with default initial allocation and
   * java.lang.StringBuilder.append()
   *
   * @return the concatenated strings length
   */
  public final int a9_fld_strbui_def() {
    int l = 0;
    final String l26 = LINE_26;
    for (int i = 0; i < MAX_LOOP_2; i++) {
      sbui = new StringBuilder();
      sbui.append(LINE_64).append(l26).append(LINE_36);
      l += sbui.length();
    }
    return l;
  }
  
  /**
   * Concatenates strings through a field StringBuilder with tuned initial allocation and
   * java.lang.StringBuilder.append()
   *
   * @return the concatenated strings length
   */
  public final int a10_fld_strbui_tuned() {
    int l = 0;
    final String l26 = LINE_26;
    for (int i = 0; i < MAX_LOOP_2; i++) {
      sbui = new StringBuilder(128);
      sbui.append(LINE_64).append(l26).append(LINE_36);
      l += sbui.length();
    }
    return l;
  }
  
  /**
   * Uses java.lang.StringBuffer.toString() to return the String image of a StringBuffer
   *
   * @return the cumulated length of the byte array image of the string
   */
  public final static int b1_byte_buf_toString() {
    int l = 0;
    byte[] b;
    final StringBuffer sb = new StringBuffer(LINE_64);
    for (int i = 0; i < MAX_LOOP_3; i++) {
      b = sb.toString().getBytes();
      l += b.length;
    }
    return l;
  }
  
  /**
   * Uses new String() to return the String image of a StringBuffer
   *
   * @return the cumulated length of the byte array image of the string
   */
  public final static int b2_byte_newString() {
    int l = 0;
    byte[] b;
    final StringBuffer sb = new StringBuffer(LINE_64);
    for (int i = 0; i < MAX_LOOP_3; i++) {
      b = (new String(sb)).getBytes();
      l += b.length;
    }
    return l;
  }
  
  /**
   * Uses java.lang.StringBuilder.toString() to return the String image of a StringBuffer
   *
   * @return the cumulated length of the byte array image of the string
   */
  public final static int b3_byte_bui_toString() {
    int l = 0;
    byte[] b;
    final StringBuilder sb = new StringBuilder(LINE_64);
    for (int i = 0; i < MAX_LOOP_3; i++) {
      b = sb.toString().getBytes();
      l += b.length;
    }
    return l;
  }
  
  /*
   * === For loops ===
   */
  /**
   * Uses methods in the check part of the for clause and within the loop, and a local StringBuffer
   *
   * @return a counter
   */
  public final static int c1_for_methods() {
    int l = 0;
    final StringBuffer sb = new StringBuffer(LINE_64);
    for (int i = 0; i < MAX_LOOP_3; i++) {
      for (int j = 0; j < sb.length(); j++) {
        if ((sb.charAt(j) < '0') && (sb.charAt(j) > 'z')) {
          // need to do something here in order to avoid compiler optimizations
          l++;
        }
      }
    }
    return l;
  }
  
  /**
   * Uses a variable in the check part of the for clause and within the loop, and a local StringBuffer
   *
   * @return a counter
   */
  public final static int c2_for_variables() {
    int l = 0;
    final StringBuffer sb = new StringBuffer(LINE_64);
    for (int i = 0; i < MAX_LOOP_3; i++) {
      // avoid calling length() method at each iteration
      final int sbl = sb.length();
      for (int j = 0; j < sbl; j++) {
        // call charAt() only once
        final char c = sb.charAt(j);
        if ((c < '0') && (c > 'z')) {
          // need to do something here in order to avoid compiler optimizations
          l++;
        }
      }
    }
    return l;
  }
  
  /**
   * Uses a variable in the check part of the for clause, and a static StringBuffer
   *
   * @return a counter
   */
  public final static int c3_for_static() {
    int l = 0;
    for (int i = 0; i < MAX_LOOP_3; i++) {
      // avoid calling length() method at each iteration
      final int sbl = STSB64.length();
      for (int j = 0; j < sbl; j++) {
        // call charAt() only once
        final char c = STSB64.charAt(j);
        if ((c < '0') && (c > 'z')) {
          // need to do something here in order to avoid compiler optimizations
          l++;
        }
      }
    }
    return l;
  }
  
  /* === direct / getters & setters === */
  /**
   * Usual getter
   *
   * @return the variable
   */
  public final int z1_getAnInt() {
    return anInt;
  }
  
  /**
   * Usual setter
   *
   * @param aCpt the value to set the variable to
   */
  public final void z2_setAnInt(final int aCpt) {
    anInt = aCpt;
  }
  
  /**
   * Small loop using the usual getters and setters to access a variable
   *
   * @return a counter
   */
  public final int d1_access_getter_setter_small() {
    int j = 0;
    for (int i = 0; i < MAX_LOOP_3; i++) {
      z2_setAnInt(j + i + 1);
      j = z1_getAnInt() - 1 - i;
    }
    return j;
  }
  
  /**
   * Small loop avoiding getters and setters and accessing directly a variable
   *
   * @return a counter
   */
  public final int d2_access_protected_variable_small() {
    int j = 0;
    for (int i = 0; i < MAX_LOOP_3; i++) {
      anInt = j + i + 1;
      j = anInt - 1 - i;
    }
    return j;
  }
  
  /**
   * Big loop using the usual getters and setters to access a variable
   *
   * @return a counter
   */
  public final int d3_access_getter_setter_big() {
    int j = 0;
    for (int i = 0; i < MAX_LOOP_1; i++) {
      z2_setAnInt(j + i + 1);
      j = z1_getAnInt() - 1 - i;
    }
    return j;
  }
  
  /**
   * Big loop avoiding getters and setters and accessing directly a variable
   *
   * @return a counter
   */
  public final int d4_access_protected_variable_big() {
    int j = 0;
    for (int i = 0; i < MAX_LOOP_1; i++) {
      anInt = j + i + 1;
      j = anInt - 1 - i;
    }
    return j;
  }
  
  /* === Hashtables === */
  /**
   * Loops 1000 times to
   * <ul>
   * <li>allocate a Hashtable with default allocation and
   * <li>insert 64 different objects
   * </ul>
   *
   * @return the cumulated Hashtables size
   */
  public final int e1_ht_put_1000x1_loc_def() {
    int l = 0;
    for (int k = 0; k < 1000; k++) {
      final Hashtable<Object, StringBuffer> ht = new Hashtable<>();
      for (int j = 0; j < 64; j++) {
        ht.put(objArr64[j], STSB64);
      }
      l += ht.size();
    }
    return l;
  }
  
  /**
   * Loops 1000 times to
   * <ul>
   * <li>allocate a Hashtable with tuned allocation - 101 - and
   * <li>insert 64 different objects
   * </ul>
   *
   * @return the cumulated Hashtables size
   */
  public final int e2_ht_put_1000x1_loc_tuned() {
    int l = 0;
    for (int k = 0; k < 1000; k++) {
      final Hashtable<Object, StringBuffer> ht = new Hashtable<>(101);
      for (int j = 0; j < 64; j++) {
        ht.put(objArr64[j], STSB64);
      }
      l += ht.size();
    }
    return l;
  }
  
  /**
   * Loops 1000 times to
   * <ul>
   * <li>allocate a Hashtable with oversized allocation - 1001 - and
   * <li>insert 64 different objects
   * </ul>
   *
   * @return the cumulated Hashtables size
   */
  public final int e3_ht_put_1000x1_loc_over() {
    int l = 0;
    for (int k = 0; k < 1000; k++) {
      final Hashtable<Object, StringBuffer> ht = new Hashtable<>(1001);
      for (int j = 0; j < 64; j++) {
        ht.put(objArr64[j], STSB64);
      }
      l += ht.size();
    }
    return l;
  }
  
  /**
   * Loops 100 times to
   * <ul>
   * <li>allocate a Hashtable with default allocation and
   * <li>loop 10 times to insert 64 different objects
   * </ul>
   *
   * @return the cumulated Hashtables size
   */
  public final int f1_ht_put_100x10_loc_def() {
    int l = 0;
    for (int k = 0; k < 100; k++) {
      final Hashtable<Object, StringBuffer> ht = new Hashtable<>();
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 64; j++) {
          ht.put(objArr64[j], STSB64);
        }
        l += ht.size();
      }
    }
    return l;
  }
  
  /**
   * Loops 100 times to
   * <ul>
   * <li>allocate a Hashtable with tuned allocation - 101 - and
   * <li>loop 10 times to insert 64 different objects
   * </ul>
   *
   * @return the cumulated Hashtables size
   */
  public final int f2_ht_put_100x10_loc_tuned() {
    int l = 0;
    for (int k = 0; k < 100; k++) {
      final Hashtable<Object, StringBuffer> ht = new Hashtable<>(101);
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 64; j++) {
          ht.put(objArr64[j], STSB64);
        }
        l += ht.size();
      }
    }
    return l;
  }
  
  /**
   * Loops 100 times to
   * <ul>
   * <li>allocate a Hashtable with oversized allocation - 1001 - and
   * <li>loop 10 times to insert 64 different objects
   * </ul>
   *
   * @return the cumulated Hashtables size
   */
  public final int f3_ht_put_100x10_loc_over() {
    int l = 0;
    for (int k = 0; k < 100; k++) {
      final Hashtable<Object, StringBuffer> ht = new Hashtable<>(1001);
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 64; j++) {
          ht.put(objArr64[j], STSB64);
        }
        l += ht.size();
      }
    }
    return l;
  }
  
  /**
   * Loops 100 times to
   * <ul>
   * <li>clear a global Hashtable with default allocation
   * <li>loop 10 times to insert 64 different objects in this global Hashtable
   * </ul>
   *
   * @return the cumulated Hashtables size
   */
  public final int g1_ht_put_100x10_glob_def() {
    int l = 0;
    for (int k = 0; k < 100; k++) {
      globDefHT.clear();
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 64; j++) {
          globDefHT.put(objArr64[j], STSB64);
        }
        l += globDefHT.size();
      }
    }
    return l;
  }
  
  /**
   * Loops 100 times to
   * <ul>
   * <li>clear a global Hashtable with tuned allocation - 101
   * <li>loop 10 times to insert 64 different objects in this global Hashtable
   * </ul>
   *
   * @return the cumulated Hashtables size
   */
  public final int g2_ht_put_100x10_glob_tuned() {
    int l = 0;
    for (int k = 0; k < 100; k++) {
      globTunedHT.clear();
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 64; j++) {
          globTunedHT.put(objArr64[j], STSB64);
        }
        l += globTunedHT.size();
      }
    }
    return l;
  }
  
  /**
   * Loops 100 times to
   * <ul>
   * <li>clear a global Hashtable with oversized allocation - 1001
   * <li>loop 10 times to insert 64 different objects in this global Hashtable
   * </ul>
   *
   * @return the cumulated Hashtables size
   */
  public final int g3_ht_put_100x10_glob_over() {
    int l = 0;
    for (int k = 0; k < 100; k++) {
      globOverHT.clear();
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 64; j++) {
          globOverHT.put(objArr64[j], STSB64);
        }
        l += globOverHT.size();
      }
    }
    return l;
  }
  
  /**
   * Loops 100 times to
   * <ul>
   * <li>loop 10 times to retrieve 64 different objects in a global Hashtable with default allocation
   * </ul>
   *
   * @return the cumulated length of the value of the keys
   */
  public final int h1_ht_get_100x10_glob_def() {
    int l = 0;
    StringBuffer sb = null;
    for (int k = 0; k < 100; k++) {
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 64; j++) {
          sb = globDefHT.get(objArr64[j]);
        }
        l += sb.length();
      }
    }
    return l;
  }
  
  /**
   * Loops 100 times to
   * <ul>
   * <li>loop 10 times to retrieve 64 different objects in a global Hashtable with tuned allocation - 101
   * </ul>
   *
   * @return the cumulated length of the value of the keys
   */
  public final int h2_ht_get_100x10_glob_tuned() {
    int l = 0;
    StringBuffer sb = null;
    for (int k = 0; k < 100; k++) {
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 64; j++) {
          sb = globTunedHT.get(objArr64[j]);
        }
        l += sb.length();
      }
    }
    return l;
  }
  
  /**
   * Loops 100 times to
   * <ul>
   * <li>loop 10 times to retrieve 64 different objects in a global Hashtable with oversized allocation - 101
   * </ul>
   *
   * @return the cumulated length of the value of the keys
   */
  public final int h3_ht_get_100x10_glob_over() {
    int l = 0;
    StringBuffer sb = null;
    for (int k = 0; k < 100; k++) {
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 64; j++) {
          sb = globOverHT.get(objArr64[j]);
        }
        l += sb.length();
      }
    }
    return l;
  }
  
  /* === HashMaps (same as Hashtables) === */
  /**
   * Loops 1000 times to
   * <ul>
   * <li>allocate a HashMap with default allocation and
   * <li>insert 64 different objects
   * </ul>
   *
   * @return the cumulated Hashtables size
   */
  public final int i1_hm_put_1000x1_loc_def() {
    int l = 0;
    for (int k = 0; k < 1000; k++) {
      final HashMap<Object, StringBuffer> hm = new HashMap<>();
      for (int j = 0; j < 64; j++) {
        hm.put(objArr64[j], STSB64);
      }
      l += hm.size();
    }
    return l;
  }
  
  /**
   * Loops 1000 times to
   * <ul>
   * <li>allocate a HashMap with tuned allocation - 128 - and
   * <li>insert 64 different objects
   * </ul>
   *
   * @return the cumulated Hashtables size
   */
  public final int i2_hm_put_1000x1_loc_tuned() {
    int l = 0;
    for (int k = 0; k < 1000; k++) {
      final HashMap<Object, StringBuffer> hm = new HashMap<>(128);
      for (int j = 0; j < 64; j++) {
        hm.put(objArr64[j], STSB64);
      }
      l += hm.size();
    }
    return l;
  }
  
  /**
   * Loops 1000 times to
   * <ul>
   * <li>allocate a HashMap with oversized allocation - 1024 - and
   * <li>insert 64 different objects
   * </ul>
   *
   * @return the cumulated Hashtables size
   */
  public final int i3_hm_put_1000x1_loc_over() {
    int l = 0;
    for (int k = 0; k < 1000; k++) {
      final HashMap<Object, StringBuffer> hm = new HashMap<>(1024);
      for (int j = 0; j < 64; j++) {
        hm.put(objArr64[j], STSB64);
      }
      l += hm.size();
    }
    return l;
  }
  
  /**
   * Loops 100 times to
   * <ul>
   * <li>allocate a HashMap with default allocation and
   * <li>loop 10 times to insert 64 different objects
   * </ul>
   *
   * @return the cumulated Hashtables size
   */
  public final int j1_hm_put_100x10_loc_def() {
    int l = 0;
    for (int k = 0; k < 100; k++) {
      final HashMap<Object, StringBuffer> hm = new HashMap<>();
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 64; j++) {
          hm.put(objArr64[j], STSB64);
        }
        l += hm.size();
      }
    }
    return l;
  }
  
  /**
   * Loops 100 times to
   * <ul>
   * <li>allocate a HashMap with tuned allocation - 128 - and
   * <li>loop 10 times to insert 64 different objects
   * </ul>
   *
   * @return the cumulated Hashtables size
   */
  public final int j2_hm_put_100x10_loc_tuned() {
    int l = 0;
    for (int k = 0; k < 100; k++) {
      final HashMap<Object, StringBuffer> hm = new HashMap<>(128);
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 64; j++) {
          hm.put(objArr64[j], STSB64);
        }
        l += hm.size();
      }
    }
    return l;
  }
  
  /**
   * Loops 100 times to
   * <ul>
   * <li>allocate a HashMap with oversized allocation - 1024 - and
   * <li>loop 10 times to insert 64 different objects
   * </ul>
   *
   * @return the cumulated Hashtables size
   */
  public final int j3_hm_put_100x10_loc_over() {
    int l = 0;
    for (int k = 0; k < 100; k++) {
      final HashMap<Object, StringBuffer> hm = new HashMap<>(1024);
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 64; j++) {
          hm.put(objArr64[j], STSB64);
        }
        l += hm.size();
      }
    }
    return l;
  }
  
  /**
   * Loops 100 times to
   * <ul>
   * <li>clear a global HashMap with default allocation
   * <li>loop 10 times to insert 64 different objects in this global HashMap
   *
   * @return the cumulated Hashtables size
   */
  public final int k1_hm_put_100x10_glob_def() {
    int l = 0;
    for (int k = 0; k < 100; k++) {
      globDefHM.clear();
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 64; j++) {
          globDefHM.put(objArr64[j], STSB64);
        }
        l += globDefHM.size();
      }
    }
    return l;
  }
  
  /**
   * Loops 100 times to
   * <ul>
   * <li>clear a global HashMap with tuned allocation - 128
   * <li>loop 10 times to insert 64 different objects in this global HashMap
   * </ul>
   *
   * @return the cumulated Hashtables size
   */
  public final int k2_hm_put_100x10_glob_tuned() {
    int l = 0;
    for (int k = 0; k < 100; k++) {
      globTunedHM.clear();
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 64; j++) {
          globTunedHM.put(objArr64[j], STSB64);
        }
        l += globTunedHM.size();
      }
    }
    return l;
  }
  
  /**
   * Loops 100 times to
   * <ul>
   * <li>clear a global HashMap with oversized allocation - 1024
   * <li>loop 10 times to insert 64 different objects in this global HashMap
   * </ul>
   *
   * @return the cumulated Hashtables size
   */
  public final int k3_hm_put_100x10_glob_over() {
    int l = 0;
    for (int k = 0; k < 100; k++) {
      globOverHM.clear();
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 64; j++) {
          globOverHM.put(objArr64[j], STSB64);
        }
        l += globOverHM.size();
      }
    }
    return l;
  }
  
  /**
   * Loops 100 times to
   * <ul>
   * <li>loop 10 times to retrieve 64 different objects in a global HashMap with default allocation
   * </ul>
   *
   * @return the cumulated length of the value of the keys
   */
  public final int l1_hm_get_100x10_glob_def() {
    int l = 0;
    StringBuffer sb = null;
    for (int k = 0; k < 100; k++) {
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 64; j++) {
          sb = globDefHM.get(objArr64[j]);
        }
        l += sb.length();
      }
    }
    return l;
  }
  
  /**
   * Loops 100 times to
   * <ul>
   * <li>loop 10 times to retrieve 64 different objects in a global HashMap with tuned allocation - 128
   * </ul>
   *
   * @return the cumulated length of the value of the keys
   */
  public final int l2_hm_get_100x10_glob_tuned() {
    int l = 0;
    StringBuffer sb = null;
    for (int k = 0; k < 100; k++) {
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 64; j++) {
          sb = globTunedHM.get(objArr64[j]);
        }
        l += sb.length();
      }
    }
    return l;
  }
  
  /**
   * Loops 100 times to
   * <ul>
   * <li>loop 10 times to retrieve 64 different objects in a global HashMap with oversized allocation - 1024
   * </ul>
   *
   * @return the cumulated length of the value of the keys
   */
  public final int l3_hm_get_100x10_glob_over() {
    int l = 0;
    StringBuffer sb = null;
    for (int k = 0; k < 100; k++) {
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 64; j++) {
          sb = globOverHM.get(objArr64[j]);
        }
        l += sb.length();
      }
    }
    return l;
  }
  
  /* === dummy processing to load and just in time compile classes === */
  /**
   * Loads classes used above
   */
  public final void z3_dummy_proc_to_load_classes() {
    final StringBuffer[] sba = new StringBuffer[6];
    final int[] c = new int[6];
    for (int k = 0; k < MAX_LOOP_4; k++) {
      if ((k - (200 * (k / 200))) == 199) {
        System.out.println("  k = " + k);
      }
      final HashMap<Object, StringBuffer> hm_def = new HashMap<>();
      final Hashtable<Object, StringBuffer> ht_def = new Hashtable<>();
      final HashMap<Object, StringBuffer> hm_101 = new HashMap<>(101);
      final Hashtable<Object, StringBuffer> ht_101 = new Hashtable<>(128);
      final HashMap<Object, StringBuffer> hm_1001 = new HashMap<>(1001);
      final Hashtable<Object, StringBuffer> ht_1001 = new Hashtable<>(1024);
      for (int i = 0; i < 1; i++) {
        for (int j = 0; j < 64; j++) {
          final Object o = objArr64[j];
          hm_def.put(o, STSB64);
          ht_def.put(o, STSB64);
          hm_101.put(o, STSB64);
          ht_101.put(o, STSB64);
          hm_1001.put(o, STSB64);
          ht_1001.put(o, STSB64);
        }
      }
      for (int l = 0; l < 6; l++) {
        sba[l] = new StringBuffer(128);
      }
      for (int i = 0; i < 1; i++) {
        for (int j = 0; j < 64; j++) {
          final Object o = Character.valueOf(STSB64.charAt(j));
          sba[0].append(hm_def.get(o));
          sba[1].append(ht_def.get(o));
          sba[2].append(hm_101.get(o));
          sba[3].append(ht_101.get(o));
          sba[4].append(hm_1001.get(o));
          sba[5].append(ht_1001.get(o));
        }
      }
      if (sba[0].toString().equals(sba[2].toString())) {
        c[0]++;
      }
      if (sba[1].toString().equals(sba[3].toString())) {
        c[1]++;
      }
      if (sba[2].toString().equals(sba[4].toString())) {
        c[2]++;
      }
      if (sba[3].toString().equals(sba[5].toString())) {
        c[3]++;
      }
      if (sba[4].toString().equals(sba[0].toString())) {
        c[4]++;
      }
      if (sba[5].toString().equals(sba[1].toString())) {
        c[5]++;
      }
      hm_def.clear();
      ht_def.clear();
    }
    for (int l = 0; l < 6; l++) {
      System.out.println("  c[" + l + "] = " + c[l]);
    }
    System.out.println("  concat");
    String s1 = "";
    String s2 = "";
    final String l64 = LINE_64;
    final String l26 = LINE_26;
    final String l36 = LINE_36;
    for (int i = 0; i < 20000; i++) {
      if ((i - (2000 * (i / 2000))) == 1999) {
        System.out.println("  i = " + i);
      }
      s1 = s2 = "";
      s1 = s1.concat(LINE_64).concat(LINE_26).concat(LINE_36);
      s2 = s2 + l64 + l26 + l36;
    }
    System.out.println("  s1l = " + s1.length() + ", s2l = " + s2.length());
    System.out.println("  buffer");
    StringBuffer sb1 = null;
    StringBuffer sb2 = null;
    StringBuffer sb3 = null;
    StringBuilder sb4 = null;
    StringBuilder sb5 = null;
    StringBuilder sb6 = null;
    int l = 0;
    int m = 0;
    final byte[][] b1 = new byte[80000][];
    final byte[][] b2 = new byte[80000][];
    for (int i = 0; i < 80000; i++) {
      if ((i - (8000 * (i / 8000))) == 7999) {
        System.out.println("  i = " + i);
      }
      sb1 = new StringBuffer();
      sb2 = new StringBuffer(128);
      sb3 = new StringBuffer(LINE_64);
      sb4 = new StringBuilder();
      sb5 = new StringBuilder(128);
      sb6 = new StringBuilder(LINE_64);
      sb1.append(LINE_64);
      sb2.append(LINE_26);
      sb3.append(LINE_36);
      sb4.append(LINE_64);
      sb5.append(LINE_26);
      sb6.append(LINE_36);
      b1[i] = sb1.toString().getBytes();
      b2[i] = (new String(sb6)).getBytes();
      l += sb1.length() + sb2.length() + sb3.length() + b1[i].length;
      m += sb4.length() + sb5.length() + sb6.length() + b2[i].length;
    }
    System.out.println("  l = " + l + ", m = " + m);
    System.out.println("  AnInt");
    int q = 0;
    for (int i = 0; i < MAX_LOOP_2; i++) {
      z2_setAnInt(q + i + 1);
      q += z1_getAnInt() - 1 - i;
    }
    System.out.println("  q = " + q);
  }
  
  /**
   * Run the garbage collector
   */
  public final static void z4_runGC() {
    System.gc();
  }
  
  /* === main - the launcher === */
  /**
   * @param args the command line parameters
   */
  public final static void main(final String[] args) {
    final Compare_sc_gs_hh cs = new Compare_sc_gs_hh();
    final int[] iRes = new int[44];
    int i = 0;
    System.out.println("z3_dummy_proc_to_load_classes");
    cs.z3_dummy_proc_to_load_classes();
    z4_runGC();
    System.out.println("a1_concat_str");
    iRes[i++] = a1_concat_str();
    z4_runGC();
    System.out.println("a2_plus_str");
    iRes[i++] = a2_plus_str();
    z4_runGC();
    System.out.println("a3_loc_strbuf_def");
    iRes[i++] = a3_loc_strbuf_def();
    z4_runGC();
    System.out.println("a4_loc_strbuf_tuned");
    iRes[i++] = a4_loc_strbuf_tuned();
    z4_runGC();
    System.out.println("a5_loc_strbui_def");
    iRes[i++] = a5_loc_strbui_def();
    z4_runGC();
    System.out.println("a6_loc_strbui_tuned");
    iRes[i++] = a6_loc_strbui_tuned();
    z4_runGC();
    System.out.println("a7_fld_strbuf_def");
    iRes[i++] = cs.a7_fld_strbuf_def();
    z4_runGC();
    System.out.println("a8_fld_strbuf_tuned");
    iRes[i++] = cs.a8_fld_strbuf_tuned();
    z4_runGC();
    System.out.println("a9_fld_strbui_def");
    iRes[i++] = cs.a9_fld_strbui_def();
    z4_runGC();
    System.out.println("a10_fld_strbui_tuned");
    iRes[i++] = cs.a10_fld_strbui_tuned();
    z4_runGC();
    System.out.println("b1_byte_buf_toString");
    iRes[i++] = b1_byte_buf_toString();
    z4_runGC();
    System.out.println("b2_byte_newString");
    iRes[i++] = b2_byte_newString();
    z4_runGC();
    System.out.println("b3_byte_bui_toString");
    iRes[i++] = b3_byte_bui_toString();
    z4_runGC();
    System.out.println("c1_for_methods");
    iRes[i++] = c1_for_methods();
    z4_runGC();
    System.out.println("c2_for_variables");
    iRes[i++] = c2_for_variables();
    z4_runGC();
    System.out.println("c3_for_static");
    iRes[i++] = c3_for_static();
    z4_runGC();
    System.out.println("d2_access_protected_variable_small");
    iRes[i++] = cs.d2_access_protected_variable_small();
    z4_runGC();
    System.out.println("d1_access_getter_setter_small");
    iRes[i++] = cs.d1_access_getter_setter_small();
    z4_runGC();
    System.out.println("d4_access_protected_variable_big");
    iRes[i++] = cs.d4_access_protected_variable_big();
    z4_runGC();
    System.out.println("d3_access_getter_setter_big");
    iRes[i++] = cs.d3_access_getter_setter_big();
    z4_runGC();
    System.out.println("e1_ht_put_1000x1_loc_def");
    iRes[i++] = cs.e1_ht_put_1000x1_loc_def();
    z4_runGC();
    System.out.println("e2_ht_put_1000x1_loc_tuned");
    iRes[i++] = cs.e2_ht_put_1000x1_loc_tuned();
    z4_runGC();
    System.out.println("e3_ht_put_1000x1_loc_over");
    iRes[i++] = cs.e3_ht_put_1000x1_loc_over();
    z4_runGC();
    System.out.println("f1_ht_put_100x10_loc_def");
    iRes[i++] = cs.f1_ht_put_100x10_loc_def();
    z4_runGC();
    System.out.println("f2_ht_put_100x10_loc_tuned");
    iRes[i++] = cs.f2_ht_put_100x10_loc_tuned();
    z4_runGC();
    System.out.println("f3_ht_put_100x10_loc_over");
    iRes[i++] = cs.f3_ht_put_100x10_loc_over();
    z4_runGC();
    System.out.println("g1_ht_put_100x10_glob_def");
    iRes[i++] = cs.g1_ht_put_100x10_glob_def();
    z4_runGC();
    System.out.println("g2_ht_put_100x10_glob_tuned");
    iRes[i++] = cs.g2_ht_put_100x10_glob_tuned();
    z4_runGC();
    System.out.println("g3_ht_put_100x10_glob_over");
    iRes[i++] = cs.g3_ht_put_100x10_glob_over();
    z4_runGC();
    System.out.println("h1_ht_get_100x10_glob_def");
    iRes[i++] = cs.h1_ht_get_100x10_glob_def();
    z4_runGC();
    System.out.println("h2_ht_get_100x10_glob_tuned");
    iRes[i++] = cs.h2_ht_get_100x10_glob_tuned();
    z4_runGC();
    System.out.println("h3_ht_get_100x10_glob_over");
    iRes[i++] = cs.h3_ht_get_100x10_glob_over();
    z4_runGC();
    System.out.println("i1_hm_put_1000x1_loc_def");
    iRes[i++] = cs.i1_hm_put_1000x1_loc_def();
    z4_runGC();
    System.out.println("i2_hm_put_1000x1_loc_tuned");
    iRes[i++] = cs.i2_hm_put_1000x1_loc_tuned();
    z4_runGC();
    System.out.println("i3_hm_put_1000x1_loc_over");
    iRes[i++] = cs.i3_hm_put_1000x1_loc_over();
    z4_runGC();
    System.out.println("j1_hm_put_100x10_loc_def");
    iRes[i++] = cs.j1_hm_put_100x10_loc_def();
    z4_runGC();
    System.out.println("j2_hm_put_100x10_loc_tuned");
    iRes[i++] = cs.j2_hm_put_100x10_loc_tuned();
    z4_runGC();
    System.out.println("j3_hm_put_100x10_loc_over");
    iRes[i++] = cs.j3_hm_put_100x10_loc_over();
    z4_runGC();
    System.out.println("k1_hm_put_100x10_glob_def");
    iRes[i++] = cs.k1_hm_put_100x10_glob_def();
    z4_runGC();
    System.out.println("k2_hm_put_100x10_glob_tuned");
    iRes[i++] = cs.k2_hm_put_100x10_glob_tuned();
    z4_runGC();
    System.out.println("k3_hm_put_100x10_glob_over");
    iRes[i++] = cs.k3_hm_put_100x10_glob_over();
    z4_runGC();
    System.out.println("l1_hm_get_100x10_glob_def");
    iRes[i++] = cs.l1_hm_get_100x10_glob_def();
    z4_runGC();
    System.out.println("l2_hm_get_100x10_glob_tuned");
    iRes[i++] = cs.l2_hm_get_100x10_glob_tuned();
    z4_runGC();
    System.out.println("l3_hm_get_100x10_glob_over");
    iRes[i++] = cs.l3_hm_get_100x10_glob_over();
    z4_runGC();
    final int lim1 = iRes.length - 1;
    System.out.print("iRes[0-" + lim1 + "] = ");
    for (int j = 0; j < lim1; j++) {
      System.out.print(iRes[j] + ", ");
    }
    System.out.println(iRes[lim1]);
  }
}
