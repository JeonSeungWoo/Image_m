package org.spring.woo.domain;

public class Paging {
	private boolean prev; //이전
	private boolean next; //다음
	private int startPage; //시작 페이지
	private int endPage; //끝 페이지
	private int totalPage; //전체 페이지
	private int page; //페이지 번호
    private int pageSize = 10; //페이지 크기
    
    //검색기능을 사용할 때만.
	private String keyword; //키워드
	private String sType; //검색 타입.
	
	public Paging() {
		
	}
	
	//페이지와 전체 페이지를 가져온다.
	public Paging(int page,int totalPage) {
		this.totalPage = totalPage;
		this.page = page;
		calcPage();
	}

	//페이징 처리.
	private void calcPage() {
		//페이지가 만약 0보다 작을때 페이지는 무조건 1페이지다.
//		if (page <= 0) {
//			page = 1;
//		}
//		page = ( page - 1 ) * pageSize;
		
		//올림.(0.1~0.9 = 1*10 ,1.1~1.9 = 2*10)
		int tempEnd = (int)(Math.ceil(page/10.0) * 10);
		
		//시작페이지 
		startPage = tempEnd -9;
		
		//이전 클릭시 (페이지가 1일때는 prev가 보이면 안된다.)
		prev = startPage == 1 ? false : true;
		
		//끝나는 페이지 설정. 완전히 마지막 페이지.
		if(tempEnd * pageSize >= totalPage){
			 
			endPage = (int)(Math.ceil(totalPage/pageSize)+1);
			//완전히 마지막에서는 next가 보이면 안된다.
			next = false;
			
		}else{
			endPage = tempEnd;
			next = true;
		}
	}

	
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getsType() {
		return sType;
	}

	public void setsType(String sType) {
		this.sType = sType;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "Paging [prev=" + prev + ", next=" + next + ", startPage=" + startPage + ", endPage=" + endPage
				+ ", totalPage=" + totalPage + ", page=" + page + ", pageSize=" + pageSize + ", keyword=" + keyword
				+ ", sType=" + sType + "]";
	}



}
