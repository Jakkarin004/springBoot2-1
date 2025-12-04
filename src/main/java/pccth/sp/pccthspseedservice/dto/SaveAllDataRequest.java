package pccth.sp.pccthspseedservice.dto;

import java.util.List;

import pccth.sp.pccthspseedservice.entity.DetailEntity;
import pccth.sp.pccthspseedservice.model.DetailModel;

public class SaveAllDataRequest {
	private String vdtNo;
	private List<DetailModel> details;
	
	 public String getVdtNo() {
	        return vdtNo;
	    }
	    public void setVdtNo(String vdtNo) {
	        this.vdtNo = vdtNo;
	    }

    public List<DetailModel> getDetails() {
        return details;
    }
    
    public void setDetails(List<DetailModel> details) {
        this.details = details;
    }
}
