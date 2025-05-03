package converter;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import model.Manufacturer;
import model.SmartWatch;

/**
 * 此类用于将 SmartWatch 对象转换为 XML 格式，并将 XML 格式的数据转换为 SmartWatch 对象。
 * This class is used to convert SmartWatch objects to XML format and convert XML data back to SmartWatch objects.
 *
 * @author Fan Xinkang
 * @version 4.4
 * @since version 4.3
 */
public class SmartWatchConverter implements Converter {

    /**
     * 判断传入的类是否为 SmartWatch 类。
     * Determines whether the passed class is a SmartWatch class.
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
        return type.equals(SmartWatch.class);
    }

    /**
     * 将 SmartWatch 对象转换为 XML 格式。
     * Converts SmartWatch objects to XML format.
     *
     * @param source 要转换的对象。
     *               The object to be converted.
     * @param writer 用于写入 XML 数据的 HierarchicalStreamWriter 对象。
     *               The HierarchicalStreamWriter object used to write XML data.
     * @param context 用于转换上下文的 MarshallingContext 对象。
     *                The MarshallingContext object used for conversion.
     * @author Fan Xinkang
     * @since version 4.3
     */
    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        SmartWatch watch = (SmartWatch) source;

        writer.startNode("modelName");
        writer.setValue(watch.getModelName());
        writer.endNode();

        writer.startNode("price");
        writer.setValue(String.valueOf(watch.getPrice()));
        writer.endNode();

        writer.startNode("manufacturer");
        context.convertAnother(watch.getManufacturer());
        writer.endNode();

        writer.startNode("id");
        writer.setValue(watch.getId());
        writer.endNode();

        writer.startNode("material");
        writer.setValue(watch.getMaterial());
        writer.endNode();

        writer.startNode("size");
        writer.setValue(watch.getSize());
        writer.endNode();

        writer.startNode("displayType");
        writer.setValue(watch.getDisplayType());
        writer.endNode();
    }

    /**
     * 将 XML 格式的数据转换为 SmartWatch 对象。
     * Converts XML data to SmartWatch objects.
     *
     * @param reader 用于读取 XML 数据的 HierarchicalStreamReader 对象。
     *               The HierarchicalStreamReader object used to read XML data.
     * @param context 用于转换上下文的 UnmarshallingContext 对象。
     *                The UnmarshallingContext object used for conversion.
     * @return 转换后的 SmartWatch 对象。
     *         The converted SmartWatch object.
     * @author Fan Xinkang
     * @since version 4.3
     */
    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        String modelName = null;
        double price = 0;
        Manufacturer manufacturer = null;
        String id = null;
        String material = null;
        String size = null;
        String displayType = null;

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
                    manufacturer = (Manufacturer) context.convertAnother(null, Manufacturer.class);
                    break;
                case "id":
                    id = value;
                    break;
                case "material":
                    material = value;
                    break;
                case "size":
                    size = value;
                    break;
                case "displayType":
                    displayType = value;
                    break;
            }
            reader.moveUp();
        }

        return new SmartWatch(modelName, price, manufacturer, id, material, size, displayType);
    }
}
/*
 * End of SmartWatchConverter Class.
 * Checked by Fan Xinkang on 2025/05/02.
 */