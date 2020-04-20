//Klasa do obsługi folderów

package vs.api.helpers;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FoldersController {
    private final String PATH = "..\\VirtualSpace";
    private String _pathUser;
    private String _currentFolder;
    private String _userName;
    private String _pathTemp;

    public FoldersController(String userName, String currentFolder) {
        if (currentFolder != null && currentFolder.length() != 0) {
            _currentFolder = currentFolder;
        } else {
            _currentFolder = "";
        }

        if (userName != null && userName.length() != 0) {
            _userName = userName;
        } else {
            throw new IllegalArgumentException("User name can't be null nor 0 length");
        }

        makeFolder();
    }

    //Tworzy nowy folder i usuwania folder tymczasowy jeśli istnieje
    private void makeFolder() {
        _pathUser = PATH + "\\" + _userName;
        _pathTemp = _pathUser + "\\temp";

        File directory = new File(PATH);
        if (!directory.exists()) {
            directory.mkdir();
        }

        File directoryUser = new File(_pathUser);
        if (!directoryUser.exists()) {
            directoryUser.mkdir();
        }

        File directoryTemp = new File(_pathTemp);
        if (directoryTemp.exists()) {
            if (directoryTemp.length() == 0) {
                directoryTemp.delete();
            }
        }
    }

    //Kopiuje pliki z folderu tymczasowego do folderu root
    public void copyFileToTempFolder(InputStream src, String fileName) throws IOException {
        File directoryTemp = new File(_pathTemp);
        if (!directoryTemp.exists()) {
            directoryTemp.mkdir();
        }

        if (fileName != null && fileName.length() != 0) {

            try (src) {
                copyFiles(src, _pathTemp + "\\" + fileName);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else throw new IllegalArgumentException("File name can't be 0 length nor null");
    }

    //Metoda do skopiowania zawartości jednego pliku do drugiego
    private void copyFiles(InputStream src, String destPath) throws IOException, NullPointerException {
        byte[] buffer = new byte[src.available()];
        src.read(buffer);
        src.close();
        File file = new File(destPath);
        OutputStream outStream = new FileOutputStream(file);
        outStream.write(buffer);
        outStream.close();
    }

    //Dodanie folderu
    public void addFolder(String folderName) {
        File directory;

        if (folderName != null && folderName.length() != 0) {
            directory = new File(getCurrentPath() + "\\" + folderName);
        } else throw new IllegalArgumentException("Folder name can't be null nor 0 length");

        if (!directory.mkdir()) {
            throw new IllegalArgumentException("Don't save a folder");
        }
    }

    //Zapisuje pliku do folderu użytkownika
    public void saveFilesToUserDirectory() throws IOException {
        File directoryTemp = new File(_pathTemp);
        if (directoryTemp.exists()) {
            File[] files = directoryTemp.listFiles();

            for (File file : files) {
                InputStream src = new FileInputStream(file);
                try {
                    copyFiles(src, getCurrentPath() + "\\" + file.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                file.delete();
            }
        }
        directoryTemp.delete();
    }

    //Metoda do pobierania rozmiaru pliku
    public long getFolderSize() {
        Path folder = Paths.get(_pathUser);
        try {
            long size = Files.walk(folder)
                    .filter(p -> p.toFile().isFile())
                    .mapToLong(p -> p.toFile().length())
                    .sum();

            final long MEGABYTE = 1024L * 1024L;

            return size / MEGABYTE;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    //Czyści cały folder z plików
    public void clear() {
        File directoryTemp = new File(_pathTemp);
        File[] allContents = directoryTemp.listFiles();

        if (allContents != null) {
            for (File file : allContents) {
                file.delete();
            }
        }
        directoryTemp.delete();
    }

    //Metoda do pobierania i wyśwwietlania na stronie odpowiedniej składni html wraz z wszystkimi plikami
    //użytkownika w danym folderze
    public String getAllFilesFromDir(String dir) {

        File[] files = getAllFiles();
        StringBuilder builder = new StringBuilder();

        if (files.length == 0) {
            builder.append("<p class=\"media-body pb-3 mt-2 mb-0 lh-125 border-bottom border-gray\">")
                    .append("Aktualnie nie przechowujesz żadnych plików w tym folderze")
                    .append("</p>");

            return builder.toString();
        } else {
            for (File file : files) {
                String fileType = file.getName().substring(file.getName().lastIndexOf(".") + 1);

                String fileIcon;
                switch (fileType) {
                    case "txt":
                        fileIcon = "file-alt-regular.svg";
                        break;
                    case "docx":
                    case "doc":
                        fileIcon = "file-word-regular.svg";
                        break;
                    case "pdf":
                        fileIcon = "file-pdf-regular.svg";
                        break;
                    case "mp4":
                    case "avi":
                    case "mov":
                    case "mkv":
                    case "flv":
                        fileIcon = "file-video-regular.svg";
                        break;
                    case "ogg":
                    case "wma":
                    case "alac":
                    case "mp3":
                    case "aac":
                    case "flac":
                    case "aiff":
                    case "wav":
                        fileIcon = "file-audio-regular.svg";
                        break;
                    case "xls":
                    case "xlsx":
                        fileIcon = "file-excel-regular.svg";
                        break;
                    case "png":
                    case "gif":
                    case "tif":
                    case "jpg":
                    case "raw":
                        fileIcon = "file-image-regular.svg";
                        break;
                    case "csv":
                        fileIcon = "file-csv-solid.svg";
                        break;
                    case "zip":
                    case "7z":
                    case "rar":
                    case "tar":
                        fileIcon = "file-archive-regular.svg";
                        break;
                    case "cs":
                    case "class":
                    case "vbx":
                    case "xq":
                    case "pwd":
                    case "idb":
                    case "playground":
                    case "cod":
                    case "cmd":
                    case "cpp":
                    case "c":
                    case "swift":
                    case "java":
                    case "vcproj":
                        fileIcon = "file-code-regular.svg";
                        break;
                    case "ppt":
                        fileIcon = "file-powerpoint-regular.svg";
                        break;
                    default:
                        fileIcon = "file-regular.svg";
                        break;
                }

                long length;

                if (file.isDirectory()) {
                    length = getFolderSize(file);
                } else {
                    length = file.length();
                }

                String sizeType = " MB";

                double roundSize = (double) length / (1024 * 1024);

                if (roundSize < 1) {
                    roundSize = (double) length / 1024;
                    sizeType = " KB";
                }

                roundSize *= 100;
                roundSize = Math.round(roundSize);
                roundSize /= 100;

                if (file.isDirectory()) {
                    fileIcon = "folder-solid.svg";
                    builder.append("<div class=\"media text-muted pt-3\">")
                            .append("<img src=\"images/")
                            .append(fileIcon)
                            .append("\" height=\"30\" width=\"30\" ")
                            .append("title=\"")
                            .append(fileType.toUpperCase())
                            .append("\" alt=\"")
                            .append(fileType.toUpperCase())
                            .append("\" >")
                            .append("</svg>")
                            .append("<p class=\"media-body pb-3 ml-2 mb-0 small lh-125 border-bottom border-gray\">")
                            .append("<a href=\"DeeperFolderServlet?param1=")
                            .append(file.getName())
                            .append("\">")
                            .append("<strong class=\"d-block text-gray-dark\">")
                            .append(file.getName())
                            .append("</strong>")
                            .append(roundSize)
                            .append(sizeType)
                            .append("</a>")
                            .append("</p>")
                            .append("<a class=\"btn btn-primary btn-sm mr-2\" href=\"DownloadFileServlet?file=")
                            .append(file.getName())
                            .append("\"><i class=\"fas fa-download\"></i> Pobierz</a>\n")
                            .append("<a class=\"btn btn-danger btn-sm\" href=\"#\" data-toggle=\"modal\" data-target=\"#deleteModal\" data-name=\"")
                            .append(file.getName())
                            .append("\"><i class=\"far fa-trash-alt\"></i> Usuń</a>\n")
                            .append("</div>");
                } else {
                    builder.append("<div class=\"media text-muted pt-3\">")
                            .append("<img src=\"images/")
                            .append(fileIcon)
                            .append("\" height=\"30\" width=\"30\" ")
                            .append("title=\"")
                            .append(fileType.toUpperCase())
                            .append("\" alt=\"")
                            .append(fileType.toUpperCase())
                            .append("\" >")
                            .append("</svg>")
                            .append("<p class=\"media-body pb-3 ml-2 mb-0 small lh-125 border-bottom border-gray\">")
                            .append("<strong class=\"d-block text-gray-dark\">")
                            .append(file.getName())
                            .append("</strong>")
                            .append(roundSize)
                            .append(sizeType)
                            .append("</p>")
                            .append("<a class=\"btn btn-primary btn-sm mr-2\" href=\"DownloadFileServlet?file=")
                            .append(file.getName())
                            .append("\"><i class=\"fas fa-download\"></i> Pobierz</a>\n");

                    if (file.getName().endsWith(".txt")) {
                        String userPath = _pathUser + "\\";
                        String filePath = file.getPath().substring(userPath.length());

                        builder.append("<a class=\"btn btn-success btn-sm mr-2\" href=\"/edittext.jsp?file=")
                                .append(filePath)
                                .append("\" ><i class=\"far fa-edit\"></i> Edytuj</a>\n");
                    }

                    builder.append("<a class=\"btn btn-danger btn-sm\" href=\"#\" data-toggle=\"modal\" data-target=\"#deleteModal\" data-name=\"")
                            .append(file.getName())
                            .append("\"><i class=\"far fa-trash-alt\"></i> Usuń</a>\n")
                            .append("</div>");
                }

            }
            return builder.toString();
        }
    }

    //Metoda do cofania się bądź wchodzenia głębiej hierarchii folderów
    public String getFoldersMenu() {
        ArrayList<String> arrayList = new ArrayList<>();

        String[] paths = getCurrentPath().split("\\\\");

        for (int i = paths.length - 1; i >= 2; i--) {
            if (paths[i].equals(_userName)) {
                arrayList.add("<li class=\"breadcrumb-item\"><img class=\"mr-2\" src=\"images/folder-solid.svg\" width=\"16\" height=\"16\" alt=\"\"><a href=\"UndoFolderServlet?param1=" + (i - 2) + "\">Folder Główny</a></li>");
            } else {
                arrayList.add("<li class=\"breadcrumb-item\"><img class=\"mr-2\" src=\"images/folder-solid.svg\" width=\"16\" height=\"16\" alt=\"\"><a href=\"UndoFolderServlet?param1=" + (i - 2) + "\">" + paths[i] + "</a></li>");
            }
        }

        StringBuilder builder = new StringBuilder();

        for (int i = arrayList.size() - 1; i >= 0; i--) {
            builder.append(arrayList.get(i));
        }

        int imageLength = "images/folder-solid.svg".length();
        int lastIndexOfImage = builder.toString().lastIndexOf("images/folder-solid.svg");

        builder.replace(lastIndexOfImage, (lastIndexOfImage + imageLength), "images/folder-open-solid.svg");

        return builder.toString();
    }

    //Metoda do pobierania rozmiaru całego folderu
    private long getFolderSize(File folder) {
        long length = 0;
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    length += file.length();
                } else {
                    length += getFolderSize(file);
                }
            }
        }
        return length;
    }

    //Metoda do pobierania ścieżki na serwerze danego usera
    public String getUserPath() {
        return _pathUser;
    }

    //Metoda do pobierania aktualnej ściężki folderu, na którą chce udać się użytkownik
    public String getCurrentPath() {
        return _pathUser + "\\" + _currentFolder;
    }

    //Pobiera wszystkich plików z obecnej lokalizacji
    public File[] getAllFiles() {
        return new File(getCurrentPath()).listFiles();
    }
}
