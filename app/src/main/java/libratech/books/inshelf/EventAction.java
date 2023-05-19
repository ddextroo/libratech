
package libratech.books.inshelf;

import libratech.user.students.Student;

public interface EventAction {

    public void delete(Book book);

    public void update(Book book);
    
    public void delete(Student student);
    
    public void update(Student student);
}