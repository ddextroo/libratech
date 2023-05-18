package libratech.user.students;

import libratech.books.inshelf.EventAction;
import libratech.books.inshelf.ModelAction;

public class Student {

    private String email;
    private String IDnumber;
    private String Status;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIDnumber() {
        return IDnumber;
    }

    public void setIDnumber(String IDnumber) {
        this.IDnumber = IDnumber;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
        public Object[] toRowTable(EventAction event) {

        return new Object[]{email, IDnumber, Status, new ModelActionStudent(this, event)};
    }
    
    public Student(String email, String IDnumber, String Status) {
        this.email = email;
        this.IDnumber = IDnumber;
        this.Status = Status;
    }
    
}
