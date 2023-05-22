package renderer;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {
    @Test
    void writeImageTest(){
        ImageWriter imageWriter = new ImageWriter("image",800,500);
        for (int i = 0; i < 500; i++){
            for (int j = 0; j < 800; j++){
                if((i % 50) == 0 || (j % 50) == 0){
                    imageWriter.writePixel(j,i,new Color(255,0,0));
                }
                else
                    imageWriter.writePixel(j,i,new Color(36,171,193));
            }
            imageWriter.writeToImage();
        }
    }
}