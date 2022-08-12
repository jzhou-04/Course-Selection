import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class UniqueCourse
{
    String id;
    String data;
    public boolean selected = false;
    public ArrayList<Double> monday = new ArrayList<Double>();
    public ArrayList<Double> tuesday = new ArrayList<Double>();
    public ArrayList<Double> wednesday = new ArrayList<Double>();
    public ArrayList<Double> thursday = new ArrayList<Double>();
    public ArrayList<Double> friday = new ArrayList<Double>();

    public UniqueCourse(Scanner oldInput)
    {
        id = oldInput.next();
        data = oldInput.nextLine();
        try
        {
            new FileWriter("temp.txt", false).close();
            FileWriter fileWriter = new FileWriter("temp.txt");
            fileWriter.write(data);
            fileWriter.close();
        }
        catch(IOException exception)
        {
            System.out.println("There was an error");
        }

        Scanner input = null;

        try
        {
            File courses = new File("temp.txt");
            input = new Scanner(courses);
        }
        catch(FileNotFoundException exception)
        {
            System.out.println("File \"temp\" not found");
        }

        while (input.hasNext())
        {
            String dates = input.next();
            String times = input.next() + input.next() + input.next();

            String startTimeString = times.substring(0, times.indexOf("-"));
            startTimeString = startTimeString.replace("a.m.", "");
            double startTime = 0;
            if (startTimeString.contains("p.m.") && !startTimeString.contains("12"))
            {
                startTime += 12;
            }
            startTimeString = startTimeString.replace("p.m.", "");
            double startMinutes = Integer.parseInt(startTimeString.substring(startTimeString.indexOf(":") + 1));
            startMinutes /= 60.0;
            startTime = startTime + Integer.parseInt(startTimeString.substring(0, startTimeString.indexOf(":"))) + startMinutes;

            String endTimeString = times.substring(times.indexOf("-") + 1);
            endTimeString = endTimeString.replace("a.m.", "");
            double endTime = 0;
            if (endTimeString.contains("p.m.") && !endTimeString.contains("12"))
            {
                endTime += 12;
            }
            endTimeString = endTimeString.replace("p.m.", "");
            double endMinutes = Integer.parseInt(endTimeString.substring(endTimeString.indexOf(":") + 1));
            endMinutes /= 60.0;
            endTime = endTime + Integer.parseInt(endTimeString.substring(0, endTimeString.indexOf(":"))) + endMinutes;

            if (dates.contains("TH"))
            {
                dates.replace("TH", "");
                thursday.add(startTime);
                thursday.add(endTime);
            }

            if (dates.contains("M"))
            {
                monday.add(startTime);
                monday.add(endTime);
            }

            if (dates.contains("T"))
            {
                tuesday.add(startTime);
                tuesday.add(endTime);
            }

            if (dates.contains("W"))
            {
                wednesday.add(startTime);
                wednesday.add(endTime);
            }

            if (dates.contains("F"))
            {
                friday.add(startTime);
                friday.add(endTime);
            }
        }

        input.close();

        data = id + data;
    }

    public String getData()
    {
        return data;
    }

    public boolean conflictsWith(UniqueCourse otherCourse)
    {
        for (int i = 0; i < otherCourse.monday.size(); i += 2)
        {
            double otherStartTime = otherCourse.monday.get(i);
            double otherEndTime = otherCourse.monday.get(i + 1);
            for (int j = 0; j < monday.size(); j++)
            {
                if (monday.get(j) > otherStartTime && monday.get(j) < otherEndTime)
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < otherCourse.monday.size(); i += 2)
        {
            double otherStartTime = otherCourse.monday.get(i);
            double otherEndTime = otherCourse.monday.get(i + 1);
            for (int j = 0; j < monday.size(); j += 2)
            {
                if (monday.get(j) == otherStartTime && monday.get(j + 1) == otherEndTime)
                {
                    return true;
                }
                else if (monday.get(j) < otherStartTime && monday.get(j + 1) > otherEndTime)
                {
                    return true;
                }
            }
        }

        for (int i = 0; i < otherCourse.tuesday.size(); i += 2)
        {
            double otherStartTime = otherCourse.tuesday.get(i);
            double otherEndTime = otherCourse.tuesday.get(i + 1);
            for (int j = 0; j < tuesday.size(); j++)
            {
                if (tuesday.get(j) > otherStartTime && tuesday.get(j) < otherEndTime)
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < otherCourse.tuesday.size(); i += 2)
        {
            double otherStartTime = otherCourse.tuesday.get(i);
            double otherEndTime = otherCourse.tuesday.get(i + 1);
            for (int j = 0; j < tuesday.size(); j += 2)
            {
                if (tuesday.get(j) == otherStartTime && tuesday.get(j + 1) == otherEndTime)
                {
                    return true;
                }
                else if (tuesday.get(j) < otherStartTime && tuesday.get(j + 1) > otherEndTime)
                {
                    return true;
                }
            }
        }

        for (int i = 0; i < otherCourse.wednesday.size(); i += 2)
        {
            double otherStartTime = otherCourse.wednesday.get(i);
            double otherEndTime = otherCourse.wednesday.get(i + 1);
            for (int j = 0; j < wednesday.size(); j++)
            {
                if (wednesday.get(j) > otherStartTime && wednesday.get(j) < otherEndTime)
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < otherCourse.wednesday.size(); i += 2)
        {
            double otherStartTime = otherCourse.wednesday.get(i);
            double otherEndTime = otherCourse.wednesday.get(i + 1);
            for (int j = 0; j < wednesday.size(); j += 2)
            {
                if (wednesday.get(j) == otherStartTime && wednesday.get(j + 1) == otherEndTime)
                {
                    return true;
                }
                else if (wednesday.get(j) < otherStartTime && wednesday.get(j + 1) > otherEndTime)
                {
                    return true;
                }
            }
        }

        for (int i = 0; i < otherCourse.thursday.size(); i += 2)
        {
            double otherStartTime = otherCourse.thursday.get(i);
            double otherEndTime = otherCourse.thursday.get(i + 1);
            for (int j = 0; j < thursday.size(); j++)
            {
                if (thursday.get(j) > otherStartTime && thursday.get(j) < otherEndTime)
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < otherCourse.thursday.size(); i += 2)
        {
            double otherStartTime = otherCourse.thursday.get(i);
            double otherEndTime = otherCourse.thursday.get(i + 1);
            for (int j = 0; j < thursday.size(); j += 2)
            {
                if (thursday.get(j) == otherStartTime && thursday.get(j + 1) == otherEndTime)
                {
                    return true;
                }
                else if (thursday.get(j) < otherStartTime && thursday.get(j + 1) > otherEndTime)
                {
                    return true;
                }
            }
        }

        for (int i = 0; i < otherCourse.friday.size(); i += 2)
        {
            double otherStartTime = otherCourse.friday.get(i);
            double otherEndTime = otherCourse.friday.get(i + 1);
            for (int j = 0; j < friday.size(); j++)
            {
                if (friday.get(j) > otherStartTime && friday.get(j) < otherEndTime)
                {
                    return true;
                }
            }
        }
        for (int i = 0; i < otherCourse.friday.size(); i += 2)
        {
            double otherStartTime = otherCourse.friday.get(i);
            double otherEndTime = otherCourse.friday.get(i + 1);
            for (int j = 0; j < friday.size(); j += 2)
            {
                if (friday.get(j) == otherStartTime && friday.get(j + 1) == otherEndTime)
                {
                    return true;
                }
                else if (friday.get(j) < otherStartTime && friday.get(j + 1) > otherEndTime)
                {
                    return true;
                }
            }
        }

        return false;
    }

    public void addData(String str)
    {
        data += str;
    }
}