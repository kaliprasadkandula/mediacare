package medicare.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import medicare.models.Patient;

@Service
public class PatientService {

	@Autowired NamedParameterJdbcTemplate temp;
	
	public void savePatient(Patient pat) {
		final String sql="insert into patient(patientid,fname,lname,pwd,gender,phone,address,dob) "
				+ "values(:patientid,:fname,:lname,:pwd,:gender,:phone,:address,:dob)";
		temp.update(sql, getSqlParameterByModel(pat));
	}
	
	public List<Patient> allPatients(){
		return temp.query("SELECT * FROM patient", new PatientRowMapper());
	}
	
	public List<Patient> allDoctorPatients(String docid){
		MapSqlParameterSource map=new MapSqlParameterSource();
		map.addValue("docid", docid);
		return temp.query("SELECT * FROM patient where patientid in(SELECT patid from appointment WHERE docid=:docid)",
				map,new PatientRowMapper());
	}
	
	public long countPatient() {
		return temp.queryForObject("SELECT count(*) from patient",getSqlParameterByModel(null),Long.class);
	}
	
	public Patient findByPatientId(String patientid) {
		Patient p=new Patient();
		p.setPatientid(patientid);
		try {
		return temp.queryForObject("SELECT * from patient WHERE patientid=:patientid", 
				getSqlParameterByModel(p), new PatientRowMapper());
		}catch(Exception ex) {
			return null;
		}
	}
	
	private SqlParameterSource getSqlParameterByModel(Patient u) {
        MapSqlParameterSource ps = new MapSqlParameterSource();
        if (u != null) {
            ps.addValue("patientid", u.getPatientid());
            ps.addValue("address", u.getAddress());
            ps.addValue("pwd", u.getPwd());
            ps.addValue("fname", u.getFname());
            ps.addValue("gender", u.getGender());
            ps.addValue("phone", u.getPhone());
            ps.addValue("lname", u.getLname());
            ps.addValue("dob", u.getDob());
        }
        return ps;
    }
	
	private class PatientRowMapper implements RowMapper<Patient>{

		@Override
		public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Patient doc=new Patient();
			doc.setPatientid(rs.getString("patientid"));
			doc.setAddress(rs.getString("address"));
			doc.setFname(rs.getString("fname"));
			doc.setLname(rs.getString("lname"));
			doc.setDob(rs.getString("dob"));
			doc.setGender(rs.getString("gender"));
			doc.setPhone(rs.getString("phone"));
			doc.setPwd(rs.getString("pwd"));
			return doc;
		}
		
	}
}
