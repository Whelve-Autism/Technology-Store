package converter;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import model.Tablet;

public class TabletConverter implements Converter {

    @Override
    public boolean canConvert(Class type) {
        return type.equals(Tablet.class);
    }

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