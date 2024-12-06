package org.exam.final_exam.bo;

import org.exam.final_exam.dao.VacXinDAO;
import org.exam.final_exam.entity.VacXin;

import java.util.List;

public class VacXinBO {
    private VacXinDAO vacXinDAO;

    public VacXinBO() {
        vacXinDAO = new VacXinDAO();
    }

    public List<VacXin> getAllVacXin() {
        return vacXinDAO.getAllVacXin();
    }

    public VacXin getVacXinByMaVacXin(String maVacXin) {
        return vacXinDAO.getVacXinByMaVacXin(maVacXin);
    }

    public int addVacXin(VacXin vacXin) {
        return vacXinDAO.addVacXin(vacXin);
    }

    public int updateVacXin(VacXin vacXin) {
        return vacXinDAO.updateVacXin(vacXin);
    }

    public int deleteVacXin(String maVacXin) {
        return vacXinDAO.deleteVacXin(maVacXin);
    }
}
