package org.springframework.roo.querydsl.processor;

import java.lang.annotation.Annotation;

import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;

import com.querydsl.apt.AbstractQuerydslProcessor;
import com.querydsl.apt.Configuration;
import com.querydsl.apt.DefaultConfiguration;
import com.querydsl.apt.jpa.JPAConfiguration;

/**
 * AnnotationProcessor for Spring Roo which takes {@link RooJpaEntity},
 * {@link MappedSuperclass}, {@link Embeddable} and {@link Transient} into account
 *
 * @author Paula Navarro
 * @author Manuel Iborra
 *
 */
@SupportedAnnotationTypes({"com.querydsl.core.annotations.*","javax.persistence.*","org.springframework.roo.addon.jpa.annotations.entity.*"})
public class RooAnnotationProcessor extends AbstractQuerydslProcessor {

  @Override
  protected Configuration createConfiguration(RoundEnvironment roundEnv) {
    Class<? extends Annotation> entity = RooJpaEntity.class;
    Class<? extends Annotation> superType = MappedSuperclass.class;
    Class<? extends Annotation> embeddable = Embeddable.class;
    Class<? extends Annotation> embedded = Embedded.class;
    Class<? extends Annotation> skip = Transient.class;
    DefaultConfiguration conf = new JPAConfiguration(roundEnv, processingEnv, processingEnv.getOptions(), entity,
        superType, embeddable, embedded, skip);
    return conf;
  }
}
