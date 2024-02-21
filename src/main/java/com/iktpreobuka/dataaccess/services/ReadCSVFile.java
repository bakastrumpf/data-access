package com.iktpreobuka.dataaccess.services;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.dataaccess.entities.UserEntity;
import com.iktpreobuka.dataaccess.repositories.UserRepository;

@Service
public class ReadCSVFile {
	
	static final String CSV_FILENAME = "C:/home/montekrista/Desktop/samplefile.csv";
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	public void ReadCSV() throws IOException {
		
		try (ICsvBeanReader beanReader = new CsvBeanReader(new FileReader(CSV_FILENAME), CsvPreference.STANDARD_PREFERENCE)) {
			// header elements used to map values to the bean
			final String[] headers = beanReader.getHeader(true);
			// final String[] headers = new String[] {"CustomerId", "CustomerName", "Country", "PinCode", "Email"};
			final CellProcessor[] processors = getProcessors();
			
			UserEntity user;
			while ((user = beanReader.read(UserEntity.class, headers, processors)) != null) {
				// System.out.println(user);
				userRepository.save(user);
				logger.info(user.toString());
			}
		}
	}
	
	// set processors used for examples
	private static CellProcessor[] getProcessors() {
		final String emailRegex = "[a-y0-9\\._]+@[a-z0-9\\._]+";
		StrRegEx.registeredMessage(emailRegex, "must be a valid email address");
		
		final CellProcessor[] processors = new CellProcessor[] {
				
				new NotNull(), // CustomerName
				new StrRegEx(emailRegex); /// Email
		}
		return processors;
	}

}
