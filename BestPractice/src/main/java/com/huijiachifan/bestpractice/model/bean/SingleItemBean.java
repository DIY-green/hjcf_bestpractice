package com.huijiachifan.bestpractice.model.bean;

public class SingleItemBean {

    private String title;
    private String desc;
    private String time;
    private String phone;
    private boolean isChecked;

    public SingleItemBean() {
    }

    public SingleItemBean(String title, String desc, String time, String phone) {
        super();
        this.title = title;
        this.desc = desc;
        this.time = time;
        this.phone = phone;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "SingleItemBean{" +
                "desc='" + desc + '\'' +
                ", title='" + title + '\'' +
                ", time='" + time + '\'' +
                ", phone='" + phone + '\'' +
                ", isChecked=" + isChecked +
                '}';
    }
}
