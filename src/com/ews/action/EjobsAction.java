package com.ews.action;

import java.util.ArrayList;
import java.util.List;

import com.ews.bean.Ejobs;
import com.ews.bean.Etalent;
import com.ews.service.IEjobsService;
import com.ews.service.IEtalentService;
import com.ews.util.Pagination;

public class EjobsAction extends EwsAction {

	private Ejobs ejobs;
	private IEjobsService<Ejobs> ejobsService;
	private IEtalentService<Etalent> etalentService;
	private List listEjobs = new ArrayList();
	private List listEtalents = new ArrayList();
	//�����������⣬�������м��ֶ�
	private String jdisplay; 
	private Etalent etalent;

	public Etalent getEtalent() {
		return etalent;
	}

	public void setEtalent(Etalent etalent) {
		this.etalent = etalent;
	}

	public String getJdisplay() {
		return jdisplay;
	}

	public void setJdisplay(String jdisplay) {
		this.jdisplay = jdisplay;
	}

	public List getListEjobs() {
		return listEjobs;
	}

	public void setListEjobs(List listEjobs) {
		this.listEjobs = listEjobs;
	}

	public Ejobs getEjobs() {
		return ejobs;
	}

	public void setEjobs(Ejobs ejobs) {
		this.ejobs = ejobs;
	}

	public void setEjobsService(IEjobsService<Ejobs> ejobsService) {
		this.ejobsService = ejobsService;
	}

	public void setEtalentService(IEtalentService<Etalent> etalentService) {
		this.etalentService = etalentService;
	}

	public List getListEtalents() {
		return listEtalents;
	}

	public void setListEtalents(List listEtalents) {
		this.listEtalents = listEtalents;
	}
	/**
	 * Ejobs
	 * 
	 * ��Ƹ��Ϣ�б�
	 * 
	 * */
	public String findList() {
		try {
			Pagination pagination = new Pagination(request, response);	
			int recordCount = ejobsService.list("from Ejobs").size();
			pagination.setRecordCount(recordCount);
			listEjobs = ejobsService.list("from Ejobs order by jorder,jid DESC", pagination.getFirstResult(), pagination.getPageSize(), null);
			request.setAttribute("pagination", pagination);
			//���ӦƸ����
			
//			System.out.println(listEjobs.size());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "findList";
	}
	/**
	 * Ejobs
	 * 
	 * �����Ƹ��Ϣ
	 * 
	 * */
	public String add() {
		try {
			ejobs.setJdisplay(Short.valueOf(jdisplay));
			ejobsService.save(ejobs);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "add";
	}
	/**
	 * Ejobs
	 * 
	 * ɾ����Ƹ��Ϣ
	 * 
	 * */
	public String delete() {
		try {
			ejobs = ejobsService.find(Ejobs.class, ejobs.getJid());
			ejobsService.delete(ejobs);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "delete";
	}
	/**
	 * Ejobs
	 * 
	 * ��ʼ���޸���Ƹ��Ϣ
	 * 
	 * */
	public String initEdit() {
		try {
			ejobs = ejobsService.find(Ejobs.class, ejobs.getJid());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "initEdit";
	}
	/**
	 * Ejobs
	 * 
	 * �޸���Ƹ��Ϣ
	 * 
	 * */
	public String edit() {
		try {
			ejobs.setJdisplay(Short.valueOf(jdisplay));
			ejobsService.update(ejobs);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "edit";
	}
	/**
	 * Ejobs
	 * 
	 * չʾ����
	 * 
	 * */
	public String details() {
		try {
			listEtalents = etalentService.list("from Etalent where tajid="+ejobs.getJid());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "details";
	}
	/**
	 * Ejobs
	 * 
	 * ��ʾ/����
	 * 
	 * */
	public String jdisplay() {
		try {
			String flag = ejobs.getJdisplay().toString();
			System.out.println(flag);
			ejobs = ejobsService.find(Ejobs.class,ejobs.getJid());
			if(flag.equals("0")){
				ejobs.setJdisplay(Short.valueOf("1"));	
			}else{
				ejobs.setJdisplay(Short.valueOf("0"));
			}
			ejobsService.update(ejobs);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "jdisplay";
	}
}
