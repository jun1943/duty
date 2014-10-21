package com.tianyi.drs.duty.viewmodel;

import java.util.ArrayList;
import java.util.List;
import com.tianyi.drs.duty.model.Org;

public class OrgVM extends Org{

	
	public static List<OrgVM> buildTree(List<Org> orgs){
		
		List<OrgVM> vms=new ArrayList<OrgVM>();
		
		int count=orgs.size();
		for(int i=0;i<count;i++){
			
			
			
		}
		
		
		return vms;
		
	}
	
	private List<OrgVM> children =new ArrayList<OrgVM>();

	public List<OrgVM> getChildren() {
		return children;
	}

	public void setChildren(List<OrgVM> children) {
		this.children = children;
	}
	
}
