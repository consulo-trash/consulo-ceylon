// This is a generated file. Not intended for manual editing.
package org.intellij.plugins.ceylon.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface CeylonStatement extends CeylonCompositeElement {

  @Nullable
  CeylonControlStatement getControlStatement();

  @Nullable
  CeylonDirectiveStatement getDirectiveStatement();

  @Nullable
  CeylonExpressionOrSpecificationStatement getExpressionOrSpecificationStatement();

}
