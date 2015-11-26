package com.ring.front.util;

public class PageUtils {
	
	// 每页默认显示条数 (10条)
	private final static int DEFAULT_SIZE_PER_PAGE = 10;
	// 页面显示的条数
	private int sizePerPage;
	// 默认页数 (第一页)
	private final static int DEFAULT_PAGE_NUM = 1;
	// 当前页数
	private int currentPage;
	// 总页数
	private int totalPage;
	// 是否为第一页
	private boolean firstFlag;
	// 是否为最后一页
	private boolean lastFlag;
	
	
	// 构造函数>>设置默认值
	public PageUtils() {
		this.currentPage = DEFAULT_PAGE_NUM;
		this.sizePerPage = DEFAULT_SIZE_PER_PAGE;
	}
	
	public static PageUtils getPageInfo(int totalSize, int currentPage) {
		// 定义分页对象
		PageUtils pageInfo = new PageUtils();
		int totalPage = 0;
		// 设置总页数
		if (totalSize%pageInfo.sizePerPage==0) {
			totalPage = totalSize/pageInfo.sizePerPage;
		} else {
			totalPage = totalSize/pageInfo.sizePerPage+1;
		}
		pageInfo.setTotalPage(totalPage);
		// 设置当前页
		pageInfo.setCurrentPage(currentPage);
		// 是否为第一页
		if (pageInfo.currentPage <=1) {
			pageInfo.setFirstFlag(true);
		} else {
			pageInfo.setFirstFlag(false);
		}
		// 是否为最后一页
		if (pageInfo.currentPage >=pageInfo.totalPage) {
			pageInfo.setLastFlag(true);
		} else {
			pageInfo.setLastFlag(false);
		}
		return pageInfo;
	}

	public int getSizePerPage() {
		return sizePerPage;
	}

	public void setSizePerPage(int sizePerPage) {
		this.sizePerPage = sizePerPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public boolean getFirstFlag() {
		return firstFlag;
	}

	public void setFirstFlag(boolean firstFlag) {
		this.firstFlag = firstFlag;
	}

	public boolean getLastFlag() {
		return lastFlag;
	}

	public void setLastFlag(boolean lastFlag) {
		this.lastFlag = lastFlag;
	}
}
