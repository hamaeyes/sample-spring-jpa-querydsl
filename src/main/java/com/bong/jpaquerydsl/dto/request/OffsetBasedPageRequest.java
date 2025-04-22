package com.bong.jpaquerydsl.dto.request;

import java.io.Serializable;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class OffsetBasedPageRequest implements Pageable, Serializable {

	private static final long serialVersionUID = 1L;
	private final int limit;
	private final long offset;
	private final Sort sort;

	protected OffsetBasedPageRequest(long offset, int limit, Sort sort) {
		if (offset < 0) {
			throw new IllegalArgumentException("Offset index must not be less than zero!");
		}

		if (limit < 1) {
			throw new IllegalArgumentException("Limit must not be less than one!");
		}
		this.limit = limit;
		this.offset = offset;
		this.sort = sort;
	}

	public static OffsetBasedPageRequest of(long offset, int limit) {
        return new OffsetBasedPageRequest(offset, limit, Sort.unsorted());
    }

    public static OffsetBasedPageRequest of(long offset, int limit, Sort sort) {
        return new OffsetBasedPageRequest(offset, limit, sort);
    }

    public static OffsetBasedPageRequest of(long offset, int limit, Sort.Direction direction, String... properties) {
        return of(offset, limit, Sort.by(direction, properties));
    }

    @Override
    public int getPageNumber() {
        return Long.valueOf(offset).intValue() / limit;
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new OffsetBasedPageRequest(getOffset() + getPageSize(), getPageSize(), getSort());
    }

    public OffsetBasedPageRequest previous() {
        return hasPrevious() ? new OffsetBasedPageRequest(getOffset() - getPageSize(), getPageSize(), getSort()) : this;
    }


    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    @Override
    public Pageable first() {
        return new OffsetBasedPageRequest(0L, getPageSize(), getSort());
    }

    @Override
    public boolean hasPrevious() {
        return offset > limit;
    }

}
