/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libratech.user.students;

import libratech.books.inshelf.EventAction;

public class ModelActionStudent {

    public Student getStudent() {
        return students;
    }

    public void setStudent(Student student) {
        this.students = student;
    }

    public EventAction getEvent() {
        return event;
    }

    public void setEvent(EventAction event) {
        this.event = event;
    }

    public ModelActionStudent(Student students, EventAction event) {
        this.students = students;
        this.event = event;
    }

    public ModelActionStudent() {
    }

    private Student students;
    private EventAction event;
}

