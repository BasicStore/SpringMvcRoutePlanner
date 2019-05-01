package com.routeplanner.shopping.converters;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import com.routeplanner.shopping.TicketType;

@Converter
public class TicketTypeConverter implements AttributeConverter<TicketType, Integer> {

    public Integer convertToDatabaseColumn(TicketType value) {
        if ( value == null ) {
            return null;
        }

        return value.getId();
    }

    public TicketType convertToEntityAttribute(Integer value) {
        if ( value == null ) {
            return null;
        }

        return TicketType.fromId(value);
    }
}