package liufeng.jdk.jvm.box;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementScanner6;
import javax.tools.Diagnostic;
import java.util.EnumSet;
import java.util.Set;

// 所有的注解
@SupportedAnnotationTypes("*")
// 支持JDK1.6
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class NameCheckProcessor extends AbstractProcessor {
    private NameChecker nameChecker;

    public void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        nameChecker = new NameChecker(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {
            for (Element element : roundEnv.getRootElements()) {
                nameChecker.checkNames(element);
            }
        }
        return false;
    }

    static class NameChecker {
        private final Messager messager;
        NameCheckScanner nameCheckScanner = new NameCheckScanner();

        NameChecker(ProcessingEnvironment processingEnv) {
            this.messager = processingEnv.getMessager();
        }

        public void checkNames(Element element) {
            nameCheckScanner.scan(element);
        }

        /**
         * 将以visitor模式访问抽象语法树中的元素
         */
        private class NameCheckScanner extends ElementScanner6<Void, Void> {
            /**
             * 检查Java类
             */
            @Override
            public Void visitType(TypeElement e, Void p) {
                scan(e.getTypeParameters(), p);
                checkCamelCase(e, true);
                super.visitType(e, p);
                return null;
            }

            /**
             * 检查方法命名是否合法
             */
            @Override
            public Void visitExecutable(ExecutableElement e, Void p) {
                if (e.getKind() == ElementKind.METHOD) {
                    Name simpleName = e.getSimpleName();
                    if (simpleName.contentEquals(e.getEnclosingElement().getSimpleName())) {
                        messager.printMessage(Diagnostic.Kind.WARNING, String.format("普通方法%s 不应该与类名重复，避免与构造函数产生混淆 %s",
                                e.getEnclosingElement().getSimpleName(), e));
                        checkCamelCase(e, false);
                    }
                }
                super.visitExecutable(e, p);
                return null;
            }

            /**
             * 检查变量命名是否合法
             */
            @Override
            public Void visitVariable(VariableElement e, Void p) {
                // 如果是枚举或常量，按大写命名检查，否则按照驼峰检查
                if (e.getKind() == ElementKind.ENUM_CONSTANT || e.getConstantValue() != null || heuristicallyConstant(e)) {
                    checkAllCaps(e);
                } else {
                    checkCamelCase(e, false);
                }
                return null;
            }


            /**
             * 判断类是否常量
             */
            private boolean heuristicallyConstant(VariableElement e) {
                if (e.getEnclosingElement().getKind() == ElementKind.INTERFACE) {
                    return true;
                } else if (e.getKind() == ElementKind.FIELD || e.getModifiers()
                        .containsAll(EnumSet.of(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL))) {
                    return true;
                } else {
                    return false;
                }


            }

            /**
             * 校验方法是否小驼峰
             */
            private void checkCamelCase(Element e, boolean b) {
            }

            /**
             * 检查是否大写字母和下划线
             */
            private void checkAllCaps(Element e) {
                String name = e.getSimpleName().toString();
                boolean conventional = true;
                int firstCodePoint = name.codePointAt(0);
                if (!Character.isUpperCase(firstCodePoint)) {
                    conventional = false;
                } else {
                    boolean previousUnderscore = false;
                    int cp = firstCodePoint;
                    for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)) {
                        cp = name.codePointAt(i);
                        if (cp == (int) '_') {
                            if (previousUnderscore) {
                                conventional = false;
                                break;
                            }
                            previousUnderscore = true;
                        } else {
                            previousUnderscore = false;
                            if (!Character.isUpperCase(cp) && !Character.isDigit(cp)) {
                                conventional = false;
                                break;
                            }
                        }
                    }
                }

                if (!conventional) {
                    messager.printMessage(Diagnostic.Kind.WARNING, String.format("常量%s应当全部以大写字母或下划线命名，并以字母开头 %s", name, e));
                }
            }


        }
    }
}
