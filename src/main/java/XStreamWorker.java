import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class XStreamWorker {

    private XStream xsWriter;
    private XStream xsReader;


    public void serialize(String directoryPath, String fileName, Set<?> serObject) throws NotDirectoryException {

        if (xsWriter == null) {
            xsWriter = new XStream();
            xsWriter.setMode(XStream.NO_REFERENCES);
        }

        if (!Files.isDirectory(Paths.get(directoryPath))) {
            throw new NotDirectoryException(directoryPath + " is not a directory!");
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(directoryPath + "\\" + fileName + ".xml")) {
            xsWriter.toXML(serObject, fileOutputStream);
        } catch (IOException e) {
            System.err.println("Unable to write file \n" + e.toString());
        }
    }

    public Set<?> deserialize(String filePath) throws IOException {
        if (xsReader == null) {
            xsReader = new XStream(new DomDriver());
        }
        Set<?> result = new HashSet<>();

        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            result = (HashSet<?>) xsReader.fromXML(fileInputStream, result);
            return result;

        } catch (IOException e) {
            throw e;
        }
    }
}
