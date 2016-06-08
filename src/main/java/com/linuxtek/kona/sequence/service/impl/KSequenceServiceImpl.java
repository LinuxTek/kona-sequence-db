/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.sequence.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linuxtek.kona.sequence.dao.KSequenceDao;
import com.linuxtek.kona.sequence.entity.KBaseSequence;
import com.linuxtek.kona.sequence.entity.KSequence;
import com.linuxtek.kona.sequence.service.KSequenceService;
import com.linuxtek.kona.util.KStringUtil;

/**
 * KSequence generator.
 */

@Service(KSequenceService.SERVICE_PATH)
public class KSequenceServiceImpl implements KSequenceService {
    private static Logger logger = Logger.getLogger(KSequenceService.class);

    private final static int BLOCK_SIZE = 1000;
    private final static int STEP_SIZE = 1;

    private Map<String, KSequence> seqMap = new HashMap<String, KSequence>();

    @Autowired
    private KSequenceDao seqDao;
    
    private synchronized KSequence initNextBlock(String name) {
        logger.debug("KSequenceServiceImpl: initNextBlock called");

        Long currentStartNo = Long.valueOf(STEP_SIZE);
        Integer blockSize = BLOCK_SIZE;
        Integer stepSize = STEP_SIZE;
        Long nextStartNo = Long.valueOf(currentStartNo +
            (blockSize * stepSize));

        KSequence seq = seqDao.fetchByName(name);

        if (seq == null) {
            // create new sequence
            logger.debug("creating new sequence for name: " + name);
            seq = new KBaseSequence();
            seq.setName(name);
            seq.setNextBlockStartNo(nextStartNo);
            seq.setBlockSize(blockSize);
            seq.setStepSize(stepSize);
            seqDao.insert(seq);
        } else {
            currentStartNo = seq.getNextBlockStartNo();
            blockSize = seq.getBlockSize();
            stepSize = seq.getStepSize();
            nextStartNo = Long.valueOf(currentStartNo + 
                (blockSize * stepSize));
            seq.setNextBlockStartNo(nextStartNo);
            seqDao.updateByPrimaryKey(seq);
        }

        ArrayList<Long> seqIds = new ArrayList<Long>();
        for (int i=0; i<blockSize; i++) {
            seqIds.add(currentStartNo + (i*stepSize));
        }

        seq.setSequenceList(seqIds);
        seq.setCurrentIndex(0);
        seqMap.put(name, seq);

        logger.debug("KSequenceUtil: created sequence:\n" + seq);

        return seq;
    }

    public synchronized Long getNextNo(String name) {

        KSequence seq = seqMap.get(name);

        if (seq == null) {
            seq = initNextBlock(name);
        }

        Integer currIndex = seq.getCurrentIndex();
        Integer blockSize = seq.getBlockSize();

        if (currIndex == null || (currIndex == (blockSize-1))) {
            seq = initNextBlock(name);

            currIndex = seq.getCurrentIndex();
            blockSize = seq.getBlockSize();

            if (currIndex == null || (currIndex == (blockSize-1))) {
                throw new IllegalStateException(
                    "Invalid sequence object:\n" + seq);
            }
        }

        List<Long> seqIds = seq.getSequenceList();
        Long currentNo = seqIds.get(currIndex++);

        seq.setCurrentNo(currentNo);
        seq.setCurrentIndex(currIndex);
        seqMap.put(name, seq);

        return (currentNo);
    }

    public synchronized String getHexNo(String name, int charCount) {
        Long nextNo = getNextNo(name);
        return (KStringUtil.toHex(nextNo, charCount));
    }
}
