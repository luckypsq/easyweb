package com.yc.easyweb.biz;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.yc.easyweb.bean.Eorder;
import com.yc.easyweb.bean.OrderDetial;
import com.yc.easyweb.bean.Page;
import com.yc.easyweb.dao.EorderDao;

/**
 * ����Eorder���������
 * 
 * @author psq
 *
 */
public class EorderBiz {

	private EorderDao dao = new EorderDao();

	// ��ѯ����
	public List<Eorder> selectAll(Eorder eorder) throws IOException, BizException  {
		if(eorder == null){
			throw new BizException("�����붩����Ϣ");
		}
		return dao.selectAll(eorder);
	}

	// ��ѯ����
	public Eorder selectSingle(Eorder eorder) throws IOException, BizException  {
		if(eorder == null){
			throw new BizException("�����붩����Ϣ");
		}
		if(eorder.getEoid() ==null && eorder.getUid() ==0 && eorder.getEostate() == 0 && eorder.getUname() == null){
			throw new BizException("��������Ҫ��ѯ�Ķ�����Ϣ");
		}
		return dao.selectSingle(eorder);
	}

	// ��ѯ��������
	public List<OrderDetial> selectAllDetail(OrderDetial detial) throws IOException, BizException  {
		if(detial == null){
			throw new BizException("�����붩����Ϣ");
		}
		return dao.selectAllDetail(detial);
	}
	// ��ѯ������������
		public OrderDetial selectSingleDetail(OrderDetial detial) throws IOException, BizException   {
			if(detial == null){
				throw new BizException("�����붩����Ϣ");
			}
			if(detial.getBid() == 0 && detial.getEoid() == null && detial.getUid() ==0 && detial.getUname() == null && detial.getEotemp() == null ){
				throw new BizException("��������Ҫ��ѯ�Ķ�����Ϣ");
			}
			return dao.selectSingleDetail(detial);
		}
	// ���
	public int insert(Eorder eorder) throws SQLException, BizException  {
		if(eorder == null){
			throw new BizException("�����붩����Ϣ����!");
		}
		if(eorder.getEoaddr() == null ){
			throw new BizException("�������ջ��ַ������");
		}else if(eorder.getEoaddr().isEmpty()){
			throw new BizException("�������ջ��ַ������");
		}
		
		
		if(eorder.getEotime() == null ){
			throw new BizException("�������µ�ʱ�䣡����");
		}else if(eorder.getEotime().isEmpty() ){
			throw new BizException("�������µ�ʱ�䣡����");
		}
		
		if(eorder.getUname() ==null ){
			throw new BizException("�������ջ��ˣ�����");
		}else if(eorder.getUname().isEmpty()){
			throw new BizException("�������ջ��ˣ�����");
		}
		
		if(eorder.getEotype() == null ){
			throw new BizException("��ѡ��֧�����ͣ�����");
		}else if(eorder.getEotype().isEmpty()){
			throw new BizException("��ѡ��֧�����ͣ�����");
		}
		
		if(eorder.getEopaytypeid() == 0){
			throw new BizException("������֧����ʽ������");
		}
		
		if(eorder.getEoid() == null ){
			throw new BizException("�����붩���ţ�����");
		}else if(eorder.getEoid().isEmpty()){
			throw new BizException("�����붩���ţ�����");
		}
		
		if(eorder.getUid() == 0){
			throw new BizException("�����붩�������ߣ�����");
		}
		
		if(eorder.getEostate() == 0){
			throw new BizException("�����붩��״̬������");
		}
		return dao.insert(eorder);

	}

	// ɾ��
	public int delete(Eorder eorder) throws SQLException, BizException  {
		if(eorder == null){
			throw new BizException("�����붩����Ϣ������");
		}
		if(eorder.getEoid() == null && eorder.getUid() == 0 && eorder.getUname() == null){
			throw new BizException("��ѡ����Ҫɾ���Ķ�����Ϣ������");
		}
	
		return dao.delete(eorder);

	}

	// ɾ�����
	public int delete(List<Eorder> list) throws SQLException, BizException  {
		if(list.size() == 0){
			throw new BizException("�����붩����Ϣ������");
		}
		for(Eorder eorder : list){
			if(eorder.getEoid() == null && eorder.getUid() == 0 && eorder.getUname() == null){
				throw new BizException("��ѡ����Ҫɾ���Ķ�����Ϣ������");
			}
		}
		return dao.delete(list);

	}

	// ����
	public int update(Eorder eorderNew, Eorder eorderOld) throws SQLException, BizException  {
		if(eorderNew == null){
			throw new BizException("�������޸ĵĶ�����Ϣ������");
		}
		if(eorderOld == null){
			throw new BizException("��������Ҫ�޸ĵĶ���������");
		}
		if(eorderOld.getEoid() == null && eorderOld.getUid() == 0 && eorderOld.getUname() == null){
			throw new BizException("��ѡ����Ҫ��ĵĶ�����Ϣ������");
		}
		return dao.update(eorderNew, eorderOld);
	}
	//��ҳ
	public Page<Eorder> eorderPage(int page, int rows,Eorder eorder) throws IOException, BizException {
		if(eorder == null){
			throw new BizException("�����붩����Ϣ������");
		}
		return dao.eorderPage(page, rows, eorder);
	}
	public Page<OrderDetial> eorderPage(int page, int rows,OrderDetial eorder) throws IOException, BizException {
		if(eorder == null){
			throw new BizException("�����붩����Ϣ������");
		}
		return dao.orderPage(page, rows, eorder);
	}
	// ����
}
