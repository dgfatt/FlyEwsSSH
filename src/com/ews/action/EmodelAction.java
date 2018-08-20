package com.ews.action;

import java.sql.Timestamp;
import java.util.List;
import com.ews.bean.Emodel;
import com.ews.bean.Emroot;
import com.ews.service.IEmodelService;
import com.ews.util.Pagination;

public class EmodelAction extends EwsAction{	
	private IEmodelService emodelService;
	private Emodel model;
	private List<Emodel> listEmodel;	
	public Emodel getModel() {
		return model;
	}
	public void setModel(Emodel model) {
		this.model = model;
	}
	public List<Emodel> getListEmodel() {
		return listEmodel;
	}
	public void setListEmodel(List<Emodel> listEmodel) {
		this.listEmodel = listEmodel;
	}
	public void setEmodelService(IEmodelService emodelService) {
		this.emodelService = emodelService;
	}
	//�б�
	public String list(){
		try {
			Pagination pagination = new Pagination(request, response);			
			int recordCount = emodelService.list("from Emodel").size();
			pagination.setRecordCount(recordCount);
			listEmodel = emodelService.list("from Emodel order by morder,mid desc", pagination.getFirstResult(), pagination.getPageSize(), null);
			request.setAttribute("pagination", pagination);	
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "listback";
	}
	//���
	public String add(){
		model.setMaddtime(new Timestamp(System.currentTimeMillis()));
		emodelService.create(model);
		return "list";
	}
	//�޸���ʾ
	public String editshow(){
		int id = Integer.parseInt(request.getParameter("id"));
		model = (Emodel) emodelService.find(Emodel.class, id);
		return "edit";
	}
	//�޸�
	public String edit(){
		emodelService.update(model);
		return "list";
	}
	//ɾ��
	public String del(){
		int id = Integer.parseInt(request.getParameter("id"));
		model = (Emodel) emodelService.find(Emodel.class, id);
		emodelService.delete(model);
		return "list";
	}
	//������ʾ
	public String dis(){
		int id = Integer.parseInt(request.getParameter("id"));
		short dis = (short) Integer.parseInt(request.getParameter("dis"));
		model = (Emodel) emodelService.find(Emodel.class, id);
		model.setMdisplay(dis);
		emodelService.update(model);
		return "list";
	}
	//������ʾ
	public String cla(){
		
		return "list";
	}
	//����ɾ��
	public String deld(){
		
		return "list";
	}

}
