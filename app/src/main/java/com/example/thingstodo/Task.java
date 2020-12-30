package com.example.thingstodo;

public class Task {
    private String taskName;
    private Boolean isChecked;

    public Task() {
    }

    public Task(String taskName, Boolean isChecked) {
        this.taskName = taskName;
        this.isChecked = isChecked;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Boolean checked) {
        isChecked = checked;
    }


}