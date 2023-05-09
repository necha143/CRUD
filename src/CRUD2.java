import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/*
CRUD 2
*/

public class CRUD2 {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createFemale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    private static SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    private static SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

    public static void main(String[] args) throws ParseException {
        Person person;
        String name;
        String sex_p;
        char sex;
        Date birth_Date;
        int id;
        int a_length;

        switch (args[0]) {
            case "-c":
                synchronized (allPeople) {
                    a_length = args.length;
                    for (int i = 1; i < a_length; i += 3) {
                        name = args[i];
                        birth_Date = inputFormat.parse(args[i + 2]);
                        sex_p = args[i + 1];
                        if (sex_p.equals("м")) {
                            person = Person.createMale(name, birth_Date);
                        } else {
                            person = Person.createFemale(name, birth_Date);
                        }
                        allPeople.add(person);
                        System.out.println(allPeople.indexOf(person));
                    }
                }
                break;
            case "-u":
                synchronized (allPeople) {
                    a_length = args.length;
                    for (int i = 1; i < a_length; i += 4) {
                        id = Integer.parseInt(args[i]);
                        name = args[i + 1];
                        birth_Date = inputFormat.parse(args[i + 3]);
                        sex_p = args[i + 2];
                        allPeople.get(id).setName(name);
                        if (sex_p.equals("м")) {
                            allPeople.get(id).setSex(Sex.MALE);
                        } else {
                            allPeople.get(id).setSex(Sex.FEMALE);
                        }
                        allPeople.get(id).setBirthDate(birth_Date);
                    }
                }
                break;
            case "-d":
                synchronized (allPeople) {
                    a_length = args.length;
                    for (int i = 1; i < a_length; i++) {
                        id = Integer.parseInt(args[i]);
                        allPeople.get(id).setName(null);
                        allPeople.get(id).setSex(null);
                        allPeople.get(id).setBirthDate(null);
                    }
                }
                break;
            case "-i":
                synchronized (allPeople) {
                    a_length = args.length;
                    for (int i = 1; i < a_length; i++) {
                        id = Integer.parseInt(args[i]);
                        name = allPeople.get(id).getName();
                        if (allPeople.get(id).getSex() == Sex.MALE) {
                            sex_p = "м";
                        } else {
                            sex_p = "ж";
                        }
                        String birthDate = outputFormat.format(allPeople.get(id).getBirthDate());
                        System.out.println(name + " " + sex_p + " " + birthDate);
                    }
                }
                break;
        }
    }
}
