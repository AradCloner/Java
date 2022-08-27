package derakhshani.arad;


import static derakhshani.arad.controller.dbcontroller.CapacityController.showCapacity;
import static derakhshani.arad.controller.dbcontroller.StudentController.*;
import static derakhshani.arad.util.IOUtil.getInput;
import static derakhshani.arad.util.MenuUtil.showMenu;

public class MainApp {
    public static void main(String[] args) {
        while (true) {

            try {
                showMenu();
                int menu = Integer.parseInt(getInput());
                switch (menu) {
                    case 1:
                        registerStudent();
                        break;
                    case 2:
                        showCapacity();
                        break;
                    case 3:
                        updateStudentInformation();
                        break;
                    case 4:
                        cancelRegistration();
                        break;
                    case 5:
                        showStudentInformation();
                        break;
                    case 0:
                        return;
                    default:
                        print(" Please Select From Menu Options");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());

            }
        }
    }

    public static void print(String str) {
        System.out.println(str);
    }
}
