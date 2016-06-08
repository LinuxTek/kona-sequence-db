/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.sequence.dao;

import com.linuxtek.kona.data.dao.KMyBatisDao;
import com.linuxtek.kona.sequence.entity.KSequence;

public interface KSequenceDao extends KMyBatisDao {
    public void insert(KSequence seq);
    public void updateByPrimaryKey(KSequence seq); 
    public void deleteByPrimaryKey(Long id);
    public KSequence fetchByName(String name);
}
