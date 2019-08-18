package individualproject;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrivateSchool {

    public static void main(String[] args) {
        System.out.println("Welcome to private school application");
        getMenu();
    }

    public static void getMenu() {
        Scanner input = new Scanner(System.in);
        for (;;) {
            System.out.println("\t~~~~~~~MENU~~~~~~~");
            System.out.println("What do you want to do?(press number)");
            System.out.println("1. Insert new Data or load Synthetic ones");
            System.out.println("2. See Stored Information");
            System.out.println("3. See Students according to submission dates");
            System.out.println("(Press 0 to exit the application)");
            int myChoice = checkInteger(input);
            switch (myChoice) {

                case 1:
                    System.out.println("Would you like to insert data?(Y/N)(Synthetic data will be used othrwise)");
                    String answer;
                    do {
                        answer = input.next();
                        if (answer.toLowerCase().charAt(0) != 'n' && answer.toLowerCase().charAt(0) != 'y') {
                            System.out.println("Incorrect answer. Please insert (Y)es or (N)o");
                        }
                    } while (answer.toLowerCase().charAt(0) != 'n' && answer.toLowerCase().charAt(0) != 'y');

                    if (answer.toLowerCase().charAt(0) == 'y') {
                        inputData(input);
                    } else {
                        loadObjects();
                    }
                    break;
                case 2:
                    printInformation(input);
                    break;
                case 3:
                    System.out.println("Insert date in format dd/MM/yyyy");
                    LocalDate givenDate = checkDate(input);
                    LocalDate firstDayOfWeek = givenDate; //έστω ότι είναι η πρώτη μέρα
                    while (firstDayOfWeek.getDayOfWeek() != DayOfWeek.MONDAY) {
                        firstDayOfWeek = firstDayOfWeek.minusDays(1);
                    }

                    LocalDate lastDayOfWeek = givenDate;
                    switch (lastDayOfWeek.getDayOfWeek()) {
                        case SATURDAY:
                            lastDayOfWeek = lastDayOfWeek.minusDays(1);
                            break;
                        case SUNDAY:
                            lastDayOfWeek = lastDayOfWeek.minusDays(2);
                            System.out.println(lastDayOfWeek);
                            break;
                        default:
                            while (lastDayOfWeek.getDayOfWeek() != DayOfWeek.FRIDAY) {
                                lastDayOfWeek = lastDayOfWeek.plusDays(1);
                            }
                            break;

                    }
                    System.out.println("First " + firstDayOfWeek);
                    System.out.println("Last " + lastDayOfWeek);

                    while (!firstDayOfWeek.equals(lastDayOfWeek.plusDays(1))) {
                        for (Assignment as : Assignment.assignments) {
                            for (Student s : Student.students) {
                                for (Course c : s.getCourses()) {
                                    if (c.equals(as.getCourse()) && (as.getSubDateTime().equals(firstDayOfWeek))) {

                                        System.out.println(s.getFirstName() + " " + s.getLastName()
                                                + "'s submission date for " + as.getTitle() + " at " + as.getCourse().getTitle() + " is " + as.getSubDateTime());
                                    }
                                }
                            }
                        }
                        firstDayOfWeek = firstDayOfWeek.plusDays(1);
                    }

                    break;
                case 0:
                    input.close();
                    return;
            }
        }

    }

    public static void inputData(Scanner sc) {

        System.out.println("Enter how many courses you want to add(If you are visiting the application for the first time, enter at least one)");
        int n = checkInteger(sc);
        while (n == 0 && Course.courses.size() == 0) {

            System.out.println("At least one course should be added. Please try again.");
            n = checkInteger(sc);
        }
        for (int j = 1; j <= n; j++) {
            sc.nextLine();
            System.out.println("Enter title: ");
            String title = sc.nextLine();
            System.out.println("Enter stream: ");
            String stream = sc.nextLine();
            System.out.println("Enter type: ");
            String type = sc.nextLine();
            System.out.println("Start date is(dd/MM/yyyy): ");
            LocalDate startDate = checkDate(sc);
            LocalDate endDate;
            for (;;) {

                System.out.println("Enter end date(dd/MM/yyyy): ");
                endDate = checkDate(sc);

                if (endDate.isBefore(startDate)) {
                    System.out.println("End date is before start date. Try again.");
                } else {
                    break;
                }
            }
            new Course(title, stream, type, startDate, endDate);
            System.out.println("Course registered successfully");
        }

        System.out.println("Enter how many students you want to make.");
        n = checkInteger(sc);

        for (int j = 1; j <= n; j++) {
            sc.nextLine();

            System.out.println("Enter first name: ");
            String firstName = sc.next();
            System.out.println("Enter last name: ");
            String lastName = sc.next();
            System.out.println("Enter date of birth(dd/MM/yyyy): ");
            LocalDate dateOfBirth = checkDate(sc);

            System.out.println("Enter tuition fees: ");
            double tuitionFees = checkDouble(sc);
            System.out.println("Available courses are: ");
            Course.printList();
            System.out.println("Enter the id of the courses - 0 to stop entering (you can enter multiple courses)");
            System.out.println("You will have to enter at least one course");
            boolean flag = true;
            //διόρθωσε συνθήκη!!!!
            List<Course> courses = new ArrayList();
            boolean atLeastACourse = false;
            do {
                boolean flag2 = false;
                int cChoice = checkInteger(sc);
                for (Course c1 : Course.courses) {
                    if (c1.getId() == cChoice) {
                        courses.add(c1);
                        flag2 = true;
                        atLeastACourse = true;
                        break;
                    } else if (cChoice == 0) {
                        if (atLeastACourse == true) {
                            flag = false;
                        } else {
                            System.out.println("You cannot exit without entering a course");
                        }
                        flag2 = true;
                        break;
                    }
                }
                if (flag2) {
                    System.out.println("Answer accepted");
                    continue;
                }
                System.out.println("Wrong input. Try again.");
                System.out.println("Available courses are: ");
                Course.printList();
            } while (flag);
            new Student(firstName, lastName, dateOfBirth, tuitionFees, courses);

            System.out.println("Student registered successfully");
            for (Course co : Course.courses) {
                new StudentsPerCourse(co);
            }

        }

        System.out.println("Enter how many trainers you want to add");
        n = checkInteger(sc);
        for (int j = 1; j <= n; j++) {
            sc.nextLine();
            System.out.println("Enter first name: ");
            String firstName = sc.next();
            System.out.println("Enter last name: ");
            String lastName = sc.next();
            System.out.println("Enter subject: ");
            String subject = sc.next();

            System.out.println("Available courses are: ");
            Course.printList();
            System.out.println("Enter the id of the course");
            boolean stop = false;
            Course course = Course.courses.get(0);
            do {
                int cChoice = checkInteger(sc);
                for (Course c1 : Course.courses) {
                    if (c1.getId() == cChoice) {

                        course = c1;
                        stop = true;
                        break;
                    }
                }
                System.out.println("Wrong input. Try again.");
                System.out.println("Available courses are: ");
                Course.printList();
            } while (!stop);

            new Trainer(firstName, lastName, subject, course);
            System.out.println("Trainer registered successfully");

        }
        for (Course co : Course.courses) {
            new TrainersPerCourse(co);
        }

        System.out.println("Enter how many assignments you want to add");
        n = checkInteger(sc);
        for (int j = 1; j <= n; j++) {
            sc.nextLine();

            System.out.println("Enter title: ");
            String title = sc.nextLine();
            System.out.println("Enter description: ");
            String description = sc.nextLine();
            System.out.println("Enter submission date(dd/MM/yyyy): ");
            LocalDate subDateTime = checkDate(sc);

            System.out.println("Enter oral mark: ");
            double oralMark = checkDouble(sc);
            System.out.println("Enter total mark: ");
            double totalMark = checkDouble(sc);

            System.out.println("Available courses are: ");
            Course.printList();
            System.out.println("Enter the id of the course");
            Course course = Course.courses.get(0);
            boolean courseChecked = false;
            do {
                int cChoice = checkInteger(sc);
                for (Course c1 : Course.courses) {
                    if (c1.getId() == cChoice) {
                        course = c1;
                        courseChecked = true;
                        break;
                    }
                }
                System.out.println("Wrong input. Try again.");
                System.out.println("Available courses are: ");
                Course.printList();
            } while (!courseChecked);
            new Assignment(title, description, subDateTime, oralMark, totalMark, course);
            System.out.println("Assignment registered successfully");
        }
        for (Course co : Course.courses) {
            new AssignmentsPerCourse(co);
        }

        getMenu();
    }

    public static void loadObjects() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date1 = LocalDate.parse("13/05/2019", formatter);
        LocalDate date2 = LocalDate.parse("01/03/2020", formatter);
        Course java = new Course("Java", "CB8", "full time", date1, date2);

        date1 = LocalDate.parse("10/08/2018", formatter);
        date2 = LocalDate.parse("12/01/2020", formatter);
        Course c = new Course("C#", "CB7", "part time", date1, date2);

        date1 = LocalDate.parse("12/04/2019", formatter);
        date2 = LocalDate.parse("28/02/2020", formatter);
        Course py = new Course("Python", "CB6", "part time", date1, date2);

        List<Course> courses = new ArrayList();
        courses.add(py);
        courses.add(java);
        date1 = LocalDate.parse("13/05/2000", formatter);
        new Student("Maria", "Papadopoulou", date1, 2500, courses);

        courses.clear();

        courses.add(java);
        date1 = LocalDate.parse("12/01/1970", formatter);
        new Student("Giorgos", "Kyriakou", date1, 1990, courses);

        courses.clear();
        courses.add(c);
        courses.add(py);
        courses.add(java);
        date1 = LocalDate.parse("28/08/1985", formatter);
        new Student("Marios", "Georgiou", date1, 3000, courses);

        courses.clear();
        courses.add(py);
        courses.add(java);
        date1 = LocalDate.parse("17/12/1990", formatter);
        new Student("Chris", "Sideris", date1, 2000, courses);

        courses.clear();
        courses.add(c);
        date1 = LocalDate.parse("25/11/1998", formatter);
        new Student("Chrysi", "Apostolou", date1, 1700, courses);

        courses.clear();
        courses.add(c);
        courses.add(java);
        date1 = LocalDate.parse("12/12/2001", formatter);
        new Student("Foivos", "Markou", date1, 2000, courses);

        courses.clear();
        courses.add(py);
        date1 = LocalDate.parse("18/05/1965", formatter);
        new Student("Margarita", "Koronaiou", date1, 500, courses);

        courses.clear();
        courses.add(c);
        date1 = LocalDate.parse("04/02/1981", formatter);
        new Student("Stavros", "Sotiriou", date1, 900, courses);

        courses.clear();
        courses.add(c);
        courses.add(java);
        courses.add(py);
        date1 = LocalDate.parse("12/06/1992", formatter);
        new Student("Charalampos", "Papacharalampous", date1, 3000, courses);

        courses.clear();
        courses.add(c);
        courses.add(py);
        date1 = LocalDate.parse("17/03/1978", formatter);
        new Student("Chrysi", "Apostolou", date1, 1500, courses);

        new Trainer("Eleni", "Stroumpouli", "Data Bases", java);
        new Trainer("Dimitris", "Efthimiou", "Introduction", py);
        new Trainer("Fotis", "Fotiou", "Web Design", c);
        new Trainer("Niki", "Spyraki", "Back end", java);

        date1 = LocalDate.parse("23/12/2019", formatter);
        new Assignment("Private School", "a private school application", date1, 30, 100, py);

        date1 = LocalDate.parse("13/07/2019", formatter);
        new Assignment("Private School", "a private school application", date1, 30, 120, java);

        date1 = LocalDate.parse("12/11/2019", formatter);
        new Assignment("Food", "an online application for delivering food online", date1, 10, 75, c);

        date1 = LocalDate.parse("22/12/2019", formatter);
        new Assignment("eshop", "an online shop", date1, 40, 150, py);

        date1 = LocalDate.parse("27/12/2019", formatter);
        new Assignment("Footbal Game", "an application for calculating points", date1, 20, 100, java);

        for (Course co : Course.courses) {
            new StudentsPerCourse(co);
        }

        for (Course co : Course.courses) {
            new TrainersPerCourse(co);
        }

        for (Course co : Course.courses) {
            new AssignmentsPerCourse(co);
        }
        return;
    }

    public static int checkInteger(Scanner sc) {
        int x;
        do {
            while (!sc.hasNextInt()) {
                System.out.println("You should enter an integer. Try again.");
                sc.next();
            }

            x = sc.nextInt();
            if (x < 0) {
                System.out.println("You should enter a positive number. Try again.");
            }
        } while (x < 0);
        return x;
    }

    public static double checkDouble(Scanner sc) {
        double x;
        do {
            while (!sc.hasNextDouble()) {
                System.out.println("You should enter a number. Try again");
                sc.next();
            }
            x = sc.nextDouble();
            if (x < 0) {
                System.out.println("You should enter a positive number. Try again.");
            }
        } while (x < 0);
        return x;
    }

    public static LocalDate checkDate(Scanner sc) {
        LocalDate date;
        for (;;) {
            try {
                String dateAsString = sc.next();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                date = LocalDate.parse(dateAsString, formatter);
                return date;
            } catch (DateTimeParseException dateTimeParseException) {
                System.out.println("Date does not exist. Enter date in form dd/MM/yyyy");
            }
        }
    }

    public static void printInformation(Scanner sc) {

        System.out.println("Select the kind of information you want to see");
        System.out.println("1. Students");
        System.out.println("2. Trainers");
        System.out.println("3. Assignments");
        System.out.println("4. Courses");
        System.out.println("5. Students per Course");
        System.out.println("6. Trainers per Course");
        System.out.println("7. Assignments per Course");
        System.out.println("8. Assignments per Student");
        System.out.println("9. Students belonging to more than one Courses");

        int choice;
        choice = checkInteger(sc);
        switch (choice) {

            case 1:
                Student.printList();
                break;
            case 2:
                Trainer.printList();
                break;
            case 3:
                Assignment.printList();
                break;
            case 4:
                Course.printList();
                break;
            case 5:
                StudentsPerCourse.printList();
                break;
            case 6:
                TrainersPerCourse.printList();
                break;
            case 7:
                AssignmentsPerCourse.printList();
                break;
            case 8:
                StudentsPerCourse.printAssignments();
                break;
            case 9:
                Student.printWithMoreCourses();
                break;
        }
    }

}
