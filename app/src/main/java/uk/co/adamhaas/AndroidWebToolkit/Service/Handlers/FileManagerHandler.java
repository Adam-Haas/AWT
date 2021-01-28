package uk.co.adamhaas.AndroidWebToolkit.Service.Handlers;

import java.io.File;

import uk.co.adamhaas.AndroidWebToolkit.Service.Models.FileSystemItem;

public class FileManagerHandler implements iFileManagerHandler {
    @Override
    public FileSystemItem[] getFiles(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        FileSystemItem[] returnObj = new FileSystemItem[files.length];
        for (int i = 0; i < files.length; i++) {
            int finalI = i;
            returnObj[i] = new FileSystemItem() {
               {
                   setItemName(files[finalI].getName());
                   setDirectory(files[finalI].isDirectory());
               }
           };
        }
        return returnObj;
    }
}
