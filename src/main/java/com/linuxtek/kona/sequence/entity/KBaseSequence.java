/*
 * Copyright (C) 2011 LINUXTEK, Inc.  All Rights Reserved.
 */
package com.linuxtek.kona.sequence.entity;

import java.util.Date;
import java.util.List;

/**
 * Maintain a programmatically controlled sequence.
 *
 * <p>
 * NOTE: This class constains the direct mapping of the database table
 * as well as other attributes to main state between database calls.
 * </p>
 *
 * <p>
 * id - reference to the table primary key
 * nextBlockStartNo - start of next sequence block
 * currentNo - the id returned from this sequence block
 * </p>
 *
 *
 * <p>
 * <code>
 * CREATE TABLE ksequence (
 *   id serial,
 *   name varchar(32) not null,
 *   next_block_start_no bigint unsigned not null default 1,
 *   block_size int unsigned not null default 1000,
 *   step_size int unsigned not null default 1,
 *
 *    last_updated timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
 *        ON UPDATE CURRENT_TIMESTAMP,
 *
 *   UNIQUE ux_ksequence_name (name),
 *
 *    PRIMARY KEY pk_ksequence (id)
 * ) ENGINE=InnoDB;
 * </code>
 * </p>
 *
 * @see "Database Schema: ksequence"
 */

public class KBaseSequence implements KSequence {
	private static final long serialVersionUID = 1L;
    
	// database mapping fields
    private Long id = null;
    private String name = null;
    private Long nextBlockStartNo = null;
    private Integer blockSize = null;
    private Integer stepSize = null;

    private Date lastUpdated = null;

    // additional fields
    private Long currentNo  = null; //
    private Integer currentIndex  = null;
    private List<Long> sequenceList  = null;

    // -------------------------------------------------------------- 

    @Override
    public Long getId() {
        return (id);
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /* ---------------------------------------------------------------- */

    @Override
    public String getName() {
        return (name);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    /* ---------------------------------------------------------------- */

    @Override
    public Long getNextBlockStartNo() {
        return (nextBlockStartNo);
    }

    @Override
    public void setNextBlockStartNo(Long nextBlockStartNo) {
        this.nextBlockStartNo = nextBlockStartNo;
    }

    /* ---------------------------------------------------------------- */

    @Override
    public Integer getBlockSize() {
        return (blockSize);
    }

    @Override
    public void setBlockSize(Integer blockSize) {
        this.blockSize = blockSize;
    }

    /* ---------------------------------------------------------------- */

    @Override
    public Integer getStepSize() {
        return (stepSize);
    }

    @Override
    public void setStepSize(Integer stepSize) {
        this.stepSize = stepSize;
    }

    /* ---------------------------------------------------------------- */

    @Override
    public Date getLastUpdated() {
        return (lastUpdated);
    }

    @Override
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /* ---------------------------------------------------------------- */

    @Override
    public Long getCurrentNo() {
        return (currentNo);
    }

    @Override
    public void setCurrentNo(Long currentNo) {
        this.currentNo = currentNo;
    }

    /* ---------------------------------------------------------------- */

    @Override
    public Integer getCurrentIndex() {
        return (currentIndex);
    }

    @Override
    public void setCurrentIndex(Integer currentIndex) {
        this.currentIndex = currentIndex;
    }

    /* ---------------------------------------------------------------- */

    @Override
    public List<Long> getSequenceList() {
        return (sequenceList);
    }

    @Override
    public void setSequenceList(List<Long> sequenceList) {
        this.sequenceList = sequenceList;
    }
}
