import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Reads a csv file and maps country name to a list of people who are from that country.
 * Then, for each list sorts by last name.
 */
public class People {

    public static void main(String[] args) throws Exception {
        HashMap<String, ArrayList<Person>> peopleByCountryMap = new HashMap<>();
        readAndSortFile(peopleByCountryMap);
        printAndSavePeople(peopleByCountryMap);
    }

    private static void readAndSortFile( HashMap<String, ArrayList<Person>> peopleByCountryMap) throws IOException{
        File f = new File("people.csv");
        Scanner fileScanner = new Scanner(f);
        String line = fileScanner.nextLine();
        while (fileScanner.hasNext()) {
            ArrayList<Person> peopleByCountryList;
            line = fileScanner.nextLine();
            String[] columns = line.split("\\,");
            Person person = new Person(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5]);
            String country = person.getCountry();
            if (peopleByCountryMap.containsKey(country)) {
                peopleByCountryList = peopleByCountryMap.get(country);
                peopleByCountryList.add(person);
                peopleByCountryMap.put(country, peopleByCountryList);
            } else {
                peopleByCountryList = new ArrayList<>();
                peopleByCountryList.add(person);
                peopleByCountryMap.put(country, peopleByCountryList);
            }
        }
    }


    private static void printAndSavePeople(HashMap<String, ArrayList<Person>> peopleByCountryMap) throws IOException {
        File file = new File("people.json");
        FileWriter fw = new FileWriter(file);
        ArrayList<ArrayList<Person>> peopleReadyToSave = new ArrayList<>();

        for (ArrayList<Person> people : peopleByCountryMap.values()) {
            Collections.sort(people); //sorts by lastname
            Collections.sort(people); // goes back through for those with same last name sorts first name
            System.out.println(people.toString());
            peopleReadyToSave.add(people);

        }
        JsonSerializer s = new JsonSerializer();
        String json = s.serialize(peopleReadyToSave);
        fw.write(json);
        fw.close();
    }
}
