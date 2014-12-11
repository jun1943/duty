package com.tianyi.drs.duty.viewmodel;

import java.util.ArrayList;
import java.util.List;
import com.tianyi.drs.duty.model.Org;
/**
 * 组织机构model类，继承组织机构实体类，用作前台显示操作
 * @author lq
 */
public class OrgVM extends Org{

	/**
	 * 创建树节点
	 * 
	 * 没有使用
	 */
	public static List<OrgVM> buildTree(List<Org> orgs){
		
		List<OrgVM> vms=new ArrayList<OrgVM>();
		
		int count=orgs.size();
		for(int i=0;i<count;i++){
			
			
			
		}
		
		
		return vms;
		
	}
	/**
	 * 子节点，内循环
	 */
	private List<OrgVM> children =new ArrayList<OrgVM>();

	public List<OrgVM> getChildren() {
		return children;
	}

	public void setChildren(List<OrgVM> children) {
		this.children = children;
	}
	
}
