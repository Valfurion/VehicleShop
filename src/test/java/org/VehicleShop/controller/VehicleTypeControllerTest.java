package org.VehicleShop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.VehicleShop.entity.EngineType;
import org.VehicleShop.repository.VehicleTypeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/*@RunWith(SpringRunner.class)
@WebMvcTest(VehicleTypeController.class)*/
@RunWith(MockitoJUnitRunner.class)
public class VehicleTypeControllerTest {
    @Mock
    private VehicleTypeRepository vehicleTypeRepository;
    @InjectMocks
    private VehicleTypeController target;
    //@Autowired
    private MockMvc mvc;

    /*@MockBean
    private VehicleTypeRepository vehicleTypeRepository;
*/
    private JacksonTester<EngineType> engineTypeJson;

    private String validVehicleTypeJson = "" +
            "{\n" +
            "  \"vehicleTypeId\": \"null\",\n" +
            "  \"vehicleTypeTitle\":\"Водородные\"\n" +
            "}";

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(target).build();
    }

    @Test
    public void vehicleTypeMustBeCreated() throws Exception {
        MvcResult mvcResult = mvc.perform(
                post("/vehicleTypes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validVehicleTypeJson))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void vehicleTypeMustNotBeCreated() throws Exception {
        MvcResult mvcResult = mvc.perform(
                post("/vehicleTypes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

}