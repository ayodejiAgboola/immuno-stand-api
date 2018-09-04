package com.immune.immunostand.model;

import java.util.ArrayList;
import java.util.List;

public class Child {
    private String childName;
    private String motherName;
    private String dob;
    private Long phoneNumber;
    private String detailsHash;
    private List<Schedule> scheduleList = new ArrayList<>();
    private boolean immunizationComplete = false;

    public void generateDetailsHash(){
        this.detailsHash = String.valueOf(phoneNumber) + childName.replace(" ", "");
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDetailsHash() {
        return detailsHash;
    }

    public void setDetailsHash(String detailsHash) {
        this.detailsHash = detailsHash;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public boolean isImmunizationComplete() {
        return immunizationComplete;
    }

    public void setImmunizationComplete(boolean immunizationComplete) {
        this.immunizationComplete = immunizationComplete;
    }

    public static class Schedule{
        private int immunizationCode;
        private int countTotal;
        private int countCompleted;

        public Schedule() {
        }

        public int getImmunizationCode() {
            return immunizationCode;
        }

        public void setImmunizationCode(int immunizationCode) {
            this.immunizationCode = immunizationCode;
        }

        public int getCountTotal() {
            return countTotal;
        }

        public void setCountTotal(int countTotal) {
            this.countTotal = countTotal;
        }

        public int getCountCompleted() {
            return countCompleted;
        }

        public void setCountCompleted(int countCompleted) {
            this.countCompleted = countCompleted;
        }
    }
}
