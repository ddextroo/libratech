package libratech.user.students;

public class Student {

    private String email;
    private String IDnumber;
    private String UID;
    private String schoolName;
    private String status;
    private String name;
    private StatusTypeStudent Status;
    private String transactionKey;
    private int booksBorrowed;
    private long days_remaining;

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public void setTransactionKey(String transactionKey) {
        this.transactionKey = transactionKey;
    }

    public String getTransactionKey() {
        return transactionKey;
    }

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

    public Object[] toRowTableSelectUser(EventActionStudent event) {

        return new Object[]{name, IDnumber, new ModelActionStudent(this, event)};
    }

    public Object[] toRowTableSelectTransaction(EventActionStudent event) {

        return new Object[]{transactionKey, IDnumber, booksBorrowed, Status, new ModelActionStudent(this, event)};
    }

    public Object[] toRowTablePromptUsers(EventActionStudent event) {

        return new Object[]{schoolName, UID, email, days_remaining, Status, new ModelActionStudent(this, event)};
    }

    public Student(String email, String IDnumber, StatusTypeStudent Status) {
        this.email = email;
        this.IDnumber = IDnumber;
        this.Status = Status;
    }

    public Student(String name, String IDnumber) {
        this.name = name;
        this.IDnumber = IDnumber;
    }

    public Student(String transactionKey, String IDnumber, int booksBorrowed, StatusTypeStudent Status) {
        this.transactionKey = transactionKey;
        this.IDnumber = IDnumber;
        this.booksBorrowed = booksBorrowed;
        this.Status = Status;
    }

    public Student(String schoolName, String UID, String email, long days_remaining, StatusTypeStudent Status) {
        this.schoolName = schoolName;
        this.UID = UID;
        this.email = email;
        this.Status = Status;
        this.days_remaining = days_remaining;
    }

    public Student() {

    }

    public void setIDnumber(String IDnumber) {
        this.IDnumber = IDnumber;
    }

}
