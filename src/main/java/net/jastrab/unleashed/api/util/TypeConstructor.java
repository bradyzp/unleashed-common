package net.jastrab.unleashed.api.util;

import net.jastrab.unleashed.api.http.UnleashedResponse;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public final class TypeConstructor {

    public static <T> ParameterizedType constructUnleashedResponse(Class<T> innerType) {
        return new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[]{innerType};
            }

            @Override
            public Type getRawType() {
                return UnleashedResponse.class;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }

            @Override
            public String toString() {
                return innerType.getName();
            }
        };
    }

}
