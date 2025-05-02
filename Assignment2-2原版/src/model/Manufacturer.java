package model;

import utils.Utilities;

import java.util.Objects;

/**
 * 此类用于创建 Manufacturer 对象。
 * This class is used to create Manufacturer objects.
 *
 * @author Guoqing lu
 * @version 0.0
 * @since version 0.0
 */
public class Manufacturer {

    private String manufacturerName = ""; // max 20 chars
    private int numEmployees = 1;   // >= 1, default 1

    /**
     * 创建 Manufacturer 对象。
     * Constructor for Manufacturer.
     *
     * @param manufacturerName 制造商的名字。
     *                         The name of the manufacturer.
     * @param numEmployees 员工数量。
     *                     The number of employees.
     * @author Guoqing Lu
     * @since version 0.0
     */
    public Manufacturer(String manufacturerName, int numEmployees) {
        this.manufacturerName = Utilities.truncateString(manufacturerName, 20);
        setNumEmployees(numEmployees);
    }

    /*
      封装。
      Encapsulation.
     */
    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        if (Utilities.validStringLength(manufacturerName, 20)) {
            this.manufacturerName = manufacturerName;
        }
    }

    public int getNumEmployees() {
        return numEmployees;
    }

    public void setNumEmployees(int numEmployees) {
        if (numEmployees >= 1) {
            this.numEmployees = numEmployees;
        }
    }

    /**
     * 重写 equals 方法，以便比较 Manufacturer 对象。
     * Override equals method to compare Manufacturer objects.
     *
     * @param o Manufacturer 对象。
     *          Manufacturer object.
     * @return 比较的结果。
     *         The result of comparison.
     * @author Guoqing Lu
     * @since version 0.0
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Manufacturer that)) {
            return false;
        }
        return numEmployees == that.numEmployees && Objects.equals(manufacturerName, that.manufacturerName);
    }

    /**
     * 重写 toString 方法，以便打印 Manufacturer 对象。
     * Override toString method to print Manufacturer objects.
     *
     * @return Manufacturer 对象的字符串表示。
     *         The string representation of Manufacturer object.
     * @author Guoqing Lu
     * @since version 0.0
     */
    @Override
    public String toString() {
        return STR."Manufacturer{Name: \{manufacturerName}, Num Employees: \{numEmployees}\{numEmployees == 1 ? " employee" : " employees"}}";
    }
}
/*
 * End of models.Manufacturer Class.
 * Checked by Fan Xinkang on 2025/04/15.
 */