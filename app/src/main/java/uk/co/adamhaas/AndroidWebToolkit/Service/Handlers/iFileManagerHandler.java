package uk.co.adamhaas.AndroidWebToolkit.Service.Handlers;


import uk.co.adamhaas.AndroidWebToolkit.Service.Models.FileSystemItem;

public interface iFileManagerHandler {
    FileSystemItem[] getFiles(String path);
}
