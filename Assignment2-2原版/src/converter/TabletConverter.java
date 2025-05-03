package converter;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import model.Tablet;

/**
 * 此类用于将 Tablet 对象转换为 XML 格式，并将 XML 格式的数据转换为 Tablet 对象。
 * This class is used to convert Tablet objects to XML format and convert XML formatted data to Tablet objects.
 *
 * @author Fan Xinkang
 * @version 4.4
 * @since version 4.3
 */
public class TabletConverter implements Converter {

    /**
     * 判断传入的类是否为 Tablet 类。
     * Determines whether the passed class is a Tablet class.
     *
     * @param type 要检查的类。
     *             The class to be checked.
     * @return 验证结果。
     *         The verification result.
     * @author Fan Xinkang
     * @since version 4.3
     */
    @Override
    public boolean canConvert(Class type) {
        return type.equals(Tablet.class);
    }

    /**
     * 将 Tablet 对象转换为 XML 格式。
     * Converts Tablet objects to XML format.
     *
     * @param source 要转换的对象。
     *               The object to be converted.
     * @param writer 用于写入 XML 数据的 HierarchicalStreamWriter 对象。
     *               The HierarchicalStreamWriter object used to write XML data.
     * @param context 用于转换的上下文对象。
     *                The context object for conversion.
     * @author Fan Xinkang
     * @since version 4.3
     */
    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        Tablet tablet = (Tablet) source;

        writer.startNode("modelName");
        writer.setValue(tablet.getModelName());
        writer.endNode();

        writer.startNode("price");
        writer.setValue(String.valueOf(tablet.getPrice()));
        writer.endNode();

        writer.startNode("manufacturer");
        context.convertAnother(tablet.getManufacturer());
        writer.endNode();

        writer.startNode("id");
        writer.setValue(tablet.getId());
        writer.endNode();

        writer.startNode("processor");
        writer.setValue(tablet.getProcessor());
        writer.endNode();

        writer.startNode("storage");
        writer.setValue(String.valueOf(tablet.getStorage()));
        writer.endNode();

        writer.startNode("operatingSystem");
        writer.setValue(tablet.getOperatingSystem());
        writer.endNode();
    }

    /**
     * 将 XML 格式的数据转换为 Tablet 对象。
     * Converts XML formatted data to Tablet objects.
     *
     * @param reader 用于读取 XML 数据的 HierarchicalStreamReader 对象。
     *               The HierarchicalStreamReader object used to read XML data.
     * @param context 用于转换的上下文对象。
     *                The context object for conversion.
     * @return 转换后的 Tablet 对象。
     *         The converted Tablet object.
     * @author Fan Xinkang
     */
    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        String modelName = null;
        double price = 0;
        Object manufacturer = null;
        String id = null;
        String processor = null;
        int storage = 0;
        String operatingSystem = null;

        while (reader.hasMoreChildren()) {
            reader.moveDown();
            String nodeName = reader.getNodeName();
            String value = reader.getValue();

            switch (nodeName) {
                case "modelName":
                    modelName = value;
                    break;
                case "price":
                    price = Double.parseDouble(value);
                    break;
                case "manufacturer":
                    manufacturer = context.convertAnother(null, model.Manufacturer.class);
                    break;
                case "id":
                    id = value;
                    break;
                case "processor":
                    processor = value;
                    break;
                case "storage":
                    storage = Integer.parseInt(value);
                    break;
                case "operatingSystem":
                    operatingSystem = value;
                    break;
            }
            reader.moveUp();
        }

        return new Tablet(modelName, price, (model.Manufacturer) manufacturer, id, processor, storage, operatingSystem);
    }
}
/*
 * End of TabletConverter Class.
 * Checked by Fan Xinkang on 2025/05/02.
 */