import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Organizer {
    String dirPath;

    public Organizer(String dirPath) {
        this.dirPath = dirPath;
    }

    public void organizeFiles() {
        // Creating new folders to move files
        String[] categories = {"Documents", "Images", "Videos", "Music", "Compressed", "Executables", "Others"};
        for (String category : categories) {
            File dir = new File(this.dirPath + "/ORGANIZED/" + category);
            if (!dir.exists()) {
                dir.mkdirs();
                System.out.println("Directory Created [" + category.toUpperCase() + "]");
            }
        }

        // Scan the main directory
        File dir = new File(this.dirPath);
        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    String fileType = getFileType(file);
                    String destination = getCategoryDestination(fileType);
                    moveFile(file, destination);
                }
            }
        }
    }

    private String getFileType(File file) {
        String fileName = file.getName();
        int index = fileName.lastIndexOf(".");
        if (index > 0) {
            return fileName.substring(index + 1).toLowerCase();
        }
        return "Others";
    }

    private String getCategoryDestination(String fileType) {
        switch (fileType) {
            case "pdf":
            case "doc":
            case "docx":
            case "txt":
            case "rtf":
            case "xlsx":
            case "xls":
            case "csv":
                return "Documents";
            case "jpg":
            case "jpeg":
            case "png":
            case "gif":
            case "bmp":
                return "Images";
            case "mp3":
            case "wav":
            case "ogg":
            case "flac":
                return "Music";
            case "mp4":
            case "avi":
            case "mkv":
            case "mov":
            case "wmv":
                return "Videos";
            case "zip":
            case "rar":
            case "7z":
            case "tar":
            case "gz":
                return "Compressed";
            case "exe":
            case "msi":
                return "Executables";
            default:
                return "Others";
        }
    }

    private void moveFile(File source, String destination) {
        Path sourcePath = source.toPath();
        Path destinationPath = new File(this.dirPath + "/ORGANIZED/" + destination + "/" + source.getName()).toPath();
        try {
            Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Moved file: " + source.getName() + " to " + destination);
        } catch (IOException ex) {
            System.err.println("Failed to move file: " + source.getName());
            System.err.println(ex.getMessage());
        }
    }
}
