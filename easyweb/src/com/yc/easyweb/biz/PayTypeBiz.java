package com.yc.easyweb.biz;

import java.util.List;

import com.yc.easyweb.bean.PayType;
import com.yc.easyweb.dao.PayTypeDao;

/**
 * ����PayType���������
 * @author psq
 *
 */
public class PayTypeBiz {

	private PayTypeDao dao= new PayTypeDao();
	
	//��ѯ����
		public List<PayType> selectAll(PayType payType) throws Exception{
			return dao.selectAll(payType);
		}
		//���
		public int insert(PayType payType) throws Exception{
			return dao.insert(payType);
			
		}
		//ɾ��
		public int delete(PayType payType){
			return 0;
			
		}
		//����
		public  int update (PayType payType) {
			return 0;
			
		}
		//����
}
