package com.revature.pojos;

import java.util.Date;
import java.util.Objects;

public class Reimbursement {

    private Integer reimbursementId;
    private Integer employeeId;
    private String reason;
    private Double amount;
    private String description;
    private String status;

    public Reimbursement() {
    }

    public Reimbursement(Integer reimbursementId, Integer employeeId, String reason, Double amount, String description, String status) {
        this.reimbursementId = reimbursementId;
        this.employeeId = employeeId;
        this.reason = reason;
        this.amount = amount;
        this.description = description;
        this.status = status;
    }

    public Reimbursement(Integer employeeId, String reason, Double amount, String description, String status) {
        this.reason = reason;
        this.employeeId = employeeId;
        this.amount = amount;
        this.description = description;
        this.status = status;
    }

    public Integer getReimbursementId() {
        return reimbursementId;
    }

    public void setReimbursementId(Integer reimbursementId) {
        this.reimbursementId = reimbursementId;
    }
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return Objects.equals(reimbursementId, that.reimbursementId) && Objects.equals(employeeId, that.employeeId) && Objects.equals(reason, that.reason) && Objects.equals(amount, that.amount) && Objects.equals(description, that.description) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimbursementId, reason, employeeId, amount, description, status);
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbursementId=" + reimbursementId +
                ", reason='" + reason + '\'' +
                ", employeeId=" + employeeId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
