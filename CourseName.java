import java.util.ArrayList;
import java.util.Scanner;

public class CourseName
{
    public ArrayList<UniqueCourse> uniqueCourses = new ArrayList<UniqueCourse>();
    Scanner input;
    String name;
    int size;

    public CourseName(Scanner input)
    {
        this.input = input;
        name = input.nextLine();
        addCourses();
    }

    private void addCourses()
    {
        while (input.hasNextInt())
        {
            uniqueCourses.add(new UniqueCourse(input));
        }
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<UniqueCourse> getCourses()
    {
        return uniqueCourses;
    }

    public int getSize()
    {
        return uniqueCourses.size();
    }

    public void Selected()
    {
        for (UniqueCourse uniqueCourse : uniqueCourses)
        {
            uniqueCourse.selected = true;
        }
    }
}