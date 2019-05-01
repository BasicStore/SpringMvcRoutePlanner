package com.routeplanner.shopping.converters;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import com.routeplanner.shopping.PassengerType;


@Converter
public class PassengerTypeConverter implements AttributeConverter<PassengerType, Integer> {

    public Integer convertToDatabaseColumn(PassengerType value) {
        if ( value == null ) {
            return null;
        }

        return value.getId();
    }

    public PassengerType convertToEntityAttribute(Integer value) {
        if ( value == null ) {
            return null;
        }

        return PassengerType.fromId(value);
    }
    
}