package tecolotl.nucleo.herramienta;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Herramientas para validar los objectos
 * @since 0.1
 * @author Antonio Francisco Alonso Valerdi
 */
@Singleton
public class ValidadorSessionBean {

    @Inject
    private Validator validator;

    @Inject
    private Logger logger;

    /**
     * Ejecuta las validaciones de un objecto conforme a un grupo de validaciones. Es importe remarcar que para {@link Level}
     * FINE sólo muestra el número de elemento, en el nivel FINER muestra cada unos de las validaciones.
     * @param objecto Objecto a ser validado.
     * @param grupos Grupo de campos a ser validados.
     * @param <T> Tipo de objecto a ser validado.
     * @exception ConstraintViolationException Conjunto de valores no validados.
     */
    public <T> void validacion(T objecto, Class<?>... grupos) {
        Set<ConstraintViolation<T>> constraintViolationConjunto = validator.validate(objecto, grupos);
        if (!constraintViolationConjunto.isEmpty()) {
            logger.fine("Validaciones incorrectas:".concat(String.valueOf(constraintViolationConjunto.size())));
            if (logger.isLoggable(Level.FINE)) {
                for (ConstraintViolation<T> constraintViolation : constraintViolationConjunto) {
                    logger.fine(String.format("Campo no validado: {0} con el valor {1}", constraintViolation.getPropertyPath(), constraintViolation.getMessage()));
                }
            }
            throw new ConstraintViolationException(constraintViolationConjunto);
        }
    }
}
