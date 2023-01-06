package br.com.deveficientemercadolivre.compartilhado;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExistePorCampoValidator implements ConstraintValidator<ExistePorCampo, Object> {
    private String atributo;
    private Class<?> classe;
    @PersistenceContext
    private EntityManager manager;


    @Override
    public void initialize(ExistePorCampo parametros) {
        this.atributo = parametros.campo();
        this.classe = parametros.classeDominio();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        var query = manager.createQuery("SELECT 1 FROM " + this.classe.getName() + " WHERE " + this.atributo + "=?1")
                .setParameter(1, value);
        return !query.getResultList().isEmpty();
    }
}
