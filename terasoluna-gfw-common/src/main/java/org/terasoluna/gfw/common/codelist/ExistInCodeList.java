/*
 * Copyright (C) 2013-2014 terasoluna.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.terasoluna.gfw.common.codelist;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintViolation;
import javax.validation.Payload;

import org.terasoluna.gfw.common.codelist.validator.ExistInCodeListValidatorForCharacter;
import org.terasoluna.gfw.common.codelist.validator.ExistInCodeListValidatorForString;

/**
 * Custom annotation that provides the functionality to check the existence of a code in the specified codelist.
 * <p>
 * The annotation has the functionality to check whether a Code, which can be the <br>
 * value of field or return value of method to which the annotation is applied, exists in the {@link CodeList} bean specified as
 * argument to the annotation. <br>
 * 
 * <pre>
 * &#064;ExistInCodeList(codeListId = &quot;CD_GENDER&quot;)
 * private String gender;
 * </pre>
 * 
 * <br>
 * {@code CD_GENDER} is a {@link CodeList} bean defined in the bean definition file. <br>
 * In the above code, ExistInCodeList annotation checks whether the code in gender field <br>
 * exists in {@code CD_GENDER} {@link CodeList}. <br>
 * <br>
 * If the the code does not exist in the {@link CodeList}, an instance of {@link ConstraintViolation} is returned with the
 * default error message represented by {@code message()} in it.
 * </p>
 */
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ExistInCodeListValidatorForString.class,
        ExistInCodeListValidatorForCharacter.class })
public @interface ExistInCodeList {

    /**
     * Error message or message key
     * @return error message or message key
     */
    String message() default "{org.terasoluna.gfw.common.codelist.ExistInCodeList}";

    /**
     * Bean ID of the codelist in which check the value<br>
     * </p>
     * @return bean ID of the codelist
     */
    String codeListId();

    /**
     * Constraint groups
     * @return constraint groups
     */
    Class<?>[] groups() default {};

    /**
     * Payload
     * @return payload
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * Defines several <code>@ExistInCodeList</code> annotations on the same element
     * @see ExistInCodeList
     */
    @Target({ ElementType.METHOD, ElementType.FIELD,
            ElementType.ANNOTATION_TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        /**
         * <code>@ExistInCodeList</code> annotations
         * @return annotations
         */
        ExistInCodeList[] value();
    }
}
