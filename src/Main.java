import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();


        directoryCreation("C://Game", sb, "src", "res", "savegames", "temp");
        directoryCreation("C://Game/src", sb, "main", "test");
        fileCreation("C://Game/src/main", sb, "Main.java", "Utils.java");
        directoryCreation("C://Game/res", sb, "drawables", "vectors", "icons");
        fileCreation("C://Game/temp", sb, "temp.txt");

        record(sb, "C://Game/temp/temp.txt");

    }


    public static void directoryCreation(String directory, StringBuilder stringBuilder, String... directories) {
        File newDir = new File(directory);
        if (newDir.mkdir()) {
            System.out.println("Каталог создан");
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss E");
            stringBuilder.append("Директория ").append(directory).append(" была создана: ");
            stringBuilder.append(dateFormat.format(now));
            stringBuilder.append("\n");
        } else {
            System.out.println("Что то пошло не так");
        }
        for (String dir : directories) {
            File myFile = new File(directory + "/" + dir);

            if (myFile.mkdir()) {
                Date now = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss E");
                stringBuilder.append("Директория ").append(directory).append("/").append(dir).append(" была создана: ");
                stringBuilder.append(dateFormat.format(now));
                stringBuilder.append("\n");
                System.out.println("Директория создана");
            } else {
                System.out.println("Что то пошло не так");
            }
        }
    }


    public static void fileCreation(String directory, StringBuilder stringBuilder, String... files) {
        for (String file : files) {
            File myFile = new File(directory + "/" + file);

            try {
                if (myFile.createNewFile()) {
                    Date now = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss E");
                    stringBuilder.append("Файл в директории: ").append(directory).append("/")
                            .append(file).append(" был создан: ");
                    stringBuilder.append(dateFormat.format(now));
                    stringBuilder.append("\n");
                    System.out.println("Файл создан");
                }
            } catch (IOException ex) {
                System.out.println("Исключение: ");
                System.out.println(ex.getMessage());
            }
        }
    }


    public static void record(StringBuilder stringBuilder, String directory) {
        try (FileOutputStream fos = new FileOutputStream(directory)) {
            byte[] bytes = stringBuilder.toString().getBytes();
            fos.write(bytes, 0, bytes.length);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public static void itemCreation(String directory, String... files) {
        File newDir = new File(directory);
        if (newDir.mkdir()) {
            System.out.println("Каталог создан");
        } else {
            System.out.println("Что то пошло не так");
        }
        for (String file : files) {
            File myFile = new File(directory + "/" + file);

            if (myFile.isDirectory()) {
                myFile.mkdir();
                System.out.println("Директория создана");
            } else {
                try {
                    myFile.createNewFile();
                    System.out.println("Файл создан");
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
}