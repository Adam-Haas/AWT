package uk.co.adamhaas.AndroidWebToolkit;

import com.x5.template.Theme;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import uk.co.adamhaas.AndroidWebToolkit.Service.Handlers.iFileManagerHandler;
import uk.co.adamhaas.AndroidWebToolkit.Service.Models.FileSystemItem;

@RunWith(RobolectricTestRunner.class)
public class TestTemplating {
    @Test
    public void TestObjectTemplating() {
        Theme theme = new Theme("main");
        iFileManagerHandler fileManagerHandler = new iFileManagerHandler() {
            @Override
            public FileSystemItem[] getFiles(String path) {
                return new FileSystemItem[0] {

                };
            }
        }
    }
}
