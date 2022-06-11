package medicare.models;

public class Treatment {
	
	private int id;
	private String patid;
	private String docid;
	private String patname;
	private String docname;
	private String symptoms;
	private String diagnosis;
	private String recommendations;
	private String diet;
	private String referal;
	private String tests;
	private String tdate;
	
	public String getTdate() {
		return tdate;
	}
	public void setTdate(String tdate) {
		this.tdate = tdate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPatid() {
		return patid;
	}
	public void setPatid(String patid) {
		this.patid = patid;
	}
	public String getDocid() {
		return docid;
	}
	public void setDocid(String docid) {
		this.docid = docid;
	}
	public String getPatname() {
		return patname;
	}
	public void setPatname(String patname) {
		this.patname = patname;
	}
	public String getDocname() {
		return docname;
	}
	public void setDocname(String docname) {
		this.docname = docname;
	}
	public String getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getRecommendations() {
		return recommendations;
	}
	public void setRecommendations(String recommendations) {
		this.recommendations = recommendations;
	}
	public String getDiet() {
		return diet;
	}
	public void setDiet(String diet) {
		this.diet = diet;
	}
	public String getReferal() {
		return referal;
	}
	public void setReferal(String referal) {
		this.referal = referal;
	}
	public String getTests() {
		return tests;
	}
	public void setTests(String tests) {
		this.tests = tests;
	}
	@Override
	public String toString() {
		return "Treatment [id=" + id + ", patid=" + patid + ", docid=" + docid + ", patname=" + patname + ", docname="
				+ docname + ", symptoms=" + symptoms + ", diagnosis=" + diagnosis + ", recommendations="
				+ recommendations + ", diet=" + diet + ", referal=" + referal + ", tests=" + tests + ", tdate=" + tdate
				+ "]";
	}
	
	
}
