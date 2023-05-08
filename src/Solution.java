import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
CRUD
*/


public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    static SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);


    public static void main(String[] args) throws IOException, ParseException {
        String name;
        String sex_p;
        char sex;
        Date date_birth;
        String dateBirth;

        switch (args[0]) {
            case "-c":
                name = args[1];
                date_birth = simpleDateFormat.parse(args[3]);
                if (args[2].equals("м")) {
                    allPeople.add(Person.createMale(name, date_birth));
                } else if (args[2].equals("ж")) {
                    allPeople.add(Person.createFemale(name, date_birth));
                }

                System.out.println(allPeople.size() - 1);
                break;

            case "-r":
                int id = Integer.parseInt(args[1]);
                name = allPeople.get(id).getName();
                sex_p = String.valueOf(allPeople.get(id).getSex());

                if (Objects.equals(sex_p, "MALE")) {
                    sex = 'м';
                } else {
                    sex = 'ж';
                }
                dateBirth = simpleDateFormat2.format(allPeople.get(id).getBirthDate());

                System.out.println(name + " " + sex + " " + dateBirth);
                break;

            case "-u":
                Person person = allPeople.get(Integer.parseInt(args[1]));
                name = args[2];
                person.setName(name);
                sex_p = args[3];
                if (sex_p.equals("м")) {
                    person.setSex(Sex.MALE);
                } else if (sex_p.equals("ж")) {
                    person.setSex(Sex.FEMALE);
                }
                date_birth = simpleDateFormat.parse(args[4]);
                person.setBirthDate(date_birth);
                break;

            case "-d":
                Person currPerson = allPeople.get(Integer.parseInt(args[1]));
                currPerson.setName(null);
                currPerson.setSex(null);
                currPerson.setBirthDate(null);
                break;
        }
    }
}
