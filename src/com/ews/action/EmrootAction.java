package com.ews.action;

import java.sql.Timestamp;
import java.util.List;

import com.ews.bean.Emroot;
import com.ews.service.IEmrootService;
import com.ews.util.Pagination;


public class EmrootAction extends EwsAction{
	private IEmrootService emrootService;
	private Emroot mroot;
	private List<Emroot> listEmroot;
	public Emroot getMroot() {
		return mroot;
	}

	public void setMroot(Emroot mroot) {
		this.mroot = mroot;
	}

	public List<Emroot> getListEmroot() {
		return listEmroot;
	}
	public void setListEmroot(List<Emroot> listEmroot) {
		this.listEmroot = listEmroot;
	}
	public void setEmrootService(IEmrootService emrootService) {
		this.emrootService = emrootService;
	}	
	//�б�
	public String list(){		
		try {
			Pagination pagination = new Pagination(request, response);			
			int recordCount = emrootService.list("from Emroot").size();
			pagination.setRecordCount(recordCount);
			listEmroot = emrootService.list("from Emroot order by mrorder,mrid desc", pagination.getFirstResult(), pagination.getPageSize(), null);
			request.setAttribute("pagination", pagination);	
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "listback";
	}
	//���
	public String add(){
		mroot.setMraddtime(new Timestamp(System.currentTimeMillis()));
		emrootService.create(mroot);
		return "list";
	}
	//�޸���ʾ
	public String editshow(){
		int id = Integer.parseInt(request.getParameter("id"));
		mroot = (Emroot) emrootService.find(Emroot.class, id);
		return "edit";
	}
	//�޸�
	public String edit(){
		emrootService.update(mroot);
		return "list";
	}
	//ɾ��
	public String del(){
		int id = Integer.parseInt(request.getParameter("id"));
		mroot = (Emroot) emrootService.find(Emroot.class, id);
		emrootService.delete(mroot);
		return "list";
	}
	//������ʾ
	public String dis(){
		int id = Integer.parseInt(request.getParameter("id"));
		short dis = (short) Integer.parseInt(request.getParameter("dis"));
		mroot = (Emroot) emrootService.find(Emroot.class, id);
		mroot.setMrdisplay(dis);
		emrootService.update(mroot);
		return "list";
	}

	

}
