package focandlol.converter;

import focandlol.converter.code.OrganizationCode;
import focandlol.exception.error.ProductException;
import focandlol.exception.errorcode.ProductErrorCode;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class OrganizationConverter implements AttributeConverter<OrganizationCode, String> {
    @Override
    public String convertToDatabaseColumn(OrganizationCode code) {
        if (code == null) {
            return OrganizationCode.NONE.getCode();
        }
        return code.getCode();
    }

    @Override
    public OrganizationCode convertToEntityAttribute(String code) {
        if (code == null || code.isEmpty()) {
            return OrganizationCode.NONE;
        }
        return OrganizationCode.findOrganization(code);
    }
}
