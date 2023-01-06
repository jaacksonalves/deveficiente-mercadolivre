package br.com.deveficientemercadolivre.compartilhado;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {ExistePorCampoValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface ExistePorCampo {

    String message() default "Objeto não existe";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String campo();

    Class<?> classeDominio();
}
