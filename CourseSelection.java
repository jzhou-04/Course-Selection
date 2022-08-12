import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

public class CourseSelection 
{
    public static void main(String[] args)
    {
        Scanner input = null;
        Scanner userInput = new Scanner(System.in);
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<CourseName> courseNames = new ArrayList<CourseName>();

        try
        {
            File courses = new File("courses.txt");
            input = new Scanner(courses);
        }
        catch(FileNotFoundException exception)
        {
            System.out.println("File \"Course Selection\" not found");
        }

        while(input.hasNextLine())
        {
            if (input.hasNextInt() == false)
            {
                CourseName newCourse = new CourseName(input);
                courseNames.add(newCourse);
                names.add(newCourse.getName());
            }
        }

       ArrayList<UniqueCourse> allCourses = new ArrayList<UniqueCourse>();
       for (CourseName course : courseNames)
        {
            ArrayList<UniqueCourse> uniqueCourses = course.getCourses();
            for (UniqueCourse uniqueCourse : uniqueCourses)
            {
                allCourses.add(uniqueCourse);
            }
        }
        
        int selectionIndex = 0;
        while (true)
        {
            int index = 0;
            System.out.println();
            System.out.println();
            for (int i = 0; i < names.size(); i++)
            {
                System.out.println(names.get(i));
                for (int j = 0; j < courseNames.get(i).getSize(); j++)
                {
                    System.out.print(index + ": ");
                    System.out.println(allCourses.get(index).getData());
                    index++;
                }
            }

            index = 0;
            selectionIndex = 0;
            while (index < allCourses.size() && allCourses.get(index).selected == true)
            {
                selectionIndex++;
                index++;
            }

            if (selectionIndex >= allCourses.size())
            {
                break;
            }
            String copyString = "";
            copyString = allCourses.get(selectionIndex).id;
            StringSelection stringSelection = new StringSelection(copyString);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            System.out.println();
            System.out.println("Copied index " + selectionIndex);

            String stringInput = userInput.next();
            if (stringInput.equals("N") || stringInput.equals("n"))
            {
                allCourses.get(selectionIndex).selected = true;
                selectionIndex++;
            }
            else if (stringInput.equals("S") || stringInput.equals("s"))
            {
                removeConflictingCourses(allCourses, selectionIndex, courseNames);
            }
            else
            {
                int inputNumber = Integer.parseInt(stringInput);
                removeConflictingCourses(allCourses, inputNumber, courseNames);
            }
        }

        userInput.close();
        input.close();
    }

    private static void removeConflictingCourses(ArrayList<UniqueCourse> allCourses, int inputNumber, ArrayList<CourseName> courseNames)
    {
        ArrayList<Integer> removalndexes = new ArrayList<Integer>();
        if (inputNumber >= 0 && inputNumber < allCourses.size())
        {
            allCourses.get(inputNumber).addData(" <== Selected");
            for (CourseName courseName : courseNames)
            {
                if (courseName.uniqueCourses.contains(allCourses.get(inputNumber)))
                {
                    courseName.Selected();
                }
            }

            for (int i = 0; i < allCourses.size(); i++)
            {
                if (i == inputNumber)
                {
                    continue;
                }
                else if (allCourses.get(inputNumber).conflictsWith(allCourses.get(i)) || allCourses.get(i).conflictsWith(allCourses.get(inputNumber)))
                {
                    removalndexes.add(i);
                }
            }
        }

        int timesRemoved = 0;
        for (int removalIndex : removalndexes)
        {
            UniqueCourse removedCourse = allCourses.remove(removalIndex - timesRemoved);
            for (CourseName courseName : courseNames)
            {
                courseName.uniqueCourses.remove(removedCourse);
            }
            timesRemoved++;
        }
    }
}
