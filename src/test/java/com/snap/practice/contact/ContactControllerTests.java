package com.snap.practice.contact;

import com.snap.practice.PracticeApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PracticeApplication.class)
@AutoConfigureMockMvc
public class ContactControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ContactService contactService;

    @Test
    public void shouldAcceptValidData() throws Exception {
        this.mockMvc.perform(put("/contact")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"name\": \"saman\",\n" +
                        "  \"phoneNumber\": \"+909352490969\",\n" +
                        "  \"email\": \"saman.sarem@hotmail.com\",\n" +
                        "  \"organization\": \"snap\",\n" +
                        "  \"github\": \"Sarem\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldAcceptEmptyAllowedFields() throws Exception {
        this.mockMvc.perform(put("/contact")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "  \"name\": \"saman\",\n" +
                        "  \"phoneNumber\": null,\n" +
                        "  \"email\": null,\n" +
                        "  \"organization\": null,\n" +
                        "  \"github\": null\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldRejectNullName() throws Exception {
        this.mockMvc.perform(put("/contact")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "  \"name\": null,\n" +
                        "  \"phoneNumber\": \"+909352490969\",\n" +
                        "  \"email\": \"saman.sarem@hotmail.com\",\n" +
                        "  \"organization\": \"snap\",\n" +
                        "  \"github\": \"Sarem\"\n" +
                        "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldRejectEmptyName() throws Exception {
        this.mockMvc.perform(put("/contact")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "  \"name\": \"\",\n" +
                        "  \"phoneNumber\": \"+909352490969\",\n" +
                        "  \"email\": \"saman.sarem@hotmail.com\",\n" +
                        "  \"organization\": \"snap\",\n" +
                        "  \"github\": \"Sarem\"\n" +
                        "}"))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void shouldRejectInvalidPhoneNumber() throws Exception {
        this.mockMvc.perform(put("/contact")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "  \"name\": \"saman\",\n" +
                        "  \"phoneNumber\": \"909352490969+\",\n" +
                        "  \"email\": \"saman.sarem@hotmail.com\",\n" +
                        "  \"organization\": \"snap\",\n" +
                        "  \"github\": \"Sarem\"\n" +
                        "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldRejectInvalidEmail() throws Exception {
        this.mockMvc.perform(put("/contact")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "  \"name\": \"saman\",\n" +
                        "  \"phoneNumber\": \"+909352490969\",\n" +
                        "  \"email\": \"saman.saremhotmail.com\",\n" +
                        "  \"organization\": \"snap\",\n" +
                        "  \"github\": \"Sarem\"\n" +
                        "}"))
                .andExpect(status().isBadRequest());
    }

}
