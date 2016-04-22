package cz.mikrobestie.idea.vaadin.declarative.formatting;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.formatter.common.AbstractBlock;
import cz.mikrobestie.idea.vaadin.declarative.psi.VDTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michal on 29.11.2015.
 */
public class VDBlock extends AbstractBlock {

    private SpacingBuilder spacingBuilder;

    protected VDBlock(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment, SpacingBuilder spacingBuilder) {
        super(node, wrap, alignment);
        this.spacingBuilder = spacingBuilder;
    }

    @Override
    protected List<Block> buildChildren() {
        List<Block> blocks = new ArrayList<>();
        ASTNode child = myNode.getFirstChildNode();
        while (child != null) {
            if (child.getElementType() != TokenType.WHITE_SPACE) {
                Block block = new VDBlock(child, Wrap.createWrap(WrapType.NONE, false), Alignment.createAlignment(), spacingBuilder);
                blocks.add(block);
            }
            child = child.getTreeNext();
        }
        return blocks;
    }

    @Override
    public Indent getIndent() {
        if (myNode.getElementType() == VDTypes.COMPONENT) {
            return Indent.getNormalIndent();
        } else if (myNode.getElementType() == VDTypes.META_TAG) {
            return Indent.getNormalIndent();
        } else if (myNode.getElementType() == VDTypes.ATTR) {
            return Indent.getContinuationIndent(true);
        }
        return Indent.getNoneIndent();
    }

    @Nullable
    @Override
    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
        return spacingBuilder.getSpacing(this, child1, child2);
    }

    @Override
    public boolean isLeaf() {
        return myNode.getFirstChildNode() == null;
    }
}
