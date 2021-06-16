# pfinv

Various programs for investigating performance solutions on Java, JavaCC and al.

Results are in directory [out](out).  
Builds are per package.  

## Low level comparators

### mma.comparators.pkg1.Compare_sc_gs_hh.java

Old style micro benchmark, to be run under a JProfiler, looking at performance of
 * string concatenation
 * getters & setters versus direct access
 * Hashtables and HashMaps

Compiled with different compilers.

Run under JProfiler with CPU recording enabled, produces snapshots.

**One interesting result is that:**
 * for JDK 1.7 & 1.8, the *+* operator is the slowest (180/190 ms), then StringBuffer/StringBuilder append() and
  String.concat() (105/130 ms)
 * for JDK >= 9, the *+* operator is now the fastest (specially in JDK 16: 40 ms), then String.concat() and
  then StringBuffer/StringBuilder append() (150/210 ms)

### mma.comparators.pkg2.Compare_param_assigned.java

Old style micro benchmark, to be run under a HPROF or JFR (JDK Flight Recorder), looking at performance of
 * primitive type parameter assignment or use of a local variable.

Compiled with different compilers, one pass with GC calls and another one without.

Run with JFR (with only CPU recording enabled for JDK >= 9), produces recorded data.  
Output copied manually to *xxx.log* files.

Results are somewhat instable (some timing figures sometimes rise without reasons).  
**However they show that, specially for JDK >= 11, not assigning a parameter and using a local variable
 does not degrade the consumptions and usually improves it.**  
And using or not *final* does not change much things (but this can be a side effect of small methods).

### mma.comparators.pkg3 TODO

*Micro benchmarks with JMH (Java Microbenchmark Harness).*

## JavaCC, JTB, JavaCC21 benchmarks TODO

### Generation

### Execution


