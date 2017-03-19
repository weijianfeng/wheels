package com.wjf.wrouter.processor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.WildcardTypeName;
import com.wjf.wrouter.Route;
import com.wjf.wrouter.util.Logger;

import org.apache.commons.collections4.CollectionUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import static javax.lang.model.element.Modifier.PUBLIC;

@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedAnnotationTypes({"com.wjf.wrouter.Route"})
public class RouteProcessor extends AbstractProcessor {

    private Filer mFiler;       // File util, write class file into disk.
    private Logger mLogger;
    private Types mTypes;
    private Elements mElements;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFiler = processingEnv.getFiler();
        mTypes = processingEnv.getTypeUtils();            // Get type utils.
        mElements = processingEnv.getElementUtils();      // Get class meta.
        mLogger = new Logger(processingEnv.getMessager());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        mLogger.info(">>> process init <<<");

        if (CollectionUtils.isNotEmpty(annotations)) {
            Set<? extends Element> routeElements = roundEnv.getElementsAnnotatedWith(Route.class);
            try {
                mLogger.info(">>> Found routes, start... <<<" + routeElements.size() + "  " + routeElements.toString());
                this.parseRoutes(routeElements);
            } catch (Exception e) {
                mLogger.error(e.toString());
            }
            return true;
        }
        return false;
    }

    private void parseRoutes(Set<? extends Element> routeElements) throws IOException{

        mLogger.info(">>> parseRoutes init <<<");

        if (CollectionUtils.isEmpty(routeElements)) {
            mLogger.info(">>> isEmpty<<<");
            return;
        }

        TypeMirror type_Activity = mElements.getTypeElement("android.app.Activity").asType();

        // interface
        TypeElement type_IRouter = mElements.getTypeElement("com.wjf.wrouter.template.IRouter");

        /*

        ```Map<String, Class<? extends Activity>>```
        */
        ParameterizedTypeName inputMapTypeOfRoute = ParameterizedTypeName.get(
                ClassName.get(Map.class),
                ClassName.get(String.class),
                ParameterizedTypeName.get(ClassName.get(Class.class),
                        WildcardTypeName.subtypeOf(ClassName.get(mElements.getTypeElement("android.app.Activity"))))
        );

        mLogger.info(">>> inputMapTypeOfRoute is"  + inputMapTypeOfRoute.toString() + "<<<");

        ParameterSpec routeParamSpec = ParameterSpec.builder(inputMapTypeOfRoute, "routes").build();

         /*
           Build method : 'loadInto'
         */
        MethodSpec.Builder loadIntoMethodOfRouteBuilder = MethodSpec.methodBuilder("loadInto")
                .addAnnotation(Override.class)
                .addModifiers(PUBLIC)
                .addParameter(routeParamSpec);

        for (Element element : routeElements) {
            TypeMirror tm = element.asType();
            Route route = element.getAnnotation(Route.class);

            if (mTypes.isSubtype(tm, type_Activity)) {
                loadIntoMethodOfRouteBuilder
                        .addStatement("routes.put($S,$T.class)",
                                route.path(),
                                ClassName.get((TypeElement) element));
            }
        }

        JavaFile.builder("com.router.test",
                TypeSpec.classBuilder("RouterMap")
                        .addSuperinterface(ClassName.get(type_IRouter))
                        .addModifiers(PUBLIC)
                        .addMethod(loadIntoMethodOfRouteBuilder.build())
                        .build()
        ).build().writeTo(mFiler);

        mLogger.info(">>> Generated RouterMap <<<");
    }
}
