
package libratech.user.students;


public class ModelActionStudent {

    public Student getStudent() {
        return students;
    }

    public void setStudent(Student student) {
        this.students = student;
    }

    public EventActionStudent getEvent() {
        return event;
    }

    public void setEvent(EventActionStudent event) {
        this.event = event;
    }

    public ModelActionStudent(Student students, EventActionStudent event) {
        this.students = students;
        this.event = event;
    }

    public ModelActionStudent() {
    }

    private Student students;
    private EventActionStudent event;
}
