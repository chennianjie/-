package pattern_mooc.behavioral.iterator;

/**
 * Created by geely.
 */
public interface CourseAggregate {

    void addCourse(Course course);
    void removeCourse(Course course);

    CourseIterator getCourseIterator();



}
