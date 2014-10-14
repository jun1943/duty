package com.tianyi.drs.duty.service;

import java.util.List;

import com.tianyi.drs.duty.model.Gps;
import com.tianyi.util.PaginationData;

public interface GpsService {

	public long findCount(Gps gps);

	public List<Gps> findPageList(Gps query, PaginationData page);
}
