package model;

import java.util.Objects;

public class DB {

    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private int companyId;

    private String createTimestamp;

    private String changeTimestamp;
    private String avatar_url;
    private String phone;
    private String birthdate;
    private boolean active;

    public DB(int id, boolean active, String createTimestamp, String changeTimestamp, String firstName, String lastName,
              String middleName, String phone, String birthdate, String avatar_url, int companyId) {
        this.id = id;
        this.active = active;
        this.createTimestamp = createTimestamp;
        this.changeTimestamp = changeTimestamp;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.phone = phone;
        this.birthdate = birthdate;
        this.avatar_url = avatar_url;
        this.companyId = companyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DB db = (DB) o;
        return id == db.id && companyId == db.companyId && active == db.active && Objects.equals(firstName, db.firstName) && Objects.equals(lastName, db.lastName) && Objects.equals(middleName, db.middleName) && Objects.equals(createTimestamp, db.createTimestamp) && Objects.equals(changeTimestamp, db.changeTimestamp) && Objects.equals(avatar_url, db.avatar_url) && Objects.equals(phone, db.phone) && Objects.equals(birthdate, db.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, active, createTimestamp, changeTimestamp, firstName, lastName, middleName, phone, birthdate, avatar_url, companyId);
    }

    @Override
    public String toString() {
        return "DB{" +
                "id=" + id +
                ", active=" + active +
                ", createTimestamp='" + createTimestamp + '\'' +
                ", changeTimestamp='" + changeTimestamp + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", phone='" + phone + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", avatarUrl='" + avatar_url + '\'' +
                ", companyId=" + companyId +
                '}';
    }
}
