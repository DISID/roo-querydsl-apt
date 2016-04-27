package org.springframework.roo.querydsl.processor;

import java.lang.annotation.Annotation;

import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;

import com.mysema.query.apt.AbstractQuerydslProcessor;
import com.mysema.query.apt.Configuration;
import com.mysema.query.apt.DefaultConfiguration;
import com.mysema.query.apt.jpa.JPAConfiguration;

/**
 * AnnotationProcessor for Spring Roo which takes {@link RooJpaEntity},
 * {@link MappedSuperclass}, {@link Embeddable} and {@link Transient} into account
 *
 * @author Paula Navarro
 *
 */
@SupportedAnnotationTypes({"com.mysema.query.annotations .*", "javax.persistence.*",
    "org.springframework.roo.addon.jpa.annotations.entity.*"})
public class RooAnnotationProcessor extends AbstractQuerydslProcessor {

  @Override
  protected Configuration createConfiguration(RoundEnvironment roundEnv) {
    Class<? extends Annotation> entity = RooJpaEntity.class;
    Class<? extends Annotation> superType = MappedSuperclass.class;
    Class<? extends Annotation> embeddable = Embeddable.class;
    Class<? extends Annotation> embedded = Embedded.class;
    Class<? extends Annotation> skip = Transient.class;
    DefaultConfiguration conf = new JPAConfiguration(roundEnv, processingEnv.getOptions(), entity,
        superType, embeddable, embedded, skip);
    return conf;
  }
}

