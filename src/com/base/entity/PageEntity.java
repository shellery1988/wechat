package com.base.entity;

import java.util.List;
import java.util.Map;

public class PageEntity<T> {
	
	/**
	 * 分页起始记录数
	 */
	private long beginIndex;
	
	/**
	 * 分页结束记录数
	 */
	private long endIndex;
	
	/**
	 * 展示分页的起始分页数
	 */
	private long startIndex;
	
	/**
	 * 展示分页的结束分页数
	 */
	private long stopIndex;
	
	/**
	 * 当前页码
	 */
	private long currentPage = 1;
	
	/**
	 * 总页数
	 */
	private long pageCount;
	
	/**
	 * 每页记录数
	 */
	private long pageSize = 10;
	
	/**
	 * 总记录数
	 */
	private long totalRecords;

	private List<T> pageData;
	
	private Map<String, T> pageDataMap;
	
	public PageEntity() {

	}
	
	public long getBeginIndex() {
		beginIndex = (currentPage - 1) * pageSize;
		return beginIndex;
	}

	public void setBeginIndex(long beginIndex) {
		this.beginIndex = beginIndex;
	}

	public long getEndIndex() {
		endIndex = currentPage * pageSize;
		return endIndex;
	}

	public void setEndIndex(long endIndex) {
		this.endIndex = endIndex;
	}

	public long getStartIndex() {
		startIndex = (currentPage-1)/5*5+1;
		return startIndex;
	}

	public void setStartIndex(long startIndex) {
		this.startIndex = startIndex;
	}

	public long getStopIndex() {
		if((totalRecords-currentPage)>4){
			stopIndex = (currentPage-1)/5*5+5;
		}else{
			stopIndex = totalRecords;
		}
		return stopIndex;
	}

	public void setStopIndex(long stopIndex) {
		this.stopIndex = stopIndex;
	}

	public long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}

	public long getPageCount() {
		// 计算pageCount
		if(totalRecords%pageSize==0){
			this.pageCount = totalRecords/ pageSize;
		} else{
			this.pageCount = (totalRecords + pageSize) / pageSize;
		}
		return pageCount;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<T> getPageData() {
		return pageData;
	}

	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}

	public Map<String, T> getPageDataMap() {
		return pageDataMap;
	}

	public void setPageDataMap(Map<String, T> pageDataMap) {
		this.pageDataMap = pageDataMap;
	}
	
}
