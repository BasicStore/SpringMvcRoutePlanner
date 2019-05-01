package com.routeplanner.shopping.converters;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import com.routeplanner.shopping.CardType;


@Converter
public class CardTypeConverter implements AttributeConverter<CardType, Integer> {

    public Integer convertToDatabaseColumn(CardType value) {
        if ( value == null ) {
            return null;
        }

        return value.getId();
    }

    public CardType convertToEntityAttribute(Integer value) {
        if ( value == null ) {
            return null;
        }

        return CardType.fromId(value);
    }
    
}
