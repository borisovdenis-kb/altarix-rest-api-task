package ru.intodayer.altarixrestapitask.models.attributeconverters;

import ru.intodayer.altarixrestapitask.models.Gender;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class GenderAttributeConverter implements AttributeConverter<Gender, String> {
    @Override
    public String convertToDatabaseColumn(Gender gender) {
        return (gender == null ? null : gender.getShortName());
    }

    @Override
    public Gender convertToEntityAttribute(String dbData) {
        return (dbData == null ? null : Gender.stringToEnum(dbData));
    }
}
