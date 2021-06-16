package canvas;

import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.net.URL;

public class SaveImg {

//public static void main(String[] args) throws Exception {
//    String imageUrl = "";
//    String destinationFile = "image.jpg";
//
//    saveImage(imageUrl, destinationFile);
//}
//https://pbs.twimg.com/media/EpUICH6WEAEb1pM.jpg
//https://pbs.twimg.com/media/Eqvm34WXAAMu-UJ?format=jpg&name=4096x4096
//https://pbs.twimg.com/media/EuH_GvZWQAMdzAT?format=jpg&name=4096x4096
	//https://pbs.twimg.com/media/EpUICH6WEAEb1pM
	//https://i.ytimg.com/vi/EzkUe9FzPQk/maxresdefault.jpg
public static void saveImage() throws IOException {
    URL url = new URL("https://pbs.twimg.com/media/EoaXIXbXIAgDrwD.jpg");
    InputStream is = url.openStream();
    OutputStream os = new FileOutputStream("amogus.jpg");

    byte[] b = new byte[2048];
    int length;

    while ((length = is.read(b)) != -1) {
        os.write(b, 0, length);
    }

    is.close();
    os.close();
}

}