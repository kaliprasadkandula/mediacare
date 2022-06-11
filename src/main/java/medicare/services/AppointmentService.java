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

import medicare.models.Appointment;

@Service
public class AppointmentService {
	
	@Autowired NamedParameterJdbcTemplate temp;
	
	public void saveAppointment(Appointment ap) {
		final String sql="insert into appointment(date,time,docid,docname,patid,patname,servicename,remarks,conditions) "
				+ "values(:date,:time,:docid,:docname,:patid,:patname,:servicename,:remarks,:conditions)";
		temp.update(sql,getSqlParameterByModel(ap));
	}
	
	public void saveStatus(int apptid,String status) {
		Appointment ap=new Appointment();
		ap.setApptid(apptid);
		ap.setStatus(status);
		temp.update("UPDATE appointment SET status=:status WHERE apptid=:apptid", getSqlParameterByModel(ap));
	}
	
	public List<Appointment> findByDoctor(String docid){
		Appointment ap=new Appointment();
		ap.setDocid(docid);
		return temp.query("SELECT * from appointment WHERE docid=:docid",getSqlParameterByModel(ap) , new ApptRowMapper());
	}

	public List<Appointment> findByPatientId(String patid){
		Appointment ap=new Appointment();
		ap.setPatid(patid);
		return temp.query("SELECT * from appointment WHERE patid=:patid",
				getSqlParameterByModel(ap) ,new ApptRowMapper());
	}
	
	public Appointment findByApptId(int id) {
		Appointment ap=new Appointment();
		ap.setApptid(id);
		return temp.queryForObject("SELECT * from appointment WHERE apptid=:apptid",
				getSqlParameterByModel(ap) ,new ApptRowMapper());
	}
	
	private SqlParameterSource getSqlParameterByModel(Appointment ap) {
        MapSqlParameterSource ps = new MapSqlParameterSource();
        if (ap != null) {
            ps.addValue("apptid", ap.getApptid());
            ps.addValue("date", ap.getDate());
            ps.addValue("time", ap.getTime());
            ps.addValue("docid", ap.getDocid());
            ps.addValue("patid", ap.getPatid());
            ps.addValue("servicename", ap.getServicename());
            ps.addValue("remarks", ap.getRemarks());
            ps.addValue("patname", ap.getPatname());
            ps.addValue("docname", ap.getDocname());
            ps.addValue("status", ap.getStatus());
            ps.addValue("conditions", ap.getConditions());            
        }
        return ps;
    }
	
	private class ApptRowMapper implements RowMapper<Appointment>{

		@Override
		public Appointment mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Appointment ap=new Appointment();
			ap.setApptid(rs.getInt("apptid"));
			ap.setConditions(rs.getString("conditions"));
			ap.setDate(rs.getString("date"));
			ap.setTime(rs.getString("time"));
			ap.setPatid(rs.getString("patid"));
			ap.setPatname(rs.getString("patname"));
			ap.setDocid(rs.getString("docid"));
			ap.setDocname(rs.getString("docname"));
			ap.setServicename(rs.getString("servicename"));
			ap.setRemarks(rs.getString("remarks"));
			ap.setStatus(rs.getString("status"));
			return ap;
		}
		
	}
}
