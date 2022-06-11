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


import medicare.models.FrontDesk;

@Service
public class FrontDeskService {

	@Autowired NamedParameterJdbcTemplate temp;
	
	public void saveFrontDesk(FrontDesk fd) {
		final String sql="insert into frontdesk(frontdeskid,fname,lname,pwd,gender,phone,dob,email,status) "
				                    + "values(:frontdeskid,:fname,:lname,:pwd,:gender,:phone,:dob,:email,:status)";
		temp.update(sql, getSqlParameterByModel(fd));
	}
	
	public List<FrontDesk> allFDs(){
		return temp.query("SELECT * FROM frontdesk", new FrontDeskRowMapper());
	}
	
	public long countFDs() {
		return temp.queryForObject("SELECT count(*) from  frontdesk",getSqlParameterByModel(null),Long.class);
	}
	
	public FrontDesk findById(String fdid) {
		FrontDesk fd=new FrontDesk();
		fd.setFrontDeskId(fdid);
		try {
		return temp.queryForObject("SELECT * from frontdesk WHERE frontdeskid=:frontdeskid", 
				getSqlParameterByModel(fd), new FrontDeskRowMapper());
		}catch(Exception ex) {
			return null;
		}
	}
	
	
	private SqlParameterSource getSqlParameterByModel(FrontDesk fd) {
        MapSqlParameterSource ps = new MapSqlParameterSource();
        if (fd != null) {
        	ps.addValue("frontdeskid", fd.getFrontDeskId());
        	ps.addValue("fname",fd.getFirstName());
        	ps.addValue("lname", fd.getLastName());
        	ps.addValue("pwd", fd.getPwd());
        	ps.addValue("gender", fd.getGender());
        	ps.addValue("phone", fd.getPhone());
        	ps.addValue("dob", fd.getDob());
        	ps.addValue("email", fd.getEmail());
        	ps.addValue("status", fd.getStatus());
        	
        }
        return ps;
    }
	
	private class FrontDeskRowMapper implements RowMapper<FrontDesk>{

		@Override
		public FrontDesk mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			FrontDesk fd=new FrontDesk();
			fd.setFrontDeskId("frontdeskid");
			fd.setFirstName("fname");
			fd.setLastName("lname");
			fd.setPwd("pwd");
			fd.setGender("gender");
			fd.setPhone("phone");
			fd.setDob("dob");
			fd.setEmail("email");
			fd.setStatus("status");
			return fd;
		}
		
	}
}
