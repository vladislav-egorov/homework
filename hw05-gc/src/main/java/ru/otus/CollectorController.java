package ru.otus;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.Set;

public class CollectorController {

    private static final Set<String> YOUNG_GC = Set.of("PS Scavenge", "ParNew", "G1 Young Generation");
    private static final Set<String> OLD_GC = Set.of("PS MarkSweep", "ConcurrentMarkSweep", "G1 Old Generation");

    public static void printGcStat() {
        long minorGCCount = 0, minorMs = 0, majorGCCount = 0, majorMs = 0;
        List<GarbageCollectorMXBean> mxBeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (var bean : mxBeans) {
            long count = bean.getCollectionCount();
            if (count >= 0) {
                if (YOUNG_GC.contains(bean.getName())) {
                    minorGCCount += count;
                    minorMs += bean.getCollectionTime();
                } else if (OLD_GC.contains(bean.getName())) {
                    majorGCCount += count;
                    majorMs += bean.getCollectionTime();
                }
            }
        }
        System.out.println("MinorGC: Count = " + minorGCCount + ", time(ms) = " + minorMs + " MajorGC: Count = " + majorGCCount + ", time(ms) = " + majorMs);
    }
}
