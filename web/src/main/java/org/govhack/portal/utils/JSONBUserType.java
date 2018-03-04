package org.govhack.portal.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.internal.util.ReflectHelper;
import org.hibernate.internal.util.SerializationHelper;
import org.hibernate.internal.util.compare.EqualsHelper;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;
import org.postgresql.util.PGobject;

import java.io.IOException;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

public class JSONBUserType implements ParameterizedType, UserType {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    public static final String CLASS = "CLASS";
    private Class returnedClass;

    @Override
    public Class<Object> returnedClass() {
        return Object.class;
    }

    @Override
    public int[] sqlTypes() {
        return new int[]{Types.JAVA_OBJECT};
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {
        try {
            final String json = rs.getString(names[0]);
            return json == null
                    ? null
                    : MAPPER.readValue(json, returnedClass);
        } catch (IOException ex) {
            throw new HibernateException(ex);
        }
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
        try {
            final String json = value == null ? null : MAPPER.writeValueAsString(value);
            PGobject x = new PGobject();
            x.setType("jsonb");
            x.setValue(json);
            st.setObject(index, x);
        } catch (JsonProcessingException ex) {
            throw new HibernateException(ex);
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public Object deepCopy(Object value) throws HibernateException {
        if (value == null) return null;
        Preconditions.checkArgument(value instanceof Serializable);
        return SerializationHelper.clone((Serializable) value);
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return EqualsHelper.equals(x, y);
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        Preconditions.checkNotNull(x);
        return x.hashCode();
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return deepCopy(cached);
    }

    /**
     * Disassembles the object in preparation for serialization.
     * See {@link UserType#disassemble(Object)}.
     * <p>
     * Expects {@link #deepCopy(Object)} to return a {@code Serializable}.
     * <strong>Subtypes whose {@code deepCopy} implementation returns a
     * non-serializable object must override this method.</strong>
     */
    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        if (value == null) return null;
        Preconditions.checkArgument(value instanceof Serializable);
        return (Serializable) SerializationHelper.clone((Serializable) value);
    }

    @Override
    public Object replace(Object original, Object target, Object owner)
            throws HibernateException {
        // also safe for mutable objects
        return deepCopy(original);
    }

    @Override
    public void setParameterValues(Properties parameters) {
        final String clazz = (String) parameters.get(CLASS);
        try {
            returnedClass = ReflectHelper.classForName(clazz, this.getClass());
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Class: " + clazz
                    + " is not a known class type.", e);
        }
    }

}
