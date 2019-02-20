
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.PositionProcession;

@Component
@Transactional
public class PositionProcessionToStringConverter implements Converter<PositionProcession, String> {

	@Override
	public String convert(final PositionProcession positionProcession) {
		String result;

		if (positionProcession == null)
			result = null;
		else
			result = String.valueOf(positionProcession.getId());
		return result;
	}

}
