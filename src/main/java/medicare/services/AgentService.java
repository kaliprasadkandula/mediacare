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

import medicare.models.Agent;

@Service
public class AgentService {
	
	@Autowired NamedParameterJdbcTemplate temp;
	
	public void addAgent(Agent agent) {
		final String sql="insert into agent(agentid,name,pwd,gender,phone,dob,bank,accno,ifsc,email) "
				+ "values(:agentid,:name,:pwd,:gender,:phone,:dob,:bank,:accno,:ifsc,:email)";
		temp.update(sql, getSqlParameterByModel(agent));
	}
	
	public List<Agent> allAgents(){
		return temp.query("SELECT * FROM agent", new AgentRowMapper());
	}
	
	public long countAgent() {
		return temp.queryForObject("SELECT count(*) from agent",getSqlParameterByModel(null),Long.class);
	}
	
	private SqlParameterSource getSqlParameterByModel(Agent u) {
        MapSqlParameterSource ps = new MapSqlParameterSource();
        if (u != null) {
            ps.addValue("agentid", u.getAgentid());
            ps.addValue("bank", u.getBank());
            ps.addValue("pwd", u.getPwd());
            ps.addValue("name", u.getName());
            ps.addValue("gender", u.getGender());
            ps.addValue("phone", u.getPhone());
            ps.addValue("email", u.getEmail());
            ps.addValue("dob", u.getDob());
            ps.addValue("ifsc", u.getIfsc());
            ps.addValue("accno", u.getAccno());
        }
        return ps;
    }
	
	private class AgentRowMapper implements RowMapper<Agent>{

		@Override
		public Agent mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Agent doc=new Agent();
			doc.setAgentid(rs.getString("agentid"));
			doc.setBank(rs.getString("bank"));
			doc.setName(rs.getString("name"));
			doc.setEmail(rs.getString("email"));
			doc.setDob(rs.getString("dob"));
			doc.setGender(rs.getString("gender"));
			doc.setPhone(rs.getString("phone"));
			doc.setPwd(rs.getString("pwd"));
			doc.setIfsc(rs.getString("ifsc"));
			doc.setAccno(rs.getString("accno"));
			return doc;
		}
		
	}
}
