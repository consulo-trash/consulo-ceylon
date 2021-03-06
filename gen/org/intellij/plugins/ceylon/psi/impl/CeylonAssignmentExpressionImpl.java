// This is a generated file. Not intended for manual editing.
package org.intellij.plugins.ceylon.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.intellij.plugins.ceylon.psi.CeylonTypes.*;
import org.intellij.plugins.ceylon.psi.*;

public class CeylonAssignmentExpressionImpl extends CeylonCompositeElementImpl implements CeylonAssignmentExpression {

  public CeylonAssignmentExpressionImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public CeylonAssignmentExpression getAssignmentExpression() {
    return findChildByClass(CeylonAssignmentExpression.class);
  }

  @Override
  @Nullable
  public CeylonAssignmentOperator getAssignmentOperator() {
    return findChildByClass(CeylonAssignmentOperator.class);
  }

  @Override
  @NotNull
  public CeylonThenElseExpression getThenElseExpression() {
    return findNotNullChildByClass(CeylonThenElseExpression.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CeylonVisitor) ((CeylonVisitor)visitor).visitAssignmentExpression(this);
    else super.accept(visitor);
  }

}
