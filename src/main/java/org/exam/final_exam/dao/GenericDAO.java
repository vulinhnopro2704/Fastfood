package org.exam.final_exam.dao;

import org.exam.final_exam.config.DBConnectionPool;
import org.exam.final_exam.config.ResultSetExtractor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenericDAO {
    public <T> List<T> executeQuery(String query, ResultSetExtractor<T> extractor, Object... params) {
        List<T> results = new ArrayList<>();
        try (Connection conn = DBConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            setParameters(stmt, params);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    results.add(extractor.extractData(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public int executeUpdate(String query, Object... params) {
        try (Connection conn = DBConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            setParameters(stmt, params);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public <T> T find(String query, ResultSetExtractor<T> extractor, Object... params) {
        T result = null;
        try (Connection conn = DBConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            setParameters(stmt, params);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    result = extractor.extractData(rs);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean isExist(String query, Object... params) {
        boolean exists = false;
        try (Connection conn = DBConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            setParameters(stmt, params);
            try (ResultSet rs = stmt.executeQuery()) {
                exists = rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    private void setParameters(PreparedStatement stmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
    }
}