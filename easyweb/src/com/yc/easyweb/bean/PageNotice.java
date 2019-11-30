package com.yc.easyweb.bean;

import java.util.List;

public class PageNotice {
	
	 // ��ǰҳ��
    private  List<Notice> data;
    // ����
    private long total;
    private int page;
	private int rows;
   

    public PageNotice(List<Notice>data, long total, int page, int rows) {
		super();
		this.data = data;
		this.total = total;
		this.page = page;
		this.rows = rows;
	}

	//��ȡ���е�����
    public List<Notice> getData() {
        return data;
    }

    //��ȡ���ҳ����
    public long getTotal() {
        return total;
    }
    //��ȡ��ǰҳ������
    public int getPage(){
    	return page;
    }
    
    //��ȡ���һ��ҳ������
    public int getLastPage(){
    	long lastPage = total / rows;
    	return (int) (total % rows == 0 ?  lastPage : (lastPage + 1));
    }
    //��ȡ��һ��ҳ������
    public int getNextPage(){
    	int lastpage = getLastPage();
    	return page < lastpage ? page + 1 : lastpage;
    }
    //��ȡ��һ��ҳ������
    public int getPreviousPage(){
    	return page > 1 ? page - 1 : 1;
    }
    //��ȡ��һ��ҳ��
    public int getFirstPage(){
    	return 1;
    }
	
}
