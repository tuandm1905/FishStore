package fishmanagement;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Dang Minh Tuan SE150430
 */
public class Main {

    private static FishManagement Fish;

    /**
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Fish = new FishManagement("Fish.txt"); // Loads fish store bank
            Fish.loadFish();

            Scanner cin = new Scanner(System.in); //  Creates new  a scanner
            int func;  // The function that selected by user
            do {
                System.out.println("\n--------- WELCOME TO OUR FISH STORE ----------");
                System.out.println("1. Add new fish.");
                System.out.println("2. Search fish by family fish.");
                System.out.println("3. Search fish from minPrice to maxPrice.");
                System.out.println("4. Update selling price.");
                System.out.println("5. Display list of discount fish.");
                System.out.println("6. Quit.");
                System.out.print("\tPlease select a function: ");
                func = cin.nextInt();
                cin.nextLine();
                switch (func) {
                    case 1:
                        System.out.println("---Fish Store [Add Fish]---");
                        String familyName = "";
                        double importPrice = 0.0;
                        double sellingPrice = 0.0;
                        String origin = "";
                        boolean isValid = true;
                        // Gets the family name of fish store
                        do {
                            System.out.print("Please enter family name of fish: ");
                            familyName = cin.nextLine().trim();
                            if (familyName.equals("")) {
                                System.out.println("Error: Family name can not be empty!");
                            }
                        } while (familyName.equals(""));
                        // Gets the fish import price of fish store
                        do {
                            try {
                                isValid = true;
                                System.out.print("Please enter import price of fish: ");
                                importPrice = cin.nextDouble();
                                cin.nextLine();
                                if (importPrice < 0) {
                                    System.out.println("Error: The price can not be less than 0!");
                                }
                            } catch (InputMismatchException e) {
                                // Alert error if user input wrong data
                                System.out.println("Error: The price of fish must be a number!");
                                isValid = false;
                                cin.nextLine();
                            }
                        } while (importPrice < 0 || isValid == false);
                        // Gets the selling price of  fish store
                        do {
                            try {
                                isValid = true;
                                System.out.print("Please enter selling price of fish: ");
                                sellingPrice = cin.nextDouble();
                                cin.nextLine();
                                if (sellingPrice < 0) {
                                    System.out.println("Error: The selling price can not be less than 0!");
                                }
                            } catch (InputMismatchException e) {
                                // Alert error if user input wrong data
                                System.out.println("Error: The selling price must be a number!");
                                isValid = false;
                                cin.nextLine();
                            }
                        } while (sellingPrice < 0 || isValid == false);
                        // Gets the origin of fish store 
                        do {
                            System.out.print("Please enter origin of fish: ");
                            origin = cin.nextLine().trim();

                            if (origin.equals("")) {
                                System.out.println("Error: Origin can not be empty!");
                            }
                        } while (origin.equals(""));
                        // Add book for fish store
                        Fish.add(familyName, importPrice, sellingPrice, origin);
                        System.out.println("Your book '" + familyName + "' is created!");
                        //  Show list book following table format
                        Fish.showTableBook();
                        cin.nextLine();
                        break;
                    case 2:
                        String title;
                        do {
                            System.out.print("Please enter title you want to find: ");
                            title = cin.nextLine().trim();
                            if (title.equals("")) {
                                System.out.println("Error: Title can not be empty!");
                            }
                        } while (title.equals(""));
                        // Find  family name of fish store
                        Fish.findFamilyName(title);
                        cin.nextLine();
                        break;
                    case 3:
                        double minPrice = 0.0;
                        double maxPrice = 0.0;
                        do {
                            try {
                                isValid = true;
                                System.out.print("Please enter minPrice: ");
                                minPrice = cin.nextDouble();
                                cin.nextLine();
                                if (minPrice < 0) {
                                    System.out.println("Error: The price can not be less than 0!");
                                }
                            } catch (InputMismatchException e) {
                                // Alert error if user input wrong data
                                System.out.println("Error: The price of fish must be a number!");
                                isValid = false;
                                cin.nextLine();
                            }
                        } while (minPrice < 0 || isValid == false);
                        do {
                            try {
                                isValid = true;
                                System.out.print("Please enter maxPrice: ");
                                maxPrice = cin.nextDouble();
                                cin.nextLine();
                                if (maxPrice < 0) {
                                    System.out.println("Error: The price can not be less than 0!");
                                }
                            } catch (InputMismatchException e) {
                                // Alert error if user input wrong data
                                System.out.println("Error: The price of fish must be a number!");
                                isValid = false;
                                cin.nextLine();
                            }
                        } while (minPrice < 0 || isValid == false);
                        Fish.findMimax(minPrice, maxPrice);
                        //  Show fish value table from min to max
                        cin.nextLine();
                        break;
                    case 4:
                        int n = Fish.FishStoreList.size();
                        int id = 0;
                        do {
                            try {
                                isValid = true;
                                System.out.print("Please enter ID you want to update price : ");
                                id = cin.nextInt();

                                if (id < 0 || id > n) {
                                    System.out.println("Error: The ID can not be less than 0 or greater than " + n + "!");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Error: The ID must be a integer number!");
                                isValid = false;
                                cin.nextLine();
                            }
                        } while (id < 0 || id > n || isValid == false);
                        // Update the book rental price of comic book
                        if (Fish.findBookID(id) == -1) {
                            System.out.println("The ID can't found!");
                        } else {
                            Fish.updatePrice(id);
                        }
                        cin.nextLine();
                        break;
                    case 5:
                        Fish.findFishsell();
                        cin.nextLine();
                        break;
                    case 6:
                        System.out.println("\n--------------------");
                        System.out.println("Thank for using our software!\n"
                                + "See you again!");
                        break;
                    default:
                        System.out.println("Error: The function must be from 1 to 6!");
                }
            } while (func != 6);
        } catch (Exception e) {
            System.out.println("Error: The selection must be a integer number");
        } finally {
            try {
                Fish.saveBook(); // Save books
            } catch (Exception e) {
                System.out.println("Exception: Can't save books!");
            }
        }
    }

}
