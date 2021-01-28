package uk.co.adamhaas.AndroidWebToolkit.Service.Models;

public class FileSystemItem {
    private String itemName;
    private boolean isDirectory;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }
}
