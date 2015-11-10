package cz.mikrobestie.idea.vaadin.declarative;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiJavaCodeReferenceElement;
import cz.mikrobestie.idea.vaadin.declarative.icons.VaadinIcons;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * Created by Michal on 9.11.2015.
 */
public class DesignRootLineMarkerProvider extends RelatedItemLineMarkerProvider {
    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result) {

        if (element instanceof PsiJavaCodeReferenceElement) {

            if (element.getText().equals("DesignRoot")) {

                NavigationGutterIconBuilder<PsiElement> builder =
                        NavigationGutterIconBuilder.create(VaadinIcons.VAADIN_16)
                                .setTarget(element)
                                .setTooltipText("Go To design file");
                result.add(builder.createLineMarkerInfo(element));
            }
        }
    }
}
