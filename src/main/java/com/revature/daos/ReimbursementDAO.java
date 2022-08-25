package com.revature.daos;

import com.revature.pojos.Reimbursement;
import com.revature.services.DataAccessService;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ReimbursementDAO implements DataSourceCRUD<Reimbursement> {

    Connection connection;

    public ReimbursementDAO() {
        this.connection = DataAccessService.getConnection();
    }

    @Override
    public void create(Reimbursement reimbursement) {

        try {
            String sql = "INSERT INTO reimbursements ( emp_id, reason, amount, description, reimbursement_status) VALUES(?, ?, ?, ?, 'pending')";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, reimbursement.getEmployeeId());
            statement.setString(2, reimbursement.getReason());
            statement.setDouble(3, reimbursement.getAmount());
            statement.setString(4, reimbursement.getDescription());
            //statement.setString(5, reimbursement.getStatus());

            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Reimbursement read(int id) {

        Reimbursement reimbursement = new Reimbursement();
        try {
            String sql = "SELECT * FROM reimbursements WHERE reimbursement_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                reimbursement.setReimbursementId(resultSet.getInt("reimbursement_id"));
                reimbursement.setEmployeeId(resultSet.getInt("emp_id"));
                reimbursement.setReason(resultSet.getString("reason"));
                reimbursement.setAmount(resultSet.getDouble("amount"));
                reimbursement.setDescription(resultSet.getString("description"));
                reimbursement.setStatus(resultSet.getString("reimbursement_status"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return reimbursement;
    }

    @Override
    public List<Reimbursement> readAll() {

       List<Reimbursement> reimbursementList = new LinkedList<>();

        try {
            String sql = "SELECT * FROM reimbursements";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Reimbursement reimbursement = new Reimbursement();
                reimbursement.setReimbursementId(resultSet.getInt("reimbursement_id"));
                reimbursement.setEmployeeId(resultSet.getInt("emp_id"));
                reimbursement.setReason(resultSet.getString("reason"));
                reimbursement.setAmount(resultSet.getDouble("amount"));
                reimbursement.setDescription(resultSet.getString("description"));
                reimbursement.setStatus(resultSet.getString("reimbursement_status"));
                reimbursementList.add(reimbursement);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return reimbursementList;
    }

    @Override
    public void update(Reimbursement reimbursement) {
        try {
            String sql = "UPDATE reimbursements SET emp_id = ?, reason = ?, amount = ?, description = ?, reimbursement_status = ? WHERE reimbursement_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, reimbursement.getEmployeeId());
            statement.setString(2, reimbursement.getReason());
            statement.setDouble(3, reimbursement.getAmount());
            statement.setString(4, reimbursement.getDescription());
            statement.setString(5, reimbursement.getStatus());
            statement.setInt(6, reimbursement.getReimbursementId());
            statement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM reimbursements WHERE reimbursement_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Reimbursement> readByEmployeeId(int id) {
        List<Reimbursement> reimbursementList = new LinkedList<>();

        try {
            String sql = "SELECT * FROM reimbursements WHERE emp_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Reimbursement reimbursement = new Reimbursement();
                reimbursement.setReimbursementId(resultSet.getInt("reimbursement_id"));
                reimbursement.setEmployeeId(resultSet.getInt("emp_id"));
                reimbursement.setReason(resultSet.getString("reason"));
                reimbursement.setAmount(resultSet.getDouble("amount"));
                reimbursement.setDescription(resultSet.getString("description"));
                reimbursement.setStatus(resultSet.getString("reimbursement_status"));
                reimbursementList.add(reimbursement);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return reimbursementList;
    }

    public List<Reimbursement> readByStatus(String status) {
        List<Reimbursement> reimbursementList = new LinkedList<>();

        try {
            String sql = "SELECT * FROM reimbursements WHERE reimbursement_status = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,status);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Reimbursement reimbursement = new Reimbursement();
                reimbursement.setReimbursementId(resultSet.getInt("reimbursement_id"));
                reimbursement.setEmployeeId(resultSet.getInt("emp_id"));
                reimbursement.setReason(resultSet.getString("reason"));
                reimbursement.setAmount(resultSet.getDouble("amount"));
                reimbursement.setDescription(resultSet.getString("description"));
                reimbursement.setStatus(resultSet.getString("reimbursement_status"));
                reimbursementList.add(reimbursement);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return reimbursementList;
    }

    public List<Reimbursement> readByIdStatus(int id, String status) {
        List<Reimbursement> reimbursementList = new LinkedList<>();

        try {
            String sql = "SELECT * FROM reimbursements WHERE emp_id = ? and reimbursement_status = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            statement.setString(2,status);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Reimbursement reimbursement = new Reimbursement();
                reimbursement.setReimbursementId(resultSet.getInt("reimbursement_id"));
                reimbursement.setEmployeeId(resultSet.getInt("emp_id"));
                reimbursement.setReason(resultSet.getString("reason"));
                reimbursement.setAmount(resultSet.getDouble("amount"));
                reimbursement.setDescription(resultSet.getString("description"));
                reimbursement.setStatus(resultSet.getString("reimbursement_status"));
                reimbursementList.add(reimbursement);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return reimbursementList;
    }


}
