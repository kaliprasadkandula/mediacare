package medicare.services;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import medicare.models.Treatment;

@Service
public class TreatmentService {

	@Autowired NamedParameterJdbcTemplate temp;
	
	public int addTreatment(Treatment t) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		final String sql="insert into treatment(patid,patname,docid,docname,diagnosis,diet,recommendations,referal,symptoms,tests) "
				+ "values(:patid,:patname,:docid,:docname,:diagnosis,:diet,:recommendations,:referal,:symptoms,:tests)";
		temp.update(sql, getSqlParameterByModel(t), keyHolder);
		BigInteger trid=(BigInteger)keyHolder.getKey();
		return trid.intValue();
	}
	
	public void updateTreatment(Treatment t) {
		final String sql="update treatment set diagnosis=:diagnosis,diet=:diet,recommendations=:recommendations,"
				+ "referal=:referal,symptoms=:symptoms,tests=:tests";
		temp.update(sql, getSqlParameterByModel(t));
	}
	
	public List<Treatment> allDoctorTreatments(String docid){
		Treatment t=new Treatment();
		t.setDocid(docid);
		return temp.query("SELECT * From treatment WHERE docid=:docid order by id desc", getSqlParameterByModel(t),new TreatmentMapper());
	}
	
	public List<Treatment> allPatientTreatments(String patid){
		Treatment t=new Treatment();
		t.setPatid(patid);
		return temp.query("SELECT * From treatment WHERE patid=:patid order by id desc", getSqlParameterByModel(t),new TreatmentMapper());
	}
	
	public Treatment findTreatment(int id) {
		final String sql="SELECT * FROM treatment where id=:id";
		Treatment t=new Treatment();
		t.setId(id);
		return temp.queryForObject(sql, getSqlParameterByModel(t), new TreatmentMapper());
	}
	
	private SqlParameterSource getSqlParameterByModel(Treatment t) {
        MapSqlParameterSource ps = new MapSqlParameterSource();
        if (t != null) {
            ps.addValue("id", t.getId());
            ps.addValue("patid", t.getPatid());
            ps.addValue("patname", t.getPatname());
            ps.addValue("docid", t.getDocid());
            ps.addValue("docname", t.getDocname());
            ps.addValue("diagnosis", t.getDiagnosis());
            ps.addValue("diet", t.getDiet());
            ps.addValue("recommendations", t.getRecommendations());
            ps.addValue("referal", t.getReferal());
            ps.addValue("symptoms", t.getSymptoms());
            ps.addValue("tdate", t.getTdate());
            ps.addValue("tests", t.getTests());
        }
        return ps;
    }
	
	private class TreatmentMapper implements RowMapper<Treatment>{

		@Override
		public Treatment mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Treatment t=new Treatment();
			t.setId(rs.getInt("id"));
			t.setPatid(rs.getString("patid"));
			t.setTdate(rs.getString("tdate"));
			t.setPatname(rs.getString("patname"));
			t.setDocid(rs.getString("docid"));
			t.setDocname(rs.getString("docname"));
			t.setTests(rs.getString("tests"));
			t.setSymptoms(rs.getString("symptoms"));
			t.setReferal(rs.getString("referal"));
			t.setDiet(rs.getString("diet"));
			t.setRecommendations(rs.getString("recommendations"));
			t.setDiagnosis(rs.getString("diagnosis"));
			return t;
		}
		
	}
}
