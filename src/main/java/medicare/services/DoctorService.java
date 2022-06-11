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

import medicare.models.Doctor;

@Service
public class DoctorService {

	@Autowired NamedParameterJdbcTemplate temp;
	
	public void saveDoctor(Doctor doc) {
		final String sql="insert into doctor(doctorid,fname,lname,pwd,gender,phone,diagservice,address,dob,speciality,qualification) "
				                + "values(:doctorid,:fname,:lname,:pwd,:gender,:phone,:diagservice,:address,:dob,:speciality,:qualification)";
		temp.update(sql, getSqlParameterByModel(doc));
	}
	
	public List<Doctor> allDocs(){
		return temp.query("SELECT * FROM doctor", new DoctorRowMapper());
	}
	
	public long countDocs() {
		return temp.queryForObject("SELECT count(*) from  doctor",getSqlParameterByModel(null),Long.class);
	}
	
	public Doctor findById(String docid) {
		Doctor d=new Doctor();
		d.setDoctorid(docid);
		try {
		return temp.queryForObject("SELECT * from doctor WHERE doctorid=:doctorid", 
				getSqlParameterByModel(d), new DoctorRowMapper());
		}catch(Exception ex) {
			return null;
		}
	}
	
	public List<Doctor> findByService(String servicename){
		Doctor d=new Doctor();
		d.setDiagservice(servicename);
		return temp.query("SELECT * from doctor WHERE diagservice=:diagservice", 
				getSqlParameterByModel(d), new DoctorRowMapper());
	}
	
	private SqlParameterSource getSqlParameterByModel(Doctor u) {
        MapSqlParameterSource ps = new MapSqlParameterSource();
        if (u != null) {
            ps.addValue("doctorid", u.getDoctorid());
            ps.addValue("address", u.getAddress());
            ps.addValue("pwd", u.getPwd());
            ps.addValue("fname", u.getFname());
            ps.addValue("gender", u.getGender());
            ps.addValue("phone", u.getPhone());
            ps.addValue("lname", u.getLname());
            ps.addValue("dob", u.getDob());
            ps.addValue("diagservice", u.getDiagservice());
            ps.addValue("qualification", u.getQualification());
            ps.addValue("speciality", u.getSpeciality());
        }
        return ps;
    }
	
	private class DoctorRowMapper implements RowMapper<Doctor>{

		@Override
		public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Doctor doc=new Doctor();
			doc.setDoctorid(rs.getString("doctorid"));
			doc.setAddress(rs.getString("address"));
			doc.setDiagservice(rs.getString("diagservice"));
			doc.setFname(rs.getString("fname"));
			doc.setLname(rs.getString("lname"));
			doc.setDob(rs.getString("dob"));
			doc.setGender(rs.getString("gender"));
			doc.setPhone(rs.getString("phone"));
			doc.setPwd(rs.getString("pwd"));
			doc.setSpeciality(rs.getString("speciality"));
			doc.setQualification(rs.getString("qualification"));
			return doc;
		}
		
	}
}
