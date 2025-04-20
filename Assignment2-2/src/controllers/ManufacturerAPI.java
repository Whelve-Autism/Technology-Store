package controllers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import models.Manufacturer;
import utils.ISerializer;

import static utils.Utilities.isValidIndex;

/**
 * 此类用于处理制造商的数据。
 * This class is used to handle manufacturer data.
 *
 * @author Guoqing Lu, Fan Xinkang
 * @version 3.1
 * @since version 0.0
 */
public class ManufacturerAPI  implements ISerializer {

    private List<Manufacturer> manufacturers = new ArrayList<>();
    private File file;

    /**
     * 创建 ManufacturerAPI 对象。
     * Constructor for ManufacturerAPI.
     *
     * @param file 文件。
     *             File.
     * @author Guoqing Lu
     * @since version 0.0
     */
    public ManufacturerAPI(File file) {
        this.file = file;
    }

    //---------------------
    // Create methods
    //---------------------

    /**
     * 添加制造商。
     * Add a manufacturer.
     *
     * @param manufacturer 制造商。
     *                     Manufacturer.
     * @return 添加的结果。
     *         The result of the addition.
     * @author Guoqing Lu
     * @since version 0.0
     */
    public boolean addManufacturer(Manufacturer manufacturer) {
        if (isValidManufacturer(manufacturer.getManufacturerName())){
            return false;
        }
        return manufacturers.add(manufacturer);
    }

    //---------------------
    // Read methods
    //---------------------

    /**
     * 根据索引获取制造商。
     * Get a manufacturer by index.
     *
     * @param index 索引。
     *              Index.
     * @return 制造商。
     *         Manufacturer.
     * @author Guoqing Lu
     * @since version 0.0
     */
    public Manufacturer getManufacturerByIndex(int index){
        if (isValidIndex(manufacturers, index)){
            return manufacturers.get(index);
        }
        else{
            return null;
        }
    }

    /**
     * 根据制造商名称获取制造商。
     * Get a manufacturer by manufacturer name.
     *
     * @param manName 制造商名称。
     *                Manufacturer name.
     * @return 制造商。
     *         Manufacturer.
     * @author Guoqing Lu
     * @since version 0.0
     */
    public Manufacturer getManufacturerByName (String manName){
        int index = retrieveManufacturerIndex(manName);
        if (index != -1) {
            return manufacturers.get(index);
        }
        return null;
    }

    /**
     * 列出所有制造商。
     * List all manufacturers.
     *
     * @return 制造商列表。
     *         List of manufacturers.
     * @author Guoqing Lu
     * @since version 0.0
     */
    public String listManufacturers() {
        StringBuilder listManufacturers = new StringBuilder();
        for (Manufacturer manufacturer : manufacturers) {
            listManufacturers.append(manufacturers.indexOf(manufacturer)).append(": ").append(manufacturer).append("\n");
        }
        if (listManufacturers.toString().isEmpty()) {
            return "No manufacturers";
        }
        else {
            return listManufacturers.toString();
        }
    }

    /**
     * 列出所有制造商。
     * List all manufacturers.
     *
     * @return 制造商列表。
     *         List of manufacturers.
     * @author Guoqing Lu
     * @since version 0.0
     */
    public String listAllByManufacturerName(String manuName){
        if (!manufacturers.isEmpty()) {
            StringBuilder listManufacturers = new StringBuilder();
            for (Manufacturer manufacturer : manufacturers) {
                if (manufacturer.getManufacturerName().equalsIgnoreCase(manuName))
                    listManufacturers.append(manufacturers.indexOf(manufacturer)).append(": ").append(manufacturer).append("\n");
            }
            if (listManufacturers.isEmpty()) {
                return "No manufacturers of that name";
            } else {
                return listManufacturers.toString();
            }
        }
        else return "There are no manufacturers in the list.";
    }

    //---------------------
    // Update methods
    //---------------------

    /**
     * 更新制造商。
     * Update a manufacturer.
     *
     * @param manufacturerName 制造商名称。
     *                         Manufacturer name.
     * @param numEmployees     制造商员工数量。
     *                         Number of employees.
     * @return 更新结果。
     *         The result of the update.
     * @author Guoqing Lu
     * @since version 0.0
     */
    public boolean updateManufacturer(String manufacturerName, int numEmployees){
        if (isValidManufacturer(manufacturerName)){
            Manufacturer manufacturerByName = getManufacturerByName(manufacturerName);
            manufacturerByName.setNumEmployees(numEmployees);
            return true;
        }
        return false;
    }

    //---------------------
    // Delete methods
    //---------------------

    /**
     * 删除制造商。
     * Remove a manufacturer.
     *
     * @param manufacturer 制造商。
     *                     Manufacturer.
     * @return 删除结果。
     *         The result of the deletion.
     * @author Guoqing Lu
     * @since version 0.0
     */
    public boolean removeManufacturer(Manufacturer manufacturer){
        if (manufacturers.contains(manufacturer)) {
            return manufacturers.remove(manufacturer);
        }
        return false;
    }

    /**
     * 删除制造商。
     * Remove a manufacturer.
     *
     * @param manufacturerName 制造商名称。
     *                         Manufacturer name.
     * @return 删除结果。
     *         The result of the deletion.
     * @author Guoqing Lu
     * @since version 0.0
     */
    public Manufacturer removeManufacturerByName(String manufacturerName){
        int index = retrieveManufacturerIndex(manufacturerName);
        if (index != -1) {
            return manufacturers.remove(index);
        }
        return null;
    }

    //---------------------
    // Validation Methods
    //---------------------

    /**
     * 验证制造商是否存在。
     * Validate if a manufacturer exists.
     *
     * @param manufacturerName 制造商名称。
     *                         Manufacturer name.
     * @return 验证结果。
     *         The validation result.
     * @author Guoqing Lu
     * @since version 0.0
     */
    public boolean isValidManufacturer(String manufacturerName){
        for (Manufacturer manufacturer : manufacturers){
            if (manufacturer.getManufacturerName().equalsIgnoreCase(manufacturerName)){
                return true;
            }
        }
        return false;
    }

    /**
     * 检索制造商索引。
     * Retrieve the index of a manufacturer.
     *
     * @param manufacturerName 制造商名称。
     *                         Manufacturer name.
     * @return 索引。
     *         The index.
     * @author Guoqing Lu, Fan Xinkang
     * @since version 4.2
     */
    public int retrieveManufacturerIndex(String manufacturerName) {
        for (int i = 0; i < manufacturers.size(); i++) {
            Manufacturer manufacturer = manufacturers.get(i);
            if (manufacturer.getManufacturerName().equalsIgnoreCase(manufacturerName)) {
                return i;
            }
        }
        return -1;
    }

    //---------------------
    // Getters/Setters
    //---------------------

    public List<Manufacturer> getManufacturers() {
        return manufacturers;
    }


    //---------------------
    // Persistence Methods
    //---------------------

    /**
     * 获取文件名。
     * Get the file name.
     *
     * @return 文件名。
     *         The file name.
     * @author Guoqing Lu
     * @since version 0.0
     */
    @Override
    public String fileName() {
        return String.valueOf(file);
    }

    /****
     * 保存制造商。
     * Save a manufacturer.
     *
     * @author Guoqing Lu
     * @since version 0.0
     */
    public void save() throws Exception {
        var xstream = new XStream(new DomDriver());
        ObjectOutputStream os = xstream.createObjectOutputStream(new FileWriter(file));
        os.writeObject(manufacturers);
        os.close();
    }

    /**
     * 加载制造商。
     * Load a manufacturer.
     *
     * @author Guoqing Lu
     * @since version 0.0
     */
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[]{ Manufacturer.class};

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader(file));
        manufacturers = (List<Manufacturer>) in.readObject();
        in.close();
    }
}
/*
 * End of controllers.ManufacturerAPI Class.
 * Checked by Fan Xinkang on 2025/04/20.
 */