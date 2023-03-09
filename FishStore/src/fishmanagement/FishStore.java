/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fishmanagement;

/**
 *
 * @author Dang Minh Tuan SE150430
 */
public final class FishStore {

    private int iD; //  FishStore id
    private String familyName; // FishStore family Name
    private double importPrice; // FishStore import price
    private double sellingPrice; // FishStore selling price
    private String origin; // FishStore origin

    /**
     * Create new FishStore
     *
     * @param iD
     * @param title
     * @param importPrice
     * @param author
     * @param sellingPrice
     * @throws FishStoreException
     */
    public FishStore(int iD, String familyName, double importPrice, double sellingPrice, String origin) throws FishStoreException {
        this.setiD(iD);
        this.setFamilyName(familyName);
        this.setImportPrice(importPrice);
        this.setSellingPrice(sellingPrice);
        this.setOrigin(origin);
    }

    /**
     * Gets the id of fish store
     *
     * @return
     */
    public int getiD() {
        return iD;
    }

    /**
     * Sets the id of fish store
     *
     * @param iD
     * @throws FishStoreException
     */
    public void setiD(int iD) throws FishStoreException {
        if (iD <= 0) {
            throw new FishStoreException("ID Fish must be a positive integer");
        } else {
            this.iD = iD;
        }
    }

    /**
     * Gets the familyName of fish store
     *
     * @return
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * Gets the familyName of fish store
     *
     * @param title
     * @throws FishStoreException
     */
    public void setFamilyName(String familyName) throws FishStoreException {
        if (familyName.equals("")) {
            throw new FishStoreException("Family name fish can't be empty");
        } else {
            this.familyName = familyName;
        }
    }

    /**
     * Gets the fish import price of fish store
     *
     * @return
     */
    public double getImportPrice() {
        return importPrice;
    }

    /**
     * Gets the fish import price of fish store
     *
     * @param importPrice
     * @throws FishStoreException
     */
    public void setImportPrice(double importPrice) throws FishStoreException {
        if (importPrice <= 0) {
            throw new FishStoreException("Fish import price must be a positive integer");
        } else {
            this.importPrice = importPrice;
        }
    }

    /**
     * Gets the origin of fish store
     *
     * @return
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Gets the origin of fish store
     *
     * @param origin
     * @throws FishStoreException
     */
    public void setOrigin(String origin) throws FishStoreException {
        if (origin.equals("")) {
            throw new FishStoreException("Name of origin can't be empty");
        } else {
            this.origin = origin;
        }
    }

    /**
     * Gest the selling Price of fish store
     *
     * @return
     */
    public double getSellingPrice() {
        return sellingPrice;
    }

    /**
     * Gest the selling Price of fish store
     *
     * @param sellingPrice
     * @throws FishStoreException
     */
    public void setSellingPrice(double sellingPrice) throws FishStoreException {
        if (sellingPrice <= 0) {
            throw new FishStoreException("Selling Price Fish must be a positive integer");
        } else {
            this.sellingPrice = sellingPrice;
        }
    }

    private void setsellingPrice(double sellingPrice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Object getorigin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setfamilyName(String familyName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
