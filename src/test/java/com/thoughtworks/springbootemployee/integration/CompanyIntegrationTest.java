package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.Arrays.asList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql(scripts = "classpath:cleanup.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class CompanyIntegrationTest {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    CompanyService companyService;

    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    void tearDown(){
        companyRepository.deleteAll();
    }

    @Test
    void should_return_companies_when_getAll() throws Exception {
        //GIVEN
        List<Employee> employees = asList(new Employee("Christian",20,"male",10000));
        Company company = new Company(1,"OOCL",employees);
//        companyService.create(company);
        companyRepository.save(company);

        //WHEN and THEN
        mockMvc.perform(get("/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].companyId").isNumber())
                .andExpect(jsonPath("$[0].companyName").value("OOCL"))
                .andExpect(jsonPath("$[0].employeeNumber").isNumber())
                .andExpect(jsonPath("$[0].employees").isArray());
    }

    @Test
    void should_create_company_when_create_given_company_request() throws Exception {
        //GIVEN
        String companyJson = "{\n" +
                "    \"companyName\": \"OOCL\",\n" +
                "    \"employeeNumber\": 100,\n" +
                "    \"employees\": [\n" +
                "        {\n" +
                "            \"name\": \"employee1\",\n" +
                "            \"age\": 22,\n" +
                "            \"gender\": \"female\",\n" +
                "            \"salary\": 200\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"employee2\",\n" +
                "            \"age\": 22,\n" +
                "            \"gender\": \"female\",\n" +
                "            \"salary\": 200\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        //WHEN and THEN

        mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(companyJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.companyId").isNumber())
                .andExpect(jsonPath("$.companyName").value("OOCL"))
                .andExpect(jsonPath("$.employeeNumber").isNumber())
                .andExpect(jsonPath("$.employees").isArray());

    }

    @Test
    void should_return_companies_when_getById() throws Exception {
        //GIVEN
        List<Employee> employees = asList(new Employee(1,"Christian",20,"male",10000));
        Company company = new Company(1,"OOCL",employees);
        companyRepository.save(company);

        //WHEN and THEN
        mockMvc.perform(get("/companies/{companyId}",companyRepository.findAll().get(0).getCompanyId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyId").isNumber())
                .andExpect(jsonPath("$.companyName").value("OOCL"))
                .andExpect(jsonPath("$.employeeNumber").isNumber())
                .andExpect(jsonPath("$.employees").isArray());
    }

    @Test
    void should_return_company_when_update() throws Exception {
        //GIVEN
        List<Employee> employees = asList(new Employee(1,"Christian",20,"male",10000));
        Company company = new Company(1,"OOCL",employees);
        companyRepository.save(company);

        String companyJson = "{\n" +
                "    \"companyName\": \"OOCL\",\n" +
                "    \"employeeNumber\": 100,\n" +
                "    \"employees\": [\n" +
                "        {\n" +
                "            \"name\": \"employee1\",\n" +
                "            \"age\": 22,\n" +
                "            \"gender\": \"female\",\n" +
                "            \"salary\": 200\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"employee2\",\n" +
                "            \"age\": 22,\n" +
                "            \"gender\": \"female\",\n" +
                "            \"salary\": 200\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        //WHEN and THEN
        mockMvc.perform(put("/companies/{companyId}",companyRepository.findAll().get(0).getCompanyId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(companyJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyId").isNumber())
                .andExpect(jsonPath("$.companyName").value("OOCL"))
                .andExpect(jsonPath("$.employeeNumber").isNumber())
                .andExpect(jsonPath("$.employees").isArray());
    }

    @Test
    void should_return_company_when_delete() throws Exception {
        //GIVEN
        List<Employee> employees = asList(new Employee(1,"Christian",20,"male",10000));
        Company company = new Company(1,"OOCL",employees);
        companyRepository.save(company);

        //WHEN and THEN
        mockMvc.perform(get("/companies/{companyId}",companyRepository.findAll().get(0).getCompanyId()))
                .andExpect(status().isOk());
    }

    @Test
    void should_return_company_when_getByPage() throws Exception {
        //GIVEN
        List<Employee> employees = asList(new Employee(1,"Christian",20,"male",10000));
        Company company = new Company(1,"OOCL",employees);
        companyRepository.save(company);

        //WHEN and THEN
        mockMvc.perform(get("/companies?page=0&pageSize=3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].companyId").isNumber())
                .andExpect(jsonPath("$[0].companyName").value("OOCL"))
                .andExpect(jsonPath("$[0].employeeNumber").isNumber())
                .andExpect(jsonPath("$[0].employees").isArray());
    }

    @Test
    void should_return_companies_when_getEmployees() throws Exception {
        //GIVEN
        List<Employee> employees = asList(new Employee("Christian",20,"male",10000));
        Company company = new Company(1,"OOCL",employees);
        companyRepository.save(company);
        //WHEN and THEN
        mockMvc.perform(get("/companies/{companyId}/employees",companyRepository.findAll().get(0).getCompanyId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").isString())
                .andExpect(jsonPath("$[0].age").isNumber())
                .andExpect(jsonPath("$[0].gender").isString())
                .andExpect(jsonPath("$[0].salary").isNumber());
    }

}
