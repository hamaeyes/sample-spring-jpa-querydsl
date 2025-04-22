package com.bong.jpaquerydsl.common.response;

import java.util.List;

import com.bong.jpaquerydsl.dto.SearchDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagedResult<T> {
	private List<T> items;
	private long totalCount;
	@Getter @Setter
	private SearchDto search;

	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}

	public void addItem(T t) {
		this.items.add(t);
	}
	public void clearItems() {
		this.items.clear();
	}

	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
}