package pccth.sp.pccthspseedservice.response;
import java.util.List;

import pccth.sp.pccthspseedservice.model.DetailModel;

public class DetailResponseDataHeader {
	private String vdtNo;
	private List<DetailModel> dataList;
	
	
	public String getVdtNo() {
		return vdtNo;
	}
	public void setVdtNo(String vdtNo) {
		this.vdtNo = vdtNo;
	}
	public List<DetailModel> getDataList() {
		return dataList;
	}
	public void setDataList(List<DetailModel> dataList) {
		this.dataList = dataList;
	}
	
	
}
