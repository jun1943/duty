package com.tianyi.drs.duty.util;

import java.util.ArrayList;
import java.util.List;

public class TreeHelper {
	/**
	 * 获取树形结构的所有父节点的全路径
	 * @param fullPath
	 * @return
	 */
	public static List<String> getAllParentFullPath(String fullPath){
		List<String>  ls=new ArrayList<String>();
		
		String[] names=fullPath.split("\\.");
		
		int count=names.length;
		
		for(int i=0;i<count;i++){
			String fp=null;
			if(i==0){
				fp=names[0];
				ls.add(fp);
			}else{
				fp=ls.get(i-1) +"." + names[i];
				ls.add(fp);
			}
		}
		return ls;
	}
}
