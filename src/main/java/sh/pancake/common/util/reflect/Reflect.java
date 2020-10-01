/*
 * Created on Sat Sep 26 2020
 *
 * Copyright (c) storycraft. Licensed under the MIT Licence.
 */

package sh.pancake.common.util.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Reflect {

    public static <T, C> WrappedField<T, C> getField(Class<C> c, String name) throws Exception {
        return Reflect.<T, C>getField(c, null, name);
    }

    public static <T, C> WrappedField<T, C> getField(Class<C> c, Object obj, String name) throws Exception {
        Field field = getDeclaredField(c, name);

        return new WrappedField<>(field);
    }

    private static Field getDeclaredField(Class<?> c, String name) throws Exception {
        Field field = c.getDeclaredField(name);
        field.setAccessible(true);

        return field;
    }

    public static <T, C> WrappedMethod<T, C> getMethod(Class<C> c, String name, Class<?>... params) throws Exception {
        return Reflect.<T, C>getMethod(c, null, name, params);
    }

    public static <T, C> WrappedMethod<T, C> getMethod(Class<C> c, Object obj, String name, Class<?>... params) throws Exception {
        Method method = getDeclaredMethod(c, name, params);

        return new WrappedMethod<>(method);
    }

    public static <T> WrappedConstructor<T> getConstructor(Class<T> c, Class<?>... params) throws Exception {
        Constructor<T> constructor = getDeclaredConstructor(c, params);

        return new WrappedConstructor<>(constructor);
    }

    private static Method getDeclaredMethod(Class<?> c, String name, Class<?>... classes) throws Exception {
        Method method = c.getDeclaredMethod(name, classes);
        method.setAccessible(true);

        return method;
    }

    private static <T> Constructor<T> getDeclaredConstructor(Class<T> c, Class<?>... classes) throws Exception {
        Constructor<T> constructor = c.getDeclaredConstructor(classes);
        constructor.setAccessible(true);

        return constructor;
    }

    public static class WrappedField<T, C> {

        private static Field modifiersField;
        
        static {
            try {
                modifiersField = Field.class.getDeclaredField("modifiers");
                modifiersField.setAccessible(true);
            } catch (NoSuchFieldException | SecurityException e) {
                e.printStackTrace();
            }
            
        }

        private Field field;
        private boolean finalLocked;

        public WrappedField(Field field) {
            this.field = field;
            this.finalLocked = false;
        }

        public Field getField() {
            return field;
        }

        public String getName() {
            return field.getName();
        }

        public T get(C object) {
            try {
                return (T) field.get(object);
            } catch (Exception e) {

            }

            return null;
        }

        public boolean set(C object, T value) {
            try {
                field.set(object, value);

                return true;
            } catch (Exception e) {
                
            }

            return false;
        }

        public boolean isFinalLocked() {
            return finalLocked;
        }

        public void unlockFinal() {
            if (!isFinalLocked())
                return;

            try {
                modifiersField.setInt(getField(), getField().getModifiers() & ~Modifier.FINAL);
                finalLocked = true;
            } catch (IllegalArgumentException | IllegalAccessException e) {
                System.out.println("Error to unlock final " + getName() + " : " + e.getMessage());
                e.printStackTrace();
            }
        }

    }

    public static class WrappedMethod<T, C> {

        private Method method;

        public WrappedMethod(Method method) {
            this.method = method;
        }

        public Method getMethod() {
            return method;
        }

        public String getName() {
            return method.getName();
        }

        public T invoke(C object, Object... objects) {
            try {
                return (T) method.invoke(object, objects);
            } catch (Exception e) {
                
            }

            return null;
        }
    }

    public static class WrappedConstructor<T> {

        private Constructor<T> constructor;

        public WrappedConstructor(Constructor<T> constructor){
            this.constructor = constructor;
        }

        public Constructor<T> getConstructor() {
            return constructor;
        }

        public String getName() {
            return constructor.getName();
        }

        public T createNew(Object... objects) {
            try {
                return constructor.newInstance(objects);
            } catch (Exception e) {
                
            }

            return null;
        }
    }
}
