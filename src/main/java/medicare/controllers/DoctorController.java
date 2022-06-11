package medicare.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import medicare.models.Prescription;
import medicare.models.Treatment;
import medicare.services.AppointmentService;
import medicare.services.DiagnosticserviceService;
import medicare.services.DoctorService;
import medicare.services.PatientService;
import medicare.services.PrescriptionService;
import medicare.services.TestService;
import medicare.services.TreatmentService;

@Controller
public class DoctorController {
	
	@Autowired DiagnosticserviceService dssrv;
	@Autowired PatientService psrv;
	@Autowired DoctorService dsrv;
	@Autowired TestService tsrv;
	@Autowired TreatmentService tmsrv;
	@Autowired AppointmentService apsrv;
	@Autowired PrescriptionService prsrv;
	@Autowired HttpSession session;

	@GetMapping("/appointments")
	public String myappointment(Model model) {
		String docid=session.getAttribute("userid").toString();
		model.addAttribute("list", apsrv.findByDoctor(docid));
		return "appointments";
	}
	
	@GetMapping("/treatment")
	public String addTreatment(Model model,String pid) {
		model.addAttribute("t", new Treatment());
		return "addtreatment";
	}
	
	@PostMapping("/treatment")
	public String savetreatment(Treatment t,
			@RequestParam(value="mname[]")String[] mname,
			@RequestParam(value="qty[]")String[] qty,
			@RequestParam(value="times[]")String[] times,
			@RequestParam(value="units[]")String[] units) {
		
		int tid=tmsrv.addTreatment(t);
		for(int i=0;i<mname.length;i++) {
			if(mname[i].length()==0)
				break;
			Prescription p=new Prescription();
			p.setTid(tid);
			p.setMname(mname[i]);
			p.setUnit(units[i]);
			p.setTimes(times[i]);
			p.setQty(Integer.parseInt(qty[i]));
			prsrv.addPrescription(p);
//			System.out.println(mname[i]+" "+qty[i]+" "+units[i]+" "+times[i]);
		}
		
		return "redirect:/dochistory";
	}
	
	@GetMapping("treatdetails")
	public String treatdetails(int tid,Model model) {
		model.addAttribute("t", tmsrv.findTreatment(tid));
		model.addAttribute("plist", prsrv.getTreamentPrescription(tid));
		return "treatmentdetails";
	}
	
	@GetMapping("edittreatment")
	public String edittreatdetails(int tid,Model model) {
		model.addAttribute("t", tmsrv.findTreatment(tid));
		model.addAttribute("plist", prsrv.getTreamentPrescription(tid));
		return "edittreatment";
	}
	
	@PostMapping("edittreatment")
	public String edittreatdetails(Treatment t,Model model) {
		tmsrv.updateTreatment(t);
		return "redirect:/dochistory";
	}
	
	@GetMapping("/dochistory")
	public String treatmenthistory(Model model) {
		String docid=session.getAttribute("userid").toString();
		model.addAttribute("list", tmsrv.allDoctorTreatments(docid));
		return "treathistory";
	}
	
	@GetMapping("aptstatus")
	public String updateStatus(int id,String status) {
		apsrv.saveStatus(id, status);
		return "redirect:/appointments";
	}
	
	@GetMapping("/doctests")
	public String tests(Model model,String patid) {
		model.addAttribute("tests", tsrv.patientTests(patid));
		return "tests";
	}
	
	@GetMapping("/docpatients")
	public String patients(Model model) {
		String docid=session.getAttribute("userid").toString();
		model.addAttribute("users", psrv.allDoctorPatients(docid));
		return "patients";
	}
}
