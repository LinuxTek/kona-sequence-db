/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.sequence.service;

import com.linuxtek.kona.remote.service.KService;
import com.linuxtek.kona.remote.service.KServiceRelativePath;

/**
 * KSequence generator.
 */
@KServiceRelativePath(KSequenceService.SERVICE_PATH)
public interface KSequenceService extends KService {

    // NOTE: SERVICE_PATH must begin with rpc/ prefix
    public static final String SERVICE_PATH = "rpc/kona/SequenceService";

    public Long getNextNo(String name);

    public String getHexNo(String name, int charCount);
}
