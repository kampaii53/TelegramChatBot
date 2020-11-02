package ru.kampaii.bot;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kampaii.bot.configurations.ServicesConfiguration;
import ru.kampaii.gdocs.services.GoogleSheetsService;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServicesConfiguration.class)
@Ignore
public class ConfigurationsTest {

    @Autowired
    private GoogleSheetsService sheetsService;

    @Test
    public void testServicesPresent(){
        assertNotNull(sheetsService);
    }
}
