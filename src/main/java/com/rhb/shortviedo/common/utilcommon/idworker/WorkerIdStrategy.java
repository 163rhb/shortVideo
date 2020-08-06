package com.rhb.shortviedo.common.utilcommon.idworker;

public interface WorkerIdStrategy {
    void initialize();

    long availableWorkerId();

    void release();
}
