package org.example.Model.Data;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class CSVDataFactory implements IDataFactory {

    private int imagePixelCount;

    public CSVDataFactory() {
        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                // Default value for MNIST if config is missing
                this.imagePixelCount = 784;
                return;
            }
            prop.load(input);
            int width = Integer.parseInt(prop.getProperty("image.width"));
            int height = Integer.parseInt(prop.getProperty("image.height"));
            this.imagePixelCount = width * height;
        } catch (IOException | NumberFormatException ex) {
            System.err.println("Error loading properties: " + ex.getMessage());
            this.imagePixelCount = 784;
        }
    }

    @Override
    public IData readSingleCSV(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            if (line != null) {
                return parseCsvLine(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
        return null; 
    }

    @Override
    public IData[] readCSVCollection(String path) {
        List<IData> dataCollection = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                dataCollection.add(parseCsvLine(line));
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV collection file: " + e.getMessage());
        }
        return dataCollection.toArray(new IData[0]);
    }

    private IData parseCsvLine(String line) {
        String[] values = line.split(",");
        if (values.length != imagePixelCount + 1) {
            System.err.println("Invalid CSV line format. Expected " + (imagePixelCount + 1) + " values, but got " + values.length);
            return null;
        }

        int label = Integer.parseInt(values[0]);

        float[] pixels = new float[imagePixelCount];

        for (int i = 0; i < imagePixelCount; i++) {
            float pixelValue = Integer.parseInt(values[i + 1]);
            // Normalize pixel values from 0-255 to 0.0-1.0
            pixels[i] = pixelValue / 255.0f;
        }

        // Assuming a concrete implementation of IData exists, e.g., 'Data' class
        // We need to check the actual implementation of IData in the project.
        // For now, let's assume there's a 'Data' class that implements IData
        // and has a constructor like Data(float[] values, int label).
        return new Data(pixels, label);
    }

    public IData convertBMP2Data(String path){
        File file = new File(path);
        try{
            BufferedImage image = ImageIO.read(file);

            //TODO: read the image size from config parameters instead of hard coded value.
            // Ensure the image is 28x28
            if (image.getWidth() != 28 || image.getHeight() != 28) {
                throw new IllegalArgumentException("The file is not 28x28 pixel.");
            }
            float[] pixels = new float[imagePixelCount];
            int pixeilIndex = 0;


            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    // Get the RGB value
                    int pixel = image.getRGB(x, y);

                    // Extract the Red component (0-255) as our grayscale value
                    // In a true grayscale image, R, G, and B are identical.
                    int grayValue = (pixel >> 16) & 0xff;

                    grayValue = 255 - grayValue;
                    pixels[pixeilIndex] = grayValue / 255.0f;
                    //pixels[pixeilIndex] = pixel / 255.0f;
                    pixeilIndex++;


                }
            }
            return new Data(pixels);

        } catch (IOException e) {
            throw new RuntimeException("Could not read the file. Make sure file exists and has correct format.");
        }
    }
}
