package libratech.user.students;

public class Student {

    private String email;
    private String IDnumber;
    private StatusTypeStudent Status;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIDnumber() {
        return IDnumber;
    }

    public StatusTypeStudent getStatus() {
        return Status;
    }

    public void setStatus(StatusTypeStudent Status) {
        this.Status = Status;
    }

    public Object[] toRowTable(EventActionStudent event) {

        return new Object[]{email, IDnumber, Status, new ModelActionStudent(this, event)};
    }

    public Student(String email, String IDnumber, StatusTypeStudent Status) {
        this.email = email;
        this.IDnumber = IDnumber;
        this.Status = Status;
    }
    public Student() {
        
    }

    public void setIDnumber(String IDnumber) {
        this.IDnumber = IDnumber;
    }

}
