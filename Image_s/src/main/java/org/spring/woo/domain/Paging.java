package org.spring.woo.domain;

public class Paging {
	private boolean prev; //����
	private boolean next; //����
	private int startPage; //���� ������
	private int endPage; //�� ������
	private int totalPage; //��ü ������
	private int page; //������ ��ȣ
    private int pageSize = 10; //������ ũ��
    
    //�˻������ ����� ����.
	private String keyword; //Ű����
	private String sType; //�˻� Ÿ��.
	
	public Paging() {
		
	}
	
	//�������� ��ü �������� �����´�.
	public Paging(int page,int totalPage) {
		this.totalPage = totalPage;
		this.page = page;
		calcPage();
	}

	//����¡ ó��.
	private void calcPage() {
		//�������� ���� 0���� ������ �������� ������ 1��������.
//		if (page <= 0) {
//			page = 1;
//		}
//		page = ( page - 1 ) * pageSize;
		
		//�ø�.(0.1~0.9 = 1*10 ,1.1~1.9 = 2*10)
		int tempEnd = (int)(Math.ceil(page/10.0) * 10);
		//���������� 
		startPage = tempEnd -9;
		
		//���� Ŭ���� (�������� 1�϶��� prev�� ���̸� �ȵȴ�.)
		prev = startPage == 1 ? false : true;
		//������ ������ ����. ������ ������ ������.
		if(tempEnd * pageSize >= totalPage){
			endPage = (int)(Math.ceil(totalPage/pageSize)+1);
			//������ ������������ next�� ���̸� �ȵȴ�.
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
