package com.rhb.shortviedo.common.utilcommon.idworker;

public interface RandomCodeStrategy {
    void init();

    int prefix();

    int next();

    void release();
}
