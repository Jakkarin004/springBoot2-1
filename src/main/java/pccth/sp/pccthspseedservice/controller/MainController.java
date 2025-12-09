package pccth.sp.pccthspseedservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pccth.sp.pccthspseedservice.dto.SaveAllDataRequest;
import pccth.sp.pccthspseedservice.entity.DetailEntity;
import pccth.sp.pccthspseedservice.entity.HeaderEntity;
import pccth.sp.pccthspseedservice.model.DetailModel;
import pccth.sp.pccthspseedservice.model.HeaderModel;
import pccth.sp.pccthspseedservice.model.RdbvrtrateModel;
import pccth.sp.pccthspseedservice.response.DetailResponseDataHeader;
import pccth.sp.pccthspseedservice.service.MainService;
import springfox.documentation.spring.web.plugins.Docket;

@RestController
@RequestMapping("Main-controller")
public class MainController {
	
	private final Docket api;
	
	@Autowired
	private MainService mainService;

    MainController(Docket api) {
        this.api = api;
    }
    
    @GetMapping("/get-search-header")
    public List<HeaderModel> searchHeader(
    		@RequestParam(required = false) String vdtNo,
			@RequestParam(required = false) String vdtDate
    		) {
    	return mainService.searchHeader(vdtNo,vdtDate);
    }
    
    @GetMapping("/get-detail-ByVdtno")
    public DetailResponseDataHeader findDetailByVdtNo(@RequestParam String vdtNo) {
    	return mainService.findDetailByVdtNo(vdtNo);
    }
    
    @PostMapping("save-all-data")
    public void saveDataAll(@RequestBody SaveAllDataRequest req) {
        if (req.getVdtNo() != null) {
        	mainService.saveDataAll(req.getDetails(), req.getVdtNo());
        } 
        else {
            //VdtNo จะส่งไป updateDetail ตาม vdtNo ของตัว detail
            for (DetailModel d : req.getDetails()) {
                mainService.updateDetail(d);
            }
        }
    }
     
    @PostMapping("/print-pdf")
    public ResponseEntity<?> printPdf(@RequestBody List<DetailModel> dataList) {
        String base64 = mainService.generatePdf(dataList);
        return ResponseEntity.ok(base64);
    }

    @DeleteMapping("/delete/{id}")
	public void deleteData(@PathVariable int id) {
    	mainService.deleteData(id);
	}
    
    @GetMapping("/get-vat-tax")
    public List<RdbvrtrateModel> getVatTax() {
    	return mainService.getVatTax();
    }
}
