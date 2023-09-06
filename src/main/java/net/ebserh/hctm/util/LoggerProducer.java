package net.ebserh.hctm.util;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class LoggerProducer {
	
	@Produces
	private Logger createLogger(InjectionPoint injectionPoint) {
		return Logger.getLogger(injectionPoint
				.getMember()
				.getDeclaringClass()
				.getName());
	}

}
