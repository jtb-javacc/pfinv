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

### mma.comparators.pkg2.Compare_param_assigned

Old style micro benchmark, to be run under a HPROF or JFR (JDK Flight Recorder), looking at performance of
 * primitive type parameter assignment or use of a local variable.

Compiled with different compilers.

### mma.comparators.pkg3 TODO

*Micro benchmarks with JMH (Java Microbenchmark Harness).*

## JavaCC, JTB, JavaCC21 benchmarks TODO

### Generation

### Execution


