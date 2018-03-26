package br.com.studiotrek.impactaservice.util;

import br.com.studiotrek.impactaservice.ImpactaserviceApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Inject {

    private Inject() {

    }

    public static AnnotationConfigApplicationContext getContext() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan(ImpactaserviceApplication.class.getPackage().getName());
        context.refresh();

        return context;
    }

}
