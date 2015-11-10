package cz.mikrobestie.idea.vaadin.declarative;

import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Michal on 9.11.2015.
 */
public class VaadinDesignFileTypeFactory extends FileTypeFactory {

    @Override
    public void createFileTypes(@NotNull FileTypeConsumer fileTypeConsumer) {
        fileTypeConsumer.consume(VaadinDesignFileType.INSTANCE);
    }
}
