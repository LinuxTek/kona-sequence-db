/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.sequence.dao;

import com.linuxtek.kona.data.dao.KMyBatisDao;
import com.linuxtek.kona.sequence.entity.KSequence;

@SuppressWarnings("rawtypes")
public interface KSequenceDao extends KMyBatisDao {
    public int insert(KSequence seq);
    public int updateByPrimaryKey(KSequence seq); 
    public int deleteByPrimaryKey(Long id);
    public KSequence fetchByName(String name);
}
