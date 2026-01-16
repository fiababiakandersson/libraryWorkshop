package se.yrgo.libraryapp.dao;

import java.sql.*;
import java.util.*;

import javax.inject.*;
import javax.sql.*;

import org.slf4j.*;

import se.yrgo.libraryapp.entities.*;

public class RoleDao {
    private static Logger logger = LoggerFactory.getLogger(RoleDao.class);
    private DataSource ds;

    @Inject
    RoleDao(DataSource ds) {
        this.ds = ds;
    }

    public List<Role> get(UserId userId) {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT r.role FROM user_role AS ur JOIN role AS r ON ur.role_id = r.id WHERE ur.user_id = ?";
        try (Connection conn = ds.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userId.toString());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    roles.add(Role.fromString(rs.getString("r.role")));
                }
            }

            return roles;
        } catch (SQLException ex) {
            logger.error("Unable to get user id", ex);
            return List.of();
        }
    }
}
