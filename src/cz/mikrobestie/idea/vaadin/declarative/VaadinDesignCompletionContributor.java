package cz.mikrobestie.idea.vaadin.declarative;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import cz.mikrobestie.idea.vaadin.declarative.icons.FontAwesomeIcon;
import cz.mikrobestie.idea.vaadin.declarative.icons.VaadinIcons;
import cz.mikrobestie.idea.vaadin.declarative.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Michal on 18.11.2015.
 */
public class VaadinDesignCompletionContributor extends CompletionContributor {

    public VaadinDesignCompletionContributor() {

        // Element name completion
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(VDTypes.ELEM_NAME).withLanguage(VaadinDeclarativeLanguage.INSTANCE),
                new ComponentNameProvider());

        // Local id attribute name completion
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(VDTypes.ATTR_NAME).withLanguage(VaadinDeclarativeLanguage.INSTANCE),
                new AttributeNameProvider());

        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(VDTypes.ATTR).withLanguage(VaadinDeclarativeLanguage.INSTANCE),
                new DebugCompletionProvider());

        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(VDTypes.ATTR_VALUE).withLanguage(VaadinDeclarativeLanguage.INSTANCE),
                new AttributeValueProvider());

        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(VDTypes.EL_RIGHT).withLanguage(VaadinDeclarativeLanguage.INSTANCE),
                new DebugCompletionProvider());
    }

    /** appears as space but I don't flip to a dot like real space char in parse tree */
    public static final char DUMMY_IDENTIFIER = 'x';

    @Override
    public void beforeCompletion(@NotNull CompletionInitializationContext context) {
        context.setDummyIdentifier(String.valueOf(DUMMY_IDENTIFIER));
    }


    /**
     * Element names, derived from class names in defined packages.
     */
    class ComponentNameProvider extends CompletionProvider<CompletionParameters> {

        @Override
        protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {

            System.out.println("Component name completion: " + parameters.getPosition() + " < " + parameters.getPosition().getParent());

            // Find default Vaadin components
            VaadinDesignFile file = (VaadinDesignFile) parameters.getOriginalFile();
            List<PsiClass> vaadinComponents = PluginUtils.findClasses(file.getProject(), "com.vaadin.ui", "com.vaadin.ui.Component");
            for (PsiClass component : vaadinComponents) {

                LookupElementBuilder offer = LookupElementBuilder.create(VaadinUtils.getClassDesignName(component, "v"))
                        .appendTailText(" " + component.getQualifiedName(), true)
                        .withIcon(VaadinIcons.VAADIN_16);
                result.addElement(offer);
            }

            // Find additional components
            for (VaadinDesignFile.PackageDefinition pkg : file.getPackages()) {

                List<PsiClass> customComponents = PluginUtils.findClasses(file.getProject(), pkg.getPackageName(), "com.vaadin.ui.Component");
                for (PsiClass component : customComponents) {

                    LookupElementBuilder offer = LookupElementBuilder.create(VaadinUtils.getClassDesignName(component, pkg.getPrefix()))
                            .appendTailText(" " + component.getQualifiedName(), true)
                            .withIcon(AllIcons.Nodes.Class);
                    result.addElement(offer);
                }

            }
        }
    }

    /**
     * Attribute names.
     */
    class AttributeNameProvider extends CompletionProvider<CompletionParameters> {

        @Override
        protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {

            PsiElement attrName = parameters.getPosition();
            PsiElement element = attrName.getParent();

            System.out.println("Attr name completion: " + attrName + " < " + element);

            // "" completion
            if (attrName.getText().endsWith("=")) {
                result.addElement(LookupElementBuilder.create("\"\""));
                return;
            }

            // Extract real parent
            if (element instanceof VDAttr) {
                element = element.getParent();
            }

            // Switch by containing element
            if (element instanceof VDMetaTag) {

                List<String> attrs = ((VDMetaTag) element).getAttrList().stream().map(VDAttrHelper::getName).collect(Collectors.toList());
                if (!attrs.contains("name")) {

                    // <meta name="package-mapping"
                    result.addElement(LookupElementBuilder.create("name=\"package-mapping\"")
                            .appendTailText(" The only meta element", true)
                            .withIcon(VaadinIcons.VAADIN_16));
                }
                if (!attrs.contains("content")) {

                    // <meta content="x:a.b.c"
                    result.addElement(LookupElementBuilder.create("content")
                            .withInsertHandler(new XmlAttributeInsertHandler())
                            .appendTailText(" Enables usage of custom components", true)
                            .withIcon(VaadinIcons.VAADIN_16));
                }
            } else if (element instanceof VDComponent) {

                VDComponent component = (VDComponent) element;
                List<String> attrs = component.getAttrList().stream().map(VDAttrHelper::getName).collect(Collectors.toList());
                if (!attrs.contains("_id")) {

                    // Local id
                    result.addElement(LookupElementBuilder.create("_id")
                            .withInsertHandler(new XmlAttributeInsertHandler())
                            .appendTailText(" Binds design to field in java class", true)
                            .withIcon(VaadinIcons.VAADIN_16));
                }

                String className = component.getComponentClassName();
                if (className != null) {
                    Map<String, PsiMethod> setters = VaadinUtils.getClassUsableSetters(component.getProject(), className);
                    for (Map.Entry<String, PsiMethod> entry : setters.entrySet()) {

                        if (!attrs.contains(entry.getKey())) {

                            // Basic setter mapping
                            LookupElementBuilder builder = LookupElementBuilder.create(entry.getKey())
                                    .appendTailText(" " + entry.getValue().getText(), true)
                                    .withIcon(VaadinIcons.VAADIN_16);

                            // Non-boolean will have value by default
                            if (!"boolean".equals(entry.getValue().getParameterList().getParameters()[0].getType().getCanonicalText())) {
                                builder = builder.withInsertHandler(new XmlAttributeInsertHandler());
                            }

                            // Local id
                            result.addElement(builder);
                        }
                    }
                }
            }
        }
    }

    /**
     * Static string completion.
     */
    class DebugCompletionProvider extends CompletionProvider<CompletionParameters> {

        @Override
        protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {
            System.out.println("Completion: " + parameters.getPosition() + " < " + parameters.getPosition().getParent());
        }
    }

    /**
     * Attribute names.
     */
    class AttributeValueProvider extends CompletionProvider<CompletionParameters> {

        @Override
        protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {

            PsiElement attrValue = parameters.getPosition();
            PsiElement parent = attrValue.getParent();

            System.out.println("Attr value completion: " + parent + " -> " + attrValue);

            // Real attribute
            if (parent instanceof VDAttr) {

                VDAttr attr = (VDAttr) parent;
                PsiElement element = parent.getParent();
                if (element instanceof VDMetaTag) {

                } else {
                    if (element instanceof VDComponent) {

                        String name = attr.getName();
                        String className = ((VDComponent) element).getComponentClassName();
                        if ("_id".equals(name)) {

                        }

                        // Find by type
                        String setterName = VaadinUtils.capitalizeSetter(name);
                        PsiMethod setter = PluginUtils.findClassSetter(element.getProject(), className, setterName);
                        if (setter != null) {

                            PsiType type = setter.getParameterList().getParameters()[0].getType();
                            if (type instanceof PsiClassType) {
                                PsiClass aClass = ((PsiClassType) type).resolve();
                                if (aClass.isEnum()) {
                                    for (PsiField field : aClass.getFields()) {
                                        if (field instanceof PsiEnumConstant) {
                                            result.addElement(LookupElementBuilder.create(field.getName()));
                                        }
                                    }
                                    return;
                                }
                            }

                            switch (type.getCanonicalText()) {

                                case "com.vaadin.server.Resource":

                                    // FontAwesome autocomplete
                                    PsiClass faClass = PluginUtils.findClass(element.getProject(), "com.vaadin.server.FontAwesome");
                                    PsiField[] allFields = faClass.getAllFields();
                                    for (PsiField icon : allFields) {

                                        String text = icon.getText();
                                        if (text.contains("('")) {

                                            String unicode = text.substring(text.length() - 6, text.length() - 2);
                                            int codepoint = Integer.parseInt(unicode, 16);

                                            LookupElementBuilder builder = LookupElementBuilder.create("font://" + icon.getName())
                                                    .withIcon(new FontAwesomeIcon(codepoint, 14));
                                            result.addElement(builder);
                                        }
                                        System.out.println(text);

                                    }
                                    break;

                                case "boolean":
                                case "java.lang.Boolean":
                                    result.addElement(LookupElementBuilder.create("true"));
                                    result.addElement(LookupElementBuilder.create("false"));
                                    break;

                            }
                        }
                    }
                }
            }
        }
    }
}
