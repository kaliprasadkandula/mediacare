package medicare.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import medicare.models.Appointment;
import medicare.models.Test;
import medicare.services.AppointmentService;
import medicare.services.DiagnosticserviceService;
import medicare.services.DoctorService;
import medicare.services.PatientService;
import medicare.services.TestService;
import medicare.services.TreatmentService;

@Controller
public class PatientController {
	
	@Autowired DiagnosticserviceService dssrv;
	@Autowired PatientService psrv;
	@Autowired DoctorService dsrv;
	@Autowired TestService tsrv;
	@Autowired TreatmentService tmsrv;
	@Autowired AppointmentService apsrv;
	@Autowired HttpSession session;

	@GetMapping("/detailds")
	public String diagdetails(int dsid,Model model) {
		model.addAttribute("ds", dssrv.findById(dsid));
		return "detailservice";
	}
	
	@GetMapping("/viewdocs")
	public String viewdoctors(String dsname,Model model) {
		model.addAttribute("users", dsrv.findByService(dsname));
		return "doctors";
	}
	
	@GetMapping("/testrequest")
	public String testrequest() {
		return "raisetestreq";
	}
	
	@PostMapping("/testrequest")
	public String testrequest(Test t) {
		tsrv.addTest(t);
		return "redirect:/mytests";
	}
	
	@GetMapping("/mytests")
	public String mytests(Model model) {
		String patid=session.getAttribute("userid").toString();
		model.addAttribute("tests", tsrv.patientTests(patid));
		return  "tests";
	}
	
	@GetMapping("/pathistory")
	public String treatmenthistory(Model model) {
		String patid=session.getAttribute("userid").toString();
		model.addAttribute("list", tmsrv.allPatientTreatments(patid));
		return "treathistory";
	}
	
	@GetMapping("/bookapt")
	public String bookappointment(Model model) {
		model.addAttribute("users", dsrv.allDocs());
		return "bookappt";
	}
	
	@PostMapping("/bookapt")
	public String saveappointment(Appointment apt) {
		String docname=dsrv.findById(apt.getDocid()).getFname()+" "+dsrv.findById(apt.getDocid()).getLname();
		apt.setDocname(docname);
		apsrv.saveAppointment(apt);
		return "redirect:/bookapt";
	}
	
	@GetMapping("/myapts")
	public String myappointment(Model model) {
		String patid=session.getAttribute("userid").toString();
		model.addAttribute("list", apsrv.findByPatientId(patid));
		return "appointments";
	}
	
	@GetMapping("/aptdetails")
	public String appointmentdetails(Model model,int id) {		
		model.addAttribute("ap", apsrv.findByApptId(id));
		return "aptdetails";
	}
}
