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
import com.intellij.psi.stubs.IStubElementType;
import org.intellij.plugins.ceylon.psi.stub.ClassStub;

public class CeylonInterfaceDeclarationImpl extends CeylonClassImpl implements CeylonInterfaceDeclaration {

  public CeylonInterfaceDeclarationImpl(ASTNode node) {
    super(node);
  }

  public CeylonInterfaceDeclarationImpl(ClassStub stub, IStubElementType nodeType) {
    super(stub, nodeType);
  }

  @Override
  @Nullable
  public CeylonAdaptedTypes getAdaptedTypes() {
    return findChildByClass(CeylonAdaptedTypes.class);
  }

  @Override
  @Nullable
  public CeylonBlock getBlock() {
    return findChildByClass(CeylonBlock.class);
  }

  @Override
  @Nullable
  public CeylonCaseTypes getCaseTypes() {
    return findChildByClass(CeylonCaseTypes.class);
  }

  @Override
  @Nullable
  public CeylonSatisfiedTypes getSatisfiedTypes() {
    return findChildByClass(CeylonSatisfiedTypes.class);
  }

  @Override
  @Nullable
  public CeylonTypeConstraints getTypeConstraints() {
    return findChildByClass(CeylonTypeConstraints.class);
  }

  @Override
  @Nullable
  public CeylonTypeName getTypeName() {
    return findChildByClass(CeylonTypeName.class);
  }

  @Override
  @Nullable
  public CeylonTypeParameters getTypeParameters() {
    return findChildByClass(CeylonTypeParameters.class);
  }

  @Override
  @Nullable
  public CeylonTypeSpecifier getTypeSpecifier() {
    return findChildByClass(CeylonTypeSpecifier.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CeylonVisitor) ((CeylonVisitor)visitor).visitInterfaceDeclaration(this);
    else super.accept(visitor);
  }

}
