package org.exam.final_exam.dao;

import org.exam.final_exam.entity.VacXin;

import java.util.List;

public class VacXinDAO {
    private GenericDAO genericDAO;

    public VacXinDAO() {
        genericDAO = new GenericDAO();
    }

    public List<VacXin> getAllVacXin() {
        String sql = "SELECT * FROM vac_xin";
        return genericDAO.executeQuery(sql, rs -> VacXin.builder()
                .maVacXin(rs.getString("MaVacXin"))
                .tenVacXin(rs.getString("TenVacXin"))
                .soMui(rs.getInt("SoMui"))
                .moTa(rs.getString("MoTa"))
                .giaVacXin(rs.getDouble("GiaVacXin"))
                .tenHangSX(rs.getString("TenHangSX"))
                .build());
    }

    public VacXin getVacXinByMaVacXin(String maVacXin) {
        String sql = "SELECT * FROM vac_xin WHERE MaVacXin = ?";
        return genericDAO.find(sql, rs -> VacXin.builder()
                .maVacXin(rs.getString("MaVacXin"))
                .tenVacXin(rs.getString("TenVacXin"))
                .soMui(rs.getInt("SoMui"))
                .moTa(rs.getString("MoTa"))
                .giaVacXin(rs.getDouble("GiaVacXin"))
                .tenHangSX(rs.getString("TenHangSX"))
                .build(), maVacXin);
    }

    public int addVacXin(VacXin vacXin) {
        String sql = "INSERT INTO vac_xin(MaVacXin, TenVacXin, SoMui, MoTa, GiaVacXin, TenHangSX) VALUES(?, ?, ?, ?, ?, ?)";
        return genericDAO.executeUpdate(sql, vacXin.getMaVacXin(), vacXin.getTenVacXin(), vacXin.getSoMui(), vacXin.getMoTa(), vacXin.getGiaVacXin(), vacXin.getTenHangSX());
    }

    public int updateVacXin(VacXin vacXin) {
        String sql = "UPDATE vac_xin SET TenVacXin = ?, SoMui = ?, MoTa = ?, GiaVacXin = ?, TenHangSX = ? WHERE MaVacXin = ?";
        return genericDAO.executeUpdate(sql, vacXin.getTenVacXin(), vacXin.getSoMui(), vacXin.getMoTa(), vacXin.getGiaVacXin(), vacXin.getTenHangSX(), vacXin.getMaVacXin());
    }

    public int deleteVacXin(String maVacXin) {
        String sql = "DELETE FROM vac_xin WHERE MaVacXin = ?";
        return genericDAO.executeUpdate(sql, maVacXin);
    }

    public boolean isExistVacXin(String maVacXin) {
        String sql = "SELECT * FROM vac_xin WHERE MaVacXin = ?";
        return genericDAO.isExist(sql, maVacXin);
    }
}
