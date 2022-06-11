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

import medicare.models.Test;

@Service
public class TestService {
	
	@Autowired NamedParameterJdbcTemplate temp;
	
	public void addTest(Test t) {
		final String sql="insert into test(testname,patid) values(:testname,:patid)";
		temp.update(sql, getSqlParameterByModel(t));
	}
	
	public List<Test> allTests(){
		return temp.query("SELECT * FROM test order by id desc", new TestMapper());
	}
	
	public List<Test> patientTests(String patid){
		Test t=new Test();
		t.setPatid(patid);
		return temp.query("SELECT * FROM test where patid=:patid order by id desc",
				getSqlParameterByModel(t) ,new TestMapper());
	}
	
	public Test findTest(int id) {
		Test t=new Test();
		t.setId(id);
		return temp.queryForObject("SELECT * FROM test WHERE id=:id",getSqlParameterByModel(t),new TestMapper());
	}
	
	public void updateTest(Test t) {
		temp.update("update test set remarks=:remarks,result=:result,tdate=current_date(),status='Processed' where id=:id", getSqlParameterByModel(t));
	}
	
	private SqlParameterSource getSqlParameterByModel(Test t) {
        MapSqlParameterSource ps = new MapSqlParameterSource();
        if (t != null) {
            ps.addValue("id", t.getId());
            ps.addValue("patid", t.getPatid());
            ps.addValue("remarks", t.getRemarks());
            ps.addValue("testname", t.getTestname());
            ps.addValue("result", t.getResult());
            ps.addValue("status", t.getStatus());
        }
        return ps;
    }
	
	private class TestMapper implements RowMapper<Test>{

		@Override
		public Test mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Test t=new Test();
			t.setId(rs.getInt("id"));
			t.setPatid(rs.getString("patid"));
			t.setRemarks(rs.getString("remarks"));
			t.setTestname(rs.getString("testname"));
			t.setResult(rs.getString("result"));
			t.setStatus(rs.getString("status"));
			t.setTdate(rs.getString("tdate"));
			return t;
		}
		
	}

}
