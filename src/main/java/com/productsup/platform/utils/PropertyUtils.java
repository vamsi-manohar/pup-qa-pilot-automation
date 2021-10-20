package com.productsup.platform.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import com.productsup.platform.constants.Constants;
import com.productsup.platform.enums.ConfigProperties;
import com.productsup.platform.exceptions.PropertyFileException;

public final class PropertyUtils {

	private PropertyUtils() {

	}

	private static Properties property = new Properties();
	private static final Map<String, String> CONFIG_MAP = new HashMap<>();

	static {
		try (FileInputStream fileInputStream = new FileInputStream(Constants.getConfigFilePath())) {

			property.load(fileInputStream);

			for (Map.Entry<Object, Object> entry : property.entrySet()) {
				CONFIG_MAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()).trim());
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}

	}

	public static String getValue(ConfigProperties key) {
		if (Objects.isNull(key.name()) || Objects.isNull(CONFIG_MAP.get(key.name()))) {
			throw new PropertyFileException(
					"Property name " + key + " is not found. Please check config.properties file");
		}

		return CONFIG_MAP.get(key.name());
	}

}
