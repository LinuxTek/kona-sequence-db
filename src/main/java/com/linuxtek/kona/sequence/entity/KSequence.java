/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.sequence.entity;

import java.util.Date;
import java.util.List;

import com.linuxtek.kona.data.entity.KEntityObject;

/**
 */

public interface KSequence extends KEntityObject {

    public Long getId();
    public void setId(Long id);

    public String getName();
    public void setName(String name);

    public Long getNextBlockStartNo();
    public void setNextBlockStartNo(Long nextBlockStartNo);

    public Integer getBlockSize();
    public void setBlockSize(Integer blockSize);

    public Integer getStepSize();
    public void setStepSize(Integer stepSize);

    public Date getLastUpdated();
    public void setLastUpdated(Date lastUpdated);


    // additional fields
    public Long getCurrentNo();
    public void setCurrentNo(Long currentNo);

    public Integer getCurrentIndex();
    public void setCurrentIndex(Integer currentIndex);

    public List<Long> getSequenceList(); 
    public void setSequenceList(List<Long> sequenceList);
}
