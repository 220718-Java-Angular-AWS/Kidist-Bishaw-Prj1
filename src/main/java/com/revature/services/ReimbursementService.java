package com.revature.services;

import com.revature.daos.ReimbursementDAO;
import com.revature.pojos.Reimbursement;

import java.util.List;

public class ReimbursementService {
    ReimbursementDAO dao;

    public ReimbursementService() {

        this.dao = new ReimbursementDAO();
    }

    public void saveReimbursement(Reimbursement reimbursement) {
            dao.create(reimbursement);
    }
    public List<Reimbursement> getReimbursementbyEmpId(int empId) {
        return dao.readByEmployeeId(empId);
    }

    public List<Reimbursement> getReimbursementbyStatus(String status) {
        return dao.readByStatus(status);
    }
    public List<Reimbursement> getReimbursementbyEmpIdStatus(int empId, String status) {
        return dao.readByIdStatus(empId, status);
    }

    public Reimbursement getReimbursement(int id) {
        return dao.read(id);
    }

    public List<Reimbursement> getAllReimbursements() {
        return dao.readAll();
      }

    public void updateReimbursement(Reimbursement reimbursement) {
        dao.update(reimbursement);
    }

    public void deleteReimbursement(int id) {
        dao.delete(id);
    }


}
