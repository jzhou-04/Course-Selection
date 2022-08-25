import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.util.TreeMap;

public class CourseSelection
{
    public static void main(String[] args)
    {
        Scanner input = null;
        Scanner userInput = new Scanner(System.in);
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<CourseName> courseNames = new ArrayList<CourseName>();
        TreeMap<Double, Double> reservedTimes = new TreeMap<Double, Double>();

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

            selectionIndex = 0;
            while (selectionIndex < allCourses.size() && allCourses.get(selectionIndex).passed == true)
            {
                selectionIndex++;
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
                allCourses.get(selectionIndex).passed = true;
                selectionIndex++;
            }
            else if (stringInput.equals("S") || stringInput.equals("s"))
            {
                removeConflictingCourses(allCourses, selectionIndex, courseNames, reservedTimes);
            }
            else
            {
                int inputNumber = Integer.parseInt(stringInput);
                removeConflictingCourses(allCourses, inputNumber, courseNames, reservedTimes);
            }
        }

        userInput.close();
        input.close();
    }

    private static void removeConflictingCourses(ArrayList<UniqueCourse> allCourses, int inputNumber, 
                                                    ArrayList<CourseName> courseNames, TreeMap<Double, Double> reservedTimes)
    {
        ArrayList<Integer> removalndexes = new ArrayList<Integer>();
        if (inputNumber >= 0 && inputNumber < allCourses.size())
        {
            UniqueCourse selectedCourse = allCourses.get(inputNumber);
            selectedCourse.addData(" <== Selected");
            selectedCourse.selected = true;
            addReservedTimes(reservedTimes, selectedCourse.classTimes);

            for (CourseName courseName : courseNames)
            {
                if (courseName.uniqueCourses.contains(selectedCourse))
                {
                    courseName.beenSelected();
                }
            }

            for (int i = 0; i < allCourses.size(); i++)
            {
                if (i == inputNumber)
                {
                    continue;
                }
                else if (!allCourses.get(i).selected && allCourses.get(i).conflictsWithReservedTimes(reservedTimes))
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

    private static void addReservedTimes(TreeMap<Double, Double> reservedTimes, ArrayList<Double> times)
    {
        for (int i = 0; i < times.size(); i += 2)
        {
            reservedTimes.put(times.get(i), times.get(i + 1));
        }
    }
}
