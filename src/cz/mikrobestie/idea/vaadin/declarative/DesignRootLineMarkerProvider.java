package cz.mikrobestie.idea.vaadin.declarative;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.psi.*;
import cz.mikrobestie.idea.vaadin.declarative.icons.VaadinIcons;
import cz.mikrobestie.idea.vaadin.declarative.psi.VaadinDesignFile;
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
            VaadinDesignFile designFile = VaadinUtils.getFileByDesignRootAnnotation(designRoot);
            if (designFile != null) {
                NavigationGutterIconBuilder<PsiElement> builder =
                        NavigationGutterIconBuilder.create(VaadinIcons.VAADIN_16)
                                .setTarget(designFile)
                                .setTooltipText("Go to design file: " + designFile.getName());
                result.add(builder.createLineMarkerInfo(element));
            }
        }
    }
}
