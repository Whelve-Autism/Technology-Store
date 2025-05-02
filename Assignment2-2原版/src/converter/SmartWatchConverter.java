package converter;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import model.Manufacturer;
import model.SmartWatch;

public class SmartWatchConverter implements Converter {

    @Override
    public boolean canConvert(Class type) {
        return type.equals(SmartWatch.class);
    }

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