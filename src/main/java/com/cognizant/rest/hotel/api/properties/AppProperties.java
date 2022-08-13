/**
 * 
 */
package com.cognizant.rest.hotel.api.properties;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author sudipta subhadarshin
 *
 */

@Component
@ConfigurationProperties(prefix="hotel")
@EnableConfigurationProperties
@Getter
@Setter
public class AppProperties {
	
	private  Map<String,String> properties=new HashMap<>();

}
