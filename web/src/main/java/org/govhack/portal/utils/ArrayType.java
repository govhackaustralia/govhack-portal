package org.govhack.portal.utils;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.internal.util.compare.EqualsHelper;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ArrayType implements UserType, ParameterizedType {
    private static final Map<Class<?>, Integer> CODE_LOOKUP = new HashMap<Class<?>, Integer>() {{
        put(Long.class, 90002);
        put(String.class, 90003);
    }};

    private Class<?> typeClass;

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return cached;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return EqualsHelper.equals(x, y);
    }

    @Override
    public int hashCode(Object value) throws HibernateException {
        return value == null ? 0 : value.hashCode();
    }


    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }

    @Override
    public void setParameterValues(Properties parameters) {
        try {
            typeClass = Class.forName((String) parameters.get("type"));
        } catch (ClassNotFoundException e) {
            throw new UnsupportedOperationException("The user type needs to be configured with the type. None provided", e);
        }
    }

    @Override
    public Class<?> returnedClass() {
        return java.lang.reflect.Array.newInstance(typeClass, 0).getClass();
    }

    @Override
    public int[] sqlTypes() {
        Integer type = CODE_LOOKUP.get(typeClass);
        if (type != null) {
            return new int[]{type};
        }
        throw new UnsupportedOperationException("The type " + typeClass + " is not a valid type");
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {
        Object result = null;
        Class<?> typeArrayClass = java.lang.reflect.Array.newInstance(typeClass, 0).getClass();
        Array sqlArray = rs.getArray(names[0]);
        if (!rs.wasNull()) {
            Object array = sqlArray.getArray();
            result = typeArrayClass.cast(array);
        }
        return result;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
        if (value == null) {
            st.setNull(index, Types.ARRAY);
            return;
        }
        Class<?> typeArrayClass = java.lang.reflect.Array.newInstance(typeClass, 0).getClass();
        java.sql.Array array = st.getConnection().createArrayOf("varchar", (Object[]) typeArrayClass.cast((Object[]) value));
        st.setArray(index, array);
    }
}
