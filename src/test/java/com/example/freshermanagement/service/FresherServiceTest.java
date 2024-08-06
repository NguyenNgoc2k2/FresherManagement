package com.example.freshermanagement.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.freshermanagement.entity.Fresher;
import com.example.freshermanagement.entity.Language;
import com.example.freshermanagement.exception.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class FresherServiceTest {

    @Autowired
    private FresherService fresherService;

    @Test
    void saveWithUsernameIsNullOrEmpty(){
        Fresher fresher = new Fresher();
        fresher.setFirstname("Hoang");
        fresher.setLastname("Nguyen");
        fresher.setEmail("nguyenhoang@gmail.com");
        fresher.setPhone("0123456778");
        fresher.setDob(convertStringToSqlDate("2002-09-10"));
        fresher.setUsername("");
        fresher.setPassword("@Hoang123");
        fresher.setCode("NH003");
        Language language = new Language();
        language.setId(1L);
        fresher.setLanguage(language);

        ValidationException ex = assertThrows(ValidationException.class,
                () -> fresherService.save(fresher));

        assertEquals("Username cannot be null or empty", ex.getMessage());
    }

    @Test
    void saveWithUsernameExists(){
        Fresher fresher = new Fresher();
        fresher.setFirstname("Hoang");
        fresher.setLastname("Nguyen");
        fresher.setEmail("nguyenhoang@gmail.com");
        fresher.setPhone("0123456778");
        fresher.setDob(convertStringToSqlDate("2002-09-10"));
        fresher.setUsername("");
        fresher.setPassword("@Hoang123");
        fresher.setCode("NH003");
        Language language = new Language();
        language.setId(1L);
        fresher.setLanguage(language);

        UsernameException ex = assertThrows(UsernameException.class,
                () -> fresherService.save(fresher));

        assertEquals("Username is already taken!", ex.getMessage());
    }

    @Test
    void saveWithEmailExists(){
        Fresher fresher = new Fresher();
        fresher.setFirstname("Hoang");
        fresher.setLastname("Nguyen");
        fresher.setEmail("nguyenngoc@gmail.com");
        fresher.setPhone("0123456778");
        fresher.setDob(convertStringToSqlDate("2002-09-10"));
        fresher.setUsername("");
        fresher.setPassword("@Hoang123");
        fresher.setCode("NH003");
        Language language = new Language();
        language.setId(1L);
        fresher.setLanguage(language);

        EmailException ex = assertThrows(EmailException.class,
                () -> fresherService.save(fresher));

        assertEquals("Email is already taken!", ex.getMessage());
    }

    @Test
    void saveWithEmailNotFormat(){
        Fresher fresher = new Fresher();
        fresher.setFirstname("Hoang");
        fresher.setLastname("Nguyen");
        fresher.setEmail("nguyenhoang@gmail.com");
        fresher.setPhone("0123456778");
        fresher.setDob(convertStringToSqlDate("2002-09-10"));
        fresher.setUsername("");
        fresher.setPassword("@Hoang123");
        fresher.setCode("NH003");
        Language language = new Language();
        language.setId(1L);
        fresher.setLanguage(language);

        ValidationException ex = assertThrows(ValidationException.class,
                () -> fresherService.save(fresher));

        assertEquals("Email is not valid.", ex.getMessage());
    }

    @Test
    void saveWithCodeIsNullOrEmpty(){
        Fresher fresher = new Fresher();
        fresher.setFirstname("Hoang");
        fresher.setLastname("Nguyen");
        fresher.setEmail("nguyenhoang@gmail.com");
        fresher.setPhone("0123456778");
        fresher.setDob(convertStringToSqlDate("2002-09-10"));
        fresher.setUsername("");
        fresher.setPassword("@Hoang123");
        fresher.setCode("");
        Language language = new Language();
        language.setId(1L);
        fresher.setLanguage(language);

        ValidationException ex = assertThrows(ValidationException.class,
                () -> fresherService.save(fresher));

        assertEquals("Fresher Code cannot be null or empty", ex.getMessage());
    }

    @Test
    void saveWithPhoneNotFormat(){
        Fresher fresher = new Fresher();
        fresher.setFirstname("Hoang");
        fresher.setLastname("Nguyen");
        fresher.setEmail("nguyenhoang@gmail.com");
        fresher.setPhone("");
        fresher.setDob(convertStringToSqlDate("2002-09-10"));
        fresher.setUsername("");
        fresher.setPassword("@Hoang123");
        fresher.setCode("NH003");
        Language language = new Language();
        language.setId(1L);
        fresher.setLanguage(language);

        ValidationException ex = assertThrows(ValidationException.class,
                () -> fresherService.save(fresher));

        assertEquals("Phone number is not valid.", ex.getMessage());
    }



    @Test
    void saveWithLanguageNotExists(){
        Fresher fresher = new Fresher();
        fresher.setFirstname("Hoang");
        fresher.setLastname("Nguyen");
        fresher.setEmail("nguyenhoang@gmail.com");
        fresher.setPhone("0123456778");
        fresher.setDob(convertStringToSqlDate("2002-09-10"));
        fresher.setUsername("");
        fresher.setPassword("@Hoang123");
        fresher.setCode("NH003");
        Language language = new Language();
        language.setId(10L);
        fresher.setLanguage(language);

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class,
                () -> fresherService.save(fresher));

        assertEquals("Language not found with id: 10", ex.getMessage());
    }


    private java.sql.Date convertStringToSqlDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date utilDate = formatter.parse(dateString);
            return new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            return null;
        }
    }
}
