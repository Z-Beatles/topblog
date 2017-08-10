package cn.waynechu.topblog.dto;

public class Paginator {
	private PageParam pageParam;

	protected Paginator(PageParam pageParam) {
		this.pageParam = pageParam;
	}

	public int getPage() {
		return pageParam.getPage();
	}

	public int getLimit() {
		return pageParam.getLimit();
	}

	public long getTotalCount() {
		return pageParam.getTotalCount();
	}

	public boolean isFirstPage() {
		return pageParam.getPage() <= 1;
	}

	public boolean isLastPage() {
		return pageParam.getPage() >= getTotalPages();
	}

	public int getPrePage() {
		if (isHasPrePage()) {
			return pageParam.getPage() - 1;
		} else {
			return pageParam.getPage();
		}
	}

	public int getNextPage() {
		if (isHasNextPage()) {
			return pageParam.getPage() + 1;
		} else {
			return pageParam.getPage();
		}
	}

	public boolean isDisabledPage(int page) {
		return ((page < 1) || (page > getTotalPages()) || (page == pageParam.getPage()));
	}

	public boolean isHasPrePage() {
		return (pageParam.getPage() - 1 >= 1);
	}

	public boolean isHasNextPage() {
		return (pageParam.getPage() + 1 <= getTotalPages());
	}

	public int getStartRow() {
		if (getLimit() <= 0 || pageParam.getTotalCount() <= 0)
			return 0;
		return pageParam.getPage() > 0 ? (pageParam.getPage() - 1) * getLimit() + 1 : 0;
	}

	public long getEndRow() {
		return pageParam.getPage() > 0 ? Math.min(pageParam.getLimit() * pageParam.getPage(), getTotalCount()) : 0;
	}

	public int getOffset() {
		return pageParam.getPage() > 0 ? (pageParam.getPage() - 1) * getLimit() : 0;
	}

	public long getTotalPages() {
		if (pageParam.getTotalCount() <= 0) {
			return 0;
		}
		if (pageParam.getLimit() <= 0) {
			return 0;
		}

		long count = pageParam.getTotalCount() / pageParam.getLimit();
		if (pageParam.getTotalCount() % pageParam.getLimit() > 0) {
			count++;
		}
		return count;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Paginator");
		sb.append("{page=").append(getPage());
		sb.append(", limit=").append(getLimit());
		sb.append(", totalCount=").append(getTotalCount());
		sb.append('}');
		return sb.toString();
	}

}
