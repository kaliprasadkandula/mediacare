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

import medicare.models.DiagnosticService;

@Service
public class DiagnosticserviceService {
	
	@Autowired NamedParameterJdbcTemplate temp;
	
	public void addService(DiagnosticService ds) {
		final String sql="insert into diagnostic_service(addline1,addline2,centername,city,email,state,zip,phone,website,servicename,facilities) "
				+ "values(:addline1,:addline2,:centername,:city,:email,:state,:zip,:phone,:website,:servicename,:facilities)";
		temp.update(sql, getSqlParameterByModel(ds));
	}
	
	public List<DiagnosticService> allDiagnosticServices(){
		return temp.query("SELECT * FROM diagnostic_service", new DSRowMapper());
	}
	
	public DiagnosticService findById(int dsid) {
		DiagnosticService ds=new DiagnosticService();
		ds.setDsid(dsid);
		return temp.queryForObject("SELECT * from diagnostic_service WHERE dsid=:dsid",
				getSqlParameterByModel(ds), new DSRowMapper());
	}
	
	private SqlParameterSource getSqlParameterByModel(DiagnosticService ds) {
        MapSqlParameterSource ps = new MapSqlParameterSource();
        if (ds != null) {
            ps.addValue("dsid", ds.getDsid());
            ps.addValue("addline1", ds.getAddline1());
            ps.addValue("addline2", ds.getAddline2());
            ps.addValue("centername", ds.getCentername());
            ps.addValue("city", ds.getCity());
            ps.addValue("facilities", ds.getFacilities());
            ps.addValue("email", ds.getEmail());
            ps.addValue("phone", ds.getPhone());
            ps.addValue("servicename", ds.getServicename());
            ps.addValue("state", ds.getState());
            ps.addValue("zip", ds.getZip());
            ps.addValue("website", ds.getWebsite());
            
        }
        return ps;
    }
	
	private class DSRowMapper implements RowMapper<DiagnosticService>{

		@Override
		public DiagnosticService mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			DiagnosticService ds=new DiagnosticService();
			ds.setDsid(rs.getInt("dsid"));
			ds.setAddline1(rs.getString("addline1"));
			ds.setAddline2(rs.getString("addline2"));
			ds.setCentername(rs.getString("centername"));
			ds.setCity(rs.getString("city"));
			ds.setFacilities(rs.getString("facilities"));
			ds.setEmail(rs.getString("email"));
			ds.setPhone(rs.getString("phone"));
			ds.setServicename(rs.getString("servicename"));
			ds.setState(rs.getString("state"));
			ds.setZip(rs.getString("zip"));
			ds.setWebsite(rs.getString("website"));
			return ds;
		}
		
	}
}
