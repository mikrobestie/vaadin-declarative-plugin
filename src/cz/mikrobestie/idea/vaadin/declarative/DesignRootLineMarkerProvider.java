package cz.mikrobestie.idea.vaadin.declarative;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.psi.*;
import cz.mikrobestie.idea.vaadin.declarative.icons.VaadinIcons;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * Created by Michal on 9.11.2015.
 */
public class DesignRootLineMarkerProvider extends RelatedItemLineMarkerProvider {

    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result) {

        if (element instanceof PsiAnnotation && "com.vaadin.annotations.DesignRoot".equals(((PsiAnnotation) element).getQualifiedName())) {

            PsiAnnotation designRoot = (PsiAnnotation) element;
            PsiJavaFile javaFile = (PsiJavaFile) element.getContainingFile();

            // Find file name from annotation
            PsiAnnotationMemberValue attributeValue = designRoot.findAttributeValue("value");
            String designFileName = attributeValue == null || attributeValue.getText().equals("\"\"") ?
                    javaFile.getName().replace(".java", ".html") : attributeValue.getText().substring(1, attributeValue.getTextLength() - 1);

            // Find file
            PsiFile designFile = PluginUtils.findResourcePsiFile(element.getProject(), javaFile.getPackageName(), designFileName);

            NavigationGutterIconBuilder<PsiElement> builder =
                    NavigationGutterIconBuilder.create(VaadinIcons.VAADIN_16)
                            .setTarget(designFile)
                            .setTooltipText("Go to design file: " + designFileName);
            result.add(builder.createLineMarkerInfo(element));
        }
    }
}
