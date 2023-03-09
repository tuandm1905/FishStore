package fishmanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Dang Minh Tuan SE150430
 */
public class FishManagement {

    private String FISH_FILE;  // The URL of data file that stores all fish store
    private int numberOfFish; // Number of fish storethat store in data file
    ArrayList<FishStore> FishStoreList; // All instance of fish store
    Scanner sc = new Scanner(System.in);

    /**
     * Create instance for fish store management
     *
     * @param FISH_FILE
     */
    public FishManagement(String FISH_FILE) {
        this.numberOfFish = 0;
        this.FishStoreList = new ArrayList<FishStore>();
        this.FISH_FILE = FISH_FILE;
    }
    //initialize width for table output
    private int widthNO = 3;
    private int widthID = 2;
    private int widthFamilyName = 5;
    private int widthImportPrice = 13;
    private int widthSellingPrice = 13;
    private int widthOrigin = 6;
    private String barLine = "";
    //initialize template for table output
    private String template = "";

    /**
     * Find fish by ID
     *
     * @param ID
     * @return
     */
    public int findBookID(int ID) {
        for (int i = 0; i < this.FishStoreList.size(); i++) {
            FishStore a = this.FishStoreList.get(i);
            if (a.getiD() == ID) {
                return i;
            }
        }
        return - 1;
    }

    /**
     * Loads data of fish store from data file and stored it into ArrayList
     *
     * @throws IOException
     * @throws FishStoreException
     */
    public void loadFish() throws IOException, FishStoreException {
        File fishFile = new File(FISH_FILE);
        if (!fishFile.exists()) {
            fishFile.createNewFile();
            System.out.printf("The data file " + FISH_FILE + " is not exits. "
                    + "Creating new data file " + FISH_FILE + "... Done!\n");
        } else {
            System.out.printf("\nThe data file " + FISH_FILE + " is found."
                    + " Data of fish is loading... \n");

            String Id, familyName, importPrice, sellingPrice, origin;
            try (BufferedReader br = new BufferedReader(new FileReader(FISH_FILE))) {
                this.numberOfFish = Integer.parseInt(br.readLine());
                for (int i = 0; i < this.numberOfFish; i++) {
                    Id = br.readLine();
                    familyName = br.readLine();
                    importPrice = br.readLine();
                    sellingPrice = br.readLine();
                    origin = br.readLine();
                    updateWidth(Id, familyName, importPrice, sellingPrice, origin);
                    this.FishStoreList.add(new FishStore(Integer.parseInt(Id), familyName, Double.parseDouble(importPrice), Double.parseDouble(sellingPrice), origin));
                }
            }
            System.out.println("Done! [" + this.numberOfFish + " fish]");
        }
    }

    /**
     * Update width for table if have width greater than current width
     *
     * @param id
     * @param familyName
     * @param importPrice
     * @param origin
     * @param sellingPrice
     */
    public void updateWidth(String id, String familyName, String importPrice, String sellingPrice, String origin) {
        widthID = Math.max(widthID, id.length());
        widthFamilyName = Math.max(widthFamilyName, familyName.length());
        widthImportPrice = Math.max(widthImportPrice, importPrice.length());
        widthSellingPrice = Math.max(widthSellingPrice, sellingPrice.length());
        widthOrigin = Math.max(widthOrigin, origin.length());

    }

    /**
     * update that print to screen
     */
    public void updateTemplate() {
        String col0 = "";
        for (int i = 0; i < widthNO + 3; i++) {
            col0 += "-";
        }
        String col1 = "";
        for (int i = 0; i < widthID + 2; i++) {
            col1 += "-";
        }
        String col2 = "";
        for (int i = 0; i < widthFamilyName + 5; i++) {
            col2 += "-";
        }
        String col3 = "";
        for (int i = 0; i < widthImportPrice + 2; i++) {
            col3 += "-";
        }
        String col4 = "";
        for (int i = 0; i < widthSellingPrice + 4; i++) {
            col4 += "-";
        }
        String col5 = "";
        for (int i = 0; i < widthOrigin + 5; i++) {
            col5 += "-";
        }
        barLine = "+" + col0 + "+" + col1 + "+" + col2 + "+" + col3 + "+" + col4 + "+" + col5 + "+";
        template = "|  %" + widthNO + "s | %" + widthID + "s | %-" + widthFamilyName + "s    |%" + widthImportPrice + "s  |   %" + widthSellingPrice + "s |    %" + widthOrigin + "s |\n";
    }

    /**
     * Add new FishStore to bank
     *
     * @param title
     * @param importPrice
     * @param origin
     * @param sellingPrice
     * @return
     * @throws FishStoreException
     */
    public int add(String familyName, double importPrice, double sellingPrice, String origin) throws FishStoreException {
        int id = 0, count = 0;
        for (int j = 1;; ++j) {
            boolean valid = true;
            for (int i = 0; i < this.FishStoreList.size(); i++) {
                FishStore a = this.FishStoreList.get(i);
                if (a.getiD() == j) {
                    valid = false;
                } else if (a.getFamilyName().equals(familyName) && a.getImportPrice() == importPrice
                        && a.getOrigin().equals(origin) && a.getSellingPrice() == sellingPrice) {
                    count++;
                }
            }
            if (valid == true) {
                id = j;
                this.FishStoreList.add(new FishStore(id, familyName, importPrice, sellingPrice, origin));
                break;
            } else if (count != 0) {
                break;
            }
        }
        return ++this.numberOfFish;
    }

    /**
     * Find fish value from min Price to maxPrice
     *
     * @param minPrice
     * @param maxPrice
     */
    public void findMimax(double minPrice, double maxPrice) {
        int count = 0;
        updateTemplate();
        System.out.println(barLine);
        System.out.printf(template, "NO.", "ID", "Family Name", "Import Price", "Selling Price", "Origin");
        System.out.println(barLine);
        for (int i = 0; i < this.FishStoreList.size(); i++) {
            FishStore a = this.FishStoreList.get(i);
            if (a.getSellingPrice() >= minPrice && a.getSellingPrice() <= maxPrice) {
                count++;
                System.out.printf(template, count + "", a.getiD() + "", a.getFamilyName() + "", "$" + a.getImportPrice() + "", "$" + a.getSellingPrice(), a.getOrigin() + "");
            }
        }
        System.out.println(barLine);
        if (count == 0) {
            System.out.println("Not found '" + minPrice + " to " + maxPrice + "' in FishStore bank!");
        }
    }
    public void findFishsell(){
        int count = 0;
        updateTemplate();
        System.out.println(barLine);
        System.out.printf(template, "NO.", "ID", "Family Name", "Import Price", "Selling Price", "Origin");
        System.out.println(barLine);
        for (int i = 0; i < this.FishStoreList.size(); i++) {
            FishStore a = this.FishStoreList.get(i);
            if (a.getSellingPrice()<a.getImportPrice()) {
                count++;
                System.out.printf(template, count + "", a.getiD() + "", a.getFamilyName() + "", "$" + a.getImportPrice() + "", "$" + a.getSellingPrice(), a.getOrigin() + "");
            }
        }
        System.out.println(barLine);
        if (count == 0) {
            System.out.println("Not found sellingPrice < importPrice in Fish Store!");
        }
    }

    /**
     * Find fish by familyName
     *
     * @param familyName
     */
    public void findFamilyName(String familyName) {
        int count = 0;

        for (int i = 0; i < this.FishStoreList.size(); i++) {
            FishStore b = this.FishStoreList.get(i);
            if (b.getFamilyName().toLowerCase().contains(familyName.toLowerCase()) == true) {
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Not found '" + familyName + "' in FishStore bank!");
        } else {
            updateTemplate();
            System.out.println(barLine);
            System.out.printf(template, "NO.", "ID", "Family Name", "Import Price", "Selling Price", "Origin");
            System.out.println(barLine);

            for (int i = 0; i < this.FishStoreList.size(); i++) {
                FishStore b = this.FishStoreList.get(i);
                if (b.getFamilyName().toLowerCase().contains(familyName.toLowerCase()) == true) {
                    showBookByID(b.getiD());
                }
            }

            System.out.println(barLine);
        }

    }

    /**
     * Deleted fish by id
     *
     * @param removeID
     * @return
     */
    public int deleteBook(int removeID) {
        int i = findBookID(removeID);
        if (i != -1) {
            {
                this.FishStoreList.remove(findBookID(removeID));
                return --this.numberOfFish;
            }
        } else {

            return -1;
        }

    }

    /**
     * Find fish by min to max Price
     *
     * @param origin
     */
    public void findOrigin(String origin) {
        int count = 0;

        for (int i = 0; i < this.FishStoreList.size(); i++) {
            FishStore b = this.FishStoreList.get(i);
            if (b.getOrigin().toLowerCase().equals(origin.toLowerCase())) {
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Not found '" + origin + "' in Fish Store bank!");
        } else {
            updateTemplate();
            System.out.println(barLine);
            System.out.printf(template, "NO.", "ID", "Family Name", "Import Price", "Selling Price", "Origin");
            System.out.println(barLine);

            for (int i = 0; i < this.FishStoreList.size(); i++) {
                FishStore b = this.FishStoreList.get(i);
                if (b.getOrigin().toLowerCase().equals(origin.toLowerCase())) {
                    showBookByID(b.getiD());
                }
            }

            System.out.println(barLine);
        }

    }

    /**
     * Update price for fish store by id
     *
     * @param Id
     * @throws FishStoreException
     */
    public void updatePrice(int Id) throws FishStoreException {
        int i = findBookID(Id);
        double price = 0;
        boolean isValid = true;
        do {
            try {
                isValid = true;
                System.out.print("Please enter new price for " + this.FishStoreList.get(i).getFamilyName() + ": ");
                price = sc.nextDouble();
                if (price <= 0) {
                    System.out.println("The price must be a positive number!");
                    isValid = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: The price must be a positive integer number!");
                isValid = false;
                sc.nextLine();

            }
            System.err.println("Update the selling price of fish successfully");
        } while (price <= 0);
        this.FishStoreList.get(i).setSellingPrice(price);
        showTableBook();

    }

    /**
     * Show all fish store following table format
     */
    public void showTableBook() {
        int No = 0;

        updateTemplate();
        System.out.println(barLine);
        System.out.printf(template, "NO.", "ID", "Family Name", "Import Price", "Selling Price", "Origin");
        System.out.println(barLine);
        for (int i = 0; i < this.FishStoreList.size(); i++) {
            FishStore a = this.FishStoreList.get(i);
            No++;
            System.out.printf(template, No + "", a.getiD() + "", a.getFamilyName() + "", "$" + a.getImportPrice() + "", "$" + a.getSellingPrice() + "", a.getOrigin() + "");

        }
        System.out.println(barLine);
    }

    /**
     * Show fish by id
     *
     * @param id
     */
    public void showBookByID(int id) {
        FishStore a = this.FishStoreList.get(findBookID(id));
        System.out.printf(template, "...", a.getiD() + "", a.getFamilyName() + "", "$" + a.getImportPrice() + "", "$" + a.getSellingPrice(), a.getOrigin() + "");
    }

    /**
     * Save fish store bank ( ArrayList) into data file
     *
     * @throws IOException
     */
    public void saveBook() throws IOException {
        try ( // Overwrite data file
                FileWriter fw = new FileWriter(new File(FISH_FILE))) {
            System.out.print("Fish Store is saving into data file Fishs.txt...");
            // Writes number of fish store
            fw.append(String.valueOf(this.numberOfFish) + "\n");
            for (int i = 0; i < this.numberOfFish; i++) {
                int iD = this.FishStoreList.get(i).getiD();
                String familyName = this.FishStoreList.get(i).getFamilyName();
                double importPrice = this.FishStoreList.get(i).getImportPrice();
                double sellingPrice = this.FishStoreList.get(i).getSellingPrice();
                String origin = this.FishStoreList.get(i).getOrigin();
                // Writes comic book's information into data file
                fw.append(String.valueOf(iD) + "\n");
                fw.append(familyName + "\n");
                fw.append(String.valueOf(importPrice) + "\n");
                fw.append(String.valueOf(sellingPrice) + "\n");
                fw.append(origin + "\n");
            }
        } finally {
// Save data file ( from RAM into HDD)
            System.out.println("Done! [" + this.numberOfFish + " fish]");
        }
    }
}
