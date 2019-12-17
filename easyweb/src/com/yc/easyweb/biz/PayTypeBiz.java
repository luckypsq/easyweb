package com.yc.easyweb.biz;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.yc.easyweb.bean.PayType;
import com.yc.easyweb.dao.PayTypeDao;

/**
 * ����PayType���������
 * 
 * @author psq
 *
 */
public class PayTypeBiz {

	private PayTypeDao dao = new PayTypeDao();

	// ��ѯ����
	public List<PayType> selectAll(PayType payType) throws IOException, BizException {
		if (payType == null) {
			throw new BizException("������֧��������Ϣ������");
		}
		return dao.selectAll(payType);
	}

	// ���
	public int insert(PayType payType) throws SQLException, BizException {
		if (payType == null) {
			throw new BizException("������֧��������Ϣ������");
		}
		if (payType.getEopayname() == null ) {
			throw new BizException("������֧��������Ϣ������");
		}else if(payType.getEopayname().isEmpty()){
			throw new BizException("������֧��������Ϣ������");
		}
		return dao.insert(payType);
	}

	// ɾ��
	public int delete(PayType payType) throws SQLException, BizException {
		if (payType == null) {
			throw new BizException("��ָ��֧��������Ϣ������");
		}
		if (payType.getEopayname() == null && payType.getEopaytypeid() == 0) {
			throw new BizException("��ָ��֧��������Ϣ������");
		}
		return dao.delete(payType);

	}
	//����
	public int update (PayType payTypeNew,PayType  payTypeOld) throws BizException {
		if (payTypeOld == null || payTypeNew == null) {
			throw new BizException("������֧��������Ϣ������");
		}
		if (payTypeOld.getEopayname() == null && !payTypeOld.getEopayname().isEmpty()) {
			throw new BizException("������֧��������Ϣ������");
		}
		return dao.update(payTypeNew, payTypeOld); 
	}
	 
}
