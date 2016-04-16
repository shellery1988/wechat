package com.util;

import java.util.List;

/**
 * 该类主要是起到对分页后的数据进行封装
 * 
 * @author 
 * @param <T>
 */
public class PageInfo<T> {

	private int pageCount; // 总页数
	private List<T> pageData; // 分页后的数据集
	private int pageSize = 10; // 分页中的数据条数
	private int currentNo = 0; // 当前页号
	private long totalRecords; // 总记录数
	private int beginIndex; // 分页起始记录号
	private int endIndex; // 分页结束记录号

	public PageInfo() {

	}

	public PageInfo(int currentNo, List<T> pageData, int pageSize, int totalRecords) {
		this.currentNo = currentNo;
		this.pageData = pageData;
		this.pageSize = pageSize;
		this.totalRecords = totalRecords;

		// 计算pageCount
		this.pageCount = (int) ((totalRecords + pageSize - 1) / pageSize);

		// 当前页是最后一页的情况，可能出现最后一页的数据小于pageSize
		if (pageCount == currentNo) {
			pageSize = totalRecords - (pageCount - 1) * pageSize;
		}

	}

	// 判断是否还有上一页
	public boolean isNextPage() {
		if (currentNo == pageCount) {
			return false;
		}
		return true;
	}

	// 判断是否还有下一页
	public boolean isPreviousPage() {
		if (currentNo == 1) {
			return false;
		}
		return true;
	}

	public int getBeginIndex() {
		// 计算beginIndex值
		beginIndex = currentNo * pageSize;
		// 对于数据库的计算
		/*if(beginIndex != 0){
			beginIndex++;
		}*/
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public int getEndIndex() {
		// 计算endIndex
		endIndex = (currentNo + 1) * pageSize;
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public int getPageCount() {
		// 计算pageCount
		this.pageCount = (int) ((totalRecords + pageSize - 1) / pageSize);

		// 当前页是最后一页的情况，可能出现最后一页的数据小于pageSize
		if (pageCount == currentNo) {
			pageSize = (int) (totalRecords - (pageCount - 1) * pageSize);
		}
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List<T> getPageData() {
		return pageData;
	}

	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentNo() {
		return currentNo;
	}

	public void setCurrentNo(int currentNo) {
		this.currentNo = currentNo;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

}
