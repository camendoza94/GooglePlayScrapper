package file;

import object.App;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GooglePlayFile {

    //Delimiter used in CSV file
    private static final String NEW_LINE_SEPARATOR = "\n";


    private CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

    private CSVPrinter csvFilePrinter = null;

    private FileWriter fileWriter = null;

    public GooglePlayFile(Set<App> courses) {
        try {
            init();
            for (App course : courses) {
                writeApp(course);
            }
            closeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() {

        try {
            //initialize FileWriter object
            fileWriter = new FileWriter("GooglePlay.csv");

            //initialize CSVPrinter object
            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

            //Create CSV file header
            Object[] FILE_HEADER =
                    {"name", "description", "averageRating", "ratingCount", "fiveStarRatings", "fourStarRatings", "recentChanges"};

            csvFilePrinter.printRecord(FILE_HEADER);

            System.out.println("CSV file was created successfully!");

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter!");
            e.printStackTrace();
            closeFile();
        }
    }

    private void writeApp(App app) throws IOException {
        List<String> appRecord = new ArrayList<>();
        appRecord.add(app.getName());
        appRecord.add(app.getDescription());
        appRecord.add(String.valueOf(app.getAverageRating()));
        appRecord.add(String.valueOf(app.getRatingCount()));
        appRecord.add(String.valueOf(app.getFiveStarsRatings()));
        appRecord.add(String.valueOf(app.getFourStarsRatings()));
        appRecord.add(String.valueOf(app.getRecentChanges()));
        csvFilePrinter.printRecord(appRecord);
    }

    private void closeFile() {
        try {
            fileWriter.flush();
            fileWriter.close();
            csvFilePrinter.close();
        } catch (IOException e) {
            System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
            e.printStackTrace();
        }

    }
}
