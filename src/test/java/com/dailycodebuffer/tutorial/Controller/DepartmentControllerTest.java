package com.dailycodebuffer.tutorial.Controller;

import com.dailycodebuffer.tutorial.Entity.Department;
import com.dailycodebuffer.tutorial.Error.DepartmentNotFoundException;
import com.dailycodebuffer.tutorial.Service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;


    private Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentAddress("Baku")
                .departmentName("IT")
                .departmentCode("IT-03")
                .departmentId(1L).build();
    }

    @Test
    void saveDepartment() {
        Department inputDepartment = Department.builder()
                .departmentAddress("Baku")
                .departmentName("IT")
                .departmentCode("IT-03")
                .build();
        Mockito.when(departmentService.saveDepartment(inputDepartment))
                .thenReturn(department);

        try {
            mockMvc.perform(post("/departments")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                "    \"departmentName\":\"IT\",\n" +
                                "    \"departmentAddress\":\"Baku\",\n" +
                                "    \"departmentCode\":\"IT-03\"\n" +
                                "}"))
                            .andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void fetchDepartmentById() throws Exception {
        Mockito.when(departmentService.fetchDepartmentById(1L))
                .thenReturn(department);

        mockMvc.perform(get("/departments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").
                        value(department.getDepartmentName()));
    }
}