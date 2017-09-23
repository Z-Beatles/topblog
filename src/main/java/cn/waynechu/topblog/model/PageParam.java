package cn.waynechu.topblog.model;

public class PageParam {

	private Integer limit;
	private Integer page;
	private Long totalCount;
	private String orderby;

	private void checkParam() {
		if (page == null || page <= 0) {
			page = 1;
		}
		if (limit == null || limit <= 0) {
			limit = 20;
		}
	}

	public Paginator toPaginator() {
		checkParam();
		return new Paginator(this);
	}

	// -------------------------------Getter/Setter----------------------------------------//

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

}