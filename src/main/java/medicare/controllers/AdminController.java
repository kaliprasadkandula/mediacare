package medicare.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import medicare.models.Admin;
import medicare.models.Agent;
import medicare.models.DiagnosticService;
import medicare.models.Doctor;
import medicare.models.FrontDesk;
import medicare.models.Patient;
import medicare.models.Test;
import medicare.services.AdminService;
import medicare.services.AgentService;
import medicare.services.DiagnosticserviceService;
import medicare.services.DoctorService;
import medicare.services.FrontDeskService;
import medicare.services.PatientService;
import medicare.services.TestService;

@Controller
public class AdminController {
	@Autowired FrontDeskService fdsrv;
	@Autowired DoctorService dsrv;
	@Autowired PatientService psrv;
	@Autowired AdminService asrv;
	
	@Autowired HttpSession session;	

	@GetMapping("/")
	public String home() {
		return  "index";
	}
	
	@PostMapping("/validate") //comes from index.jsp page
	public String validate(String userid,String pwd,Model model,RedirectAttributes ra) {
		
		if(userid.startsWith("doctor")) {
			
			Doctor d=dsrv.findById(userid);
			//System.out.println(d.getDoctorid()+" "+d.getPwd());
			if(d.getPwd().equals(pwd)) {
			session.setAttribute("userid", userid);
			session.setAttribute("role", "Doctor");
			session.setAttribute("uname", d.getFname()+" "+d.getLname());
			return "redirect:/docpatients";
			}else {
				System.out.println("doctor");
				ra.addFlashAttribute("msg", "Incorrect userid or password");
				return "redirect:/";
			}
			
		}
		else if(userid.startsWith("frontdesk")) {
			
			FrontDesk fd=fdsrv.findById(userid);
			System.out.println(fdsrv.countFDs());
			if(fd.getPwd().equals(pwd) && fd.getStatus().equals("approved")) {
			session.setAttribute("userid", userid);
			session.setAttribute("role", "FrontDesk");
			session.setAttribute("uname", fd.getFirstName()+" "+fd.getLastName());
			
			return "redirect:/viewdocs";
			}
			else if(fd.getPwd().equals(pwd) && fd.getStatus().equals("rejected")) {
				ra.addFlashAttribute("msg", "Sorry,Your registration was rejected");
				return "redirect:/dashboard";//this redirect goes to same page but with the redirect attributes
			}
			else if(fd.getPwd().equals(pwd) && fd.getStatus().equals("not approved")) {
				ra.addFlashAttribute("msg", "Sorry,Your registration was not approved yet");
				return "redirect:/dashboard";//this redirect goes to same page but with the redirect attributes
			}
			else {
				System.out.println("frontdesk");
				ra.addFlashAttribute("msg", "Incorrect userid or password");
				return "redirect:/";
			}
		}
		else if(userid.startsWith("patient")) {
			Patient d=psrv.findByPatientId(userid);
			if(d.getPwd().equals(pwd)) {
			session.setAttribute("userid", userid);
			session.setAttribute("role", "Patient");
			session.setAttribute("uname", d.getFname()+" "+d.getLname());
			return "redirect:/viewdocs";
			}else {
				System.out.println("patient");
				ra.addFlashAttribute("msg", "Incorrect userid or password");
				return "redirect:/";
			}
		}
		else {
			Admin admin=asrv.validate(new Admin(userid, pwd));
			if(admin==null) {
				ra.addFlashAttribute("msg", "Incorrect userid or password");
				return "redirect:/";
			}else {
				session.setAttribute("userid", userid);
				session.setAttribute("role", "Admin");
				session.setAttribute("uname", "Admin");
				return "redirect:/dashboard";
			}
		}
	}
	
	@GetMapping("/dashboard")
	public String dashboard() {
		return "adashboard";
	}
	
	@GetMapping("/doctors")
	public String doctors(Model model) {
		model.addAttribute("users", dsrv.allDocs());
		return "doctors";
	}
	@GetMapping("/frontdesks")
	public String frontdesks(Model model) {
		model.addAttribute("users", fdsrv.allFDs());
		return "frontdesks";
	}
	
	@GetMapping("/patients")
	public String patients(Model model) {
		model.addAttribute("users", psrv.allPatients());
		return "patients";
	}
	
	
	
	
	@GetMapping("/register")
	public String register(Model model,String role) {
		String docid="doctor"+(dsrv.countDocs()+1);
		String patid="patient"+(psrv.countPatient()+1);
		String frontdeskid="frontdesk"+(fdsrv.countFDs()+1);
		model.addAttribute("docid", docid);
		model.addAttribute("patid", patid);
		model.addAttribute("fdid", frontdeskid);
		
		model.addAttribute("doctor", new Doctor());
		model.addAttribute("patient", new Patient());
		model.addAttribute("frontdesk",new FrontDesk());
		return "register";
	}
	
	@PostMapping("/registerdoc")
	public String saveDoctor(Doctor doc,String role,Model model,RedirectAttributes ra) {
		System.out.println(doc);
		dsrv.saveDoctor(doc);
		ra.addFlashAttribute("msg", "Doctor registered succecssfully");
		return "redirect:/";
	}
	
	
	@PostMapping("/registerpat")
	public String savePatient(Patient pat,String role,RedirectAttributes ra) {
		System.out.println(pat);
		psrv.savePatient(pat);
		ra.addFlashAttribute("msg", "Patient registered succecssfully");
		return "redirect:/";
	}
	
	@PostMapping("/registerfd")
	public String saveFrontDesk(FrontDesk fd,String role,Model model,RedirectAttributes ra) {
		System.out.println(fd);
		fdsrv.saveFrontDesk(fd);
		ra.addFlashAttribute("msg", "FrontDesk registered succecssfully");
		return "redirect:/";
	}
	
	
	
	
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	
}
