public abstract class NumberPhone implements ShowInterface {
    public String internationalСode;
    public Integer MediumCode; // для мобильных код оператора для городских код города
    public Integer PhoneBody;
    public String getInternationalСode() {
        return internationalСode;
    }
    public void setInternationalСode(String internationalСode) {
        this.internationalСode = internationalСode;
    }
    public Integer getMediumCode() {
        return MediumCode;
    }
    public void setMediumCode(Integer mediumCode) {
        MediumCode = mediumCode;
    }
    public Integer getPhoneBody() {
        return PhoneBody;
    }
    public void setPhoneBody(Integer phoneBody) {
        PhoneBody = phoneBody;
    }

}