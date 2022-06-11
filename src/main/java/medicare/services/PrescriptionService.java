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

import medicare.models.Prescription;

@Service
public class PrescriptionService {
	@Autowired NamedParameterJdbcTemplate temp;
	
	public void addPrescription(Prescription p) {
		final String sql="insert into prescription(mname,qty,unit,times,tid) values(:mname,:qty,:unit,:times,:tid)";
		temp.update(sql, getSqlParameterByModel(p));
	}
	
	public List<Prescription> getTreamentPrescription(int tid){
		Prescription p=new Prescription();
		p.setTid(tid);
		return temp.query("SELECT * from prescription WHERE tid=:tid", getSqlParameterByModel(p), new PrescriptionMapper());
	}
	
	private SqlParameterSource getSqlParameterByModel(Prescription t) {
        MapSqlParameterSource ps = new MapSqlParameterSource();
        if (t != null) {
            ps.addValue("id", t.getId());
            ps.addValue("mname", t.getMname());
            ps.addValue("qty", t.getQty());
            ps.addValue("unit", t.getUnit());
            ps.addValue("times", t.getTimes());
            ps.addValue("tid", t.getTid());
        }
        return ps;
    }
	
	private class PrescriptionMapper implements RowMapper<Prescription>{

		@Override
		public Prescription mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Prescription t=new Prescription();
			t.setId(rs.getInt("id"));
			t.setMname(rs.getString("mname"));
			t.setQty(rs.getInt("qty"));
			t.setTid(rs.getInt("tid"));
			t.setTimes(rs.getString("times"));
			t.setUnit(rs.getString("unit"));
			return t;
		}
		
	}
}
