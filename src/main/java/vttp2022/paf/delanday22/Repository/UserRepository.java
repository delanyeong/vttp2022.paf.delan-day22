package vttp2022.paf.delanday22.Repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;


import vttp2022.paf.delanday22.Model.User;
import static vttp2022.paf.delanday22.Repository.Queries.*;

@Repository
public class UserRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean authenticate (User user) {
        
        // Create the query
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_AUTHENTICATE_USER,
            user.getUsername(), user.getPassword());

        //check for record
        if (rs.next()) {
            return rs.getBoolean("auth_state");
            //this query simplifies the name to better extract the data type which is boolean
        }

        return false;
    }

    public Integer createUser(User user) throws Exception {
        return jdbcTemplate.update(SQL_INSERT_USER, 
            user.getUsername(), user.getPassword(), user.getEmail(), user.getPhone(), user.getDob());
    }

}
