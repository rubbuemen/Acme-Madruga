
package converters;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.PositionProcessionRepository;
import domain.PositionProcession;

@Component
@Transactional
public class StringToPositionProcessionConverter implements Converter<String, PositionProcession> {

	@Autowired
	PositionProcessionRepository	positionProcessionRepository;


	@Override
	public PositionProcession convert(final String text) {
		PositionProcession result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.positionProcessionRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
