import org.eclipse.persistence.dynamic.DynamicEntity;
import org.eclipse.persistence.jaxb.dynamic.DynamicJAXBContext;
import org.eclipse.persistence.jaxb.dynamic.DynamicJAXBContextFactory;

import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamSource;

public class XMLGen {

    public static void main(String[] args) throws Exception {

        StreamSource xsdSource = new StreamSource(XMLGen.class.getResourceAsStream("Item.xsd"));

        DynamicJAXBContext context = DynamicJAXBContextFactory.createContextFromXSD(xsdSource, null,
                XMLGen.class.getClassLoader(), null);

        DynamicEntity item = context.newDynamicEntity("Item");

        //FIXME: This should have worked, but throws an exception if uncommented.
        //Object blackColorEnum = context.getEnumConstant("generated.Color", "Black");

        //FIXME: This works but outputs invalid XML
        Object blackColorEnum = context.getEnumConstant("generated.Color", "BLACK");

        item.set("color", blackColorEnum);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(item, System.out);
    }
}
