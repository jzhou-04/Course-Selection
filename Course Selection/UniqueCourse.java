import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.TreeMap;
import java.util.HashMap;

public class UniqueCourse
{
    String id;
    String data;
    int index;
    public boolean passed = false;
    public boolean selected = false;
    public ArrayList<Double> classTimes;
    public HashMap<String, Double> datePrefixes;

    public UniqueCourse(Scanner oldInput)
    {
        id = oldInput.next();
        data = oldInput.nextLine();
        classTimes = new ArrayList<Double>();
        
        initializeMap();
        Scanner input = getScanner();
        addData(input);

        input.close();

        data = id + data;
    }

    private void initializeMap()
    {
        datePrefixes = new HashMap<String, Double>();
        datePrefixes.put("M", 0.0);
        datePrefixes.put("T", 100.0);
        datePrefixes.put("W", 200.0);
        datePrefixes.put("TH", 300.0);
        datePrefixes.put("F", 400.0);
    }

    // Gets a Scanner object linked to Courses.txt
    private Scanner getScanner()
    {
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

        try
        {
            File courses = new File("temp.txt");
            return new Scanner(courses);
        }
        catch(FileNotFoundException exception)
        {
            System.out.println("File \"temp\" not found");
        }

        return null; 
    }

    // Stores all data from Courses.txt into instance variables
    private void addData(Scanner input)
    {
        while (input.hasNext())
        {
            String dates = input.next();
            String times = input.next() + input.next() + input.next();

            double startTime = processStartTime(times);
            double endTime = processEndTime(times);

            if (dates.contains("TH")) 
            {
                dates.replace("TH", ""); 

                double prefix = datePrefixes.get("TH");
                classTimes.add(startTime + prefix);
                classTimes.add(endTime + prefix);
            }

            if (dates.contains("M"))
            {
                double prefix = datePrefixes.get("M");
                classTimes.add(startTime + prefix);
                classTimes.add(endTime + prefix);
            }

            if (dates.contains("T"))
            {
                double prefix = datePrefixes.get("T");
                classTimes.add(startTime + prefix);
                classTimes.add(endTime + prefix);
            }

            if (dates.contains("W"))
            {
                double prefix = datePrefixes.get("W");
                classTimes.add(startTime + prefix);
                classTimes.add(endTime + prefix);
            }

            if (dates.contains("F"))
            {
                double prefix = datePrefixes.get("F");
                classTimes.add(startTime + prefix);
                classTimes.add(endTime + prefix);
            }
        }
    }

    // Returns a double representing the start time of the course in military time
    private double processStartTime(String times)
    {
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
        return startTime + Integer.parseInt(startTimeString.substring(0, startTimeString.indexOf(":"))) + startMinutes;
    }

    // Returns a double representing the end time of the course in military time
    private double processEndTime(String times)
    {
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
            return endTime + Integer.parseInt(endTimeString.substring(0, endTimeString.indexOf(":"))) + endMinutes;
    }

    public String getData()
    {
        return data;
    }

    public boolean conflictsWithReservedTimes(TreeMap<Double, Double> reservedTimes)
    {
        for (int i = 0; i < classTimes.size(); i += 2)
        {
            double startTime = classTimes.get(i);
            Double previousStartTime = reservedTimes.floorKey(startTime);
            Double nextStartTime = reservedTimes.ceilingKey(startTime);

            if ((previousStartTime != null && startTime < reservedTimes.get(previousStartTime))
                || (nextStartTime != null && classTimes.get(i + 1) > nextStartTime))
            {
                return true;
            }
        }

        return false;
    }

    public void addData(String str)
    {
        data += str;
    }
}