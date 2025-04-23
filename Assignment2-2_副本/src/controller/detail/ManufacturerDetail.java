//package controller.detail;
//
//import model.Manufacturer;
//
//import java.util.List;
//
//public class ManufacturerDetail {
//
//    private static List<Manufacturer> manufacturers;
//
//    public List<Manufacturer> getManufacturers() {
//        return manufacturers;
//    }
//
//    public void setManufacturers(List<Manufacturer> manufacturers) {
//        ManufacturerDetail.manufacturers = manufacturers;
//    }
//
//    public static boolean hasManufacturers() {
//        return manufacturers != null && !manufacturers.isEmpty();
//    }
//
//    public static boolean isExistManufacturerName(String manufacturerName) {
//        for (Manufacturer manufacturer : manufacturers) {
//            if (manufacturer.getManufacturerName().equalsIgnoreCase(manufacturerName)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public static int getManufacturerIndex(String manufacturerName) {
//        for (int i = 0; i < manufacturers.size(); i++) {
//            Manufacturer manufacturer = manufacturers.get(i);
//            if (manufacturer.getManufacturerName().equalsIgnoreCase(manufacturerName)) {
//                return i;
//            }
//        }
//        return -1;
//    }
//
//    public boolean addManufacturer(Manufacturer manufacturer) {
//        if (manufacturer == null || manufacturer.getManufacturerName().trim().isEmpty()) {
//            System.out.println("The manufacturer name cannot be empty.");
//            return false;
//        }
//
//        if (isExistManufacturerName(manufacturer.getManufacturerName())) {
//            System.out.println("This manufacturer name already exists.");
//            return false;
//        }
//
//        if (manufacturer.getNumEmployees() <= 0) {
//            System.out.println("The number of employees must be greater than 0.");
//            return false;
//        }
//        return manufacturers.add(manufacturer);
//    }
//
//    public static boolean deleteManufacturer(String manufacturerName) {
//        int index = getManufacturerIndex(manufacturerName);
//        if (index == -1) {
//            System.out.println("Manufacturer name does not exist.");
//            return false;
//        } else {
//            manufacturers.remove(index);
//            return true;
//        }
//    }
//
//    public static boolean updateManufacturer(String manufacturerName, int numEmployees) {
//        int index = getManufacturerIndex(manufacturerName);
//        if (index == -1) {
//            System.out.println("Manufacturer name does not exist.");
//            return false;
//        }
//
//        if (numEmployees <= 0) {
//            System.out.println("The number of employees must be greater than 0.");
//            return false;
//        }
//        Manufacturer currentManufacturer = manufacturers.get(index);
//        String currentManufacturerName = currentManufacturer.getManufacturerName();
//        if (!currentManufacturerName.equalsIgnoreCase(manufacturerName)) {
//            if (isExistManufacturerName(manufacturerName)) {
//                System.out.println("Manufacturer name already exists.");
//                return false;
//            }
//        }
//        manufacturers.get(index).setManufacturerName(manufacturerName);
//        manufacturers.get(index).setNumEmployees(numEmployees);
//        return true;
//    }
//
//    public static String listAllManufacturers() {
//        StringBuilder builder = new StringBuilder();
//        for (Manufacturer manufacturer : manufacturers) {
//            builder.append(manufacturer.getManufacturerName()).append(": ").append(manufacturer.getNumEmployees()).append("\n");
//        }
//        return builder.toString();
//    }
//
//    public String listAllManufacturersByManufacturerName(String manufacturerName) {
//        int index = getManufacturerIndex(manufacturerName);
//        if (index == -1) {
//            return "Manufacturer not found";
//        }
//        return STR."\{manufacturers.get(index).getManufacturerName()}: \{manufacturers.get(index).getNumEmployees()}";
//    }
//
//    public String listAllManufacturersByTechnologyName(String technologyName) {
//        StringBuilder builder = new StringBuilder();
//        for (Manufacturer manufacturer : manufacturers) {
//            if (manufacturer.getManufacturerName().equalsIgnoreCase(technologyName)) {
//                builder.append(manufacturer.getManufacturerName()).append(": ").append(manufacturer.getNumEmployees()).append("\n");
//            }
//        }
//        return builder.toString();
//    }
//}